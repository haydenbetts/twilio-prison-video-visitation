package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.presentation.presenter.calls.LiveswitchCallPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class LiveswitchCallFragment$$PresentersBinder extends moxy.PresenterBinder<LiveswitchCallFragment> {
    public List<PresenterField<? super LiveswitchCallFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: LiveswitchCallFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<LiveswitchCallFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, LiveswitchCallPresenter.class);
        }

        public void bind(LiveswitchCallFragment liveswitchCallFragment, MvpPresenter mvpPresenter) {
            liveswitchCallFragment.presenter = (LiveswitchCallPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(LiveswitchCallFragment liveswitchCallFragment) {
            return liveswitchCallFragment.providePresenter();
        }
    }
}
