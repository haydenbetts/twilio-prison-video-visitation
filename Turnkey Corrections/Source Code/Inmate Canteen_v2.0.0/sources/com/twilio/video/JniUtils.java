package com.twilio.video;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 5)
class JniUtils {
    private static native String nativeJavaUtf16StringToStdString(String str);

    JniUtils() {
    }

    static String javaUtf16StringToStdString(String str) {
        return nativeJavaUtf16StringToStdString(str);
    }
}
