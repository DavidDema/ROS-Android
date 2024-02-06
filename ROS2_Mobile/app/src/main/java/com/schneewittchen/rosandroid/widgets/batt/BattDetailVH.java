package com.schneewittchen.rosandroid.widgets.batt;


import android.view.View;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.ui.views.details.SubscriberWidgetViewHolder;

import java.util.Collections;
import java.util.List;

import geometry_msgs.msg.Twist;

/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.0
 * @created on 13.05.2021
 */
public class BattDetailVH extends SubscriberWidgetViewHolder {

    SwitchMaterial voltageSwitch;
    boolean forceSetChecked;

    @Override
    public void initView(View view) {

    }

    @Override
    protected void bindEntity(BaseEntity entity) {
        BattEntity batteryEntity = (BattEntity) entity;
    }

    @Override
    protected void updateEntity(BaseEntity entity) {
        BattEntity batteryEntity = (BattEntity) entity;
    }

    @Override
    public List<String> getTopicTypes() {
        return Collections.singletonList(Twist.class.getCanonicalName());
    }

}