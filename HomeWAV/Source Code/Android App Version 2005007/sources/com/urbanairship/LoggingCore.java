package com.urbanairship;

import android.util.Log;
import com.urbanairship.util.UAStringUtil;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoggingCore {
    private boolean isDefaultLoggerEnabled = true;
    private final List<LoggerListener> listeners = new CopyOnWriteArrayList();
    private int logLevel;
    private String logTag;

    public LoggingCore(int i, String str) {
        this.logLevel = i;
        this.logTag = str;
    }

    public void setTag(String str) {
        this.logTag = str;
    }

    public void setDefaultLoggerEnabled(boolean z) {
        this.isDefaultLoggerEnabled = z;
    }

    public void addListener(LoggerListener loggerListener) {
        this.listeners.add(loggerListener);
    }

    public void removeListener(LoggerListener loggerListener) {
        this.listeners.remove(loggerListener);
    }

    public void log(int i, Throwable th, String str, Object... objArr) {
        if (this.logLevel <= i) {
            if (str != null || th != null) {
                if (UAStringUtil.isEmpty(str)) {
                    str = "";
                } else if (!(objArr == null || objArr.length == 0)) {
                    str = String.format(Locale.ROOT, str, objArr);
                }
                for (LoggerListener onLog : this.listeners) {
                    onLog.onLog(i, th, str);
                }
                if (!this.isDefaultLoggerEnabled) {
                    return;
                }
                if (th != null) {
                    switch (i) {
                        case 2:
                            Log.v(this.logTag, str, th);
                            return;
                        case 3:
                            Log.d(this.logTag, str, th);
                            return;
                        case 4:
                            Log.i(this.logTag, str, th);
                            return;
                        case 5:
                            Log.w(this.logTag, str, th);
                            return;
                        case 6:
                            Log.e(this.logTag, str, th);
                            return;
                        case 7:
                            Log.wtf(this.logTag, str, th);
                            return;
                        default:
                            return;
                    }
                } else if (i == 7) {
                    Log.wtf(this.logTag, str);
                } else {
                    Log.println(i, this.logTag, str);
                }
            }
        }
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    public int getLogLevel() {
        return this.logLevel;
    }
}
