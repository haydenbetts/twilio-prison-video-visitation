package com.stripe.android.view;

import java.util.Calendar;

class DateUtils {
    static final int MAX_VALID_YEAR = 9980;

    DateUtils() {
    }

    static boolean isValidMonth(String str) {
        if (str == null) {
            return false;
        }
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt > 0 && parseInt <= 12;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    static String[] separateDateStringParts(String str) {
        String[] strArr = new String[2];
        if (str.length() >= 2) {
            strArr[0] = str.substring(0, 2);
            strArr[1] = str.substring(2);
        } else {
            strArr[0] = str;
            strArr[1] = "";
        }
        return strArr;
    }

    static boolean isExpiryDataValid(int i, int i2) {
        return isExpiryDataValid(i, i2, Calendar.getInstance());
    }

    static boolean isExpiryDataValid(int i, int i2, Calendar calendar) {
        int i3;
        if (i < 1 || i > 12 || i2 < 0 || i2 > MAX_VALID_YEAR || i2 < (i3 = calendar.get(1))) {
            return false;
        }
        if (i2 <= i3 && i < calendar.get(2) + 1) {
            return false;
        }
        return true;
    }

    static String createDateStringFromIntegerInput(int i, int i2) {
        String valueOf = String.valueOf(i);
        if (valueOf.length() == 1) {
            valueOf = "0" + valueOf;
        }
        String valueOf2 = String.valueOf(i2);
        if (valueOf2.length() == 3) {
            return "";
        }
        if (valueOf2.length() > 2) {
            valueOf2 = valueOf2.substring(valueOf2.length() - 2);
        } else if (valueOf2.length() == 1) {
            valueOf2 = "0" + valueOf2;
        }
        return valueOf + valueOf2;
    }

    static int convertTwoDigitYearToFour(int i) {
        return convertTwoDigitYearToFour(i, Calendar.getInstance());
    }

    static int convertTwoDigitYearToFour(int i, Calendar calendar) {
        int i2 = calendar.get(1);
        int i3 = i2 / 100;
        int i4 = i2 % 100;
        if (i4 > 80 && i < 20) {
            i3++;
        } else if (i4 < 20 && i > 80) {
            i3--;
        }
        return (i3 * 100) + i;
    }
}
