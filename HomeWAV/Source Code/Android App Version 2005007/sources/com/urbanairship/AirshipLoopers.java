package com.urbanairship;

import android.os.Looper;
import com.urbanairship.util.AirshipHandlerThread;

public class AirshipLoopers {
    private static Looper backgroundLooper;

    public static Looper getBackgroundLooper() {
        if (backgroundLooper == null) {
            synchronized (AirshipLoopers.class) {
                if (backgroundLooper == null) {
                    AirshipHandlerThread airshipHandlerThread = new AirshipHandlerThread("background");
                    airshipHandlerThread.start();
                    backgroundLooper = airshipHandlerThread.getLooper();
                }
            }
        }
        return backgroundLooper;
    }
}
