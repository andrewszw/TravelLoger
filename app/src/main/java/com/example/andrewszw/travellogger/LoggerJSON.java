package com.example.andrewszw.travellogger;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by andrewszw on 6/23/15.
 */
public class LoggerJSON {

    private Context mContext;
    private String mFilename;

    public LoggerJSON(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public ArrayList<Logger> loadTrips() throws IOException, JSONException {
        ArrayList<Logger> trips = new ArrayList<Logger>();
        BufferedReader read = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            read = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = read.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();

            for(int i = 0; i < array.length(); i++) {
                trips.add(new Logger(array.getJSONObject(i)));
            }
        } catch(FileNotFoundException e) {

        } finally {
            if(read != null) {
                read.close();
            }
        }
        return trips;
    }

    public void saveTrips(ArrayList<Logger> trips)
        throws JSONException, IOException {

        JSONArray array = new JSONArray();
        for (Logger l: trips) {
            array.put(l.toJSON());
        }

        Writer writer = null;
        try {
            OutputStream out = mContext
                    .openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
