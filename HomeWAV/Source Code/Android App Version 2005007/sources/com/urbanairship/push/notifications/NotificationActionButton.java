package com.urbanairship.push.notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.text.HtmlCompat;
import com.urbanairship.push.NotificationProxyActivity;
import com.urbanairship.push.NotificationProxyReceiver;
import com.urbanairship.push.PushManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationActionButton {
    private final String description;
    private final Bundle extras;
    private final int iconId;
    private final String id;
    private final boolean isForegroundAction;
    private final String label;
    private final int labelId;
    private final List<LocalizableRemoteInput> remoteInputs;

    private NotificationActionButton(Builder builder, Bundle bundle) {
        this.id = builder.buttonId;
        this.labelId = builder.labelId;
        this.label = builder.label;
        this.iconId = builder.iconId;
        this.description = builder.description;
        this.isForegroundAction = builder.isForegroundAction;
        this.remoteInputs = builder.remoteInputs;
        this.extras = bundle;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public String getLabel(Context context) {
        String str = this.label;
        if (str != null) {
            return str;
        }
        int i = this.labelId;
        if (i != 0) {
            return context.getString(i);
        }
        return null;
    }

    public int getIcon() {
        return this.iconId;
    }

    public boolean isForegroundAction() {
        return this.isForegroundAction;
    }

    public Bundle getExtras() {
        return new Bundle(this.extras);
    }

    public List<LocalizableRemoteInput> getRemoteInputs() {
        if (this.remoteInputs == null) {
            return null;
        }
        return new ArrayList(this.remoteInputs);
    }

    public NotificationCompat.Action createAndroidNotificationAction(Context context, String str, NotificationArguments notificationArguments) {
        PendingIntent pendingIntent;
        String label2 = getLabel(context);
        if (label2 == null) {
            label2 = "";
        }
        String str2 = this.description;
        if (str2 == null) {
            str2 = label2;
        }
        Intent putExtra = new Intent(PushManager.ACTION_NOTIFICATION_RESPONSE).addCategory(UUID.randomUUID().toString()).putExtra(PushManager.EXTRA_PUSH_MESSAGE_BUNDLE, notificationArguments.getMessage().getPushBundle()).putExtra(PushManager.EXTRA_NOTIFICATION_ID, notificationArguments.getNotificationId()).putExtra(PushManager.EXTRA_NOTIFICATION_TAG, notificationArguments.getNotificationTag()).putExtra(PushManager.EXTRA_NOTIFICATION_BUTTON_ID, this.id).putExtra(PushManager.EXTRA_NOTIFICATION_BUTTON_ACTIONS_PAYLOAD, str).putExtra(PushManager.EXTRA_NOTIFICATION_BUTTON_FOREGROUND, this.isForegroundAction).putExtra(PushManager.EXTRA_NOTIFICATION_ACTION_BUTTON_DESCRIPTION, str2);
        if (this.isForegroundAction) {
            putExtra.setClass(context, NotificationProxyActivity.class);
            pendingIntent = PendingIntent.getActivity(context, 0, putExtra, 0);
        } else {
            putExtra.setClass(context, NotificationProxyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(context, 0, putExtra, 0);
        }
        NotificationCompat.Action.Builder addExtras = new NotificationCompat.Action.Builder(this.iconId, (CharSequence) HtmlCompat.fromHtml(label2, 0), pendingIntent).addExtras(this.extras);
        List<LocalizableRemoteInput> list = this.remoteInputs;
        if (list != null) {
            for (LocalizableRemoteInput createRemoteInput : list) {
                addExtras.addRemoteInput(createRemoteInput.createRemoteInput(context));
            }
        }
        return addExtras.build();
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final String buttonId;
        /* access modifiers changed from: private */
        public String description;
        private List<NotificationCompat.Action.Extender> extenders;
        /* access modifiers changed from: private */
        public int iconId = 0;
        /* access modifiers changed from: private */
        public boolean isForegroundAction = true;
        /* access modifiers changed from: private */
        public String label;
        /* access modifiers changed from: private */
        public int labelId = 0;
        /* access modifiers changed from: private */
        public List<LocalizableRemoteInput> remoteInputs;

        public Builder(String str) {
            this.buttonId = str;
        }

        public Builder setLabel(int i) {
            this.labelId = i;
            this.label = null;
            return this;
        }

        public Builder setLabel(String str) {
            this.labelId = 0;
            this.label = str;
            return this;
        }

        public Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder setIcon(int i) {
            this.iconId = i;
            return this;
        }

        public Builder setPerformsInForeground(boolean z) {
            this.isForegroundAction = z;
            return this;
        }

        public Builder addRemoteInput(LocalizableRemoteInput localizableRemoteInput) {
            if (this.remoteInputs == null) {
                this.remoteInputs = new ArrayList();
            }
            this.remoteInputs.add(localizableRemoteInput);
            return this;
        }

        public Builder extend(NotificationCompat.Action.Extender extender) {
            if (this.extenders == null) {
                this.extenders = new ArrayList();
            }
            this.extenders.add(extender);
            return this;
        }

        public NotificationActionButton build() {
            Bundle bundle;
            if (this.extenders != null) {
                NotificationCompat.Action.Builder builder = new NotificationCompat.Action.Builder(this.iconId, (CharSequence) null, (PendingIntent) null);
                for (NotificationCompat.Action.Extender extend : this.extenders) {
                    builder.extend(extend);
                }
                bundle = builder.build().getExtras();
            } else {
                bundle = new Bundle();
            }
            return new NotificationActionButton(this, bundle);
        }
    }
}
