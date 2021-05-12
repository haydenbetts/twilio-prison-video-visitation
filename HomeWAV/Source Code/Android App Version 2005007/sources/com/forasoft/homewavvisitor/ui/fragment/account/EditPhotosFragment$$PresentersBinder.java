package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.EditPhotosPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class EditPhotosFragment$$PresentersBinder extends moxy.PresenterBinder<EditPhotosFragment> {
    public List<PresenterField<? super EditPhotosFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: EditPhotosFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<EditPhotosFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, EditPhotosPresenter.class);
        }

        public void bind(EditPhotosFragment editPhotosFragment, MvpPresenter mvpPresenter) {
            editPhotosFragment.presenter = (EditPhotosPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(EditPhotosFragment editPhotosFragment) {
            return editPhotosFragment.providePresenter();
        }
    }
}
