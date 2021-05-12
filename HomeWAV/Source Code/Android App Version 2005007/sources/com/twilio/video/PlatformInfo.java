package com.twilio.video;

import android.os.Build;

final class PlatformInfo {
    private static final String PLATFORM_NAME = "Android";
    private static long nativeHandle;

    private static native long nativeCreate(String str, String str2, String str3, String str4, String str5, String str6);

    private static native void nativeRelease(long j);

    private PlatformInfo() {
    }

    static synchronized long getNativeHandle() {
        long j;
        synchronized (PlatformInfo.class) {
            if (nativeHandle == 0) {
                nativeHandle = nativeCreate(PLATFORM_NAME, Build.VERSION.RELEASE, Build.MANUFACTURER, Build.MODEL, Video.getVersion(), System.getProperty("os.arch"));
            }
            j = nativeHandle;
        }
        return j;
    }

    static synchronized void release() {
        synchronized (PlatformInfo.class) {
            long j = nativeHandle;
            if (j != 0) {
                nativeRelease(j);
                nativeHandle = 0;
            }
        }
    }
}
