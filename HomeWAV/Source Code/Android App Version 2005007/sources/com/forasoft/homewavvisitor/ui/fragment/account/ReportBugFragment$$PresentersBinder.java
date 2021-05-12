package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.ReportBugPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class ReportBugFragment$$PresentersBinder extends moxy.PresenterBinder<ReportBugFragment> {
    public List<PresenterField<? super ReportBugFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: ReportBugFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<ReportBugFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, ReportBugPresenter.class);
        }

        public void bind(ReportBugFragment reportBugFragment, MvpPresenter mvpPresenter) {
            reportBugFragment.presenter = (ReportBugPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(ReportBugFragment reportBugFragment) {
            return reportBugFragment.providePresenter();
        }
    }
}
