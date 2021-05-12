package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.forasoft.homewavvisitor.presentation.presenter.visits.InmateChooserPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class InmateChooserVisitsFragment$$PresentersBinder extends moxy.PresenterBinder<InmateChooserVisitsFragment> {
    public List<PresenterField<? super InmateChooserVisitsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: InmateChooserVisitsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<InmateChooserVisitsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, InmateChooserPresenter.class);
        }

        public void bind(InmateChooserVisitsFragment inmateChooserVisitsFragment, MvpPresenter mvpPresenter) {
            inmateChooserVisitsFragment.presenter = (InmateChooserPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(InmateChooserVisitsFragment inmateChooserVisitsFragment) {
            return inmateChooserVisitsFragment.providePresenter();
        }
    }
}
