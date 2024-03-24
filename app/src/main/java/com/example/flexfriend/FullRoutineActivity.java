package com.example.flexfriend;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// this activity shows the full workout of a category ex(leg day - hip thrust, squats, etc.
public class FullRoutineActivity extends AppCompatActivity implements View.OnClickListener {
    TextView routineNameTV;
    RecyclerView fullRoutineRecyclerView;
    FullRoutine_RecyclerViewAdapter adapter;
    MyHelper helper;
    Button playRoutineBtn, routinesBtn, newRoutineBtn, progressBtn;
    FlexFriendDatabase db;
    ArrayList<String[]> movementsArrayList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_routine_descrip);
        //Textviews
        routineNameTV = findViewById(R.id.routineNameTV);
        fullRoutineRecyclerView = findViewById(R.id.fullRoutineRecyclerView);

        //buttons
        playRoutineBtn = findViewById(R.id.playRoutineBtn);
        routinesBtn = findViewById(R.id.routinesBtn);
        newRoutineBtn = findViewById(R.id.newRoutineBtn);
        progressBtn = findViewById(R.id.progressBtn);

        //register the buttons
        playRoutineBtn.setOnClickListener(this);
        routinesBtn.setOnClickListener(this);
        newRoutineBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);

        db = new FlexFriendDatabase(this);
        helper = new MyHelper(this);

        Intent intent = getIntent();
        String routineName = intent.getStringExtra("ROUTINE NAME");
        routineNameTV.setText(routineName);

        movementsArrayList = new ArrayList<>();
        //TODO: add the movement data into the movementsArrayList from the database
        fullRoutineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FullRoutine_RecyclerViewAdapter(this, movementsArrayList);
        fullRoutineRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.playRoutineBtn){
            //go to the activity that plays the routine
            Toast.makeText(this, "clicked play button", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(this, ProgressActivity.class);
            startActivity(intent);
        }
    }
}
