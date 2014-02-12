TiltToView
==========

Example of scrolling an Android ImageView by tilting the phone. Similar to Facebook Paper.

==========

The main logic is in onSensorChanged:
```java
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
```
Smoothing is applied to the raw sensor values to filter small changes. Image bounds are checked to ensure the image is not scrolled past the edges.

