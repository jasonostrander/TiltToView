package com.jwo.example.tilttoview;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {

    private static final float ALPHA = 0.2f;

    float smoothedValue;
    ImageView mImageView;
    int mImageWidth = -1;
    int mImageHeight = -1;
    SensorManager mSensorManager;
    Sensor mSensor;
    int mMaxScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mImageView = (ImageView) findViewById(R.id.image);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // In a real app, this should not be done on the main thread
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.golden_gate, opts);
        mImageWidth = opts.outWidth;
        mImageHeight = opts.outHeight;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mMaxScroll = (mImageWidth - metrics.widthPixels) / 2;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private static float smooth(float input, float output) {
        return (int) (output + ALPHA * (input - output));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[1];
        value = value*100;
        smoothedValue = smooth(value, smoothedValue);
        value = smoothedValue;

        int scrollX = mImageView.getScrollX();
        if (scrollX + value >= mMaxScroll) value = mMaxScroll - scrollX;
        if (scrollX + value <= -mMaxScroll) value = -mMaxScroll - scrollX;
        mImageView.scrollBy((int) value, 0);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
