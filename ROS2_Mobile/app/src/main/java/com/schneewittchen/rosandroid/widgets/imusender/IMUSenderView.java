package com.schneewittchen.rosandroid.widgets.imusender;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.widgets.PublisherWidgetView;
import com.schneewittchen.rosandroid.utility.Utils;

public class IMUSenderView extends PublisherWidgetView {

    private SensorManager mSensorManager;
    private int color = R.color.color_attention;
    private Paint outerPaint = new Paint();

    public IMUSenderView(Context context) {
        super(context);
        init();
    }

    public IMUSenderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        this.setKeepScreenOn(true);
    }

    private void setupSensors() {
        IMUSenderEntity imuSenderEntity = (IMUSenderEntity) widgetEntity;
        MySensorEventListener imuEventListener = MySensorEventListener.getImu();

        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor mSensorRot = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        Sensor mSensorGyr = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSensorManager.unregisterListener(imuEventListener);

        mSensorManager.registerListener(imuEventListener, mSensorAcc, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(imuEventListener, mSensorRot, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(imuEventListener, mSensorGyr, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        outerPaint.setColor(getResources().getColor(this.color, getResources().newTheme()));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(Utils.dpToPx(getContext(), 3));

        float width = getWidth();
        float height = getHeight();

        // Outer ring
        canvas.drawCircle(width / 2, height / 2, width / 2 - 2, outerPaint);

        setupSensors();
    }
}