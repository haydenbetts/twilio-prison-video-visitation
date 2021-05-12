package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.BaseView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class ReportBugPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new BaseView$$State();
    }
}
