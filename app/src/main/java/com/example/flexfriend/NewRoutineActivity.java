package com.example.flexfriend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
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
    private ImageButton addMoreBtn;
    private EditText routineNameET;
    private RadioGroup categoriesRG;
    private ArrayList<String[]> movementCards;
    private String[] movementData = {"","","",""}; // add the movement editText info that was in the card
    private String category = "";
    private int counter = 0;
    FlexFriendDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);
        create = (Button) findViewById(R.id.createRoutineBtn);
        create.setOnClickListener(this);
        addMoreBtn = (ImageButton) findViewById(R.id.addMoreBtn);
        addMoreBtn.setOnClickListener(this);
        routineNameET = (EditText)findViewById(R.id.routineNameET);

        categoriesRG = (RadioGroup)findViewById(R.id.categoryRadioGroup);
        categoriesRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.FlexibilityRadioButton) {
                    category = "flexibility";
                }
                if (checkedId == R.id.CardioRadioButton) {
                    category = "cardio";
                }
                if (checkedId == R.id.StrengthRadioButton) {
                    category = "strength";
                }
            }
        });

        db = new FlexFriendDatabase(this);

        // this sets up the recycler view that was created in the NewRoutine_RecyclerViewAdapter.java
        // which allows you to add and remove cardviews of the created movements
        movementCards= new ArrayList<>();
//        movementCards.add(movementData); // add an empty movement card to the recyclerview

        recyclerView = findViewById(R.id.createRoutineRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewRoutine_RecyclerViewAdapter(movementCards);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.createRoutineBtn) {
            //make sure routine has a name and user has chosen a category before adding to FlexFriendDatabase
            String routineName = routineNameET.getText().toString(); // get the routine name and the radiogroup string value
            if(routineName.equals("")){
                Toast.makeText(this, "Enter a name for the routine", Toast.LENGTH_SHORT).show();
            }
            if (Objects.equals(category, "")) {
                Toast.makeText(this, "Pick a category", Toast.LENGTH_SHORT).show();
            }
            else{
                for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                    MovementVH vh = (MovementVH) recyclerView.findViewHolderForLayoutPosition(i);
                    if (vh != null) {
                        movementCards.set(i, vh.getCardInfo());
                    }
                } // end of for loop

                // add to the flexfriend database
                for (int i = 0; i < movementCards.size(); i++) { // [[,,,],[,,,]]
                    Log.d("movementCardInfo","movementCardsArray " + i + ": " + Arrays.toString(movementCards.get(i)));
                    for (int j = 0; j < 1; j++) { // less than 1 b/c the time index is in the first position of the array  [,,,]
                        Toast.makeText(this, movementCards.get(i)[j], Toast.LENGTH_SHORT).show();

                        // if the time is true, make reps = 0, but if time is false make time = 0
                        if (Objects.equals(movementCards.get(i)[j], "true")) { //time is true
                                    db.insertData(category, routineName, "true", movementCards.get(i)[j+1],  movementCards.get(i)[j+2],
                                            "0", movementCards.get(i)[j+3]);
                        } //end of if (Objects.equals(movementCards.get(i)[j], "true"))

                        else{// time is false
                                    //category, routineName, isTimed, movementName, sets, reps, time
                                    db.insertData(category, routineName, "false", movementCards.get(i)[j + 1], movementCards.get(i)[j + 2],
                                            movementCards.get(i)[j + 3], "0");
                                    Log.d("food", movementCards.get(i)[j] + movementCards.get(i)[j+1]+ movementCards.get(i)[j+2]+
                                            "0"+ movementCards.get(i)[j+3]);
                        } //end of else
                    }// end of for loop j
                }// end of for loop i

                movementCards.clear(); // clear the movementCards arraylist
                // go to the main activity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }


        } // end of if (v.getId() == R.id.createRoutineBtn)


        if (v.getId() == R.id.addMoreBtn) {
            String[] move = {"", "", "", ""};


            movementCards.add(move); // this just adds an empty movement card to the recycler view
            counter++;
            Log.d("counter", String.valueOf(counter));
            Log.d("movementcards", String.valueOf(movementCards));
            Log.d("movementData"," movementCardsArray: " + movementCards);
            adapter.notifyItemInserted(movementCards.size()-1);



        } // end of if (v.getId() == R.id.addMoreBtn)
    } //end of onClick

}

