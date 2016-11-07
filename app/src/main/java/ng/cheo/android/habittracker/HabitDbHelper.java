package ng.cheo.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

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

    // Insert data
    public void insertData() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME_DESC, "Walk the Dog");
        values.put(HabitEntry.COLUMN_NAME_COUNT, 1);

        db.beginTransaction();
        try {
            db.insertOrThrow(HabitEntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(LOG_TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }


    // Read data
    public Cursor readData() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_NAME_DESC,
                HabitEntry.COLUMN_NAME_COUNT
        };

        Cursor c = db.query(
                HabitEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                        // The sort order
        );

        return c;
    }

}
