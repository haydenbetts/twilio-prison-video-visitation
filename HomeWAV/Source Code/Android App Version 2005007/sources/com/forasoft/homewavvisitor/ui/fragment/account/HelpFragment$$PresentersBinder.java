package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.HelpPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class HelpFragment$$PresentersBinder extends moxy.PresenterBinder<HelpFragment> {
    public List<PresenterField<? super HelpFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: HelpFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<HelpFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, HelpPresenter.class);
        }

        public void bind(HelpFragment helpFragment, MvpPresenter mvpPresenter) {
            helpFragment.presenter = (HelpPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(HelpFragment helpFragment) {
            return helpFragment.providePresenter();
        }
    }
}
