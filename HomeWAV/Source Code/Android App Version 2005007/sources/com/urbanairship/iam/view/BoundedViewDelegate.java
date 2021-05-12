package com.urbanairship.iam.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.urbanairship.R;

class BoundedViewDelegate {
    private int maxHeight;
    private int maxWidth;

    BoundedViewDelegate(Context context, AttributeSet attributeSet, int i, int i2) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.UrbanAirshipLayout, i, i2);
            this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UrbanAirshipLayout_urbanAirshipMaxWidth, 0);
            this.maxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UrbanAirshipLayout_urbanAirshipMaxHeight, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public int getWidthMeasureSpec(int i) {
        int size = View.MeasureSpec.getSize(i);
        int i2 = this.maxWidth;
        return (i2 <= 0 || i2 >= size) ? i : View.MeasureSpec.makeMeasureSpec(i2, View.MeasureSpec.getMode(i));
    }

    /* access modifiers changed from: package-private */
    public int getHeightMeasureSpec(int i) {
        int size = View.MeasureSpec.getSize(i);
        int i2 = this.maxHeight;
        return (i2 <= 0 || i2 >= size) ? i : View.MeasureSpec.makeMeasureSpec(i2, View.MeasureSpec.getMode(i));
    }
}
