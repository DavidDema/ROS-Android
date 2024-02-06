package com.schneewittchen.rosandroid.widgets.imusender;

import com.schneewittchen.rosandroid.utility.ros.geometry.Quaternion;


public class RotationData {
    private Quaternion quaternion;
    private boolean isSet = false;
    public double deviation;

    public void setData(double w, double x, double y, double z, double deviation) {
        this.quaternion = new Quaternion(x,y,z,w);
        this.deviation = deviation;
        this.isSet = true;
    }

    public void resetData(){
        this.quaternion = null;
        this.isSet = false;
    }

    public boolean readyToSend(){
        return this.isSet;
    }

    public double getDeviation(){
        return deviation;
    }

    public Quaternion getQuaternion() {
        return quaternion;
    }

}
