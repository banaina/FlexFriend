package com.example.flexfriend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    private static final int img_id = 1;
    private Button cameraButton, galleryButton;
    private ImageCapture imageCapture;
    private Sensor accelerometer;
    private SensorManager mySensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(this);
        galleryButton = findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(this);

        mySensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
//        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    @Override
    public void onClick(View v) {
        //button which opens the camera, this is an option if the device does not have an accelerometer
        //which means the shaking motion does not open the camera
        if (v.getId() == R.id.cameraButton){
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, img_id);
        }
        if (v.getId() == R.id.galleryButton){
            //send to gallery
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
            startActivityForResult(camera_intent, img_id);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}