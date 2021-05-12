package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.RefundsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class RefundsFragment$$PresentersBinder extends moxy.PresenterBinder<RefundsFragment> {
    public List<PresenterField<? super RefundsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: RefundsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<RefundsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, RefundsPresenter.class);
        }

        public void bind(RefundsFragment refundsFragment, MvpPresenter mvpPresenter) {
            refundsFragment.presenter = (RefundsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(RefundsFragment refundsFragment) {
            return refundsFragment.providePresenter();
        }
    }
}
