package com.stripe.android.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;
import com.stripe.android.utils.ClassUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IconTextInputLayout extends TextInputLayout {
    private static final Set<String> BOUNDS_FIELD_NAMES = new HashSet(Arrays.asList(new String[]{"mCollapsedBounds", "collapsedBounds"}));
    private static final Set<String> RECALCULATE_METHOD_NAMES = Collections.singleton("recalculate");
    private static final Set<String> TEXT_FIELD_NAMES = new HashSet(Arrays.asList(new String[]{"mCollapsingTextHelper", "collapsingTextHelper"}));
    private final Rect mBounds;
    private final Object mCollapsingTextHelper;
    private final Method mRecalculateMethod;

    public IconTextInputLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public IconTextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconTextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Object internalObject = ClassUtils.getInternalObject(TextInputLayout.class, TEXT_FIELD_NAMES, this);
        this.mCollapsingTextHelper = internalObject;
        if (internalObject == null) {
            this.mBounds = null;
            this.mRecalculateMethod = null;
            return;
        }
        this.mBounds = (Rect) ClassUtils.getInternalObject(internalObject.getClass(), BOUNDS_FIELD_NAMES, internalObject);
        this.mRecalculateMethod = ClassUtils.findMethod(internalObject.getClass(), RECALCULATE_METHOD_NAMES);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        adjustBounds();
    }

    private void adjustBounds() {
        if (this.mCollapsingTextHelper != null && getEditText() != null) {
            try {
                this.mBounds.left = getEditText().getLeft() + getEditText().getPaddingLeft();
                this.mRecalculateMethod.invoke(this.mCollapsingTextHelper, new Object[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasObtainedCollapsingTextHelper() {
        return (this.mCollapsingTextHelper == null || this.mBounds == null || this.mRecalculateMethod == null) ? false : true;
    }
}
