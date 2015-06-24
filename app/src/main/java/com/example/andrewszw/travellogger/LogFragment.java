package com.example.andrewszw.travellogger;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LogFragment extends Fragment {

    private static final String TAG = "LogFragment";

    public static final String EXTRA_LOG_ID =
            "com.example.andrewszw.travellogger.log_id";

    private static final String START_DIALOG_DATE = "start_date";
    private static final String END_DIALOG_DATE = "end_date";

    private static final int START_REQUEST_DATE = 0;
    private static final int END_REQUEST_DATE = 1;

    private Logger mLogger;
    private LocationAddress mLocationAddress;

    private EditText mStartLocationField;
    private EditText mEndLocationField;
    private EditText mTitleField;
    private Button mStartDateButton, mEndDateButton, mMapButton, mDetailButton;

    public static LogFragment newInstance(UUID logId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_LOG_ID, logId);

        LogFragment frag = new LogFragment();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID logId = (UUID)getArguments().getSerializable(EXTRA_LOG_ID);
        mLogger = LoggerLab.get(getActivity()).getLogger(logId);
        mLocationAddress = new LocationAddress();

        setHasOptionsMenu(true);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, parent, false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTitleField = (EditText)v.findViewById(R.id.title);
        mTitleField.setText(mLogger.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mLogger.setTitle(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mStartLocationField = (EditText)v.findViewById(R.id.start_location);
        mStartLocationField.setText(mLogger.getStartLocation());
        mStartLocationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing will be here initially
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mLogger.setStartLocation(s.toString());
                new convertToLatLongStart().execute();
            }
        });

        mEndLocationField = (EditText)v.findViewById(R.id.end_location);
        mEndLocationField.setText(mLogger.getEndLocation());
        mEndLocationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mLogger.setEndLocation(s.toString());
                new convertToLatLongEnd().execute();
            }
        });

        mStartDateButton = (Button)v.findViewById(R.id.date_startButton);
        mStartDateButton.setText(mLogger.getStartDateFormat());
        mStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                StartDatePickerFragment dialog = StartDatePickerFragment
                        .newInstance(mLogger.getStartDate());
                dialog.setTargetFragment(LogFragment.this, START_REQUEST_DATE);
                dialog.show(fm, START_DIALOG_DATE);
            }
        });

        mEndDateButton = (Button)v.findViewById(R.id.date_endButton);
        mEndDateButton.setText(mLogger.getEndDateFormat());
        mEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                EndDatePickerFragment dialog = EndDatePickerFragment
                        .newInstance(mLogger.getEndDate());
                dialog.setTargetFragment(LogFragment.this, END_REQUEST_DATE);
                dialog.show(fm, END_DIALOG_DATE);
            }
        });

        mMapButton = (Button)v.findViewById(R.id.map);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LogMapActivity.class);
                i.putExtra(LogMapFragment.EXTRA_LOG_ID, mLogger.getUUID());
                startActivity(i);
            }
        });

        mDetailButton = (Button)v.findViewById(R.id.detail);
        mDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TripDetailActivity.class);
                i.putExtra(TripDetailFragment.EXTRA_LOG_ID, mLogger.getUUID());
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) return;

        if(requestCode == START_REQUEST_DATE) {
            Date date = (Date)data
                    .getSerializableExtra(StartDatePickerFragment.EXTRA_START_DATE);
            mLogger.setStartDate(date);
            mStartDateButton.setText(mLogger.getStartDateFormat());
        } else if(requestCode == END_REQUEST_DATE) {
            Date date = (Date)data
                    .getSerializableExtra(EndDatePickerFragment.EXTRA_END_DATE);
            mLogger.setEndDate(date);
            mEndDateButton.setText(mLogger.getEndDateFormat());
        }
    }

    private class convertToLatLongStart extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            String address = mLogger.getStartLocation();
            GeocodingLocation geoLoc = new GeocodingLocation();
            double[] points = geoLoc.getLatLongFromAddress(getActivity(), address);
            mLogger.setStartLatitude(points[0]);
            mLogger.setStartLongitude(points[1]);
            Log.d(TAG, "Start Latitude is: " + mLogger.getStartLatitude());
            Log.d(TAG, "Start Longitude is: " + mLogger.getStartLongitude());
            return null;
        }
    }

    private class convertToLatLongEnd extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            String address = mLogger.getEndLocation();
            GeocodingLocation geoLoc = new GeocodingLocation();
            double[] points = geoLoc.getLatLongFromAddress(getActivity(), address);
            mLogger.setEndLatitude(points[0]);
            mLogger.setEndLongitude(points[1]);
            Log.d(TAG, "End Latitude is: " + mLogger.getEndLatitude());
            Log.d(TAG, "End Longitude is: " + mLogger.getEndLongitude());
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggerLab.get(getActivity()).saveTrips();
    }
}
