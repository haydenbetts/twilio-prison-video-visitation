package com.urbanairship.iam.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BoundedLinearLayout extends LinearLayout {
    private final BoundedViewDelegate boundedViewDelegate;
    private final ClippableViewDelegate clippableViewDelegate;

    public BoundedLinearLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public BoundedLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BoundedLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.boundedViewDelegate = new BoundedViewDelegate(context, attributeSet, i, 0);
        this.clippableViewDelegate = new ClippableViewDelegate();
    }

    public BoundedLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.boundedViewDelegate = new BoundedViewDelegate(context, attributeSet, i, i2);
        this.clippableViewDelegate = new ClippableViewDelegate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(this.boundedViewDelegate.getWidthMeasureSpec(i), this.boundedViewDelegate.getHeightMeasureSpec(i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.clippableViewDelegate.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.clippableViewDelegate.onDraw(canvas);
        super.onDraw(canvas);
    }

    public void setClipPathBorderRadius(float f) {
        this.clippableViewDelegate.setClipPathBorderRadius(this, f);
    }
}
