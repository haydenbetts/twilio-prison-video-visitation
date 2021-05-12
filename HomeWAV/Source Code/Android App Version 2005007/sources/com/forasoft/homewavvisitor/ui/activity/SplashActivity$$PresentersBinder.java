package com.forasoft.homewavvisitor.ui.activity;

import com.forasoft.homewavvisitor.presentation.presenter.SplashPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class SplashActivity$$PresentersBinder extends moxy.PresenterBinder<SplashActivity> {
    public List<PresenterField<? super SplashActivity>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: SplashActivity$$PresentersBinder */
    public class PresenterBinder extends PresenterField<SplashActivity> {
        public PresenterBinder() {
            super("presenter", (String) null, SplashPresenter.class);
        }

        public void bind(SplashActivity splashActivity, MvpPresenter mvpPresenter) {
            splashActivity.presenter = (SplashPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(SplashActivity splashActivity) {
            return splashActivity.providePresenter();
        }
    }
}
