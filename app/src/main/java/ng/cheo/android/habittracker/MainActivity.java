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

        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Insert data
        mDbHelper.insertData();

        // Read data
        Cursor cursor = mDbHelper.readData();

        int descColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_DESC);
        int countColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_COUNT);

        TextView displayView = (TextView) findViewById(R.id.results);
        String results = "";
        while(cursor.moveToNext()) {
            results += "Habit: " + cursor.getString(descColumnIndex) + ", Count: " + String.valueOf(cursor.getInt(countColumnIndex)) + "\n";
        }
        displayView.setText(results);

        cursor.close();
    }



}
