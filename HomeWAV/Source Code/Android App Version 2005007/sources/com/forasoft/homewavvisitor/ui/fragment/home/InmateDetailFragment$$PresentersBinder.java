package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.presentation.presenter.home.InmateDetailPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class InmateDetailFragment$$PresentersBinder extends moxy.PresenterBinder<InmateDetailFragment> {
    public List<PresenterField<? super InmateDetailFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: InmateDetailFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<InmateDetailFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, InmateDetailPresenter.class);
        }

        public void bind(InmateDetailFragment inmateDetailFragment, MvpPresenter mvpPresenter) {
            inmateDetailFragment.presenter = (InmateDetailPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(InmateDetailFragment inmateDetailFragment) {
            return inmateDetailFragment.providePresenter();
        }
    }
}
