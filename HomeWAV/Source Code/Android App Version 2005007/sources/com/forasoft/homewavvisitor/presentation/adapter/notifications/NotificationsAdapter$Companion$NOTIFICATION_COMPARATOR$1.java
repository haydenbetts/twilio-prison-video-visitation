package com.forasoft.homewavvisitor.presentation.adapter.notifications;

import androidx.recyclerview.widget.DiffUtil;
import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"com/forasoft/homewavvisitor/presentation/adapter/notifications/NotificationsAdapter$Companion$NOTIFICATION_COMPARATOR$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationsAdapter.kt */
public final class NotificationsAdapter$Companion$NOTIFICATION_COMPARATOR$1 extends DiffUtil.ItemCallback<NotificationWithInmateStatus> {
    NotificationsAdapter$Companion$NOTIFICATION_COMPARATOR$1() {
    }

    public boolean areContentsTheSame(NotificationWithInmateStatus notificationWithInmateStatus, NotificationWithInmateStatus notificationWithInmateStatus2) {
        Intrinsics.checkParameterIsNotNull(notificationWithInmateStatus, "oldItem");
        Intrinsics.checkParameterIsNotNull(notificationWithInmateStatus2, "newItem");
        return notificationWithInmateStatus.getNotification().getId() == notificationWithInmateStatus2.getNotification().getId();
    }

    public boolean areItemsTheSame(NotificationWithInmateStatus notificationWithInmateStatus, NotificationWithInmateStatus notificationWithInmateStatus2) {
        Intrinsics.checkParameterIsNotNull(notificationWithInmateStatus, "oldItem");
        Intrinsics.checkParameterIsNotNull(notificationWithInmateStatus2, "newItem");
        return Intrinsics.areEqual((Object) notificationWithInmateStatus, (Object) notificationWithInmateStatus2);
    }
}
