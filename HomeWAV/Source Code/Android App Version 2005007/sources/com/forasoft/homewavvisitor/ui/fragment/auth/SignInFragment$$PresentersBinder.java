package com.forasoft.homewavvisitor.ui.fragment.auth;

import com.forasoft.homewavvisitor.presentation.presenter.auth.SignInPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class SignInFragment$$PresentersBinder extends moxy.PresenterBinder<SignInFragment> {
    public List<PresenterField<? super SignInFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: SignInFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<SignInFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, SignInPresenter.class);
        }

        public void bind(SignInFragment signInFragment, MvpPresenter mvpPresenter) {
            signInFragment.presenter = (SignInPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(SignInFragment signInFragment) {
            return signInFragment.providePresenter();
        }
    }
}
