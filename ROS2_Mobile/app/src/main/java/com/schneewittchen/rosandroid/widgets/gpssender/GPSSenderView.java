package com.schneewittchen.rosandroid.widgets.gpssender;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.widgets.PublisherWidgetView;
import com.schneewittchen.rosandroid.utility.Utils;
import com.schneewittchen.rosandroid.widgets.testsender.TestSenderData;

//import org.gogpsproject.producer.parser.rtcm3.RTCM3Client;

public class GPSSenderView extends PublisherWidgetView {

    private LocationManager mLocationManager;
    Paint outerPaint = new Paint();


    public GPSSenderView(Context context) {
        super(context);
        init();
    }

    public GPSSenderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        this.setKeepScreenOn(true);
    }

    private void setupLocation(){
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        GPSSenderEntity gpsSenderEntity = (GPSSenderEntity) widgetEntity;
        MyLocationListener fusionLocationListener = MyLocationListener.getFusion();
        MyLocationListener gpsLocationListener = MyLocationListener.getGPS();

        mLocationManager.removeUpdates(fusionLocationListener);
        mLocationManager.removeUpdates(gpsLocationListener);
        if(gpsSenderEntity != null && gpsSenderEntity.fusion){
            //Implicitly set Provider to Fusion
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            mLocationManager.requestLocationUpdates(0, 0, criteria, fusionLocationListener, null);
        }else{
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, gpsLocationListener);
        }
        //Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //boolean location_enabled = mLocationManager.isLocationEnabled();
        //boolean provider_enabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
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

        setupLocation();
    }
}