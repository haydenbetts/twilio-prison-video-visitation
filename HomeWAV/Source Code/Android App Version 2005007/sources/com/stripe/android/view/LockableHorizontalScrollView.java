package com.stripe.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class LockableHorizontalScrollView extends HorizontalScrollView {
    private LockableScrollChangedListener mLockableScrollChangedListener;
    private boolean mScrollable;

    interface LockableScrollChangedListener {
        void onSmoothScrollBy(int i, int i2);
    }

    public LockableHorizontalScrollView(Context context) {
        super(context);
    }

    public LockableHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LockableHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean isScrollable() {
        return this.mScrollable;
    }

    public void setScrollable(boolean z) {
        this.mScrollable = z;
        setSmoothScrollingEnabled(z);
    }

    public void scrollTo(int i, int i2) {
        if (this.mScrollable) {
            super.scrollTo(i, i2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        return this.mScrollable && super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mScrollable && super.onInterceptTouchEvent(motionEvent);
    }

    /* access modifiers changed from: package-private */
    public void setScrollChangedListener(LockableScrollChangedListener lockableScrollChangedListener) {
        this.mLockableScrollChangedListener = lockableScrollChangedListener;
    }

    /* access modifiers changed from: package-private */
    public void wrappedSmoothScrollBy(int i, int i2) {
        if (this.mScrollable) {
            smoothScrollBy(i, i2);
            LockableScrollChangedListener lockableScrollChangedListener = this.mLockableScrollChangedListener;
            if (lockableScrollChangedListener != null) {
                lockableScrollChangedListener.onSmoothScrollBy(i, i2);
            }
        }
    }
}
