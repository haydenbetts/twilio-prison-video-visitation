package com.urbanairship.iam.banner;

import android.os.Handler;
import android.os.SystemClock;

public abstract class Timer {
    private long elapsedTimeMs;
    private final Handler handler = new Handler();
    /* access modifiers changed from: private */
    public boolean isStarted;
    private long remainingTimeMs;
    private long startTimeMs;
    private final Runnable trigger = new Runnable() {
        public void run() {
            if (Timer.this.isStarted) {
                Timer.this.stop();
                Timer.this.onFinish();
            }
        }
    };

    /* access modifiers changed from: protected */
    public abstract void onFinish();

    public Timer(long j) {
        this.remainingTimeMs = j;
    }

    public void start() {
        if (!this.isStarted) {
            this.isStarted = true;
            this.startTimeMs = SystemClock.elapsedRealtime();
            long j = this.remainingTimeMs;
            if (j > 0) {
                this.handler.postDelayed(this.trigger, j);
            } else {
                this.handler.post(this.trigger);
            }
        }
    }

    public void stop() {
        if (this.isStarted) {
            this.elapsedTimeMs = SystemClock.elapsedRealtime() - this.startTimeMs;
            this.isStarted = false;
            this.handler.removeCallbacks(this.trigger);
            this.remainingTimeMs = Math.max(0, this.remainingTimeMs - (SystemClock.elapsedRealtime() - this.startTimeMs));
        }
    }

    public long getRunTime() {
        if (this.isStarted) {
            return (this.elapsedTimeMs + SystemClock.elapsedRealtime()) - this.startTimeMs;
        }
        return this.elapsedTimeMs;
    }
}
