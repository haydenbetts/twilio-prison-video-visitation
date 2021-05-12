package com.urbanairship.iam.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.StateSet;
import android.util.TypedValue;
import androidx.core.graphics.ColorUtils;

public class BackgroundDrawableBuilder {
    private int backgroundColor = 0;
    private float borderRadiusDps = 0.0f;
    private int borderRadiusFlag;
    private final Context context;
    private Integer pressedColor;
    private Integer strokeColor;
    private int strokeWidthDps = 0;

    public static BackgroundDrawableBuilder newBuilder(Context context2) {
        return new BackgroundDrawableBuilder(context2);
    }

    private BackgroundDrawableBuilder(Context context2) {
        this.context = context2;
    }

    public BackgroundDrawableBuilder setPressedColor(int i) {
        this.pressedColor = Integer.valueOf(i);
        return this;
    }

    public BackgroundDrawableBuilder setBackgroundColor(int i) {
        this.backgroundColor = i;
        return this;
    }

    public BackgroundDrawableBuilder setBorderRadius(float f, int i) {
        this.borderRadiusFlag = i;
        this.borderRadiusDps = f;
        return this;
    }

    public BackgroundDrawableBuilder setStrokeWidth(int i) {
        this.strokeWidthDps = i;
        return this;
    }

    public BackgroundDrawableBuilder setStrokeColor(int i) {
        this.strokeColor = Integer.valueOf(i);
        return this;
    }

    public Drawable build() {
        int round = Math.round(TypedValue.applyDimension(1, (float) this.strokeWidthDps, this.context.getResources().getDisplayMetrics()));
        Integer num = this.strokeColor;
        int intValue = num == null ? this.backgroundColor : num.intValue();
        float[] createRadiiArray = BorderRadius.createRadiiArray(TypedValue.applyDimension(1, this.borderRadiusDps, this.context.getResources().getDisplayMetrics()), this.borderRadiusFlag);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(createRadiiArray);
        gradientDrawable.setColor(this.backgroundColor);
        gradientDrawable.setStroke(round, intValue);
        if (this.pressedColor == null) {
            return gradientDrawable;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return new RippleDrawable(ColorStateList.valueOf(this.pressedColor.intValue()), gradientDrawable, new ShapeDrawable(new RoundRectShape(createRadiiArray, (RectF) null, (float[]) null)));
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setCornerRadii(createRadiiArray);
        gradientDrawable2.setColor(ColorUtils.compositeColors(this.pressedColor.intValue(), this.backgroundColor));
        gradientDrawable2.setStroke(round, ColorUtils.compositeColors(this.pressedColor.intValue(), intValue));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        return stateListDrawable;
    }
}
