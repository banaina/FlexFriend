package com.example.flexfriend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.FileOutputStream;
import java.util.concurrent.ExecutionException;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    /* ProgressActivity Class:
     * A class that allows the user to capture an image, save it to SharedPreferences, and then
     * add the picture to the ImageAdapter
     */
    private static final int REQUEST_CAMERA = 1;
    private Button cameraButton, galleryButton, routinesBtn, newRoutineBtn, progressBtn, logBtn;
    private ImageCapture imageCapture;
    private Sensor accelerometer;
    private SensorManager mySensorManager;
    protected ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(this);
        galleryButton = findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(this);
        logBtn = findViewById(R.id.logButton);
        logBtn.setOnClickListener(this);

        //bottom of the page buttons
        routinesBtn = (Button) findViewById(R.id.routinesBtn);
        newRoutineBtn = (Button) findViewById(R.id.newRoutineBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);
        routinesBtn.setOnClickListener(this);
        newRoutineBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);

        mySensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        adapter = new ImageAdapter(this);
    }
    protected void onResume() {
        super.onResume();
        //register resources late, register sensors late
        mySensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //release resources early, unregister sensors early
        mySensorManager.unregisterListener(this);
    }

    //get an instance of ProcessCameraProvider
    private void startCamera() {
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture
                = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);

                } catch (ExecutionException | InterruptedException e) {
                    // This should never be reached.
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    //selecting a camera and binding to the lifecycle
    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        cameraProvider.unbindAll();

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();


        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    @Override
    public void onClick(View v) {
        //button which opens the camera, this is an option if the device does not have an accelerometer
        //which means the shaking motion does not open the camera
        if (v.getId() == R.id.cameraButton){
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, REQUEST_CAMERA);
        }
        if (v.getId() == R.id.galleryButton){
            //send to gallery
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.logButton) {
            //send to log page
            Intent intent = new Intent(this, LogProgressActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.routinesBtn) {
            // go to the routines page that lets the user choose which category of routines
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.newRoutineBtn) {
            //go to the create new routine activity page
            Intent intent = new Intent(this, NewRoutineActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.progressBtn) {
            //go to the progress page activity where user can take and store progress photos
            Toast.makeText(this, "currently on the progress page",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //when the accelerometer value surpasses the threshold, it signals that there is movement
        //shaking movement from the phone causes the camera to open
        float[] values = event.values;
        if (values[0] > 15 || values[1] > 15 || values[2] > 15) {
            Toast.makeText(this, "Phone shaken, sending to camera activity", Toast.LENGTH_SHORT).show();
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, REQUEST_CAMERA);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the result has the right request code and the result is valid, execute code
        if (requestCode == REQUEST_CAMERA){
            //get the image and convert it into a bitmap
            Bundle extras = data.getExtras();
            Bitmap imgBM =  (Bitmap) extras.get("data");
            String imageUri = saveImageToPreferences(imgBM);

            adapter.addImage(imageUri);
            adapter.notifyDataSetChanged();
        }
    }

    private String saveImageToPreferences(Bitmap img){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        int nextImageIndex = sharedPref.getInt("nextImageIndex", 0);
        //identify the file name/path
        String imageUri = "image_" + nextImageIndex + ".png";
        try {
            //pass the image Uri through the open file output
            FileOutputStream fos = openFileOutput(imageUri, Context.MODE_PRIVATE);
            //compress the bitmap before saving it
            img.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //finally save it to shared preferences using the editor
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("nextImageIndex", nextImageIndex +1);
        editor.apply();
        //return the file path name
        return imageUri;

    }
}