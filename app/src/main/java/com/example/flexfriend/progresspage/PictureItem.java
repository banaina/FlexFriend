package com.example.flexfriend.progresspage;

import androidx.camera.core.ImageCapture;

public class PictureItem {
    /*Data Item Class:
     * This class acts as a data transfer object which holds the information of each instance of
     * image taken from the camera and stored in the database.
     * */
    private ImageCapture img;
    private String name;

    public PictureItem() {
    }
    public PictureItem(ImageCapture img, String name) {
        this.img = img;
        this.name = name;
    }
    public ImageCapture getImg() {
        return img;
    }

    public void setImg(ImageCapture img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
