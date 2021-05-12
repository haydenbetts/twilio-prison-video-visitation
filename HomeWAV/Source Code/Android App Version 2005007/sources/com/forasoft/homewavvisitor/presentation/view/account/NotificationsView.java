package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/NotificationsView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "displayNotifications", "", "notifications", "", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "updateToolbar", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationsView.kt */
public interface NotificationsView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: NotificationsView.kt */
    public static final class DefaultImpls {
        public static void showProgress(NotificationsView notificationsView, boolean z) {
            BaseView.DefaultImpls.showProgress(notificationsView, z);
        }

        public static void updateNotificationMenu(NotificationsView notificationsView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(notificationsView, i);
        }
    }

    void displayNotifications(List<NotificationWithInmateStatus> list);

    void updateToolbar(int i);
}
