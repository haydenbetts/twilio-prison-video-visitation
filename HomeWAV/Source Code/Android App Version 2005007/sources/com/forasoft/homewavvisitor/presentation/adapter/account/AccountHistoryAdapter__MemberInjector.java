package com.forasoft.homewavvisitor.presentation.adapter.account;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AccountHistoryAdapter__MemberInjector implements MemberInjector<AccountHistoryAdapter> {
    public void inject(AccountHistoryAdapter accountHistoryAdapter, Scope scope) {
        accountHistoryAdapter.inmateDao = (InmateDao) scope.getInstance(InmateDao.class);
        accountHistoryAdapter.authHolder = (AuthHolder) scope.getInstance(AuthHolder.class);
    }
}
