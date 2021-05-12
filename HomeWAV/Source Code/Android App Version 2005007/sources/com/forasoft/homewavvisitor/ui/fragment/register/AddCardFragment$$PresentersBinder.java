package com.forasoft.homewavvisitor.ui.fragment.register;

import com.forasoft.homewavvisitor.presentation.presenter.AddCardPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class AddCardFragment$$PresentersBinder extends moxy.PresenterBinder<AddCardFragment> {
    public List<PresenterField<? super AddCardFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: AddCardFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<AddCardFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, AddCardPresenter.class);
        }

        public void bind(AddCardFragment addCardFragment, MvpPresenter mvpPresenter) {
            addCardFragment.presenter = (AddCardPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(AddCardFragment addCardFragment) {
            return addCardFragment.providePresenter();
        }
    }
}
