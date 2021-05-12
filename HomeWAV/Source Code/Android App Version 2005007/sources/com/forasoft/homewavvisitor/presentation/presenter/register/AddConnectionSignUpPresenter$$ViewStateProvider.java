package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class AddConnectionSignUpPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new AddConnectionView$$State();
    }
}
