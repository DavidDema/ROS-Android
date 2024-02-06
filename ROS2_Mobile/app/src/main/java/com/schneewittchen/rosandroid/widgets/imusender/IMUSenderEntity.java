package com.schneewittchen.rosandroid.widgets.imusender;


import com.schneewittchen.rosandroid.model.entities.widgets.PublisherWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import sensor_msgs.msg.Imu;

public class IMUSenderEntity extends PublisherWidgetEntity {

    public IMUSenderEntity() {
        this.width = 4;
        this.height = 4;
        this.topic = new Topic("imu", Imu.class.getCanonicalName());
        this.immediatePublish = true;
    }

}