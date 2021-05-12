package com.urbanairship.iam.view;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;

class ClippableViewDelegate {
    private float borderRadius;
    private Path clipPath;
    private RectF rect;

    ClippableViewDelegate() {
        if (Build.VERSION.SDK_INT < 21) {
            this.clipPath = new Path();
            this.rect = new RectF();
        }
    }

    /* access modifiers changed from: package-private */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z && this.borderRadius > 0.0f && Build.VERSION.SDK_INT < 21) {
            this.clipPath.reset();
            this.rect.set(0.0f, 0.0f, (float) (i3 - i), (float) (i4 - i2));
            float f = this.borderRadius;
            this.clipPath.addRoundRect(this.rect, new float[]{f, f, f, f, f, f, f, f}, Path.Direction.CW);
        }
    }

    /* access modifiers changed from: package-private */
    public void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT < 21 && this.borderRadius != 0.0f) {
            canvas.clipPath(this.clipPath);
        }
    }

    /* access modifiers changed from: package-private */
    public void setClipPathBorderRadius(View view, float f) {
        final float applyDimension = TypedValue.applyDimension(1, f, view.getResources().getDisplayMetrics());
        if (Build.VERSION.SDK_INT < 21) {
            this.borderRadius = applyDimension;
        } else if (applyDimension == 0.0f) {
            view.setClipToOutline(false);
            view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        } else {
            view.setClipToOutline(true);
            view.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getRight() - view.getLeft(), view.getBottom() - view.getTop(), applyDimension);
                }
            });
        }
    }
}
