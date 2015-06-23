package com.example.andrewszw.travellogger;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LoggerLab {

    private static final String TAG = "LoggerLab";
    private static final String FILENAME = "logs.json";

    private ArrayList<Logger> mLoggers;

    private static LoggerLab sLoggerLab;
    private LoggerJSON mJson;

    private Context mAppContext;

    private LoggerLab(Context appContext) {
        mAppContext = appContext;
        mJson = new LoggerJSON(mAppContext, FILENAME);

        try {
            mLoggers = mJson.loadTrips();
            Log.e(TAG, "Loaded Trips");
        } catch (Exception e) {
            mLoggers = new ArrayList<Logger>();
            Log.e(TAG, "Error Loading Trips: " , e);
        }
    }

    public static LoggerLab get(Context c) {
        if(sLoggerLab == null) {
            sLoggerLab = new LoggerLab(c.getApplicationContext());
        }
        return sLoggerLab;
    }

    public ArrayList<Logger> getLoggers() {
        return mLoggers;
    }

    public Logger getLogger(UUID id) {
        for(Logger l: mLoggers) {
            if(l.getUUID().equals(id)) {
                return l;
            }
        }
        return null;
    }

    public void addLogger(Logger l) {
        mLoggers.add(l);
    }

    public boolean saveTrips() {
        try {
            mJson.saveTrips(mLoggers);
            Log.d(TAG, "trips saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: " , e);
            return false;
        }
    }
}
