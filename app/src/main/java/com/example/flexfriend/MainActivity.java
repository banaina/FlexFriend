package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button camera;
    private Button intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = findViewById(R.id.camActivity);
        camera.setOnClickListener(this);
        intro = findViewById(R.id.introActivity);
        intro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.camActivity){
            Intent intent = new Intent(this, ProgressActivity.class);
            Toast.makeText(this, "heading to progress viewing activity",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if (v.getId() == R.id.introActivity){
            Intent intent = new Intent(this, RoutinesActivity.class);
            Toast.makeText(this, "heading to routine creation activity",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
}