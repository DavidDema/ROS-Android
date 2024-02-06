package com.schneewittchen.rosandroid.widgets.imusender;


import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.node.BaseData;

import org.ros2.rcljava.interfaces.MessageDefinition;
import org.ros2.rcljava.Time;
import org.ros2.rcljava.publisher.Publisher;

import sensor_msgs.msg.Imu;


public class IMUSenderData extends BaseData {

    public static final String TAG = IMUSenderData.class.getSimpleName();
    private GyroscopeData gyro;
    private RotationData rot;
    private AccelerometerData acc;

    public IMUSenderData(){
        gyro = new GyroscopeData();
        rot = new RotationData();
        acc = new AccelerometerData();
    }

    public boolean readyToSend(){
        return gyro.readyToSend() && rot.readyToSend() && acc.readyToSend();
    }

    public void reset(){
        gyro.resetData();
        rot.resetData();
        acc.resetData();
    }

    public GyroscopeData getGyro() {
        return gyro;
    }

    public RotationData getRot() {
        return rot;
    }

    public AccelerometerData getAcc() {
        return acc;
    }

    @Override
    public MessageDefinition toRosMessage(Publisher<MessageDefinition> publisher, BaseEntity widget) {
        IMUSenderEntity senderWidget = (IMUSenderEntity) widget;

        Imu message = new Imu();
        message.setAngularVelocity(gyro.getVector().toVector3Message(message.getAngularVelocity()));
        message.setOrientation(rot.getQuaternion().toQuaternionMessage(message.getOrientation()));

        message.setLinearAcceleration(acc.getVector().toVector3Message(message.getLinearAcceleration()));

        //message.getHeader().setStamp(Time.fromMillis(System.currentTimeMillis()));
        message.getHeader().setStamp(Time.now());
        message.getHeader().setFrameId("base_link");

        return message;
    }
}