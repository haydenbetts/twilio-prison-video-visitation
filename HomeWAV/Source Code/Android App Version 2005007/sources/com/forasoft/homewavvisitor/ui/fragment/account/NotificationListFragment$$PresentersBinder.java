package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class NotificationListFragment$$PresentersBinder extends moxy.PresenterBinder<NotificationListFragment> {
    public List<PresenterField<? super NotificationListFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: NotificationListFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<NotificationListFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, NotificationsPresenter.class);
        }

        public void bind(NotificationListFragment notificationListFragment, MvpPresenter mvpPresenter) {
            notificationListFragment.presenter = (NotificationsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(NotificationListFragment notificationListFragment) {
            return notificationListFragment.providePresenter();
        }
    }
}
