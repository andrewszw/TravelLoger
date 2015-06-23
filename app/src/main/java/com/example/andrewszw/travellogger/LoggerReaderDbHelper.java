package com.example.andrewszw.travellogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andrewszw on 6/23/15.
 */
public class LoggerReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TripReader.db";

    public static final String TABLE_NAME = "trip";
    // public static final String COLUMN_NAME_TRIP_ID = "trip_id";
    public static final String COLUMN_NAME_LATITUDE = "latitude";
    public static final String COLUMN_NAME_LONGITUDE = "longitude";
    public static final String COLUMN_NAME_START_DATE = "start_date";
    public static final String COLUMN_NAME_END_DATE = "end_date";

    private static final String SQL_CREATE_TRIP =
            "CREATE TABLE " + TABLE_NAME + " (_id" +
                    " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_LATITUDE + " REAL," +
                    COLUMN_NAME_LONGITUDE + " REAL," +
                    COLUMN_NAME_START_DATE + " DATE," +
                    COLUMN_NAME_END_DATE + " DATE, )";

    private static final String SQL_DELETE_TRIPS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public LoggerReaderDbHelper helper;

    public LoggerReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        helper = new LoggerReaderDbHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TRIP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing here for this application
    }

    public long insertTrip(Logger l) {
        ContentValues cv = new ContentValues();
        // add latitude and longitude once I have those values from GeoCoder
        cv.put(COLUMN_NAME_START_DATE, l.getStartDate().getTime());
        cv.put(COLUMN_NAME_END_DATE, l.getEndDate().getTime());
        return getWritableDatabase().insert(TABLE_NAME, null, cv);
    }
}
