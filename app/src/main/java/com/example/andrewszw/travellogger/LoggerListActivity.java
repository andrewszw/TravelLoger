package com.example.andrewszw.travellogger;

import android.support.v4.app.Fragment;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LoggerListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new LoggerListFragment();
    }
}
