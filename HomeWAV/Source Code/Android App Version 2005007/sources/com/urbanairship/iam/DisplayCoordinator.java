package com.urbanairship.iam;

public abstract class DisplayCoordinator {
    private OnDisplayReadyCallback displayReadyCallback;

    interface OnDisplayReadyCallback {
        void onReady();
    }

    public abstract boolean isReady();

    public abstract void onDisplayFinished(InAppMessage inAppMessage);

    public abstract void onDisplayStarted(InAppMessage inAppMessage);

    /* access modifiers changed from: package-private */
    public void setDisplayReadyCallback(OnDisplayReadyCallback onDisplayReadyCallback) {
        this.displayReadyCallback = onDisplayReadyCallback;
    }

    public final void notifyDisplayReady() {
        OnDisplayReadyCallback onDisplayReadyCallback = this.displayReadyCallback;
        if (onDisplayReadyCallback != null) {
            onDisplayReadyCallback.onReady();
        }
    }
}
