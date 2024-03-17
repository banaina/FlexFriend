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

    private ArrayList<NewRoutineDataModel> data;
    NewRoutineDataModel newRoutineDataModel;
    //String[] movementData;
    String[] movementData = {"movement","sets","reps", "isTimed"}; // add the movement editText info that was in the card
    int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        // generate the ArrayList and store in NewRoutineDataModel
        for (int i = 0; i < movementData.length; i++) {
            newRoutineDataModel = new NewRoutineDataModel(i,null, 0, 0, false);
            data.add(newRoutineDataModel);

        }

        // this sets up the recycler view that was created in the NewRoutine_RecyclerViewAdapter.java
        // which allows you to add and remove cardviews of the created movements
        ArrayList<NewRoutineDataModel> movementCards = new ArrayList<>();
        movementCards.add(newRoutineDataModel); // add the movement editText info that was in the card

        RecyclerView recyclerView = findViewById(R.id.createRoutineRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewRoutine_RecyclerViewAdapter adapter = new NewRoutine_RecyclerViewAdapter(movementCards);
        recyclerView.setAdapter(adapter);
        
        data = new ArrayList<>();



        findViewById(R.id.addMoreBtn).setOnClickListener(view ->{
            movementCards.add(newRoutineDataModel); // add the movement editText info that was in the card
            counter++;
           // movementCard.add(Arrays.toString(movementData)); // add the movement editText info that was in the card
            Log.d("movementData", "movementDataArray: " + Arrays.toString(movementData)
            + " movementCardArray size: " + movementCards.size());
            adapter.notifyItemInserted(movementCards.size()-1);
        });
    }
}
