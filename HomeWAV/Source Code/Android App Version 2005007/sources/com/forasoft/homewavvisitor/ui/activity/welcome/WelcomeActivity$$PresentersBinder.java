package com.forasoft.homewavvisitor.ui.activity.welcome;

import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class WelcomeActivity$$PresentersBinder extends moxy.PresenterBinder<WelcomeActivity> {
    public List<PresenterField<? super WelcomeActivity>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: WelcomeActivity$$PresentersBinder */
    public class PresenterBinder extends PresenterField<WelcomeActivity> {
        public PresenterBinder() {
            super("presenter", (String) null, WelcomePresenter.class);
        }

        public void bind(WelcomeActivity welcomeActivity, MvpPresenter mvpPresenter) {
            welcomeActivity.presenter = (WelcomePresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(WelcomeActivity welcomeActivity) {
            return welcomeActivity.providePresenter();
        }
    }
}
