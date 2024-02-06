package com.schneewittchen.rosandroid.widgets.gpssender;

import android.view.View;
import android.widget.Switch;

import androidx.appcompat.widget.SwitchCompat;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.ui.views.details.PublisherWidgetViewHolder;
//import com.schneewittchen.rosandroid.widgets.logger.LoggerEntity;

import org.ros2.rcljava.interfaces.MessageDefinition;

import java.util.Collections;
import java.util.List;

import sensor_msgs.msg.NavSatFix;

public class GPSSenderDetailVH extends PublisherWidgetViewHolder  {

    //private SwitchCompat fusionSwitch;
    private Switch fusionSwitch;

    @Override
    public void initView(View view) {
        fusionSwitch = view.findViewById(R.id.fusionSwitch);
    }

    @Override
    public void bindEntity(BaseEntity entity) {
    GPSSenderEntity gpsSenderEntity = (GPSSenderEntity) entity;
        fusionSwitch.setChecked(gpsSenderEntity.fusion);
    }


    @Override
    public void updateEntity(BaseEntity entity) {
        GPSSenderEntity gpsSenderEntityEntity = (GPSSenderEntity) entity;
        gpsSenderEntityEntity.fusion = fusionSwitch.isChecked();
    }

    @Override
    public List<Class<? extends MessageDefinition>> getTopicTypes() {
        return Collections.singletonList(NavSatFix.class);
    }
}