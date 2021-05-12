package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.presentation.view.register.CreateAccountView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class CreateAccountPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new CreateAccountView$$State();
    }
}
