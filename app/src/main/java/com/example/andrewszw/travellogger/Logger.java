package com.example.andrewszw.travellogger;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class Logger {

    private UUID mId;
    private Date mStartDate;
    private Date mEndDate;
    private String mTitle;
    private String mStartLocation;
    private String mEndLocation;

    public Logger() {
        mId = UUID.randomUUID();
        mStartDate = new Date();
        mEndDate = new Date();
        mStartLocation = "";
        mEndLocation = "";
    }

    public UUID getUUID() {
        return mId;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setStartDate(Date date) {
        mStartDate = date;
    }

    public void setEndDate(Date date) {
        mEndDate = date;
    }

    public String getStartLocation() {
        return mStartLocation;
    }

    public String getEndLocation() {
        return mEndLocation;
    }

    public void setStartLocation(String startLocation) {
        mStartLocation = startLocation;
    }

    public void setEndLocation(String endLocation) {
        mEndLocation = endLocation;
    }

    public String getStartDateFormat() {
        DateFormat df = DateFormat.getDateInstance();
        return df.format(mStartDate).toString();
    }

    public String getEndDateFormat() {
        DateFormat df = DateFormat.getDateInstance();
        return df.format(mEndDate).toString();
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
