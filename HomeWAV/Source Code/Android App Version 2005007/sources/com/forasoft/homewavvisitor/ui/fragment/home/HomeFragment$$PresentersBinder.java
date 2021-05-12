package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.presentation.presenter.home.HomePresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class HomeFragment$$PresentersBinder extends moxy.PresenterBinder<HomeFragment> {
    public List<PresenterField<? super HomeFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: HomeFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<HomeFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, HomePresenter.class);
        }

        public void bind(HomeFragment homeFragment, MvpPresenter mvpPresenter) {
            homeFragment.presenter = (HomePresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(HomeFragment homeFragment) {
            return homeFragment.providePresenter();
        }
    }
}
