package com.forasoft.homewavvisitor.ui.fragment.auth;

import com.forasoft.homewavvisitor.presentation.presenter.auth.ForgotPasswordPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class ForgotPasswordFragment$$PresentersBinder extends moxy.PresenterBinder<ForgotPasswordFragment> {
    public List<PresenterField<? super ForgotPasswordFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: ForgotPasswordFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<ForgotPasswordFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, ForgotPasswordPresenter.class);
        }

        public void bind(ForgotPasswordFragment forgotPasswordFragment, MvpPresenter mvpPresenter) {
            forgotPasswordFragment.presenter = (ForgotPasswordPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(ForgotPasswordFragment forgotPasswordFragment) {
            return forgotPasswordFragment.providePresenter();
        }
    }
}
