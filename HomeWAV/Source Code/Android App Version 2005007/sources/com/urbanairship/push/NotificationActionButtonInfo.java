package com.urbanairship.push;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.RemoteInput;

public class NotificationActionButtonInfo {
    private final String buttonId;
    private final String description;
    private final boolean isForeground;
    private final Bundle remoteInput;

    public NotificationActionButtonInfo(String str, boolean z, Bundle bundle, String str2) {
        this.buttonId = str;
        this.isForeground = z;
        this.remoteInput = bundle;
        this.description = str2;
    }

    static NotificationActionButtonInfo fromIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(PushManager.EXTRA_NOTIFICATION_BUTTON_ID);
        if (stringExtra == null) {
            return null;
        }
        return new NotificationActionButtonInfo(stringExtra, intent.getBooleanExtra(PushManager.EXTRA_NOTIFICATION_BUTTON_FOREGROUND, true), RemoteInput.getResultsFromIntent(intent), intent.getStringExtra(PushManager.EXTRA_NOTIFICATION_ACTION_BUTTON_DESCRIPTION));
    }

    public String getButtonId() {
        return this.buttonId;
    }

    public boolean isForeground() {
        return this.isForeground;
    }

    public Bundle getRemoteInput() {
        return this.remoteInput;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "NotificationActionButtonInfo{buttonId='" + this.buttonId + '\'' + ", isForeground=" + this.isForeground + ", remoteInput=" + this.remoteInput + ", description='" + this.description + '\'' + '}';
    }
}
