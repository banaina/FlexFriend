package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryItemActivity extends AppCompatActivity {
    /* GalleryItemActivity Class:
     * A class that will be used during future changes made to the gallery grid.
     * Will allow user to expand the image from the gallery when clicked on and view it in its own
     * activity.
     */
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_item);

        img = findViewById(R.id.galleryItemView);
        Intent intent = getIntent();
        String s = intent.getStringExtra("imageuri");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
//        img.setImageResource(intent.getIntExtra("imageuri", 0));
    }
}