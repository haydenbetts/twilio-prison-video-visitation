package com.urbanairship.push.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class NotificationChannelRegistry {
    private static final String DATABASE_NAME = "ua_notification_channel_registry.db";
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final NotificationChannelRegistryDataManager dataManager;
    private final Executor executor;
    /* access modifiers changed from: private */
    public NotificationManager notificationManager;

    public NotificationChannelRegistry(Context context2, AirshipConfigOptions airshipConfigOptions) {
        this(context2, new NotificationChannelRegistryDataManager(context2, airshipConfigOptions.appKey, DATABASE_NAME), AirshipExecutors.newSerialExecutor());
    }

    NotificationChannelRegistry(Context context2, NotificationChannelRegistryDataManager notificationChannelRegistryDataManager, Executor executor2) {
        this.context = context2;
        this.dataManager = notificationChannelRegistryDataManager;
        this.executor = executor2;
        this.notificationManager = (NotificationManager) context2.getSystemService("notification");
    }

    public PendingResult<NotificationChannelCompat> getNotificationChannel(final String str) {
        final PendingResult<NotificationChannelCompat> pendingResult = new PendingResult<>();
        this.executor.execute(new Runnable() {
            public void run() {
                NotificationChannelCompat notificationChannelCompat;
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel notificationChannel = NotificationChannelRegistry.this.notificationManager.getNotificationChannel(str);
                    if (notificationChannel != null) {
                        notificationChannelCompat = new NotificationChannelCompat(notificationChannel);
                    } else {
                        notificationChannelCompat = NotificationChannelRegistry.this.dataManager.getChannel(str);
                        if (notificationChannelCompat != null) {
                            NotificationChannelRegistry.this.notificationManager.createNotificationChannel(notificationChannelCompat.toNotificationChannel());
                        }
                    }
                } else {
                    notificationChannelCompat = NotificationChannelRegistry.this.dataManager.getChannel(str);
                }
                pendingResult.setResult(notificationChannelCompat);
            }
        });
        return pendingResult;
    }

    public NotificationChannelCompat getNotificationChannelSync(String str) {
        try {
            return getNotificationChannel(str).get();
        } catch (InterruptedException e) {
            Logger.error(e, "Failed to get notification channel.", new Object[0]);
            Thread.currentThread().interrupt();
            return null;
        } catch (ExecutionException e2) {
            Logger.error(e2, "Failed to get notification channel.", new Object[0]);
            return null;
        }
    }

    public void deleteNotificationChannel(final String str) {
        this.executor.execute(new Runnable() {
            public void run() {
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannelRegistry.this.notificationManager.deleteNotificationChannel(str);
                }
                NotificationChannelRegistry.this.dataManager.deleteChannel(str);
            }
        });
    }

    public void createNotificationChannel(final NotificationChannelCompat notificationChannelCompat) {
        this.executor.execute(new Runnable() {
            public void run() {
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannelRegistry.this.notificationManager.createNotificationChannel(notificationChannelCompat.toNotificationChannel());
                }
                NotificationChannelRegistry.this.dataManager.createChannel(notificationChannelCompat);
            }
        });
    }

    public void createDeferredNotificationChannel(final NotificationChannelCompat notificationChannelCompat) {
        this.executor.execute(new Runnable() {
            public void run() {
                NotificationChannelRegistry.this.dataManager.createChannel(notificationChannelCompat);
            }
        });
    }

    public void createNotificationChannels(final int i) {
        this.executor.execute(new Runnable() {
            public void run() {
                for (NotificationChannelCompat next : NotificationChannelCompat.fromXml(NotificationChannelRegistry.this.context, i)) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        NotificationChannelRegistry.this.notificationManager.createNotificationChannel(next.toNotificationChannel());
                    }
                    NotificationChannelRegistry.this.dataManager.createChannel(next);
                }
            }
        });
    }

    public void createDeferredNotificationChannels(final int i) {
        this.executor.execute(new Runnable() {
            public void run() {
                for (NotificationChannelCompat createChannel : NotificationChannelCompat.fromXml(NotificationChannelRegistry.this.context, i)) {
                    NotificationChannelRegistry.this.dataManager.createChannel(createChannel);
                }
            }
        });
    }
}
