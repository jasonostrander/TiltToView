TiltToView
==========

Example of scrolling an Android ImageView by tilting the phone, similar to Facebook Paper.

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

Thanks to [Romain Guy](https://twitter.com/romainguy) for the Golden Gate image.

# License
The MIT License (MIT)

Copyright (c) [year] [fullname]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
