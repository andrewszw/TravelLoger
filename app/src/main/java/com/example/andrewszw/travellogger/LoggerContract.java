package com.example.andrewszw.travellogger;

import android.provider.BaseColumns;

/**
 * Created by andrewszw on 6/23/15.
 */
public final class LoggerContract {

    public LoggerContract() {}

    public static abstract class LogEntry implements BaseColumns {
        public static final String TABLE_NAME = "trip";
        // public static final String COLUMN_NAME_TRIP_ID = "trip_id";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";

        private static final String SQL_CREATE_TRIP =
                "CREATE TABLE " + LogEntry.TABLE_NAME + " (" +
                        LogEntry._ID + " INTEGER PRIMARY KEY," +
                        LogEntry.COLUMN_NAME_LATITUDE + " REAL," +
                        LogEntry.COLUMN_NAME_LONGITUDE + " REAL," +
                        LogEntry.COLUMN_NAME_START_DATE + " DATE," +
                        LogEntry.COLUMN_NAME_END_DATE + " DATE, )";

        private static final String SQL_DELETE_TRIPS =
                "DROP TABLE IF EXISTS " + LogEntry.TABLE_NAME;
    }
}
