package com.forasoft.homewavvisitor.presentation.presenter.account;

import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class AccountHistoryActivityPresenter__Factory implements Factory<AccountHistoryActivityPresenter> {
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

    public AccountHistoryActivityPresenter createInstance(Scope scope) {
        return new AccountHistoryActivityPresenter((Router) getTargetScope(scope).getInstance(Router.class));
    }
}
