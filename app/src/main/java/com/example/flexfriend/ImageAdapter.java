package com.example.flexfriend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter implements Serializable {
    /* ImageAdapter Class:
    * This class allows the app to display multiple images within the gallery gridview
    * */

    private final Context mContext;
    private final List<String> imgUris;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
        imgUris = new ArrayList<>(); //an arraylist that holds the image Uris of all captured imgs

        File[] files = mContext.getFilesDir().listFiles();
        //for each file, display them in a grid structure
        //if the file starts with image_, add them to the grid
        if (files !=null){
            for (File file: files){
                if (file.isFile() && file.getName().startsWith("image_")){
                    imgUris.add(file.getName());
                }
            }
        }
    }

    //a method that allows new images to be inserted into the image uri list (and then into the gallery)
    public void addImage(String imgUri){
        imgUris.add(imgUri);
    }

    @Override
    public int getCount() {
        return imgUris.size();
    }

    @Override
    public String getItem(int position) {
        return imgUris.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //this method will return the actual image view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null){
            //if the image is not recycled, initialize attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        //convert the imagefile into a bitmap
        String imgUri = imgUris.get(position);
        try{
            FileInputStream fis = mContext.openFileInput(imgUri);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            imageView.setImageBitmap(bitmap);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return imageView;
    }
}
