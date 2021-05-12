package com.forasoft.homewavvisitor.model.interactor.auth;

import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import toothpick.Factory;
import toothpick.Scope;

public final class AuthInteractor__Factory implements Factory<AuthInteractor> {
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

    public AuthInteractor createInstance(Scope scope) {
        return new AuthInteractor((AuthRepository) getTargetScope(scope).getInstance(AuthRepository.class));
    }
}
