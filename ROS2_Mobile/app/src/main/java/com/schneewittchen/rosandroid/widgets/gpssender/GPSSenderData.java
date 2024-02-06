package com.schneewittchen.rosandroid.widgets.gpssender;


import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Message;

import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.node.BaseData;

import org.ros2.rcljava.Time;
import org.ros2.rcljava.interfaces.MessageDefinition;
import org.ros2.rcljava.publisher.Publisher;

import org.ros2.rcljava.interfaces.MessageDefinition; //internal.message.Message;

import sensor_msgs.msg.NavSatFix;
import sensor_msgs.msg.NavSatStatus;


public class GPSSenderData extends BaseData {

    public static final String TAG = GPSSenderData.class.getSimpleName();
    public double latitude;
    public double longitude;
    public double altitude;
    public double deviation;


    public GPSSenderData(double latitude, double longitude, double altitude, double deviation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.deviation = deviation;
    }

    @Override
    public MessageDefinition toRosMessage(Publisher<MessageDefinition> publisher, BaseEntity widget) {

        NavSatFix message = new NavSatFix();
        message.setLatitude(this.latitude);
        message.setAltitude(this.altitude);
        message.setLongitude(this.longitude);
        message.getStatus().setStatus(NavSatStatus.STATUS_FIX);
        message.getStatus().setService(NavSatStatus.SERVICE_GPS);

        message.setPositionCovarianceType(NavSatFix.COVARIANCE_TYPE_APPROXIMATED);
        double covariance = this.deviation*this.deviation;
        double[] tmpCov = {covariance,0,0, 0,covariance,0, 0,0,covariance};
        message.setPositionCovariance(tmpCov);

        //message.getHeader().setStamp(Time.fromMillis(System.currentTimeMillis()));
        message.getHeader().setStamp(Time.now());
        message.getHeader().setFrameId("base_link");

        return message;
    }
}
