package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.flexfriend.R;

public class GalleryActivity extends ProgressActivity {
    /* GalleryActivity Class:
     * This class displays all the images captured and saved from the camera into a grid view
     */

    private GridView imgGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //set the adapter holding the image uri's to the grid view
        imgGrid = findViewById(R.id.imgGrid);
        imgGrid.setAdapter(adapter);

        //Code below is for future implementation and improving gallery activity
//        imgGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String imageUri = adapter.getItem(position);
//                Intent intent = new Intent(getApplicationContext(), GalleryItemActivity.class);
//                intent.putExtra("imageuri", imageUri);
////                Toast.makeText(GalleryActivity.this, imageUri, Toast.LENGTH_LONG).show();
//                startActivity(intent);
//            }
//        });
    }
}