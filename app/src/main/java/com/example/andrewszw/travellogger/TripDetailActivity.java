package com.example.andrewszw.travellogger;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by andrewszw on 6/23/15.
 */
public class TripDetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        UUID logId = (UUID)getIntent()
                .getSerializableExtra(TripDetailFragment.EXTRA_LOG_ID);

        return TripDetailFragment.newInstance(logId);
    }
}
