package com.example.flexfriend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

// this activity lets the user create a new routine
// the add more button will add another cardview to the arraylist of the recyclerview
// create a list that starts off with at least 2 cardviews
public class NewRoutineActivity extends AppCompatActivity{

//    private ArrayList<NewRoutine_RecyclerViewAdapter>;
    String[] movementData = {"false","bulgarian","3","8"}; // add the movement editText info that was in the card
    int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        // this sets up the recycler view that was created in the NewRoutine_RecyclerViewAdapter.java
        // which allows you to add and remove cardviews of the created movements
        ArrayList<String> movementCard = new ArrayList<>();
        movementCard.add(Arrays.toString(movementData)); // add the movement editText info that was in the card

        RecyclerView recyclerView = findViewById(R.id.createRoutineRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewRoutine_RecyclerViewAdapter adapter = new NewRoutine_RecyclerViewAdapter(movementCard);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addMoreBtn).setOnClickListener(view ->{
            movementCard.add(Arrays.toString(movementData));
            counter++;
           // movementCard.add(Arrays.toString(movementData)); // add the movement editText info that was in the card
            Log.d("movementData", "movementDataArray: " + Arrays.toString(movementData)
            + " movementCardArray: " + movementCard);
            adapter.notifyItemInserted(movementCard.size()-1);
        });
    }
}
