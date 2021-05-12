package com.urbanairship.push.notifications;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.urbanairship.UAirship;

public class ActionsNotificationExtender implements NotificationCompat.Extender {
    private final NotificationArguments arguments;
    private final Context context;

    public ActionsNotificationExtender(Context context2, NotificationArguments notificationArguments) {
        this.context = context2.getApplicationContext();
        this.arguments = notificationArguments;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
        NotificationActionButtonGroup notificationActionGroup = UAirship.shared().getPushManager().getNotificationActionGroup(this.arguments.getMessage().getInteractiveNotificationType());
        if (notificationActionGroup == null) {
            return builder;
        }
        Context context2 = this.context;
        NotificationArguments notificationArguments = this.arguments;
        for (NotificationCompat.Action addAction : notificationActionGroup.createAndroidActions(context2, notificationArguments, notificationArguments.getMessage().getInteractiveActionsPayload())) {
            builder.addAction(addAction);
        }
        return builder;
    }
}
