package com.schneewittchen.rosandroid.widgets.gpssender;


import android.app.Activity;
import android.app.Application;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.schneewittchen.rosandroid.domain.RosDomain;
import com.schneewittchen.rosandroid.model.entities.widgets.PublisherWidgetEntity;
//import com.schneewittchen.rosandroid.model.repositories.rosRepo.RosRepository;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

//import org.ros.namespace.GraphName;

import sensor_msgs.msg.NavSatFix;

public class GPSSenderEntity extends PublisherWidgetEntity {

    public boolean fusion;

    public GPSSenderEntity() {
        this.width = 4;
        this.height = 4;
        this.fusion = false;

        this.topic = new Topic("navsat/fix", NavSatFix.class.getCanonicalName());
        this.immediatePublish = true;
    }

}