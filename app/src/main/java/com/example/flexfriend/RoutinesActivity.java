package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// this class shows all the user created routines through a recycler view
// get data from either the flexibility, cardio, or strength button intent

public class RoutinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
    }
}