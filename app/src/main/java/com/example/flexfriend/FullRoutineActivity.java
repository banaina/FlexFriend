package com.example.flexfriend;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
    ArrayList<String> movementsArrayList;
    private String movementInfo;

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

        //initialize database and helper
        db = new FlexFriendDatabase(this);
        helper = new MyHelper(this);
        //get the data from the intent and create a string holding the name of the current routine
        Intent intent = getIntent();
        String routineName = intent.getStringExtra("ROUTINE NAME");
        routineNameTV.setText(routineName);
        //get movement names
        Cursor cursor = db.getMovementData(routineName);
        // looks at the routine names
        int index1 = cursor.getColumnIndex(Constants.MOVEMENT);
        int index2 = cursor.getColumnIndex(Constants.NUM_OF_REPS);
        int index3 = cursor.getColumnIndex(Constants.NUM_OF_SETS);

        //TODO: add the movement data into the movementsArrayList from the database
        movementsArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String movementNameIndex = cursor.getString(index1);
            String repsORsecondsIndex = cursor.getString(index2);
            String setsIndex = cursor.getString(index3);
            movementInfo = movementNameIndex + "," + repsORsecondsIndex + " REP" +"," + setsIndex + " SET";
            movementsArrayList.add(movementInfo);
            cursor.moveToNext();
        }

        fullRoutineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FullRoutine_RecyclerViewAdapter(this, movementsArrayList);
        fullRoutineRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.playRoutineBtn){
            //go to the activity that plays the routine
            Intent intent = new Intent(this, PlayRoutineActivity.class);
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
            Intent intent = new Intent(this, ProgressActivity.class);
            startActivity(intent);
        }
    }
}
