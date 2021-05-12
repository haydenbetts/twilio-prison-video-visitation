package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.presentation.view.home.PendingInmatesView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class PendingInmatesPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new PendingInmatesView$$State();
    }
}
