package org.java_websocket.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String threadPrefix;

    public NamedThreadFactory(String str) {
        this.threadPrefix = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.defaultThreadFactory.newThread(runnable);
        newThread.setName(this.threadPrefix + "-" + this.threadNumber);
        return newThread;
    }
}
