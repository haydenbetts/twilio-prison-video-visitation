package com.forasoft.homewavvisitor.model.interactor.register;

import toothpick.Factory;
import toothpick.Scope;

public final class RegisterStepsInteractor__Factory implements Factory<RegisterStepsInteractor> {
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

    public RegisterStepsInteractor createInstance(Scope scope) {
        return new RegisterStepsInteractor();
    }
}
