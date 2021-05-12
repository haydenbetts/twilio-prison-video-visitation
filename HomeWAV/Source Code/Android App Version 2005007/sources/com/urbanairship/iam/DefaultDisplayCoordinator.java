package com.urbanairship.iam;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.TimeUnit;

class DefaultDisplayCoordinator extends DisplayCoordinator {
    /* access modifiers changed from: private */
    public InAppMessage currentMessage = null;
    private long displayInterval;
    /* access modifiers changed from: private */
    public boolean isLocked = false;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable postDisplayRunnable = new Runnable() {
        public void run() {
            if (DefaultDisplayCoordinator.this.currentMessage == null) {
                boolean unused = DefaultDisplayCoordinator.this.isLocked = false;
                DefaultDisplayCoordinator.this.notifyDisplayReady();
            }
        }
    };

    DefaultDisplayCoordinator(long j) {
        this.displayInterval = j;
    }

    /* access modifiers changed from: package-private */
    public void setDisplayInterval(long j, TimeUnit timeUnit) {
        this.displayInterval = timeUnit.toMillis(j);
    }

    /* access modifiers changed from: package-private */
    public long getDisplayInterval() {
        return this.displayInterval;
    }

    public boolean isReady() {
        if (this.currentMessage != null) {
            return false;
        }
        return !this.isLocked;
    }

    public void onDisplayStarted(InAppMessage inAppMessage) {
        this.currentMessage = inAppMessage;
        this.isLocked = true;
        this.mainHandler.removeCallbacks(this.postDisplayRunnable);
    }

    public void onDisplayFinished(InAppMessage inAppMessage) {
        this.currentMessage = null;
        this.mainHandler.postDelayed(this.postDisplayRunnable, this.displayInterval);
    }
}
