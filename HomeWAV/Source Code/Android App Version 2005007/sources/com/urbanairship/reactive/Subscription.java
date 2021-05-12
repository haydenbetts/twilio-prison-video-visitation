package com.urbanairship.reactive;

public class Subscription {
    private boolean canceled = false;
    private Runnable runnable;

    protected Subscription() {
    }

    protected Subscription(Runnable runnable2) {
        this.runnable = runnable2;
    }

    public static Subscription create(Runnable runnable2) {
        return new Subscription(runnable2);
    }

    public static Subscription empty() {
        return new Subscription();
    }

    public synchronized void cancel() {
        Runnable runnable2 = this.runnable;
        if (runnable2 != null) {
            runnable2.run();
        }
        this.canceled = true;
    }

    public synchronized boolean isCancelled() {
        return this.canceled;
    }
}
