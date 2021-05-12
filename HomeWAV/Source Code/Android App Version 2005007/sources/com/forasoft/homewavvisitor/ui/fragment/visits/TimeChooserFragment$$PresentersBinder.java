package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class TimeChooserFragment$$PresentersBinder extends moxy.PresenterBinder<TimeChooserFragment> {
    public List<PresenterField<? super TimeChooserFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: TimeChooserFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<TimeChooserFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, TimeChooserPresenter.class);
        }

        public void bind(TimeChooserFragment timeChooserFragment, MvpPresenter mvpPresenter) {
            timeChooserFragment.presenter = (TimeChooserPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(TimeChooserFragment timeChooserFragment) {
            return timeChooserFragment.providePresenter();
        }
    }
}
