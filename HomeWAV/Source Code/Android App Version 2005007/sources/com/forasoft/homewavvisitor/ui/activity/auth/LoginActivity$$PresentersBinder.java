package com.forasoft.homewavvisitor.ui.activity.auth;

import com.forasoft.homewavvisitor.presentation.presenter.auth.LoginPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.PresenterBinder;
import moxy.presenter.PresenterField;

public class LoginActivity$$PresentersBinder extends PresenterBinder<LoginActivity> {
    public List<PresenterField<? super LoginActivity>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new MLoginPresenterBinder());
        return arrayList;
    }

    /* compiled from: LoginActivity$$PresentersBinder */
    public class MLoginPresenterBinder extends PresenterField<LoginActivity> {
        public MLoginPresenterBinder() {
            super("mLoginPresenter", (String) null, LoginPresenter.class);
        }

        public void bind(LoginActivity loginActivity, MvpPresenter mvpPresenter) {
            loginActivity.mLoginPresenter = (LoginPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(LoginActivity loginActivity) {
            return loginActivity.providePresenter();
        }
    }
}
