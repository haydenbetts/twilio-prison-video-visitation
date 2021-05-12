package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.presentation.presenter.home.PendingInmatesPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class PendingInmatesFragment$$PresentersBinder extends moxy.PresenterBinder<PendingInmatesFragment> {
    public List<PresenterField<? super PendingInmatesFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: PendingInmatesFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<PendingInmatesFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, PendingInmatesPresenter.class);
        }

        public void bind(PendingInmatesFragment pendingInmatesFragment, MvpPresenter mvpPresenter) {
            pendingInmatesFragment.presenter = (PendingInmatesPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(PendingInmatesFragment pendingInmatesFragment) {
            return pendingInmatesFragment.providePresenter();
        }
    }
}
