package com.forasoft.homewavvisitor.ui.activity;

import com.forasoft.homewavvisitor.presentation.presenter.MainPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.PresenterBinder;
import moxy.presenter.PresenterField;

public class MainActivity$$PresentersBinder extends PresenterBinder<MainActivity> {
    public List<PresenterField<? super MainActivity>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new MainPresenterBinder());
        return arrayList;
    }

    /* compiled from: MainActivity$$PresentersBinder */
    public class MainPresenterBinder extends PresenterField<MainActivity> {
        public MainPresenterBinder() {
            super("mainPresenter", (String) null, MainPresenter.class);
        }

        public void bind(MainActivity mainActivity, MvpPresenter mvpPresenter) {
            mainActivity.mainPresenter = (MainPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(MainActivity mainActivity) {
            return mainActivity.providePresenter();
        }
    }
}
