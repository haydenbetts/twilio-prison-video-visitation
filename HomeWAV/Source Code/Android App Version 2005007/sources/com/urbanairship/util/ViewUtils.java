package com.urbanairship.util;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

public final class ViewUtils {
    public static void applyTextStyle(Context context, TextView textView, int i) {
        if (i == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(context, i);
        }
    }
}
