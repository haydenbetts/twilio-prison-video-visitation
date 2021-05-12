package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class TimeChooserPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new TimeChooserView$$State();
    }
}
