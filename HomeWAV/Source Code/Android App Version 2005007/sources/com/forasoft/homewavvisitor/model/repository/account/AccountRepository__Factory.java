package com.forasoft.homewavvisitor.model.repository.account;

import com.forasoft.homewavvisitor.model.server.apis.AccountApi;
import toothpick.Factory;
import toothpick.Scope;

public final class AccountRepository__Factory implements Factory<AccountRepository> {
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

    public AccountRepository createInstance(Scope scope) {
        return new AccountRepository((AccountApi) getTargetScope(scope).getInstance(AccountApi.class));
    }
}
