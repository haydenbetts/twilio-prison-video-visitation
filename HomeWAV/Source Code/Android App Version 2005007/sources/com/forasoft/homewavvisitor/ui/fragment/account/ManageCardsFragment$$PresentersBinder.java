package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.ManageCardsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class ManageCardsFragment$$PresentersBinder extends moxy.PresenterBinder<ManageCardsFragment> {
    public List<PresenterField<? super ManageCardsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: ManageCardsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<ManageCardsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, ManageCardsPresenter.class);
        }

        public void bind(ManageCardsFragment manageCardsFragment, MvpPresenter mvpPresenter) {
            manageCardsFragment.presenter = (ManageCardsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(ManageCardsFragment manageCardsFragment) {
            return manageCardsFragment.providePresenter();
        }
    }
}
