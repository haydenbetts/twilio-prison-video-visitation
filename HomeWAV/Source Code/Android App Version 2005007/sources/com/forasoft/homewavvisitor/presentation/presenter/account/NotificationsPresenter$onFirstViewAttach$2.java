package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.account.NotificationsView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: NotificationsPresenter.kt */
final class NotificationsPresenter$onFirstViewAttach$2<T> implements Consumer<List<? extends NotificationWithInmateStatus>> {
    final /* synthetic */ NotificationsPresenter this$0;

    NotificationsPresenter$onFirstViewAttach$2(NotificationsPresenter notificationsPresenter) {
        this.this$0 = notificationsPresenter;
    }

    public final void accept(List<NotificationWithInmateStatus> list) {
        ((NotificationsView) this.this$0.getViewState()).updateToolbar(list.size());
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        ((NotificationsView) this.this$0.getViewState()).displayNotifications(list);
    }
}
