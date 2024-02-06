package com.schneewittchen.rosandroid.widgets.testsender;

import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.node.BaseData;

import org.ros2.rcljava.interfaces.MessageDefinition;
import org.ros2.rcljava.publisher.Publisher;

import std_msgs.msg.String;

//import org.ros2.rcljava.


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.0
 * @created on 17.03.20
 * @updated on 17.03.20
 * @modified by
 */
public class TestSenderData extends BaseData {

    public java.lang.String sendText;

    public TestSenderData(java.lang.String text){
        this.sendText = text;
    }
    @Override
    public MessageDefinition toRosMessage(Publisher<MessageDefinition> publisher, BaseEntity widget) {
        TestSenderEntity testWidget = (TestSenderEntity) widget;

        String message = new String();
        //message;
        //Twist message = new Twist();
        message.setData(this.sendText);
        return message;
    }
}
