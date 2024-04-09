package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.flexfriend.R;

public class GalleryActivity extends ProgressActivity {
    /* GalleryActivity Class:
     * This class displays all the images captured and saved from the camera into a grid view
     *
     * References used:
     * https://www.youtube.com/watch?v=0YL3pms_0JE
     * https://www.youtube.com/watch?v=Jp6QHc5ip18&t=1028s
     */

    private GridView imgGrid;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //set the adapter holding the image uri's to the grid view
        imgGrid = findViewById(R.id.imgGrid);
        imgGrid.setAdapter(adapter);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            Intent intent = new Intent(this, ProgressActivity.class);
            startActivity(intent);
        }
    }
}