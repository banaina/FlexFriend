package com.example.flexfriend;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {
    private Context context;
    private static MyHelper mInstance = null;

    private static final String CREATE_TABLE =
            "CREATE TABLE "+
                    Constants.TABLE_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.CATEGORY + " TEXT, " +
                    Constants.ROUTINE_NAME + " TEXT, " +
                    Constants.TIMED + " TEXT, " +
                    Constants.MOVEMENT + " TEXT, " +
                    Constants.NUM_OF_SETS + " TEXT, " +
                    Constants.NUM_OF_REPS + " TEXT, " +
                    Constants.TIME + " TEXT);" ;



    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME;


    public static MyHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new MyHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public MyHelper(Context context){
        super (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
//        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
//            Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onCreate() db", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
//            Toast.makeText(context, "onUpgrade called", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onUpgrade() db", Toast.LENGTH_LONG).show();
        }
    }
}
