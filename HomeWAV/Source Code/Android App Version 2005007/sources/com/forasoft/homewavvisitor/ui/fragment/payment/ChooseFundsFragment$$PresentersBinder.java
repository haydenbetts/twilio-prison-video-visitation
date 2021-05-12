package com.forasoft.homewavvisitor.ui.fragment.payment;

import com.forasoft.homewavvisitor.presentation.presenter.payment.ChooseFundsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class ChooseFundsFragment$$PresentersBinder extends moxy.PresenterBinder<ChooseFundsFragment> {
    public List<PresenterField<? super ChooseFundsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: ChooseFundsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<ChooseFundsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, ChooseFundsPresenter.class);
        }

        public void bind(ChooseFundsFragment chooseFundsFragment, MvpPresenter mvpPresenter) {
            chooseFundsFragment.presenter = (ChooseFundsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(ChooseFundsFragment chooseFundsFragment) {
            return chooseFundsFragment.providePresenter();
        }
    }
}
