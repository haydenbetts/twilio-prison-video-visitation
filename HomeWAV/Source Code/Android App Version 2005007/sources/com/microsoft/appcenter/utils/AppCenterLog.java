package com.microsoft.appcenter.utils;

import android.util.Log;

public class AppCenterLog {
    public static final String LOG_TAG = "AppCenter";
    public static final int NONE = 8;
    private static int sLogLevel = 7;

    public static int getLogLevel() {
        return sLogLevel;
    }

    public static void setLogLevel(int i) {
        sLogLevel = i;
    }

    public static void verbose(String str, String str2) {
        if (sLogLevel <= 2) {
            Log.v(str, str2);
        }
    }

    public static void verbose(String str, String str2, Throwable th) {
        if (sLogLevel <= 2) {
            Log.v(str, str2, th);
        }
    }

    public static void debug(String str, String str2) {
        if (sLogLevel <= 3) {
            Log.d(str, str2);
        }
    }

    public static void debug(String str, String str2, Throwable th) {
        if (sLogLevel <= 3) {
            Log.d(str, str2, th);
        }
    }

    public static void info(String str, String str2) {
        if (sLogLevel <= 4) {
            Log.i(str, str2);
        }
    }

    public static void info(String str, String str2, Throwable th) {
        if (sLogLevel <= 4) {
            Log.i(str, str2, th);
        }
    }

    public static void warn(String str, String str2) {
        if (sLogLevel <= 5) {
            Log.w(str, str2);
        }
    }

    public static void warn(String str, String str2, Throwable th) {
        if (sLogLevel <= 5) {
            Log.w(str, str2, th);
        }
    }

    public static void error(String str, String str2) {
        if (sLogLevel <= 6) {
            Log.e(str, str2);
        }
    }

    public static void error(String str, String str2, Throwable th) {
        if (sLogLevel <= 6) {
            Log.e(str, str2, th);
        }
    }

    public static void logAssert(String str, String str2) {
        if (sLogLevel <= 7) {
            Log.println(7, str, str2);
        }
    }

    public static void logAssert(String str, String str2, Throwable th) {
        if (sLogLevel <= 7) {
            Log.println(7, str, str2 + "\n" + Log.getStackTraceString(th));
        }
    }
}
