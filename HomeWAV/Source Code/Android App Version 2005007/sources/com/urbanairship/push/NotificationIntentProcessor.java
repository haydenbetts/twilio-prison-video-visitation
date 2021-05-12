package com.urbanairship.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionCompletionCallback;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.actions.ActionRunRequest;
import com.urbanairship.actions.ActionValue;
import com.urbanairship.analytics.InteractiveNotificationEvent;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

class NotificationIntentProcessor {
    private final NotificationActionButtonInfo actionButtonInfo;
    private final UAirship airship;
    private final Context context;
    private final Executor executor;
    private final Intent intent;
    private final NotificationInfo notificationInfo;

    NotificationIntentProcessor(Context context2, Intent intent2) {
        this(UAirship.shared(), context2, intent2, AirshipExecutors.THREAD_POOL_EXECUTOR);
    }

    NotificationIntentProcessor(UAirship uAirship, Context context2, Intent intent2, Executor executor2) {
        this.airship = uAirship;
        this.executor = executor2;
        this.intent = intent2;
        this.context = context2;
        this.notificationInfo = NotificationInfo.fromIntent(intent2);
        this.actionButtonInfo = NotificationActionButtonInfo.fromIntent(intent2);
    }

    /* access modifiers changed from: package-private */
    public PendingResult<Boolean> process() {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        if (this.intent.getAction() == null || this.notificationInfo == null) {
            Logger.error("NotificationIntentProcessor - invalid intent %s", this.intent);
            pendingResult.setResult(false);
            return pendingResult;
        }
        Logger.verbose("NotificationIntentProcessor - Processing intent: %s", this.intent.getAction());
        String action = this.intent.getAction();
        action.hashCode();
        if (action.equals(PushManager.ACTION_NOTIFICATION_DISMISSED)) {
            onNotificationDismissed();
            pendingResult.setResult(true);
        } else if (!action.equals(PushManager.ACTION_NOTIFICATION_RESPONSE)) {
            Logger.error("NotificationIntentProcessor - Invalid intent action: %s", this.intent.getAction());
            pendingResult.setResult(false);
        } else {
            onNotificationResponse(new Runnable() {
                public void run() {
                    pendingResult.setResult(true);
                }
            });
        }
        return pendingResult;
    }

    private void onNotificationResponse(Runnable runnable) {
        Logger.info("Notification response: %s, %s", this.notificationInfo, this.actionButtonInfo);
        NotificationActionButtonInfo notificationActionButtonInfo = this.actionButtonInfo;
        if (notificationActionButtonInfo == null || notificationActionButtonInfo.isForeground()) {
            this.airship.getAnalytics().setConversionSendId(this.notificationInfo.getMessage().getSendId());
            this.airship.getAnalytics().setConversionMetadata(this.notificationInfo.getMessage().getMetadata());
        }
        NotificationListener notificationListener = this.airship.getPushManager().getNotificationListener();
        NotificationActionButtonInfo notificationActionButtonInfo2 = this.actionButtonInfo;
        if (notificationActionButtonInfo2 != null) {
            this.airship.getAnalytics().addEvent(new InteractiveNotificationEvent(this.notificationInfo, notificationActionButtonInfo2));
            NotificationManagerCompat.from(this.context).cancel(this.notificationInfo.getNotificationTag(), this.notificationInfo.getNotificationId());
            if (this.actionButtonInfo.isForeground()) {
                if (notificationListener == null || !notificationListener.onNotificationForegroundAction(this.notificationInfo, this.actionButtonInfo)) {
                    launchApplication();
                }
            } else if (notificationListener != null) {
                notificationListener.onNotificationBackgroundAction(this.notificationInfo, this.actionButtonInfo);
            }
        } else if (notificationListener == null || !notificationListener.onNotificationOpened(this.notificationInfo)) {
            launchApplication();
        }
        for (InternalNotificationListener onNotificationResponse : this.airship.getPushManager().getInternalNotificationListeners()) {
            onNotificationResponse.onNotificationResponse(this.notificationInfo, this.actionButtonInfo);
        }
        runNotificationResponseActions(runnable);
    }

    private void onNotificationDismissed() {
        PendingIntent pendingIntent;
        Logger.info("Notification dismissed: %s", this.notificationInfo);
        if (!(this.intent.getExtras() == null || (pendingIntent = (PendingIntent) this.intent.getExtras().get(PushManager.EXTRA_NOTIFICATION_DELETE_INTENT)) == null)) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Logger.debug("Failed to send notification's deleteIntent, already canceled.", new Object[0]);
            }
        }
        NotificationListener notificationListener = this.airship.getPushManager().getNotificationListener();
        if (notificationListener != null) {
            notificationListener.onNotificationDismissed(this.notificationInfo);
        }
    }

    private void launchApplication() {
        PendingIntent pendingIntent;
        if (this.intent.getExtras() != null && (pendingIntent = (PendingIntent) this.intent.getExtras().get(PushManager.EXTRA_NOTIFICATION_CONTENT_INTENT)) != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Logger.debug("Failed to send notification's contentIntent, already canceled.", new Object[0]);
            }
        } else if (this.airship.getAirshipConfigOptions().autoLaunchApplication) {
            Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(UAirship.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setFlags(805306368);
                launchIntentForPackage.putExtra(PushManager.EXTRA_PUSH_MESSAGE_BUNDLE, this.notificationInfo.getMessage().getPushBundle());
                launchIntentForPackage.setPackage((String) null);
                Logger.info("Starting application's launch intent.", new Object[0]);
                this.context.startActivity(launchIntentForPackage);
                return;
            }
            Logger.info("Unable to launch application. Launch intent is unavailable.", new Object[0]);
        }
    }

    private void runNotificationResponseActions(Runnable runnable) {
        int i;
        Map<String, ActionValue> map;
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionArguments.PUSH_MESSAGE_METADATA, this.notificationInfo.getMessage());
        if (this.actionButtonInfo != null) {
            String stringExtra = this.intent.getStringExtra(PushManager.EXTRA_NOTIFICATION_BUTTON_ACTIONS_PAYLOAD);
            if (!UAStringUtil.isEmpty(stringExtra)) {
                map = parseActionValues(stringExtra);
                if (this.actionButtonInfo.getRemoteInput() != null) {
                    bundle.putBundle(ActionArguments.REMOTE_INPUT_METADATA, this.actionButtonInfo.getRemoteInput());
                }
                i = this.actionButtonInfo.isForeground() ? 4 : 5;
            } else {
                map = null;
                i = 0;
            }
        } else {
            i = 2;
            map = this.notificationInfo.getMessage().getActions();
        }
        if (map == null || map.isEmpty()) {
            runnable.run();
        } else {
            runActions(map, i, bundle, runnable);
        }
    }

    private void runActions(Map<String, ActionValue> map, int i, Bundle bundle, Runnable runnable) {
        final Map<String, ActionValue> map2 = map;
        final Bundle bundle2 = bundle;
        final int i2 = i;
        final Runnable runnable2 = runnable;
        this.executor.execute(new Runnable() {
            public void run() {
                final CountDownLatch countDownLatch = new CountDownLatch(map2.size());
                for (Map.Entry entry : map2.entrySet()) {
                    ActionRunRequest.createRequest((String) entry.getKey()).setMetadata(bundle2).setSituation(i2).setValue((ActionValue) entry.getValue()).run(new ActionCompletionCallback() {
                        public void onFinish(ActionArguments actionArguments, ActionResult actionResult) {
                            countDownLatch.countDown();
                        }
                    });
                }
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    Logger.error(e, "Failed to wait for actions", new Object[0]);
                    Thread.currentThread().interrupt();
                }
                runnable2.run();
            }
        });
    }

    private Map<String, ActionValue> parseActionValues(String str) {
        HashMap hashMap = new HashMap();
        try {
            JsonMap map = JsonValue.parseString(str).getMap();
            if (map != null) {
                Iterator<Map.Entry<String, JsonValue>> it = map.iterator();
                while (it.hasNext()) {
                    Map.Entry next = it.next();
                    hashMap.put(next.getKey(), new ActionValue((JsonValue) next.getValue()));
                }
            }
        } catch (JsonException e) {
            Logger.error(e, "Failed to parse actions for push.", new Object[0]);
        }
        return hashMap;
    }
}
