package com.stripe.android.model;

import android.text.TextUtils;
import java.util.Calendar;
import java.util.Locale;

class ModelUtils {
    ModelUtils() {
    }

    static boolean isWholePositiveNumber(String str) {
        return str != null && TextUtils.isDigitsOnly(str);
    }

    static boolean hasMonthPassed(int i, int i2, Calendar calendar) {
        if (hasYearPassed(i, calendar)) {
            return true;
        }
        if (normalizeYear(i, calendar) != calendar.get(1) || i2 >= calendar.get(2) + 1) {
            return false;
        }
        return true;
    }

    static boolean hasYearPassed(int i, Calendar calendar) {
        if (normalizeYear(i, calendar) < calendar.get(1)) {
            return true;
        }
        return false;
    }

    static int normalizeYear(int i, Calendar calendar) {
        if (i >= 100 || i < 0) {
            return i;
        }
        String valueOf = String.valueOf(calendar.get(1));
        String substring = valueOf.substring(0, valueOf.length() - 2);
        return Integer.parseInt(String.format(Locale.US, "%s%02d", new Object[]{substring, Integer.valueOf(i)}));
    }
}
