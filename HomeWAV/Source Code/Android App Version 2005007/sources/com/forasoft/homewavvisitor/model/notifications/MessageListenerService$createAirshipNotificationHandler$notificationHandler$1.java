package com.forasoft.homewavvisitor.model.notifications;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushMessage;
import com.urbanairship.push.notifications.AirshipNotificationProvider;
import com.urbanairship.push.notifications.NotificationArguments;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\t"}, d2 = {"com/forasoft/homewavvisitor/model/notifications/MessageListenerService$createAirshipNotificationHandler$notificationHandler$1", "Lcom/urbanairship/push/notifications/AirshipNotificationProvider;", "onExtendBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "context", "Landroid/content/Context;", "builder", "arguments", "Lcom/urbanairship/push/notifications/NotificationArguments;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageListenerService.kt */
public final class MessageListenerService$createAirshipNotificationHandler$notificationHandler$1 extends AirshipNotificationProvider {
    final /* synthetic */ UAirship $airship;
    final /* synthetic */ MessageListenerService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MessageListenerService$createAirshipNotificationHandler$notificationHandler$1(MessageListenerService messageListenerService, UAirship uAirship, Context context, AirshipConfigOptions airshipConfigOptions) {
        super(context, airshipConfigOptions);
        this.this$0 = messageListenerService;
        this.$airship = uAirship;
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder onExtendBuilder(Context context, NotificationCompat.Builder builder, NotificationArguments notificationArguments) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        Intrinsics.checkParameterIsNotNull(notificationArguments, "arguments");
        int notificationId = notificationArguments.getNotificationId();
        StringBuilder sb = new StringBuilder();
        sb.append("\n                            {\n                               \"type\":\"airship\",\n                               \"inmateId\":\"\",\n                               \"inmateName\":\"\",\n                               \"message\":\"");
        PushMessage message = notificationArguments.getMessage();
        Intrinsics.checkExpressionValueIsNotNull(message, "arguments.message");
        sb.append(message.getAlert());
        sb.append("\"\n                            }\n                        ");
        String sb2 = sb.toString();
        LocalDateTime now = LocalDateTime.now();
        Intrinsics.checkExpressionValueIsNotNull(now, "LocalDateTime.now()");
        this.this$0.getNotificationDao().saveNotification(new Notification(notificationId, MessageListenerService.AIRSHIP_MESSAGE, sb2, now));
        builder.setDeleteIntent(NotificationDismissReceiver.Companion.getIntent(context, Integer.valueOf(notificationArguments.getNotificationId()).hashCode()));
        NotificationCompat.Builder onExtendBuilder = super.onExtendBuilder(context, builder, notificationArguments);
        Intrinsics.checkExpressionValueIsNotNull(onExtendBuilder, "super.onExtendBuilder(context, builder, arguments)");
        return onExtendBuilder;
    }
}
