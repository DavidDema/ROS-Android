package com.schneewittchen.rosandroid.widgets.imusender;


import android.view.View;

import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.ui.views.details.PublisherWidgetViewHolder;

import org.ros2.rcljava.interfaces.MessageDefinition;

import java.util.Collections;
import java.util.List;

import sensor_msgs.msg.Imu;

public class IMUSenderDetailVH extends PublisherWidgetViewHolder {

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
        return Collections.singletonList(Imu.class);
    }
}