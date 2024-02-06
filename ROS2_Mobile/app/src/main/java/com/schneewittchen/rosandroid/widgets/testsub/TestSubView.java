package com.schneewittchen.rosandroid.widgets.testsub;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.widgets.SubscriberWidgetView;
import com.schneewittchen.rosandroid.utility.Utils;

import org.ros2.rcljava.interfaces.MessageDefinition;

import geometry_msgs.msg.Twist;


/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 05.05.20
 * @updated on 05.05.20
 * @modified by
 */

// TODO: Add maybe a button for getting back to gps position
public class TestSubView extends SubscriberWidgetView {

    public static final String TAG = TestSubView.class.getSimpleName();
    TestSubData data;
    Paint outerPaint = new Paint();
    public TestSubView(Context context) {
        super(context);
        init();
    }

    public TestSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void onNewMessage(MessageDefinition message) {
        super.onNewMessage(message);

        this.data = new TestSubData((Twist) message);
        this.changeColor(this.data);
        this.invalidate();
    }

    public void changeColor(TestSubData data){
        outerPaint.setColor(getResources().getColor(R.color.color_attention, getResources().newTheme()));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(Utils.dpToPx(getContext(), 3));

        float width = getWidth();
        float height = getHeight();

        // Outer ring
        //canvas.drawCircle(width / 2, height / 2, width / 2 - 2, outerPaint);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        outerPaint.setColor(getResources().getColor(R.color.colorAccent, getResources().newTheme()));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(Utils.dpToPx(getContext(), 2));

        float width = getWidth();
        float height = getHeight();

        // Outer ring
        canvas.drawCircle(width / 2, height / 2, width / 2 - 2, outerPaint);
    }

}