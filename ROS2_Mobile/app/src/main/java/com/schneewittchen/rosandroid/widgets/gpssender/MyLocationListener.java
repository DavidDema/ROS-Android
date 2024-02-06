package com.schneewittchen.rosandroid.widgets.gpssender;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.schneewittchen.rosandroid.domain.RosDomain;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import java.util.List;

import sensor_msgs.msg.NavSatFix;

public class MyLocationListener implements LocationListener {

    private static final MyLocationListener gps = new MyLocationListener();
    private static final MyLocationListener fusion = new MyLocationListener();

    private RosDomain rosDomain;
    private Topic topic;


    public static MyLocationListener getFusion() {
        return fusion;
    }

    public static MyLocationListener getGPS() {
        return gps;
    }

    private MyLocationListener(){
        String provider = LocationManager.GPS_PROVIDER;
        this.rosDomain = RosDomain.getInstance(null);
        this.topic = new Topic("navsat/fix", NavSatFix.class.getCanonicalName());

    }

    @Override
    public void onLocationChanged(Location location) {
        GPSSenderData data = new GPSSenderData(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy());
        data.setTopic(this.topic);
        rosDomain.publishData(data);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }
}
