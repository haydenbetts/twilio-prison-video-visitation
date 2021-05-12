package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.forasoft.homewavvisitor.presentation.presenter.visits.VisitsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class VisitsFragment$$PresentersBinder extends moxy.PresenterBinder<VisitsFragment> {
    public List<PresenterField<? super VisitsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: VisitsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<VisitsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, VisitsPresenter.class);
        }

        public void bind(VisitsFragment visitsFragment, MvpPresenter mvpPresenter) {
            visitsFragment.presenter = (VisitsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(VisitsFragment visitsFragment) {
            return visitsFragment.providePresenter();
        }
    }
}
