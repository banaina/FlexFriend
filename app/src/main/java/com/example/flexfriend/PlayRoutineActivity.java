package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayRoutineActivity extends AppCompatActivity {
    /* PlayRoutineActivityClass:
     * A class which controls the behavior of how each routine is executed,
     * based on the information stored in the data base for each movement.
     *
     * Reference: https://www.geeksforgeeks.org/how-to-implement-circular-progressbar-in-android/
     */

    private ProgressBar progressBar;
    private TextView progressText;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_routine);

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressText.setText("" + i);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);

    }
}