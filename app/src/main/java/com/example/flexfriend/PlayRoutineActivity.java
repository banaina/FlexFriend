package com.example.flexfriend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayRoutineActivity extends AppCompatActivity{
    /* PlayRoutineActivityClass:
     * A class which controls the behavior of how each routine is executed,
     * each routine starts with a 10 second startup buffer to setup before the routine.
     * After the timer is up, the movements are displayed one by one.
     *
     * Uses fragments to showcase each movement screen
     *
     * Reference: https://www.geeksforgeeks.org/how-to-implement-circular-progressbar-in-android/
     * Referece: https://www.geeksforgeeks.org/how-to-create-a-stopwatch-app-using-android-studio/
     */

    private ProgressBar progressBar;
    private TextView progressText;
    private int i = 0;
    //signals whether the stopwatch is running or not
    private boolean running;

    private boolean wasRunning;
    private ArrayList<String> movementsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_routine);

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);

        Intent intent = getIntent();
        movementsArrayList = intent.getStringArrayListExtra("movement arraylist");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i < 11) {
                    progressText.setText("" + i);
                    progressBar.setProgress(i*10);
                    i++;
                    handler.postDelayed(this, 1000);

                } else {
                    handler.removeCallbacks(this);
                }

                if (i==10) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(PlayRoutineActivity.this, MovementScreenActivity.class);
                                intent.putExtra("movements arraylist", movementsArrayList);
                                startActivity(intent);
                            }
                        });
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000);


    }

}