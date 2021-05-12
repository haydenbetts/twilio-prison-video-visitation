package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.EditAccountPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class EditAccountFragment$$PresentersBinder extends moxy.PresenterBinder<EditAccountFragment> {
    public List<PresenterField<? super EditAccountFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: EditAccountFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<EditAccountFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, EditAccountPresenter.class);
        }

        public void bind(EditAccountFragment editAccountFragment, MvpPresenter mvpPresenter) {
            editAccountFragment.presenter = (EditAccountPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(EditAccountFragment editAccountFragment) {
            return editAccountFragment.providePresenter();
        }
    }
}
