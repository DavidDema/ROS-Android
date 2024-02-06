package com.schneewittchen.rosandroid.widgets.testsender;

import android.view.View;
import android.widget.EditText;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.ui.views.details.PublisherWidgetViewHolder;

import org.ros2.rcljava.interfaces.MessageDefinition;

import java.util.Collections;
import java.util.List;

import geometry_msgs.msg.Twist;
import std_msgs.msg.String;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.2
 * @created on 13.02.20
 * @updated on 20.05.20
 * @modified by Nico Studt
 * @updated on 05.11.2020
 * @modified by Nico Studt
 */
public class TestSenderDetailVH extends PublisherWidgetViewHolder {

    @Override
    public void initView(View view) {

    }

    @Override
    public void bindEntity(BaseEntity entity) {
    }


    @Override
    public void updateEntity(BaseEntity entity) {
    }

    @Override
    public List<Class<? extends MessageDefinition>> getTopicTypes() {
        return Collections.singletonList(String.class);
    }
}
