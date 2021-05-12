package com.forasoft.homewavvisitor.ui.fragment.auth;

import com.forasoft.homewavvisitor.presentation.presenter.auth.VerifyMethodPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class VerifyMethodFragment$$PresentersBinder extends moxy.PresenterBinder<VerifyMethodFragment> {
    public List<PresenterField<? super VerifyMethodFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: VerifyMethodFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<VerifyMethodFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, VerifyMethodPresenter.class);
        }

        public void bind(VerifyMethodFragment verifyMethodFragment, MvpPresenter mvpPresenter) {
            verifyMethodFragment.presenter = (VerifyMethodPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(VerifyMethodFragment verifyMethodFragment) {
            return verifyMethodFragment.providePresenter();
        }
    }
}
