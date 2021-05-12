package com.forasoft.homewavvisitor.navigation;

import toothpick.Factory;
import toothpick.Scope;

public final class BusNotifier__Factory implements Factory<BusNotifier> {
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

    public BusNotifier createInstance(Scope scope) {
        return new BusNotifier();
    }
}
