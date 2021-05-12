package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.presentation.view.auth.VerifyCodeView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class VerifyCodePresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new VerifyCodeView$$State();
    }
}
