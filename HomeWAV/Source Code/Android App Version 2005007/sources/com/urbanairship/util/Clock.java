package com.urbanairship.util;

import android.os.SystemClock;

public class Clock {
    public static final Clock DEFAULT_CLOCK = new Clock();

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
