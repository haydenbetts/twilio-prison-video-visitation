package com.forasoft.homewavvisitor.ui.fragment.register;

import com.forasoft.homewavvisitor.presentation.presenter.register.AddConnectionSignUpPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class AddConnectionSignUpFragment$$PresentersBinder extends moxy.PresenterBinder<AddConnectionSignUpFragment> {
    public List<PresenterField<? super AddConnectionSignUpFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: AddConnectionSignUpFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<AddConnectionSignUpFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, AddConnectionSignUpPresenter.class);
        }

        public void bind(AddConnectionSignUpFragment addConnectionSignUpFragment, MvpPresenter mvpPresenter) {
            addConnectionSignUpFragment.presenter = (AddConnectionSignUpPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(AddConnectionSignUpFragment addConnectionSignUpFragment) {
            return addConnectionSignUpFragment.providePresenter();
        }
    }
}
