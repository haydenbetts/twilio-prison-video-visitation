package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.presentation.view.auth.ForgotPasswordView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class ForgotPasswordPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new ForgotPasswordView$$State();
    }
}
