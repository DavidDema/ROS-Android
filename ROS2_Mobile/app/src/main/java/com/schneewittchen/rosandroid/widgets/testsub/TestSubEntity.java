package com.schneewittchen.rosandroid.widgets.testsub;


import com.schneewittchen.rosandroid.model.entities.widgets.SubscriberWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import geometry_msgs.msg.Twist;


/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 05.05.20
 * @updated on 27.10.2020
 * @modified by Nico Studt
 */
public class TestSubEntity extends SubscriberWidgetEntity {

    public TestSubEntity() {
        this.width = 2;
        this.height = 2;
        this.topic = new Topic("cmd_vel", Twist.class.getCanonicalName());
        //this.topic = new Topic("testmsg", String.class.getCanonicalName());
    }
}