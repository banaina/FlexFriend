package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView flexibilityBtn, cardioBtn, strengthBtn; // routine category buttons
    private ImageButton routinesBtn, newRoutineBtn, progressBtn; // bottom page buttons
    FlexFriendDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // routine category selection ids
        flexibilityBtn = (TextView) findViewById(R.id.flexibilityBtn);
        cardioBtn = (TextView) findViewById(R.id.cardioBtn);
        strengthBtn = (TextView) findViewById(R.id.strengthBtn);

        //bottom of the page buttons
        routinesBtn = (ImageButton) findViewById(R.id.routinesBtn);
        newRoutineBtn = (ImageButton) findViewById(R.id.newRoutineBtn);
        progressBtn = (ImageButton) findViewById(R.id.progressBtn);

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
        if (v.getId() == R.id.flexibilityBtn) {
            // access all the created routines of the flexibility category from the database
            // then send all the info into a recycler view
            Intent intent = new Intent(this, RoutinesActivity.class);
            intent.putExtra("category", "flexibility"); // use the value as a key to the database
            startActivity(intent);
        }

        if (v.getId() == R.id.cardioBtn) {
            // access all the created routines of the cardio category from the database
            // then send all the info into a recycler view
            Intent intent = new Intent(this, RoutinesActivity.class);
            intent.putExtra("category", "cardio"); // use the value as a key to the database
            startActivity(intent);
        }

        if (v.getId() == R.id.strengthBtn) {
            // access all the created routines of the strength category from the database
            // then send all the info into a recycler view
            Intent intent = new Intent(this, RoutinesActivity.class);
            intent.putExtra("category", "strength"); // use the value as a key to the database and also change to title name
            startActivity(intent);
        }

        if (v.getId() == R.id.routinesBtn) {
            // go to the routines page that lets the user choose which category of routines
            Toast.makeText(this, "currently on the routines page",Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.newRoutineBtn) {
            //go to the create new routine activity page
            Intent intent = new Intent(this, NewRoutineActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.progressBtn) {
            //go to the progress page activity where user can take and store progress photos
            Intent intent = new Intent(this, ProgressActivity.class);
            startActivity(intent);
        }
    }
}