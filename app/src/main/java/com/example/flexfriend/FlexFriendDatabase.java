package com.example.flexfriend;

import static com.example.flexfriend.Constants.TABLE_NAME;

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

        long id = db.insert(TABLE_NAME, null, contentValues);
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

    public Cursor getRoutineData(String categoryName){

        // this does a query search of the category i.e. flexibility, cardio, strength

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.CATEGORY, Constants.ROUTINE_NAME};
        Cursor cursor = db.query(TABLE_NAME, columns, Constants.CATEGORY + "='" +categoryName+ "'", null, null,
                null, null);

        return cursor;

    }

    // below is the method for deleting our course.
    public void deleteRoutine(String routineName) {

        SQLiteDatabase db = helper.getWritableDatabase();

        // the table we are deleting from, the column we are deleting from, and the routine
        // in the column we are deleting from passed as a parameter
        db.delete(TABLE_NAME, "ROUTINE_NAME = ?", new String[]{routineName});
        db.close();
    }


    public Cursor getMovementData(String routineName){

        // return a string of each movement from the data base, i.e lunge, 10 rep, 3 set

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.UID, Constants.ROUTINE_NAME, Constants.MOVEMENT,
                Constants.NUM_OF_REPS, Constants.NUM_OF_SETS, Constants.TIME, Constants.TIMED};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, Constants.ROUTINE_NAME + "='" +routineName+ "'", null, null,
                null, null);

        return cursor;

    }



}
