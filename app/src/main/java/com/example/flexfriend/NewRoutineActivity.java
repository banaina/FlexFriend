package com.example.flexfriend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedList;

// this activity lets the user create a new routine
// the add more button will add another cardview to the arraylist of the recyclerview
// create a list that starts off with at least 2 cardviews
public class NewRoutineActivity extends AppCompatActivity{

//    private ArrayList<NewRoutine_RecyclerViewAdapter>;
    String[] movementData = {"",""};
    int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        ArrayList<String> items = new ArrayList<>();
        items.add("");

        RecyclerView recyclerView = findViewById(R.id.createRoutineRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewRoutine_RecyclerViewAdapter adapter = new NewRoutine_RecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addMoreBtn).setOnClickListener(view ->{
            items.add(movementData[counter%2]);
            counter++;
            adapter.notifyItemInserted(items.size()-1);
        });
    }
}
