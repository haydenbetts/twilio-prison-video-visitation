package com.urbanairship.iam;

import android.content.Context;

public abstract class ForegroundDisplayAdapter implements InAppMessageAdapter {
    public boolean isReady(Context context) {
        return !InAppActivityMonitor.shared(context).getResumedActivities().isEmpty();
    }
}
