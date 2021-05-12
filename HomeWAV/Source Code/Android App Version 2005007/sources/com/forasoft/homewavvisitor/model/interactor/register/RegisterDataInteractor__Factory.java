package com.forasoft.homewavvisitor.model.interactor.register;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import toothpick.Factory;
import toothpick.Scope;

public final class RegisterDataInteractor__Factory implements Factory<RegisterDataInteractor> {
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

    public RegisterDataInteractor createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new RegisterDataInteractor((RegisterRepository) targetScope.getInstance(RegisterRepository.class), (AuthHolder) targetScope.getInstance(AuthHolder.class));
    }
}
