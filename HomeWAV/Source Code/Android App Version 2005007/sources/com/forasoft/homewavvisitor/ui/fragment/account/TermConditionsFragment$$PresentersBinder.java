package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.TermConditionsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class TermConditionsFragment$$PresentersBinder extends moxy.PresenterBinder<TermConditionsFragment> {
    public List<PresenterField<? super TermConditionsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: TermConditionsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<TermConditionsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, TermConditionsPresenter.class);
        }

        public void bind(TermConditionsFragment termConditionsFragment, MvpPresenter mvpPresenter) {
            termConditionsFragment.presenter = (TermConditionsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(TermConditionsFragment termConditionsFragment) {
            return termConditionsFragment.providePresenter();
        }
    }
}
