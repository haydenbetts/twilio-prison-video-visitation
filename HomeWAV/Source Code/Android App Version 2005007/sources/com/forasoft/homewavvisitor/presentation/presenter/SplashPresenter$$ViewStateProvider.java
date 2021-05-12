package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.presentation.view.SplashView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class SplashPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new SplashView$$State();
    }
}
