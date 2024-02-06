package com.schneewittchen.rosandroid.widgets.imusender;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.schneewittchen.rosandroid.domain.RosDomain;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;
import com.schneewittchen.rosandroid.widgets.gpssender.GPSSenderData;

import sensor_msgs.msg.Imu;

public class MySensorEventListener implements SensorEventListener {

    private static final MySensorEventListener imu = new MySensorEventListener();

    private RosDomain rosDomain;
    private Topic topic;
    private IMUSenderData imuData;


    public static MySensorEventListener getImu() {
        return imu;
    }

    private MySensorEventListener(){
        this.rosDomain = RosDomain.getInstance(null);
        this.imuData = new IMUSenderData();
        this.topic = new Topic("imu", Imu.class.getCanonicalName());
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            this.imuData.getAcc().setData(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], sensorEvent.accuracy);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            this.imuData.getGyro().setData(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], sensorEvent.accuracy);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            float[] quaternion = new float[4];
            SensorManager.getQuaternionFromVector(quaternion, sensorEvent.values);
            this.imuData.getRot().setData(quaternion[0], quaternion[1], quaternion[2], quaternion[3], sensorEvent.accuracy);
        }

        if(this.imuData.readyToSend()){
            this.imuData.setTopic(this.topic);
            this.rosDomain.publishData(this.imuData);
            this.imuData.reset();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
