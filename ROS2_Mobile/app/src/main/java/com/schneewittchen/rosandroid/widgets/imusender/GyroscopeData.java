package com.schneewittchen.rosandroid.widgets.imusender;

import com.schneewittchen.rosandroid.utility.ros.geometry.Vector3;

public class GyroscopeData {
    private Vector3 vector;
    private double deviation;
    private boolean isSet;


    public void setData(double x, double y, double z, double deviation) {
        this.vector = new Vector3(x, y, z);
        this.deviation = deviation;
        this.isSet = true;
    }

    public void resetData() {
        this.vector = null;
        this.isSet = false;
    }

    public boolean readyToSend(){
        return this.isSet;
    }

    public double getDeviation() {
        return deviation;
    }

    public Vector3 getVector() {
        return vector;
    }
}
