package com.forasoft.homewavvisitor.toothpick.provider;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import toothpick.Factory;
import toothpick.Scope;

public final class GsonProvider__Factory implements Factory<GsonProvider> {
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

    public GsonProvider createInstance(Scope scope) {
        return new GsonProvider((AuthHolder) getTargetScope(scope).getInstance(AuthHolder.class));
    }
}
