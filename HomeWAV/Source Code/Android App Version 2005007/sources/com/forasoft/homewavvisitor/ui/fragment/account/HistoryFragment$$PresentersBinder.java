package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.HistoryPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class HistoryFragment$$PresentersBinder extends moxy.PresenterBinder<HistoryFragment> {
    public List<PresenterField<? super HistoryFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: HistoryFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<HistoryFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, HistoryPresenter.class);
        }

        public void bind(HistoryFragment historyFragment, MvpPresenter mvpPresenter) {
            historyFragment.presenter = (HistoryPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(HistoryFragment historyFragment) {
            return historyFragment.providePresenter();
        }
    }
}
