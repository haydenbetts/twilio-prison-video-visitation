package com.urbanairship;

public interface LoggerListener {
    void onLog(int i, Throwable th, String str);
}
