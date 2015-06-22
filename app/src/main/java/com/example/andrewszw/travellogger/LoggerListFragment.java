package com.example.andrewszw.travellogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LoggerListFragment extends ListFragment {

    private static final String TAG = "LoggerListFragment";

    private ArrayList<Logger> mLoggers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.trips);
        mLoggers = LoggerLab.get(getActivity()).getLoggers();

        LoggerAdapter adapter = new LoggerAdapter(mLoggers);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Logger logger = (Logger)(getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), LogActivity.class);
        i.putExtra(LogFragment.EXTRA_LOG_ID, logger.getUUID());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoggerAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class LoggerAdapter extends ArrayAdapter<Logger> {

        public LoggerAdapter(ArrayList<Logger> loggers) {
            super(getActivity(), 0, loggers);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_logger, null);
            }

            Logger l = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.logger_list_item_title);
            titleTextView.setText(l.getTitle());

            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.logger_list_item_startDate);
            dateTextView.setText(l.getStartDateFormat());

            return convertView;
        }
    }
}
