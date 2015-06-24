package com.example.andrewszw.travellogger;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by andrewszw on 6/23/15.
 */
public class TripDetailFragment extends Fragment {

    public static final String EXTRA_LOG_ID =
            "com.example.andrewszw.travelloger.id";

    private static final String TAG = "TripDetailFragment";

    private Logger mLogger;
    private Location startLocation, endLocation;

    private TextView mStartDateTextView, mEndDateTextView,
        mTotalDaysTextView, mTotalDistanceTextView;

    public static TripDetailFragment newInstance(UUID logId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_LOG_ID, logId);

        TripDetailFragment frag = new TripDetailFragment();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID logId = (UUID)getActivity().getIntent()
                .getSerializableExtra(EXTRA_LOG_ID);

        mLogger = LoggerLab.get(getActivity()).getLogger(logId);

        startLocation = new Location("");
        startLocation.setLatitude(mLogger.getStartLatitude());
        startLocation.setLongitude(mLogger.getStartLongitude());

        endLocation = new Location("");
        endLocation.setLatitude(mLogger.getEndLatitude());
        endLocation.setLongitude(mLogger.getEndLongitude());

        getActivity().setTitle(R.string.details);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trip_detail_fragment, parent, false);

        mStartDateTextView = (TextView)v.findViewById(R.id.start_date_detailTextView);
        mStartDateTextView.setText(mLogger.getStartDateFormat());

        mEndDateTextView = (TextView)v.findViewById(R.id.end_date_detailTextView);
        mEndDateTextView.setText(mLogger.getEndDateFormat());

        mTotalDaysTextView = (TextView)v.findViewById(R.id.total_days_detailTextView);
        StringBuilder sb = new StringBuilder();
        sb.append(mLogger.getTotalDays());
        sb.toString();
        mTotalDaysTextView.setText(sb);

        mTotalDistanceTextView = (TextView)v.findViewById(R.id.total_distance_detailTextView);

        // tmp variables for lat, long
        double startLat = startLocation.getLatitude();
        Log.d(TAG, "Start location lat: " + startLat);
        double startLong = startLocation.getLongitude();
        Log.d(TAG, "Start location long: " + startLong);
        double endLat = endLocation.getLatitude();
        Log.d(TAG, "End location lat: " + endLat);
        double endLong = endLocation.getLongitude();
        Log.d(TAG, "End location lat: " + endLong);

        // get the distance between the two points and store in array
        float[] distance = new float[1];
        Location.distanceBetween(startLat, startLong, endLat, endLong, distance);

        // convert to miles for more readable output
        distance[0] = distance[0] * 0.000621371f;

        Log.d(TAG, "Distance between: " + distance[0]);

        StringBuilder sb2 = new StringBuilder();
        sb2.append(distance[0]);
        sb2.toString();

        mTotalDistanceTextView.setText(sb2 + " miles");

        return v;

    }

}
