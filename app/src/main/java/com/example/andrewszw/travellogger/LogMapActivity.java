package com.example.andrewszw.travellogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LogMapActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new LogMapFragment();
    }



}
