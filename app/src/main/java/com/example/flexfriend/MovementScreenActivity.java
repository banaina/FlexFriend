package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovementScreenActivity extends AppCompatActivity implements View.OnClickListener {
    /* MovementScreenActivity Class:
     * As soon as the startup timer runs out from the PlayRoutineActivity screen, this
     * class will help display each movement as a fragment containing the information from the
     * database about the routine details.
     */

    private ImageButton leftButton, rightButton;
    private ArrayList<String> movementsArrayList;
    private FragmentManager fragManager;
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_screen);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);

        Intent intent = getIntent();
        movementsArrayList = new ArrayList<String>();
        movementsArrayList = intent.getStringArrayListExtra("movements arraylist");
        sendInfo(position);
    }

    @Override
    public void onClick(View v) {
        //when right button is clicked, the next movement is displayed within a fragment

        // when the right button is clicked, it gets the position of the item in the list
        // (starting with the first one) and passes its values into the fragment.
        // When it is clicked again, it gets the current item position increases in increments of one

        if (v.getId() == R.id.rightButton){
            if (movementsArrayList !=null) {
                position += 1;
                if (position < movementsArrayList.size()) {
                    sendInfo(position);
                } else {
                    //TODO: set this to the workout completed activity after done debugging
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }
        if (v.getId() == R.id.leftButton){
            if (movementsArrayList !=null && position>0) {
                position -= 1;
                if (position < movementsArrayList.size()) {
                    sendInfo(position);
                }
            }
        }
    }

    public void sendInfo(int pos){
        String info = movementsArrayList.get(pos);
        String[] infoItems = info.split(",");

        if (pos == 0) leftButton.setVisibility(View.GONE);
        else leftButton.setVisibility(View.VISIBLE);


        PlayRoutineFragment frg = new PlayRoutineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("movement name", infoItems[0]);
        bundle.putString("reps or sec", infoItems[1]);
        bundle.putString("sets", infoItems[2]);
        frg.setArguments(bundle);
        //send this info to the playfragment class so it can be displayed when button is pressed

        //fragment manager helps manage multiple fragments within an activity
        fragManager = getSupportFragmentManager();//retrieves whichever frag manager is available within app
        fragManager.beginTransaction()//transact from one fragment to another
                .replace(R.id.fragContainer, PlayRoutineFragment.class, bundle)//replace the current fragment with another
                .setReorderingAllowed(true).addToBackStack("name").commit();

//        if (infoItems[1].contains("SEC")) {
//            repsOrsecs = Integer.parseInt(infoItems[1].replace(" SEC", ""));
//        } else if (infoItems[1].contains("REP")) {
//            repsOrsecs = Integer.parseInt(infoItems[1].replace(" REP", ""));
//        }
//        timed = infoItems[1].contains("SEC");
    }

}