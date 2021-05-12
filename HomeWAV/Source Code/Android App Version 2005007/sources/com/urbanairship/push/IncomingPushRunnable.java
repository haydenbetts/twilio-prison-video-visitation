package com.urbanairship.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionRunRequest;
import com.urbanairship.actions.ActionValue;
import com.urbanairship.analytics.PushArrivedEvent;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonMap;
import com.urbanairship.push.notifications.NotificationArguments;
import com.urbanairship.push.notifications.NotificationChannelCompat;
import com.urbanairship.push.notifications.NotificationChannelUtils;
import com.urbanairship.push.notifications.NotificationProvider;
import com.urbanairship.push.notifications.NotificationResult;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ManifestUtils;
import java.util.Map;
import java.util.UUID;

class IncomingPushRunnable implements Runnable {
    private static final long AIRSHIP_WAIT_TIME_MS = 5000;
    private static final long LONG_AIRSHIP_WAIT_TIME_MS = 10000;
    private final Context context;
    private final boolean isLongRunning;
    private final boolean isProcessed;
    private final JobDispatcher jobDispatcher;
    private final PushMessage message;
    private final NotificationManagerCompat notificationManager;
    private final String providerClass;

    private IncomingPushRunnable(Builder builder) {
        Context access$000 = builder.context;
        this.context = access$000;
        this.message = builder.message;
        this.providerClass = builder.providerClass;
        this.isLongRunning = builder.isLongRunning;
        this.isProcessed = builder.isProcessed;
        this.notificationManager = builder.notificationManager == null ? NotificationManagerCompat.from(access$000) : builder.notificationManager;
        this.jobDispatcher = builder.jobDispatcher == null ? JobDispatcher.shared(access$000) : builder.jobDispatcher;
    }

    public void run() {
        Autopilot.automaticTakeOff(this.context);
        UAirship waitForTakeOff = UAirship.waitForTakeOff(this.isLongRunning ? 10000 : 5000);
        if (waitForTakeOff == null) {
            Logger.error("Unable to process push, Airship is not ready. Make sure takeOff is called by either using autopilot or by calling takeOff in the application's onCreate method.", new Object[0]);
        } else if (!this.message.isAccengagePush() && !this.message.isAirshipPush()) {
            Logger.debug("Ignoring push: %s", this.message);
        } else if (!checkProvider(waitForTakeOff, this.providerClass)) {
        } else {
            if (this.isProcessed) {
                postProcessPush(waitForTakeOff);
            } else {
                processPush(waitForTakeOff);
            }
        }
    }

    private void processPush(UAirship uAirship) {
        Logger.info("Processing push: %s", this.message);
        if (!uAirship.getPushManager().isPushEnabled()) {
            Logger.debug("Push disabled, ignoring message", new Object[0]);
        } else if (!uAirship.getPushManager().isComponentEnabled()) {
            Logger.debug("PushManager component is disabled, ignoring message.", new Object[0]);
        } else if (!uAirship.getPushManager().isUniqueCanonicalId(this.message.getCanonicalPushId())) {
            Logger.debug("Received a duplicate push with canonical ID: %s", this.message.getCanonicalPushId());
        } else if (this.message.isExpired()) {
            Logger.debug("Received expired push message, ignoring.", new Object[0]);
        } else if (this.message.isPing()) {
            Logger.verbose("PushJobHandler - Received UA Ping", new Object[0]);
        } else {
            runActions();
            uAirship.getPushManager().setLastReceivedMetadata(this.message.getMetadata());
            postProcessPush(uAirship);
        }
    }

    private void postProcessPush(UAirship uAirship) {
        NotificationResult notificationResult;
        if (!uAirship.getPushManager().isOptIn()) {
            Logger.info("User notifications opted out. Unable to display notification for message: %s", this.message);
            notifyPushReceived(uAirship, false);
            uAirship.getAnalytics().addEvent(new PushArrivedEvent(this.message));
            return;
        }
        NotificationProvider notificationProvider = getNotificationProvider(uAirship);
        if (notificationProvider == null) {
            Logger.error("NotificationProvider is null. Unable to display notification for message: %s", this.message);
            notifyPushReceived(uAirship, false);
            uAirship.getAnalytics().addEvent(new PushArrivedEvent(this.message));
            return;
        }
        try {
            NotificationArguments onCreateNotificationArguments = notificationProvider.onCreateNotificationArguments(this.context, this.message);
            if (this.isLongRunning || !onCreateNotificationArguments.getRequiresLongRunningTask()) {
                try {
                    notificationResult = notificationProvider.onCreateNotification(this.context, onCreateNotificationArguments);
                } catch (Exception e) {
                    Logger.error(e, "Cancelling notification display to create and display notification.", new Object[0]);
                    notificationResult = NotificationResult.cancel();
                }
                Logger.debug("IncomingPushRunnable - Received result status %s for push message: %s", Integer.valueOf(notificationResult.getStatus()), this.message);
                int status = notificationResult.getStatus();
                if (status == 0) {
                    Notification notification = notificationResult.getNotification();
                    Checks.checkNotNull(notification, "Invalid notification result. Missing notification.");
                    NotificationChannelCompat notificationChannel = getNotificationChannel(uAirship, notification, onCreateNotificationArguments);
                    if (Build.VERSION.SDK_INT < 26) {
                        if (notificationChannel != null) {
                            NotificationChannelUtils.applyLegacySettings(notification, notificationChannel);
                        } else {
                            applyDeprecatedSettings(uAirship, notification);
                        }
                    }
                    notificationProvider.onNotificationCreated(this.context, notification, onCreateNotificationArguments);
                    boolean postNotification = postNotification(notification, onCreateNotificationArguments);
                    uAirship.getAnalytics().addEvent(new PushArrivedEvent(this.message, notificationChannel));
                    if (postNotification) {
                        notifyPushReceived(uAirship, true);
                        notifyNotificationPosted(uAirship, onCreateNotificationArguments);
                        return;
                    }
                    notifyPushReceived(uAirship, false);
                } else if (status == 1) {
                    Logger.debug("Scheduling notification to be retried for a later time: %s", this.message);
                    reschedulePush(this.message);
                } else if (status == 2) {
                    uAirship.getAnalytics().addEvent(new PushArrivedEvent(this.message));
                    notifyPushReceived(uAirship, false);
                }
            } else {
                Logger.debug("Push requires a long running task. Scheduled for a later time: %s", this.message);
                reschedulePush(this.message);
            }
        } catch (Exception e2) {
            Logger.error(e2, "Failed to generate notification arguments for message. Skipping.", new Object[0]);
            notifyPushReceived(uAirship, false);
            uAirship.getAnalytics().addEvent(new PushArrivedEvent(this.message));
        }
    }

    private NotificationProvider getNotificationProvider(UAirship uAirship) {
        if (!this.message.isAccengagePush()) {
            return uAirship.getPushManager().getNotificationProvider();
        }
        if (uAirship.getAccengageNotificationHandler() != null) {
            return uAirship.getAccengageNotificationHandler().getNotificationProvider();
        }
        return null;
    }

    private void applyDeprecatedSettings(UAirship uAirship, Notification notification) {
        if (!uAirship.getPushManager().isVibrateEnabled() || uAirship.getPushManager().isInQuietTime()) {
            notification.vibrate = null;
            notification.defaults &= -3;
        }
        if (!uAirship.getPushManager().isSoundEnabled() || uAirship.getPushManager().isInQuietTime()) {
            notification.sound = null;
            notification.defaults &= -2;
        }
    }

    private NotificationChannelCompat getNotificationChannel(UAirship uAirship, Notification notification, NotificationArguments notificationArguments) {
        String str;
        if (Build.VERSION.SDK_INT >= 26) {
            str = NotificationCompat.getChannelId(notification);
        } else {
            str = notificationArguments.getNotificationChannelId();
        }
        return uAirship.getPushManager().getNotificationChannelRegistry().getNotificationChannelSync(str);
    }

    private boolean postNotification(Notification notification, NotificationArguments notificationArguments) {
        String notificationTag = notificationArguments.getNotificationTag();
        int notificationId = notificationArguments.getNotificationId();
        Intent putExtra = new Intent(this.context, NotificationProxyActivity.class).setAction(PushManager.ACTION_NOTIFICATION_RESPONSE).addCategory(UUID.randomUUID().toString()).putExtra(PushManager.EXTRA_PUSH_MESSAGE_BUNDLE, notificationArguments.getMessage().getPushBundle()).addFlags(268435456).putExtra(PushManager.EXTRA_NOTIFICATION_ID, notificationArguments.getNotificationId()).putExtra(PushManager.EXTRA_NOTIFICATION_TAG, notificationArguments.getNotificationTag());
        if (notification.contentIntent != null) {
            putExtra.putExtra(PushManager.EXTRA_NOTIFICATION_CONTENT_INTENT, notification.contentIntent);
        }
        Intent putExtra2 = new Intent(this.context, NotificationProxyReceiver.class).setAction(PushManager.ACTION_NOTIFICATION_DISMISSED).addCategory(UUID.randomUUID().toString()).putExtra(PushManager.EXTRA_PUSH_MESSAGE_BUNDLE, notificationArguments.getMessage().getPushBundle()).putExtra(PushManager.EXTRA_NOTIFICATION_ID, notificationArguments.getNotificationId()).putExtra(PushManager.EXTRA_NOTIFICATION_TAG, notificationArguments.getNotificationTag());
        if (notification.deleteIntent != null) {
            putExtra2.putExtra(PushManager.EXTRA_NOTIFICATION_DELETE_INTENT, notification.deleteIntent);
        }
        notification.contentIntent = PendingIntent.getActivity(this.context, 0, putExtra, 0);
        notification.deleteIntent = PendingIntent.getBroadcast(this.context, 0, putExtra2, 0);
        Logger.info("Posting notification: %s id: %s tag: %s", notification, Integer.valueOf(notificationId), notificationTag);
        try {
            this.notificationManager.notify(notificationTag, notificationId, notification);
            return true;
        } catch (Exception e) {
            Logger.error(e, "Failed to post notification.", new Object[0]);
            return false;
        }
    }

    private void notifyPushReceived(UAirship uAirship, boolean z) {
        for (PushListener onPushReceived : uAirship.getPushManager().getPushListeners()) {
            onPushReceived.onPushReceived(this.message, z);
        }
    }

    private void notifyNotificationPosted(UAirship uAirship, NotificationArguments notificationArguments) {
        NotificationListener notificationListener = uAirship.getPushManager().getNotificationListener();
        if (notificationListener != null) {
            notificationListener.onNotificationPosted(new NotificationInfo(notificationArguments.getMessage(), notificationArguments.getNotificationId(), notificationArguments.getNotificationTag()));
        }
    }

    private void runActions() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionArguments.PUSH_MESSAGE_METADATA, this.message);
        for (Map.Entry next : this.message.getActions().entrySet()) {
            ActionRunRequest.createRequest((String) next.getKey()).setMetadata(bundle).setValue((ActionValue) next.getValue()).setSituation(1).run();
        }
    }

    private boolean checkProvider(UAirship uAirship, String str) {
        PushProvider pushProvider = uAirship.getPushManager().getPushProvider();
        if (pushProvider == null || !pushProvider.getClass().toString().equals(str)) {
            Logger.error("Received message callback from unexpected provider %s. Ignoring.", str);
            return false;
        } else if (!pushProvider.isAvailable(this.context)) {
            Logger.error("Received message callback when provider is unavailable. Ignoring.", new Object[0]);
            return false;
        } else if (uAirship.getPushManager().isPushAvailable() && uAirship.getPushManager().isPushEnabled()) {
            return true;
        } else {
            Logger.error("Received message when push is disabled. Ignoring.", new Object[0]);
            return false;
        }
    }

    private void reschedulePush(PushMessage pushMessage) {
        if (!ManifestUtils.isPermissionGranted("android.permission.RECEIVE_BOOT_COMPLETED")) {
            Logger.error("Notification factory requested long running task but the application does not define RECEIVE_BOOT_COMPLETED in the manifest. Notification will be lost if the device reboots before the notification is processed.", new Object[0]);
        }
        this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction("ACTION_DISPLAY_NOTIFICATION").generateUniqueId(this.context).setAirshipComponent((Class<? extends AirshipComponent>) PushManager.class).setPersistent(true).setExtras(JsonMap.newBuilder().putOpt("EXTRA_PUSH", pushMessage).put("EXTRA_PROVIDER_CLASS", this.providerClass).build()).build());
    }

    static class Builder {
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean isLongRunning;
        /* access modifiers changed from: private */
        public boolean isProcessed;
        /* access modifiers changed from: private */
        public JobDispatcher jobDispatcher;
        /* access modifiers changed from: private */
        public PushMessage message;
        /* access modifiers changed from: private */
        public NotificationManagerCompat notificationManager;
        /* access modifiers changed from: private */
        public String providerClass;

        Builder(Context context2) {
            this.context = context2.getApplicationContext();
        }

        /* access modifiers changed from: package-private */
        public Builder setMessage(PushMessage pushMessage) {
            this.message = pushMessage;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setProviderClass(String str) {
            this.providerClass = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setLongRunning(boolean z) {
            this.isLongRunning = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setProcessed(boolean z) {
            this.isProcessed = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setNotificationManager(NotificationManagerCompat notificationManagerCompat) {
            this.notificationManager = notificationManagerCompat;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setJobDispatcher(JobDispatcher jobDispatcher2) {
            this.jobDispatcher = jobDispatcher2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public IncomingPushRunnable build() {
            Checks.checkNotNull(this.providerClass, "Provider class missing");
            Checks.checkNotNull(this.message, "Push Message missing");
            return new IncomingPushRunnable(this);
        }
    }
}
