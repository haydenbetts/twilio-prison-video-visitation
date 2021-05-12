package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.presentation.view.home.TutorialsView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class TutorialsPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new TutorialsView$$State();
    }
}
