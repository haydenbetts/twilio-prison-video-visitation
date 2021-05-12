package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class LoginPresenter__Factory implements Factory<LoginPresenter> {
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

    public LoginPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new LoginPresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (AuthInteractor) targetScope.getInstance(AuthInteractor.class));
    }
}
