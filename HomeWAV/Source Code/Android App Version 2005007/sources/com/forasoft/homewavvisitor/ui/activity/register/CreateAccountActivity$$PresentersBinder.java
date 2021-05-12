package com.forasoft.homewavvisitor.ui.activity.register;

import com.forasoft.homewavvisitor.presentation.presenter.register.CreateAccountPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class CreateAccountActivity$$PresentersBinder extends moxy.PresenterBinder<CreateAccountActivity> {
    public List<PresenterField<? super CreateAccountActivity>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: CreateAccountActivity$$PresentersBinder */
    public class PresenterBinder extends PresenterField<CreateAccountActivity> {
        public PresenterBinder() {
            super("presenter", (String) null, CreateAccountPresenter.class);
        }

        public void bind(CreateAccountActivity createAccountActivity, MvpPresenter mvpPresenter) {
            createAccountActivity.presenter = (CreateAccountPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(CreateAccountActivity createAccountActivity) {
            return createAccountActivity.providePresenter();
        }
    }
}
