package ng.cheo.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ng.cheo.android.habittracker.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Insert data
        insertHabitData();

        // Read data
        readHabitData();
    }

    // Insert data
    private void insertHabitData() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME_DESC, "Walk the Dog");
        values.put(HabitEntry.COLUMN_NAME_COUNT, 1);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.i("habit", "NewRowId:" + String.valueOf(newRowId));
    }


    // Read data
    private void readHabitData() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

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

        int descColumnIndex = c.getColumnIndex(HabitEntry.COLUMN_NAME_DESC);
        int countColumnIndex = c.getColumnIndex(HabitEntry.COLUMN_NAME_COUNT);

        TextView displayView = (TextView) findViewById(R.id.results);

        try {
            String results = "";
            while(c.moveToNext()) {
                results += "Habit: " + c.getString(descColumnIndex) + ", Count: " + String.valueOf(c.getInt(countColumnIndex)) + "\n";
            }

            displayView.setText(results);
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            c.close();
        }
    }
}
