package com.example.andrewszw.travellogger;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.UUID;

public class LogActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        UUID logId = (UUID)getIntent()
                .getSerializableExtra(LogFragment.EXTRA_LOG_ID);

        return LogFragment.newInstance(logId);
    }
}
