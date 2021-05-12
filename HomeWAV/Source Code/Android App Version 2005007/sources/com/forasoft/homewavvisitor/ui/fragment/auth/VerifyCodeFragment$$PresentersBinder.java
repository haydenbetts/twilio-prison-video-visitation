package com.forasoft.homewavvisitor.ui.fragment.auth;

import com.forasoft.homewavvisitor.presentation.presenter.auth.VerifyCodePresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class VerifyCodeFragment$$PresentersBinder extends moxy.PresenterBinder<VerifyCodeFragment> {
    public List<PresenterField<? super VerifyCodeFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: VerifyCodeFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<VerifyCodeFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, VerifyCodePresenter.class);
        }

        public void bind(VerifyCodeFragment verifyCodeFragment, MvpPresenter mvpPresenter) {
            verifyCodeFragment.presenter = (VerifyCodePresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(VerifyCodeFragment verifyCodeFragment) {
            return verifyCodeFragment.providePresenter();
        }
    }
}
