package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class TransferFundsFragment$$PresentersBinder extends moxy.PresenterBinder<TransferFundsFragment> {
    public List<PresenterField<? super TransferFundsFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: TransferFundsFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<TransferFundsFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, TransferFundsPresenter.class);
        }

        public void bind(TransferFundsFragment transferFundsFragment, MvpPresenter mvpPresenter) {
            transferFundsFragment.presenter = (TransferFundsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(TransferFundsFragment transferFundsFragment) {
            return transferFundsFragment.providePresenter();
        }
    }
}
