package com.example.flexfriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class FlexFriendDatabase {
    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    public FlexFriendDatabase(Context c){
        context = c;
        helper = new MyHelper(context);
    }

    public void insertData(String category, String routineName, String timed, String movement,
                           String numOfSets, String numOfReps, String time)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.CATEGORY, category);
        contentValues.put(Constants.ROUTINE_NAME, routineName);
        contentValues.put(Constants.TIMED, timed);
        contentValues.put(Constants.MOVEMENT, movement);
        contentValues.put(Constants.NUM_OF_SETS, numOfSets);
        contentValues.put(Constants.NUM_OF_REPS, numOfReps);
        contentValues.put(Constants.TIME, time);

        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        if (id < 0)
        {
            Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        }
       // return id;
    }

    public Cursor getData(){

        // this does a query search of all the data in each column
        // if smt is null it means that it will return all the data

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.CATEGORY, Constants.ROUTINE_NAME,
                Constants.MOVEMENT, Constants.NUM_OF_SETS, Constants.NUM_OF_REPS, Constants.TIMED,
                Constants.TIME};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null,
                null, null);

        return cursor;

    }

    public Cursor getRoutineData(){

        // this does a query search of all the data in the column of category and routine name
        // groups the data by category


        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.CATEGORY, Constants.ROUTINE_NAME};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns,null, null, Constants.CATEGORY, null, null);

        return cursor;
    }




}
