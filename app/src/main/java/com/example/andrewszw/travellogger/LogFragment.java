package com.example.andrewszw.travellogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LogFragment extends Fragment {

    private EditText mStartLocationField;
    private EditText mEndLocationField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, parent, false);

        mStartLocationField = (EditText)v.findViewById(R.id.start_location);

        mEndLocationField = (EditText)v.findViewById(R.id.end_location);

        return v;
    }
}
