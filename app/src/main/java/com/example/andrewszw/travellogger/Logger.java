package com.example.andrewszw.travellogger;

import android.location.Geocoder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class Logger {

    private static final String TAG = "Logger";

    private static final String JSON_ID = "id";
    private static final String JSON_START_DATE = "start_date";
    private static final String JSON_END_DATE = "end_date";
    private static final String JSON_TITLE = "title";
    private static final String JSON_START_LOCATION = "start_location";
    private static final String JSON_END_LOCATION = "end_location";
    private static final String JSON_START_LATITUDE = "start_latitude";
    private static final String JSON_START_LONGITUDE = "start_longitude";
    private static final String JSON_END_LATITUDE = "end_latitude";
    private static final String JSON_END_LONGITUDE = "end_longitude";

    private UUID mId;
    private Date mStartDate;
    private Date mEndDate;
    private String mTitle;
    private String mStartLocation;
    private String mEndLocation;
    private Double mStartLatitude;
    private Double mStartLongitude;
    private Double mEndLatitude;
    private Double mEndLongitude;

    public Logger() {
        mId = UUID.randomUUID();
        mStartDate = new Date();
        mEndDate = new Date();
        mStartLocation = "";
        mEndLocation = "";
        mStartLatitude = 0.0;
        mStartLongitude = 0.0;
        mEndLatitude = 0.0;
        mEndLongitude = 0.0;
    }

    public Logger(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));

        if(json.has(JSON_TITLE)) {
            mTitle = json.getString(JSON_TITLE);
        }

        mStartDate = new Date(json.getLong(JSON_START_DATE));
        mEndDate = new Date(json.getLong(JSON_END_DATE));

        mStartLocation = json.getString(JSON_START_LOCATION);

        mEndLocation = json.getString(JSON_END_LOCATION);

        mStartLatitude = json.getDouble(JSON_START_LATITUDE);
        mStartLongitude = json.getDouble(JSON_START_LONGITUDE);
        mEndLatitude = json.getDouble(JSON_END_LATITUDE);
        mEndLongitude = json.getDouble(JSON_END_LONGITUDE);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_START_DATE, mStartDate.getTime());
        json.put(JSON_END_DATE, mEndDate.getTime());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_START_LOCATION, mStartLocation);
        json.put(JSON_END_LOCATION, mEndLocation);
        json.put(JSON_START_LATITUDE, mStartLatitude);
        json.put(JSON_START_LONGITUDE, mStartLongitude);
        json.put(JSON_END_LATITUDE, mEndLatitude);
        json.put(JSON_END_LONGITUDE, mEndLongitude);
        return json;
    }

    public Double getStartLatitude() {
        return mStartLatitude;
    }

    public Double getStartLongitude() {
        return mStartLongitude;
    }

    public void setStartLatitude(Double latitude) {
        mStartLatitude = latitude;
    }

    public void setStartLongitude(Double longitude) {
        mStartLongitude = longitude;
    }

    public Double getEndLatitude() {
        return mEndLatitude;
    }

    public Double getEndLongitude() {
        return mEndLongitude;
    }

    public void setEndLatitude(Double latitude) {
        mEndLatitude = latitude;
    }

    public void setEndLongitude(Double longitude) {
        mEndLongitude = longitude;
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
