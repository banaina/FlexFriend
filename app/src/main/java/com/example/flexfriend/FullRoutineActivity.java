package com.example.flexfriend;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

// this activity shows the full workout of a category ex(leg day - hip thrust, squats, etc.
public class FullRoutineActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView routineNameTV;
    private RecyclerView fullRoutineRecyclerView;
    private FullRoutine_RecyclerViewAdapter adapter;
    private MyHelper helper;
    private Button routinesBtn, newRoutineBtn, progressBtn;
    private ImageButton playRoutineBtn, backBtn;
    private FlexFriendDatabase db;
    private ArrayList<String> movementsArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_routine_descrip);
        //Textviews
        routineNameTV = findViewById(R.id.routineNameTV);
        fullRoutineRecyclerView = findViewById(R.id.fullRoutineRecyclerView);

        //buttons
        playRoutineBtn = (ImageButton) findViewById(R.id.playRoutineBtn);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        routinesBtn = (Button) findViewById(R.id.routinesBtn);
        newRoutineBtn = (Button) findViewById(R.id.newRoutineBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);

        //register the buttons
        playRoutineBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
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
        int index1 = cursor.getColumnIndex(Constants.TIMED);
        int index2 = cursor.getColumnIndex(Constants.MOVEMENT);
        int index3 = cursor.getColumnIndex(Constants.NUM_OF_REPS);
        int index4 = cursor.getColumnIndex(Constants.TIME);
        int index5 = cursor.getColumnIndex(Constants.NUM_OF_SETS);

        //add each movement to the arrayList so it can be displayed in the recyclerview using the adapter
        movementsArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String timedIndex = cursor.getString(index1);
            String movementNameIndex = cursor.getString(index2);
            String repIndex = cursor.getString(index3);
            String secIndex = cursor.getString(index4);
            String setsIndex = cursor.getString(index5);

            //list each movement and their reps according to whether it is a timed movement or not
            String movementInfo;
            if (Objects.equals(timedIndex, "true")) {
                movementInfo = movementNameIndex + "," + secIndex + " SEC" + "," + setsIndex + " SET";
            } else{
                movementInfo = movementNameIndex + "," + repIndex + " REP" + "," + setsIndex + " SET";
            }
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
            //TODO: change to playroutine activity after debugging
            Intent intent = new Intent(this, MovementScreenActivity.class);
            intent.putExtra("movements arraylist", movementsArrayList);
            startActivity(intent);
        }
        if (v.getId() == R.id.backBtn) {
            // return to the routines page that lets the user choose which category of routines
            Intent intent = new Intent(this, MainActivity.class);
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
