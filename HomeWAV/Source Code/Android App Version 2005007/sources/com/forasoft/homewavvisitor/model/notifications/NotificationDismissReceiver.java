package com.forasoft.homewavvisitor.model.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.toothpick.DI;
import kotlin.Metadata;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/model/notifications/NotificationDismissReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationDismissReceiver.kt */
public final class NotificationDismissReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRA_MESSAGE_ID = "EXTRA_MESSAGE_ID";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/notifications/NotificationDismissReceiver$Companion;", "", "()V", "EXTRA_MESSAGE_ID", "", "getIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "messageId", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationDismissReceiver.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PendingIntent getIntent(Context context, int i) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent putExtra = new Intent(context, NotificationDismissReceiver.class).putExtra(NotificationDismissReceiver.EXTRA_MESSAGE_ID, i);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, Notifica…RA_MESSAGE_ID, messageId)");
            PendingIntent broadcast = PendingIntent.getBroadcast(context, i, putExtra, 1073741824);
            Intrinsics.checkExpressionValueIsNotNull(broadcast, "PendingIntent.getBroadca…dingIntent.FLAG_ONE_SHOT)");
            return broadcast;
        }
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new NotificationDismissReceiver$onReceive$1((NotificationDao) Toothpick.openScope(DI.APP_SCOPE).getInstance(NotificationDao.class), intent.getIntExtra(EXTRA_MESSAGE_ID, 0)), 31, (Object) null);
    }
}
