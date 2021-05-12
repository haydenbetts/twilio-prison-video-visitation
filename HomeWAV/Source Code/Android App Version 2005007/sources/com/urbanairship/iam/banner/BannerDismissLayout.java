package com.urbanairship.iam.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

public class BannerDismissLayout extends FrameLayout {
    private static final int DEFAULT_OVER_DRAG_DP = 24;
    private static final float FLING_MIN_DRAG_PERCENT = 0.1f;
    private static final float IDLE_MIN_DRAG_PERCENT = 0.4f;
    /* access modifiers changed from: private */
    public ViewDragHelper dragHelper;
    /* access modifiers changed from: private */
    public Listener listener;
    /* access modifiers changed from: private */
    public float minFlingVelocity;
    /* access modifiers changed from: private */
    public float overDragAmount;
    /* access modifiers changed from: private */
    public String placement;

    public interface Listener {
        void onDismissed(View view);

        void onDragStateChanged(View view, int i);
    }

    public BannerDismissLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerDismissLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.placement = BannerDisplayContent.PLACEMENT_BOTTOM;
        init(context);
    }

    public BannerDismissLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.placement = BannerDisplayContent.PLACEMENT_BOTTOM;
        init(context);
    }

    private void init(Context context) {
        if (!isInEditMode()) {
            this.dragHelper = ViewDragHelper.create(this, new ViewDragCallback());
            this.minFlingVelocity = (float) ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
            this.overDragAmount = TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
        }
    }

    public void setMinFlingVelocity(float f) {
        this.minFlingVelocity = f;
    }

    public float getMinFlingVelocity() {
        return this.minFlingVelocity;
    }

    public void setListener(Listener listener2) {
        synchronized (this) {
            this.listener = listener2;
        }
    }

    public float getYFraction() {
        int height = getHeight();
        if (height == 0) {
            return 0.0f;
        }
        return getTranslationY() / ((float) height);
    }

    public void setYFraction(final float f) {
        if (getVisibility() == 0 && getHeight() == 0) {
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    BannerDismissLayout.this.setYFraction(f);
                    BannerDismissLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
            return;
        }
        setTranslationY(f * ((float) getHeight()));
    }

    public float getXFraction() {
        int width = getWidth();
        if (width == 0) {
            return 0.0f;
        }
        return getTranslationX() / ((float) width);
    }

    public void setXFraction(final float f) {
        if (getVisibility() == 0 && getHeight() == 0) {
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    BannerDismissLayout.this.setXFraction(f);
                    BannerDismissLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
            return;
        }
        setTranslationX(f * ((float) getWidth()));
    }

    public void computeScroll() {
        super.computeScroll();
        ViewDragHelper viewDragHelper = this.dragHelper;
        if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        View findTopChildUnder;
        if (this.dragHelper.shouldInterceptTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        if (this.dragHelper.getViewDragState() != 0 || motionEvent.getActionMasked() != 2 || !this.dragHelper.checkTouchSlop(2) || (findTopChildUnder = this.dragHelper.findTopChildUnder((int) motionEvent.getX(), (int) motionEvent.getY())) == null || findTopChildUnder.canScrollVertically(this.dragHelper.getTouchSlop())) {
            return false;
        }
        this.dragHelper.captureChildView(findTopChildUnder, motionEvent.getPointerId(0));
        if (this.dragHelper.getViewDragState() == 1) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View findTopChildUnder;
        this.dragHelper.processTouchEvent(motionEvent);
        if (this.dragHelper.getCapturedView() == null && motionEvent.getActionMasked() == 2 && this.dragHelper.checkTouchSlop(2) && (findTopChildUnder = this.dragHelper.findTopChildUnder((int) motionEvent.getX(), (int) motionEvent.getY())) != null && !findTopChildUnder.canScrollVertically(this.dragHelper.getTouchSlop())) {
            this.dragHelper.captureChildView(findTopChildUnder, motionEvent.getPointerId(0));
        }
        if (this.dragHelper.getCapturedView() != null) {
            return true;
        }
        return false;
    }

    public void setPlacement(String str) {
        this.placement = str;
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private View capturedView;
        private float dragPercent;
        private boolean isDismissed;
        private int startLeft;
        private int startTop;

        private ViewDragCallback() {
            this.dragPercent = 0.0f;
            this.isDismissed = false;
        }

        public boolean tryCaptureView(View view, int i) {
            return this.capturedView == null;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return view.getLeft();
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int clampViewPositionVertical(android.view.View r2, int r3, int r4) {
            /*
                r1 = this;
                com.urbanairship.iam.banner.BannerDismissLayout r2 = com.urbanairship.iam.banner.BannerDismissLayout.this
                java.lang.String r2 = r2.placement
                int r4 = r2.hashCode()
                r0 = -1383228885(0xffffffffad8d9a2b, float:-1.6098308E-11)
                if (r4 == r0) goto L_0x001f
                r0 = 115029(0x1c155, float:1.6119E-40)
                if (r4 == r0) goto L_0x0015
                goto L_0x0029
            L_0x0015:
                java.lang.String r4 = "top"
                boolean r2 = r2.equals(r4)
                if (r2 == 0) goto L_0x0029
                r2 = 0
                goto L_0x002a
            L_0x001f:
                java.lang.String r4 = "bottom"
                boolean r2 = r2.equals(r4)
                if (r2 == 0) goto L_0x0029
                r2 = 1
                goto L_0x002a
            L_0x0029:
                r2 = -1
            L_0x002a:
                if (r2 == 0) goto L_0x0040
                float r2 = (float) r3
                int r3 = r1.startTop
                float r3 = (float) r3
                com.urbanairship.iam.banner.BannerDismissLayout r4 = com.urbanairship.iam.banner.BannerDismissLayout.this
                float r4 = r4.overDragAmount
                float r3 = r3 - r4
                float r2 = java.lang.Math.max(r2, r3)
                int r2 = java.lang.Math.round(r2)
                return r2
            L_0x0040:
                float r2 = (float) r3
                int r3 = r1.startTop
                float r3 = (float) r3
                com.urbanairship.iam.banner.BannerDismissLayout r4 = com.urbanairship.iam.banner.BannerDismissLayout.this
                float r4 = r4.overDragAmount
                float r3 = r3 + r4
                float r2 = java.lang.Math.min(r2, r3)
                int r2 = java.lang.Math.round(r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.banner.BannerDismissLayout.ViewDragCallback.clampViewPositionVertical(android.view.View, int, int):int");
        }

        public void onViewCaptured(View view, int i) {
            this.capturedView = view;
            this.startTop = view.getTop();
            this.startLeft = view.getLeft();
            this.dragPercent = 0.0f;
            this.isDismissed = false;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            int height = BannerDismissLayout.this.getHeight();
            int abs = Math.abs(i2 - this.startTop);
            if (height > 0) {
                this.dragPercent = ((float) abs) / ((float) height);
            }
            BannerDismissLayout.this.invalidate();
        }

        public void onViewDragStateChanged(int i) {
            if (this.capturedView != null) {
                synchronized (this) {
                    if (BannerDismissLayout.this.listener != null) {
                        BannerDismissLayout.this.listener.onDragStateChanged(this.capturedView, i);
                    }
                    if (i == 0) {
                        if (this.isDismissed) {
                            if (BannerDismissLayout.this.listener != null) {
                                BannerDismissLayout.this.listener.onDismissed(this.capturedView);
                            }
                            BannerDismissLayout.this.removeView(this.capturedView);
                        }
                        this.capturedView = null;
                    }
                }
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            float abs = Math.abs(f2);
            if (!BannerDisplayContent.PLACEMENT_TOP.equals(BannerDismissLayout.this.placement) ? this.startTop <= view.getTop() : this.startTop >= view.getTop()) {
                this.isDismissed = this.dragPercent >= 0.4f || abs > BannerDismissLayout.this.minFlingVelocity || this.dragPercent > 0.1f;
            }
            if (this.isDismissed) {
                BannerDismissLayout.this.dragHelper.settleCapturedViewAt(this.startLeft, BannerDisplayContent.PLACEMENT_TOP.equals(BannerDismissLayout.this.placement) ? -view.getHeight() : view.getHeight() + BannerDismissLayout.this.getHeight());
            } else {
                BannerDismissLayout.this.dragHelper.settleCapturedViewAt(this.startLeft, this.startTop);
            }
            BannerDismissLayout.this.invalidate();
        }
    }
}
