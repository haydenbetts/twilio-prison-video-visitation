package com.forasoft.homewavvisitor.model.interactor.register;

import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import toothpick.Factory;
import toothpick.Scope;

public final class AddConnectionInteractor__Factory implements Factory<AddConnectionInteractor> {
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

    public AddConnectionInteractor createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AddConnectionInteractor((RegisterRepository) targetScope.getInstance(RegisterRepository.class), (Analytics) targetScope.getInstance(Analytics.class));
    }
}
