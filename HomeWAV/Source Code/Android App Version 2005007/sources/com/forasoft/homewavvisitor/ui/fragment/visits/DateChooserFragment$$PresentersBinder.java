package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.forasoft.homewavvisitor.presentation.presenter.visits.DateChooserPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class DateChooserFragment$$PresentersBinder extends moxy.PresenterBinder<DateChooserFragment> {
    public List<PresenterField<? super DateChooserFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: DateChooserFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<DateChooserFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, DateChooserPresenter.class);
        }

        public void bind(DateChooserFragment dateChooserFragment, MvpPresenter mvpPresenter) {
            dateChooserFragment.presenter = (DateChooserPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(DateChooserFragment dateChooserFragment) {
            return dateChooserFragment.providePresenter();
        }
    }
}
