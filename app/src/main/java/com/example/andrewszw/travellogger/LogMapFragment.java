package com.example.andrewszw.travellogger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by andrewszw on 6/22/15.
 */
public class LogMapFragment extends SupportMapFragment {

    private GoogleMap mGoogleMap;
    private Logger mLogger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        mGoogleMap = getMap();

        updateUI();

        return v;

    }

    public Double[] grabStartCoordinates() {
        Double[] points = getLatLongFromAddress(
                getActivity(), "289 Ambling Way, Boone, NC");
        return points;
    }

    public Double[] grabEndCoordinates() {
        Double[] points = getLatLongFromAddress(
                getActivity(), "104 Breckenridge Drive, Knightdale, NC");
        return points;
    }

    private void updateUI() {
        if(mGoogleMap == null) return;

        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

        Double [] startPoint = grabStartCoordinates();
        Double [] endPoint = grabEndCoordinates();

        LatLng start = new LatLng(startPoint[0], startPoint[1]);
        LatLng end = new LatLng(endPoint[0], endPoint[1]);

        Resources r = getResources();

        // Add polyline using Mercator projection
        Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
                .add(start, end)
                .width(5)
                .color(Color.RED));

        MarkerOptions startMarkerOptions = new MarkerOptions()
                .position(start)
                .title("289 Ambling Way, Boone, NC")
                .snippet("This trip began on some day");
        mGoogleMap.addMarker(startMarkerOptions);

        MarkerOptions endMarkerOptions = new MarkerOptions()
                .position(end)
                .title("104 BreckenRidge Drive")
                .snippet("This trip ended on some day");
        mGoogleMap.addMarker(endMarkerOptions);

        CameraUpdate movement = CameraUpdateFactory.newLatLngZoom(start, 9.0f);
        mGoogleMap.moveCamera(movement);
    }

    public Double[] getLatLongFromAddress(Context context, String address) {
        Geocoder geo = new Geocoder(context, Locale.getDefault());
        Double[] points = new Double[2];

        try {
            List<Address> list = geo.getFromLocationName(address, 1);
            Address tmp_address = list.get(0);
            double latitude = tmp_address.getLatitude();
            double longitude = tmp_address.getLongitude();
            points[0] = latitude;
            points[1] = longitude;
        } catch(IOException ioe) {

        }
        return points;
    }
}
