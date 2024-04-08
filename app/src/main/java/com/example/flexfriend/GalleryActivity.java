package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.flexfriend.R;

public class GalleryActivity extends AppCompatActivity{
    /* GalleryActivity Class:
     * This class displays all the images captured and saved from the camera into a grid view
     *
     * References used:
     * https://www.youtube.com/watch?v=0YL3pms_0JE
     * https://www.youtube.com/watch?v=Jp6QHc5ip18&t=1028s
     */

    private GridView imgGrid;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        //set the adapter holding the image uri's to the grid view
        imgGrid = findViewById(R.id.imgGrid);
        adapter = (ImageAdapter) getIntent().getSerializableExtra("adapter");
        imgGrid.setAdapter(adapter);

//        Code below is for future implementation and improving gallery activity
        imgGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imageUri = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), GalleryItemActivity.class);
                intent.putExtra("imageuri", imageUri);
//                Toast.makeText(GalleryActivity.this, imageUri, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}