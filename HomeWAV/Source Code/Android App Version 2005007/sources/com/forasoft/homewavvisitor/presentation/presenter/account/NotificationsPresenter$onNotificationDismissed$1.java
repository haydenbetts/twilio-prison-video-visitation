package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Notification;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: NotificationsPresenter.kt */
final class NotificationsPresenter$onNotificationDismissed$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Notification $notification;
    final /* synthetic */ NotificationsPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NotificationsPresenter$onNotificationDismissed$1(NotificationsPresenter notificationsPresenter, Notification notification) {
        super(0);
        this.this$0 = notificationsPresenter;
        this.$notification = notification;
    }

    public final void invoke() {
        this.this$0.notificationDao.deleteNotification(this.$notification.getId());
    }
}
