package com.schneewittchen.rosandroid.widgets.testsender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.widgets.PublisherWidgetView;
import com.schneewittchen.rosandroid.utility.Utils;
import com.schneewittchen.rosandroid.widgets.joystick.JoystickData;

import std_msgs.msg.String;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.1.0
 * @created on 18.10.19
 */
public class TestSenderView extends PublisherWidgetView {

    //public static final String TAG = TestSenderView.class.getSimpleName();
    private int color = R.color.color_attention;
    Paint outerPaint = new Paint();

    public TestSenderView(Context context) {
        super(context);
        init();
    }

    private void init() {

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.publishViewData(new TestSenderData("helloWorld"));
        return true;
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        outerPaint.setColor(getResources().getColor(R.color.colorPrimary, getResources().newTheme()));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(Utils.dpToPx(getContext(), 3));

        float width = getWidth();
        float height = getHeight();

        // Outer ring
        canvas.drawCircle(width / 2, height / 2, width / 2 - 2, outerPaint);

    }
}
