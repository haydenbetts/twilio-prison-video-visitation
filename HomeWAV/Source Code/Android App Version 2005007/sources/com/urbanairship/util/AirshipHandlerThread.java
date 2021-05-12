package com.urbanairship.util;

import android.net.TrafficStats;
import android.os.HandlerThread;

public class AirshipHandlerThread extends HandlerThread {
    public AirshipHandlerThread(String str) {
        super(str);
    }

    public void run() {
        TrafficStats.setThreadStatsTag(AirshipThreadFactory.THREAD_STATS_TAG);
        super.run();
    }
}
