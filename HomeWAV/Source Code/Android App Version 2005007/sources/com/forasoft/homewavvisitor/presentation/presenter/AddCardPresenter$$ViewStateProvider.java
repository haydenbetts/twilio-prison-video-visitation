package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.presentation.view.AddCardView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class AddCardPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new AddCardView$$State();
    }
}
