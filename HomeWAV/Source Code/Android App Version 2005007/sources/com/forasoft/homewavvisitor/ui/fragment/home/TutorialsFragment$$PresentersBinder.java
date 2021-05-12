package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.presentation.presenter.home.TutorialsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class TutorialsFragment$$PresentersBinder extends moxy.PresenterBinder<TutorialsFragment> {
    public List<PresenterField<? super TutorialsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: TutorialsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<TutorialsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, TutorialsPresenter.class);
        }

        public void bind(TutorialsFragment tutorialsFragment, MvpPresenter mvpPresenter) {
            tutorialsFragment.presenter = (TutorialsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(TutorialsFragment tutorialsFragment) {
            return tutorialsFragment.providePresenter();
        }
    }
}
