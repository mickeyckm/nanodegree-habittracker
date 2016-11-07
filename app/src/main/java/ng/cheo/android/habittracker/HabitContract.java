package ng.cheo.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by mickey on 7/11/16.
 */

public final class HabitContract {

    // prevent accidental instantiation
    private HabitContract() {}

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_DESC = "description";
        public static final String COLUMN_NAME_COUNT = "count";
    }
}
