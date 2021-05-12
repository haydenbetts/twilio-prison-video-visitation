package com.forasoft.homewavvisitor.ui.fragment.payment;

import com.forasoft.homewavvisitor.presentation.presenter.payment.PayNearMePresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class PayNearMeFragment$$PresentersBinder extends moxy.PresenterBinder<PayNearMeFragment> {
    public List<PresenterField<? super PayNearMeFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: PayNearMeFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<PayNearMeFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, PayNearMePresenter.class);
        }

        public void bind(PayNearMeFragment payNearMeFragment, MvpPresenter mvpPresenter) {
            payNearMeFragment.presenter = (PayNearMePresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(PayNearMeFragment payNearMeFragment) {
            return payNearMeFragment.providePresenter();
        }
    }
}
