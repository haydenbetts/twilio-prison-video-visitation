package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.forasoft.homewavvisitor.presentation.presenter.visits.VisitDetailsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class VisitDetailsFragment$$PresentersBinder extends moxy.PresenterBinder<VisitDetailsFragment> {
    public List<PresenterField<? super VisitDetailsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: VisitDetailsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<VisitDetailsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, VisitDetailsPresenter.class);
        }

        public void bind(VisitDetailsFragment visitDetailsFragment, MvpPresenter mvpPresenter) {
            visitDetailsFragment.presenter = (VisitDetailsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(VisitDetailsFragment visitDetailsFragment) {
            return visitDetailsFragment.providePresenter();
        }
    }
}
