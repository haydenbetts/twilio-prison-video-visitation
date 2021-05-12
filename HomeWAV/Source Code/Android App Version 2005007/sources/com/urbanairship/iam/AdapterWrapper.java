package com.urbanairship.iam;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.urbanairship.Logger;
import com.urbanairship.iam.assets.Assets;

final class AdapterWrapper {
    public final InAppMessageAdapter adapter;
    public final DisplayCoordinator coordinator;
    public boolean displayed = false;
    public final InAppMessage message;
    public final String scheduleId;

    static class DisplayException extends Exception {
        DisplayException(String str, Exception exc) {
            super(str, exc);
        }
    }

    AdapterWrapper(String str, InAppMessage inAppMessage, InAppMessageAdapter inAppMessageAdapter, DisplayCoordinator displayCoordinator) {
        this.scheduleId = str;
        this.message = inAppMessage;
        this.adapter = inAppMessageAdapter;
        this.coordinator = displayCoordinator;
    }

    /* access modifiers changed from: package-private */
    public int prepare(Context context, Assets assets) {
        try {
            Logger.debug("AdapterWrapper - Preparing message for schedule %s", this.scheduleId);
            return this.adapter.onPrepare(context, assets);
        } catch (Exception e) {
            Logger.error(e, "AdapterWrapper - Exception during prepare(Context).", new Object[0]);
            return 1;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isReady(Context context) {
        try {
            return this.adapter.isReady(context) && this.coordinator.isReady();
        } catch (Exception e) {
            Logger.error(e, "AdapterWrapper - Exception during isReady(Activity).", new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void display(Context context) throws DisplayException {
        Logger.debug("AdapterWrapper - Displaying message for schedule %s", this.scheduleId);
        this.displayed = true;
        try {
            this.adapter.onDisplay(context, new DisplayHandler(this.scheduleId));
            this.coordinator.onDisplayStarted(this.message);
        } catch (Exception e) {
            throw new DisplayException("Adapter onDisplay(Activity, boolean, DisplayHandler) unexpected exception", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void displayFinished() {
        Logger.debug("AdapterWrapper - Display finished for schedule %s", this.scheduleId);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    AdapterWrapper.this.coordinator.onDisplayFinished(AdapterWrapper.this.message);
                } catch (Exception e) {
                    Logger.error(e, "AdapterWrapper - Exception during onDisplayFinished().", new Object[0]);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void adapterFinished(Context context) {
        Logger.debug("AdapterWrapper - Adapter finished for schedule %s", this.scheduleId);
        try {
            this.adapter.onFinish(context);
        } catch (Exception e) {
            Logger.error(e, "AdapterWrapper - Exception during finish().", new Object[0]);
        }
    }
}
