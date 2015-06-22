package com.example.andrewszw.travellogger;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LoggerLab {

    private ArrayList<Logger> mLoggers;

    private static LoggerLab sLoggerLab;
    private Context mAppContext;

    private LoggerLab(Context appContext) {
        mAppContext = appContext;
        mLoggers = new ArrayList<Logger>();

        // test data
        for(int i = 0; i < 100; i++) {
            Logger l = new Logger();
            l.setTitle("Trip #" + i);
            mLoggers.add(l);
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
}
