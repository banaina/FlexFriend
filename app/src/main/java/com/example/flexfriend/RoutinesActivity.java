package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// this class shows all the user created routines through a recycler view
// get data from either the flexibility, cardio, or strength button intent to access the database

public class RoutinesActivity extends AppCompatActivity implements View.OnClickListener {
    TextView routineCategoryTV;
    FlexFriendDatabase db;
    Routines_RecyclerViewAdapter routinesAdapter;
    RecyclerView routinesRecycler;
    private LinearLayoutManager mLayoutManager;
    MyHelper helper;
    private ArrayList<String> routinesArrayList;
    Button routinesBtn, newRoutineBtn, progressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        routineCategoryTV = findViewById(R.id.routineCategoryTV);
        routinesRecycler = findViewById(R.id.routinesRecyclerView);

        //bottom of the page buttons
        routinesBtn = (Button) findViewById(R.id.routinesBtn);
        newRoutineBtn = (Button) findViewById(R.id.newRoutineBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);

        routinesBtn.setOnClickListener(this);
        newRoutineBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String categoryName = intent.getStringExtra("category");
        // display the string into textView
        routineCategoryTV.setText(categoryName);

        db = new FlexFriendDatabase(this);
        helper = new MyHelper(this);

        Cursor cursor = db.getRoutineData(categoryName);

        // looks at the category column
        int index = cursor.getColumnIndex(Constants.ROUTINE_NAME);

        routinesArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String routineNameIndex = cursor.getString(index);
            // if category & routine name is already in arraylist, do not add to arraylist
            if (!routinesArrayList.contains(routineNameIndex)){
                routinesArrayList.add(routineNameIndex);
            }

            cursor.moveToNext();
        }
        Log.d("routinesArray", String.valueOf(routinesArrayList));

        routinesAdapter = new Routines_RecyclerViewAdapter(this, routinesArrayList);
        routinesRecycler.setAdapter(routinesAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        routinesRecycler.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onClick(View v) {
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