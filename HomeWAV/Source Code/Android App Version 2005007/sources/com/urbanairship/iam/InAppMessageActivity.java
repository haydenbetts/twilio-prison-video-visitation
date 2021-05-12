package com.urbanairship.iam;

import android.os.Bundle;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.activity.ThemedActivity;
import com.urbanairship.iam.assets.Assets;

public abstract class InAppMessageActivity extends ThemedActivity {
    public static final String DISPLAY_HANDLER_EXTRA_KEY = "display_handler";
    private static final String DISPLAY_TIME_KEY = "display_time";
    public static final String IN_APP_ASSETS = "assets";
    public static final String IN_APP_MESSAGE_KEY = "in_app_message";
    private Assets assets;
    private DisplayHandler displayHandler;
    private long displayTime = 0;
    private InAppMessage inAppMessage;
    private long resumeTime = 0;

    /* access modifiers changed from: protected */
    public abstract void onCreateMessage(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Autopilot.automaticTakeOff(getApplicationContext());
        super.onCreate(bundle);
        if (getIntent() == null || getIntent().getExtras() == null) {
            finish();
            return;
        }
        this.displayHandler = (DisplayHandler) getIntent().getParcelableExtra(DISPLAY_HANDLER_EXTRA_KEY);
        this.inAppMessage = (InAppMessage) getIntent().getParcelableExtra("in_app_message");
        this.assets = (Assets) getIntent().getParcelableExtra(IN_APP_ASSETS);
        DisplayHandler displayHandler2 = this.displayHandler;
        if (displayHandler2 == null || this.inAppMessage == null) {
            Logger.error("%s unable to show message. Missing display handler or in-app message", getClass());
            finish();
        } else if (!displayHandler2.isDisplayAllowed(this)) {
            finish();
        } else {
            if (bundle != null) {
                this.displayTime = bundle.getLong(DISPLAY_TIME_KEY, 0);
            }
            onCreateMessage(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (!this.displayHandler.isDisplayAllowed(this)) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong(DISPLAY_TIME_KEY, this.displayTime);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.resumeTime = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.displayTime += System.currentTimeMillis() - this.resumeTime;
        this.resumeTime = 0;
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.displayHandler.finished(ResolutionInfo.dismissed(), getDisplayTime());
        finish();
    }

    /* access modifiers changed from: protected */
    public long getDisplayTime() {
        long j = this.displayTime;
        return this.resumeTime > 0 ? j + (System.currentTimeMillis() - this.resumeTime) : j;
    }

    /* access modifiers changed from: protected */
    public InAppMessage getMessage() {
        return this.inAppMessage;
    }

    /* access modifiers changed from: protected */
    public DisplayHandler getDisplayHandler() {
        return this.displayHandler;
    }

    /* access modifiers changed from: protected */
    public Assets getMessageAssets() {
        return this.assets;
    }
}
