package com.forasoft.homewavvisitor.ui.fragment.register;

import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class SignUp4Fragment$$PresentersBinder extends moxy.PresenterBinder<SignUp4Fragment> {
    public List<PresenterField<? super SignUp4Fragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: SignUp4Fragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<SignUp4Fragment> {
        public PresenterBinder() {
            super("presenter", (String) null, SignUp4Presenter.class);
        }

        public void bind(SignUp4Fragment signUp4Fragment, MvpPresenter mvpPresenter) {
            signUp4Fragment.presenter = (SignUp4Presenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(SignUp4Fragment signUp4Fragment) {
            return signUp4Fragment.providePresenter();
        }
    }
}
