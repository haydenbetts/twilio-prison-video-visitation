package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.AccountPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class AccountFragment$$PresentersBinder extends moxy.PresenterBinder<AccountFragment> {
    public List<PresenterField<? super AccountFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: AccountFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<AccountFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, AccountPresenter.class);
        }

        public void bind(AccountFragment accountFragment, MvpPresenter mvpPresenter) {
            accountFragment.presenter = (AccountPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(AccountFragment accountFragment) {
            return accountFragment.providePresenter();
        }
    }
}
