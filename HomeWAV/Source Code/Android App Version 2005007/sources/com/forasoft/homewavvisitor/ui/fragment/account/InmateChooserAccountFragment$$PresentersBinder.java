package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.InmateChooserPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class InmateChooserAccountFragment$$PresentersBinder extends moxy.PresenterBinder<InmateChooserAccountFragment> {
    public List<PresenterField<? super InmateChooserAccountFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: InmateChooserAccountFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<InmateChooserAccountFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, InmateChooserPresenter.class);
        }

        public void bind(InmateChooserAccountFragment inmateChooserAccountFragment, MvpPresenter mvpPresenter) {
            inmateChooserAccountFragment.presenter = (InmateChooserPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(InmateChooserAccountFragment inmateChooserAccountFragment) {
            return inmateChooserAccountFragment.providePresenter();
        }
    }
}
