package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.PaymentMethodsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class PaymentMethodsFragment$$PresentersBinder extends moxy.PresenterBinder<PaymentMethodsFragment> {
    public List<PresenterField<? super PaymentMethodsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: PaymentMethodsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<PaymentMethodsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, PaymentMethodsPresenter.class);
        }

        public void bind(PaymentMethodsFragment paymentMethodsFragment, MvpPresenter mvpPresenter) {
            paymentMethodsFragment.presenter = (PaymentMethodsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(PaymentMethodsFragment paymentMethodsFragment) {
            return paymentMethodsFragment.providePresenter();
        }
    }
}
