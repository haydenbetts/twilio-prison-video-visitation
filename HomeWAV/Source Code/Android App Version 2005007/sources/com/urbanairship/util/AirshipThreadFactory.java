package com.urbanairship.util;

import android.net.TrafficStats;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AirshipThreadFactory implements ThreadFactory {
    public static final AirshipThreadFactory DEFAULT_THREAD_FACTORY = new AirshipThreadFactory("UrbanAirship");
    public static final int THREAD_STATS_TAG = 11797;
    private static final AtomicInteger count = new AtomicInteger(1);
    private final String threadNamePrefix;

    public AirshipThreadFactory(String str) {
        this.threadNamePrefix = str;
    }

    public Thread newThread(final Runnable runnable) {
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                TrafficStats.setThreadStatsTag(AirshipThreadFactory.THREAD_STATS_TAG);
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        };
        Thread thread = new Thread(r0, this.threadNamePrefix + "#" + count.getAndIncrement());
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
