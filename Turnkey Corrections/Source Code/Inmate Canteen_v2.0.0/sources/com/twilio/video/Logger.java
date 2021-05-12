package com.twilio.video;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

class Logger {
    public static final int INHERIT = 8;
    private static int globalLevel = 6;
    private static final Map<Class<?>, Logger> loggers = new HashMap();
    private int level = 8;
    private final String name;

    public static Logger getLogger(Class<?> cls) {
        if (!loggers.containsKey(cls)) {
            synchronized (loggers) {
                if (!loggers.containsKey(cls)) {
                    loggers.put(cls, new Logger(cls.getSimpleName()));
                }
            }
        }
        return loggers.get(cls);
    }

    public static void setLogLevel(int i) {
        globalLevel = i;
    }

    public static int getLogLevel() {
        return globalLevel;
    }

    private Logger(String str) {
        this.name = str;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public boolean isVerboseEnabled() {
        int i = this.level;
        return i <= 2 || (i == 8 && globalLevel <= 2);
    }

    public boolean isDebugEnabled() {
        int i = this.level;
        return i <= 3 || (i == 8 && globalLevel <= 3);
    }

    public boolean isInfoEnabled() {
        int i = this.level;
        return i <= 4 || (i == 8 && globalLevel <= 4);
    }

    public boolean isWarnEnabled() {
        int i = this.level;
        return i <= 5 || (i == 8 && globalLevel <= 5);
    }

    public boolean isErrorEnabled() {
        int i = this.level;
        return i <= 6 || (i == 8 && globalLevel <= 6);
    }

    public void v(String str, Throwable th) {
        if (isVerboseEnabled()) {
            Log.v(this.name, str, th);
        }
    }

    public void v(String str) {
        if (isVerboseEnabled()) {
            Log.v(this.name, str);
        }
    }

    public void d(String str, Throwable th) {
        if (isDebugEnabled()) {
            Log.d(this.name, str, th);
        }
    }

    public void d(String str) {
        if (isDebugEnabled()) {
            Log.d(this.name, str);
        }
    }

    public void i(String str, Throwable th) {
        if (isInfoEnabled()) {
            Log.i(this.name, str, th);
        }
    }

    public void i(String str) {
        if (isInfoEnabled()) {
            Log.i(this.name, str);
        }
    }

    public void w(String str, Throwable th) {
        if (isWarnEnabled()) {
            Log.w(this.name, str, th);
        }
    }

    public void w(String str) {
        if (isWarnEnabled()) {
            Log.w(this.name, str);
        }
    }

    public void e(String str, Throwable th) {
        if (isErrorEnabled()) {
            Log.e(this.name, str, th);
        }
    }

    public void e(String str) {
        if (isErrorEnabled()) {
            Log.e(this.name, str);
        }
    }
}
