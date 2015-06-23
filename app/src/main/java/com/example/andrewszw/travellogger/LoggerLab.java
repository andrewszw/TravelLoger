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
}
