package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.animation.GlideAnimation;
import java.util.Objects;

public class NotificationTarget extends SimpleTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final RemoteViews remoteViews;
    private final int viewId;

    public NotificationTarget(Context context2, RemoteViews remoteViews2, int i, Notification notification2, int i2) {
        this(context2, remoteViews2, i, Integer.MIN_VALUE, Integer.MIN_VALUE, notification2, i2);
    }

    public NotificationTarget(Context context2, RemoteViews remoteViews2, int i, int i2, int i3, Notification notification2, int i4) {
        super(i2, i3);
        Objects.requireNonNull(context2, "Context must not be null!");
        Objects.requireNonNull(notification2, "Notification object can not be null!");
        Objects.requireNonNull(remoteViews2, "RemoteViews object can not be null!");
        this.context = context2;
        this.viewId = i;
        this.notification = notification2;
        this.notificationId = i4;
        this.remoteViews = remoteViews2;
    }

    private void update() {
        ((NotificationManager) this.context.getSystemService("notification")).notify(this.notificationId, this.notification);
    }

    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
