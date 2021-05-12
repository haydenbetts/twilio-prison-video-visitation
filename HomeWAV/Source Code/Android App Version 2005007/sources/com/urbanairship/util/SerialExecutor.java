package com.urbanairship.util;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    private final Executor executor;
    private boolean isExecuting = false;
    private final ArrayDeque<Runnable> runnables = new ArrayDeque<>();

    public SerialExecutor(Executor executor2) {
        this.executor = executor2;
    }

    public void execute(final Runnable runnable) {
        if (runnable != null) {
            AnonymousClass1 r0 = new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        SerialExecutor.this.next();
                    }
                }
            };
            synchronized (this.runnables) {
                this.runnables.offer(r0);
                if (!this.isExecuting) {
                    next();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void next() {
        synchronized (this.runnables) {
            Runnable pollFirst = this.runnables.pollFirst();
            if (pollFirst != null) {
                this.isExecuting = true;
                this.executor.execute(pollFirst);
            } else {
                this.isExecuting = false;
            }
        }
    }
}
