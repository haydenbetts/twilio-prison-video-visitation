package com.forasoft.homewavvisitor.presentation.extensions;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u000e\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0007"}, d2 = {"dpToPx", "", "dp", "pxToDp", "px", "pxToSp", "", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: Dimensions.kt */
public final class DimensionsKt {
    public static final int pxToDp(int i) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return (int) (system.getDisplayMetrics().density * ((float) i));
    }

    public static final int dpToPx(int i) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return (int) (((float) i) * (((float) system.getDisplayMetrics().densityDpi) / ((float) 160)));
    }

    public static final float pxToSp(int i) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return system.getDisplayMetrics().scaledDensity * ((float) i);
    }
}
