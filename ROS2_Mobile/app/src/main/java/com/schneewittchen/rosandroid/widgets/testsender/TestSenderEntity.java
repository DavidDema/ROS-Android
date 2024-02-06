package com.schneewittchen.rosandroid.widgets.testsender;

import com.schneewittchen.rosandroid.model.entities.widgets.PublisherWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import std_msgs.msg.String;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.1.1
 * @created on 31.01.20
 * @updated on 10.05.20
 * @modified by Nico Studt
 */
public class TestSenderEntity extends PublisherWidgetEntity {

    public TestSenderEntity() {
        this.width = 4;
        this.height = 4;
        this.topic = new Topic("/testmsg", String.class.getCanonicalName());
        this.immediatePublish = true;
        //this.publishRate = 20f;
    }

}
