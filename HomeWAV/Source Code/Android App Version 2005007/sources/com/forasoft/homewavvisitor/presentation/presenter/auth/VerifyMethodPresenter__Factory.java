package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class VerifyMethodPresenter__Factory implements Factory<VerifyMethodPresenter> {
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

    public VerifyMethodPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new VerifyMethodPresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (SignUpApi) targetScope.getInstance(SignUpApi.class), (AccountInteractor) targetScope.getInstance(AccountInteractor.class), (AuthRepository) targetScope.getInstance(AuthRepository.class));
    }
}
