package com.example.flexfriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FlexFriendDatabase {
    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    public FlexFriendDatabase(Context c){
        context = c;
        helper = new MyHelper(context);
    }

    public long insertData(String category, String routineName, String movement,
                           String numOfSets, String numOfReps, String timed, String time)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.CATEGORY, category);
        contentValues.put(Constants.ROUTINE_NAME, routineName);
        contentValues.put(Constants.MOVEMENT, movement);
        contentValues.put(Constants.NUM_OF_SETS, numOfSets);
        contentValues.put(Constants.NUM_OF_REPS, numOfReps);
        contentValues.put(Constants.TIMED, timed);
        contentValues.put(Constants.TIME, time);

        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.CATEGORY, Constants.ROUTINE_NAME,
                Constants.MOVEMENT, Constants.NUM_OF_SETS, Constants.NUM_OF_REPS, Constants.TIMED,
                Constants.TIME};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null,
                null, null);
        return cursor;

    }




}
