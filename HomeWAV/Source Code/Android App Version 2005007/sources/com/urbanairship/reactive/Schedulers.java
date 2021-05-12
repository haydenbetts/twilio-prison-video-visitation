package com.urbanairship.reactive;

import android.os.Handler;
import android.os.Looper;

public class Schedulers {
    private static LooperScheduler main;

    public static LooperScheduler looper(Looper looper) {
        return new LooperScheduler(looper);
    }

    public static LooperScheduler main() {
        if (main == null) {
            main = looper(Looper.getMainLooper());
        }
        return main;
    }

    public static class LooperScheduler implements Scheduler {
        private final Looper looper;

        public LooperScheduler(Looper looper2) {
            this.looper = looper2;
        }

        public Subscription schedule(final Runnable runnable) {
            final Subscription empty = Subscription.empty();
            new Handler(this.looper).post(new Runnable() {
                public void run() {
                    if (!empty.isCancelled()) {
                        runnable.run();
                    }
                }
            });
            return empty;
        }

        public Subscription schedule(long j, final Runnable runnable) {
            final Subscription empty = Subscription.empty();
            new Handler(this.looper).postDelayed(new Runnable() {
                public void run() {
                    if (!empty.isCancelled()) {
                        runnable.run();
                    }
                }
            }, j);
            return empty;
        }
    }
}
