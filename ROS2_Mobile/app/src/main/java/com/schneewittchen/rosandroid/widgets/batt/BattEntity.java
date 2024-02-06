package com.schneewittchen.rosandroid.widgets.batt;


import com.schneewittchen.rosandroid.model.entities.widgets.SubscriberWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import geometry_msgs.msg.Twist;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.0
 * @created on 13.05.2021
 */
public class BattEntity extends SubscriberWidgetEntity {



    public BattEntity() {
        this.width = 1;
        this.height = 2;
        this.topic = new Topic("cmd_vel", Twist.class.getCanonicalName());
    }

}