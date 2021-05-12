package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.TestVideoPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class TestVideoFragment$$PresentersBinder extends moxy.PresenterBinder<TestVideoFragment> {
    public List<PresenterField<? super TestVideoFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: TestVideoFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<TestVideoFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, TestVideoPresenter.class);
        }

        public void bind(TestVideoFragment testVideoFragment, MvpPresenter mvpPresenter) {
            testVideoFragment.presenter = (TestVideoPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(TestVideoFragment testVideoFragment) {
            return testVideoFragment.providePresenter();
        }
    }
}
