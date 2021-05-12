package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView$$State;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class ChooseFundsPresenter$$ViewStateProvider extends ViewStateProvider {
    public MvpViewState<? extends MvpView> getViewState() {
        return new ChooseFundsView$$State();
    }
}
