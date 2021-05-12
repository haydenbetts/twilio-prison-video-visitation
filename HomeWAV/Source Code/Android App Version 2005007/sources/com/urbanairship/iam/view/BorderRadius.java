package com.urbanairship.iam.view;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public abstract class BorderRadius {
    public static final int ALL = 15;
    public static final int BOTTOM = 12;
    public static final int BOTTOM_LEFT = 8;
    public static final int BOTTOM_RIGHT = 4;
    public static final int LEFT = 9;
    public static final int RIGHT = 6;
    public static final int TOP = 3;
    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BorderRadiusFlag {
    }

    public static float[] createRadiiArray(float f, int i) {
        float[] fArr = new float[8];
        if ((i & 1) == 1) {
            fArr[0] = f;
            fArr[1] = f;
        }
        if ((i & 2) == 2) {
            fArr[2] = f;
            fArr[3] = f;
        }
        if ((i & 4) == 4) {
            fArr[4] = f;
            fArr[5] = f;
        }
        if ((i & 8) == 8) {
            fArr[6] = f;
            fArr[7] = f;
        }
        return fArr;
    }

    public static void applyBorderRadiusPadding(View view, final float f, final int i) {
        int i2;
        int i3;
        int i4;
        if (view.getWidth() == 0) {
            final WeakReference weakReference = new WeakReference(view);
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    View view = (View) weakReference.get();
                    if (view == null) {
                        return false;
                    }
                    BorderRadius.applyBorderRadiusPadding(view, f, i);
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    return false;
                }
            });
        }
        float min = Math.min(TypedValue.applyDimension(1, f, view.getResources().getDisplayMetrics()), (float) Math.min(view.getHeight() / 2, view.getWidth() / 2));
        int round = Math.round(min - (((float) Math.sin(Math.toRadians(45.0d))) * min));
        int round2 = Math.round(min - (((float) Math.sin(Math.toRadians(45.0d))) * min));
        int i5 = 0;
        if ((i & 1) == 1) {
            i2 = round2;
            i3 = round;
        } else {
            i3 = 0;
            i2 = 0;
        }
        if ((i & 2) == 2) {
            i2 = round2;
            i4 = round;
        } else {
            i4 = 0;
        }
        if ((i & 4) == 4) {
            i5 = round2;
            i4 = round;
        }
        if ((i & 8) != 8) {
            round = i3;
            round2 = i5;
        }
        view.setPadding(view.getPaddingLeft() + round, view.getPaddingTop() + i2, view.getPaddingRight() + i4, view.getPaddingBottom() + round2);
    }
}
