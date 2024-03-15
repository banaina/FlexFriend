package com.example.flexfriend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String CREATE_TABLE =
            "CREATE TABLE "+
                    Constants.TABLE_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.CATEGORY + " TEXT, " +
                    Constants.ROUTINE_NAME + " TEXT, " +
                    Constants.MOVEMENT + " TEXT, " +
                    Constants.NUM_OF_SETS + " TEXT, " +
                    Constants.NUM_OF_REPS + " TEXT, " +
                    Constants.TIMED + " TEXT, " +
                    Constants.TIME + " TEXT);" ;

    public MyHelper(Context context){
        super (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
