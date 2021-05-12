package com.forasoft.homewavvisitor.presentation.adapter.account;

import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AccountHistoryAdapter__Factory implements Factory<AccountHistoryAdapter> {
    private MemberInjector<AccountHistoryAdapter> memberInjector = new AccountHistoryAdapter__MemberInjector();

    public Scope getTargetScope(Scope scope) {
        return scope;
    }

    public boolean hasProvidesReleasableAnnotation() {
        return false;
    }

    public boolean hasProvidesSingletonAnnotation() {
        return false;
    }

    public boolean hasReleasableAnnotation() {
        return false;
    }

    public boolean hasScopeAnnotation() {
        return false;
    }

    public boolean hasSingletonAnnotation() {
        return false;
    }

    public AccountHistoryAdapter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        AccountHistoryAdapter accountHistoryAdapter = new AccountHistoryAdapter((HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class));
        this.memberInjector.inject(accountHistoryAdapter, targetScope);
        return accountHistoryAdapter;
    }
}
