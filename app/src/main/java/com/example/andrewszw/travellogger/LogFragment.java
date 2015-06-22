package com.example.andrewszw.travellogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LogFragment extends Fragment {

    private static final String TAG = "LogFragment";

    public static final String EXTRA_LOG_ID =
            "com.example.andrewszw.travellogger.log_id";

    private Logger mLogger;

    private EditText mStartLocationField;
    private EditText mEndLocationField;
    private EditText mTitleField;
    private Button mStartDateButton, mEndDateButton, mMapButton;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, parent, false);

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
        String startAddress = mStartLocationField.getText().toString();
        Log.d(TAG, "The start address is: " + startAddress);

        mEndLocationField = (EditText)v.findViewById(R.id.end_location);
        String endAddress = mEndLocationField.getText().toString();
        Log.d(TAG, "The end address is: " + endAddress);

        mStartDateButton = (Button)v.findViewById(R.id.date_startButton);
        mStartDateButton.setText(mLogger.getStartDateFormat());
        mStartDateButton.setEnabled(false);

        mEndDateButton = (Button)v.findViewById(R.id.date_endButton);
        mEndDateButton.setText(mLogger.getEndDateFormat());
        mEndDateButton.setEnabled(false);

        mMapButton = (Button)v.findViewById(R.id.map);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LogMapActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
}
