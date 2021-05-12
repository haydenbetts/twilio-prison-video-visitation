package com.forasoft.homewavvisitor.model.interactor.account;

import com.forasoft.homewavvisitor.model.repository.account.AccountRepository;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import toothpick.Factory;
import toothpick.Scope;

public final class AccountInteractor__Factory implements Factory<AccountInteractor> {
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

    public AccountInteractor createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AccountInteractor((RegisterRepository) targetScope.getInstance(RegisterRepository.class), (AccountRepository) targetScope.getInstance(AccountRepository.class));
    }
}
