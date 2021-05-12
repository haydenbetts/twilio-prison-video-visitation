package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class InmateDetailPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new InmateDetailView$$State();
    }
}
