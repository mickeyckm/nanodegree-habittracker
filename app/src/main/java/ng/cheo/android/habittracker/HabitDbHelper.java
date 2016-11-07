package ng.cheo.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ng.cheo.android.habittracker.HabitContract.HabitEntry;

/**
 * Created by mickey on 7/11/16.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_HABITS_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_NAME_DESC + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_NAME_COUNT + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Still at version 1, nothing to be done here.
    }
}
