package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button camera;
    private Button flexibilityBtn, cardioBtn, strengthBtn; // routine category buttons
    private Button routinesBtn, newRoutineBtn, progressBtn; // bottom page buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        camera = findViewById(R.id.camActivity); IDK WERE TO PUT THE CAMERA BUTTON OR IF WE'RE USING IT
//        camera.setOnClickListener(this);

        // routine category selection ids
        flexibilityBtn = (Button) findViewById(R.id.flexibilityBtn);
        cardioBtn = (Button) findViewById(R.id.cardioBtn);
        strengthBtn = (Button) findViewById(R.id.strengthBtn);

        //bottom of the page buttons
        routinesBtn = (Button) findViewById(R.id.routinesBtn);
        newRoutineBtn = (Button) findViewById(R.id.newRoutineBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);

        // setting onclick listeners for all the buttons in the activity
        flexibilityBtn.setOnClickListener(this);
        cardioBtn.setOnClickListener(this);
        strengthBtn.setOnClickListener(this);

        routinesBtn.setOnClickListener(this);
        newRoutineBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.camActivity){
//            Intent intent = new Intent(this, ProgressActivity.class);
//            Toast.makeText(this, "heading to progress viewing activity",Toast.LENGTH_SHORT).show();
//            startActivity(intent);
//        }
//        if (v.getId() == R.id.introActivity){
//            Intent intent = new Intent(this, RoutinesActivity.class);
//            Toast.makeText(this, "heading to routine creation activity",Toast.LENGTH_SHORT).show();
//            startActivity(intent);
//        }

        if (v.getId() == R.id.flexibilityBtn) {
            // access all the created routines of the flexibility category from the database
            // then send all the info into a recycler view
            Intent intent = new Intent(this, RoutinesActivity.class);
            Toast.makeText(this, "flexibility category clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        if (v.getId() == R.id.cardioBtn) {
            // access all the created routines of the cardio category from the database
            // then send all the info into a recycler view
            Toast.makeText(this, "cardio category clicked",Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.strengthBtn) {
            // access all the created routines of the strength category from the database
            // then send all the info into a recycler view
            Toast.makeText(this, "strength category clicked",Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.routinesBtn) {
            // go to the routines page that lets the user choose which category of routines
            Toast.makeText(this, "routines category clicked",Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.newRoutineBtn) {
            //go to the create new routine activity page
            Toast.makeText(this, "create new routine clicked",Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.progressBtn) {
            //go to the progress page activity
            Toast.makeText(this, "progress Button clicked",Toast.LENGTH_SHORT).show();
        }
    }
}