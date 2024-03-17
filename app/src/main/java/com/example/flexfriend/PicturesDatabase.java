package com.example.flexfriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;
import androidx.camera.core.ImageCapture;

public class PicturesDatabase extends SQLiteOpenHelper {
    /* A SQlite database holding the images taken and stored from the camera on the progress page
     */
    public PicturesDatabase(@Nullable Context context) {
        super(context, "gallery.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tableimage (name text, image blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tableimage");
    }

    public boolean insertData(String name, ImageCapture img){
        SQLiteDatabase galleryDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("images", String.valueOf(img));
        long insert = galleryDatabase.insert("tableimage", null, contentValues);
        //return false if insert = -1, and true if insert != -1
        return insert != -1;
    }

    public String getName(String name){
        SQLiteDatabase galleryDatabase = this.getWritableDatabase();
        Cursor cursor = galleryDatabase.rawQuery("Select * from tableimage where name = ?", new String[]{name});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Bitmap getImage(String name){
        SQLiteDatabase galleryDatabase = this.getWritableDatabase();
        Cursor cursor = galleryDatabase.rawQuery("Select * from tableimage where name = ?", new String[]{name});
        cursor.moveToFirst();
        byte[] bitmap = cursor.getBlob(1);
        Bitmap img = BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
        return img;
    }
}
