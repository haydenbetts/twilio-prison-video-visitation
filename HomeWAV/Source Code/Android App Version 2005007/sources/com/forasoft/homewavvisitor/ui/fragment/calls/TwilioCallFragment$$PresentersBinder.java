package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.presentation.presenter.calls.TwilioCallPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class TwilioCallFragment$$PresentersBinder extends moxy.PresenterBinder<TwilioCallFragment> {
    public List<PresenterField<? super TwilioCallFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: TwilioCallFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<TwilioCallFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, TwilioCallPresenter.class);
        }

        public void bind(TwilioCallFragment twilioCallFragment, MvpPresenter mvpPresenter) {
            twilioCallFragment.presenter = (TwilioCallPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(TwilioCallFragment twilioCallFragment) {
            return twilioCallFragment.providePresenter();
        }
    }
}
