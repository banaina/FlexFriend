package com.example.flexfriend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

// this activity lets the user create a new routine
// the add more button will add another cardview to the arraylist of the recyclerview
// create a list that starts off with at least 2 cardviews
public class NewRoutineActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    NewRoutine_RecyclerViewAdapter adapter;
    private Button create;
    private ArrayList<String> movementCards;
//    private ArrayList<NewRoutine_RecyclerViewAdapter>;
    private String[] movementData = {"","","",""}; // add the movement editText info that was in the card
    private String s;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);
        create = findViewById(R.id.createRoutineBtn);
        create.setOnClickListener(this);

        // this sets up the recycler view that was created in the NewRoutine_RecyclerViewAdapter.java
        // which allows you to add and remove cardviews of the created movements
        movementCards= new ArrayList<>();
        movementCards.add(Arrays.toString(movementData)); // add the movement editText info that was in the card

        recyclerView = findViewById(R.id.createRoutineRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewRoutine_RecyclerViewAdapter(movementCards);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addMoreBtn).setOnClickListener(view ->{
            movementCards.add(Arrays.toString(movementData));
            counter++;
           // movementCard.add(Arrays.toString(movementData)); // add the movement editText info that was in the card
            Log.d("movementData", "movementDataArray: " + Arrays.toString(movementData)
            + " movementCardArray: " + movementCards);
            adapter.notifyItemInserted(movementCards.size()-1);
        });
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.createRoutineBtn) {
            for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                MovementVH vh = (MovementVH) recyclerView.findViewHolderForLayoutPosition(i);
                if (vh != null) {
                   movementCards.set(i, vh.getCardInfo());
                }
            }

            //below code is just for debugging, can delete later
            String s = String.valueOf(movementCards.size());
            //must add at least 3 cards before pressing create or else itll crash before
            //displaying the toast
            String d = movementCards.get(0);
            String f = movementCards.get(1);
            String t = movementCards.get(2);
            String g = String.valueOf(recyclerView.getAdapter().getItemCount());
            Toast.makeText(this, s+ g + d + f + t, Toast.LENGTH_SHORT).show();
        }
    }

}
