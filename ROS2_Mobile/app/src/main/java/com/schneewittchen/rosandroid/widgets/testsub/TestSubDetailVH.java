package com.schneewittchen.rosandroid.widgets.testsub;


import android.view.View;

import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.ui.views.details.SubscriberWidgetViewHolder;

import java.util.Collections;
import java.util.List;

import geometry_msgs.msg.Twist;


/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 05.05.20
 * @updated on 17.09.20
 * @modified by Nils Rottmann
 * @updated on 20.03.21
 * @modified by Nico Studt
 */
public class TestSubDetailVH extends SubscriberWidgetViewHolder {


    @Override
    protected void initView(View parentView) {

    }

    @Override
    protected void bindEntity(BaseEntity entity) {

    }

    @Override
    protected void updateEntity(BaseEntity entity) {

    }

    @Override
    public List<String> getTopicTypes(){
        return Collections.singletonList(Twist.class.getCanonicalName());
    }

    //@Override
    //public List<java.lang.String> getTopicTypes() {
    //    return Collections.singletonList(String.class.getCanonicalName());
    //}


}
