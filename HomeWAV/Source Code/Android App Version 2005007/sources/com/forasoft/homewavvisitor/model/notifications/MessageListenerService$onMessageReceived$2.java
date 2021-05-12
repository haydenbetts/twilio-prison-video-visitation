package com.forasoft.homewavvisitor.model.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: MessageListenerService.kt */
final class MessageListenerService$onMessageReceived$2<T> implements Consumer<User> {
    final /* synthetic */ String $messageBody;
    final /* synthetic */ String $messageId;
    final /* synthetic */ String $messageType;
    final /* synthetic */ Notification $notification;
    final /* synthetic */ long $timestamp;
    final /* synthetic */ MessageListenerService this$0;

    MessageListenerService$onMessageReceived$2(MessageListenerService messageListenerService, String str, Notification notification, long j, String str2, String str3) {
        this.this$0 = messageListenerService;
        this.$messageId = str;
        this.$notification = notification;
        this.$timestamp = j;
        this.$messageType = str2;
        this.$messageBody = str3;
    }

    public final void accept(User user) {
        Object systemService = this.this$0.getSystemService("notification");
        if (systemService != null) {
            NotificationManager notificationManager = (NotificationManager) systemService;
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel("homewav", "Homewav notifications", 3));
            }
            notificationManager.notify(this.$messageId.hashCode(), this.$notification);
            LocalDateTime localDateTime = Instant.ofEpochMilli(this.$timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
            NotificationDao notificationDao = this.this$0.getNotificationDao();
            int hashCode = this.$messageId.hashCode();
            String str = this.$messageType;
            String str2 = this.$messageBody;
            Intrinsics.checkExpressionValueIsNotNull(localDateTime, "createdAt");
            notificationDao.saveNotification(new com.forasoft.homewavvisitor.model.data.Notification(hashCode, str, str2, localDateTime));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
    }
}
