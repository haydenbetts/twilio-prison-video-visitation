package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.account.NotificationsView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class NotificationsPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new NotificationsView$$State();
    }
}
