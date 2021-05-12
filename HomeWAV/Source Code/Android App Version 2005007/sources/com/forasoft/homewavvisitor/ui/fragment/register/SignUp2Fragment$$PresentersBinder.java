package com.forasoft.homewavvisitor.ui.fragment.register;

import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp2Presenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class SignUp2Fragment$$PresentersBinder extends moxy.PresenterBinder<SignUp2Fragment> {
    public List<PresenterField<? super SignUp2Fragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: SignUp2Fragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<SignUp2Fragment> {
        public PresenterBinder() {
            super("presenter", (String) null, SignUp2Presenter.class);
        }

        public void bind(SignUp2Fragment signUp2Fragment, MvpPresenter mvpPresenter) {
            signUp2Fragment.presenter = (SignUp2Presenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(SignUp2Fragment signUp2Fragment) {
            return signUp2Fragment.providePresenter();
        }
    }
}
