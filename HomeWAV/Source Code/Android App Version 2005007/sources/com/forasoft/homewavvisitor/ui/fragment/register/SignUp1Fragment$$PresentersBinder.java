package com.forasoft.homewavvisitor.ui.fragment.register;

import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp1Presenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class SignUp1Fragment$$PresentersBinder extends moxy.PresenterBinder<SignUp1Fragment> {
    public List<PresenterField<? super SignUp1Fragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: SignUp1Fragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<SignUp1Fragment> {
        public PresenterBinder() {
            super("presenter", (String) null, SignUp1Presenter.class);
        }

        public void bind(SignUp1Fragment signUp1Fragment, MvpPresenter mvpPresenter) {
            signUp1Fragment.presenter = (SignUp1Presenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(SignUp1Fragment signUp1Fragment) {
            return signUp1Fragment.providePresenter();
        }
    }
}
