package com.forasoft.homewavvisitor.navigation;

import toothpick.Factory;
import toothpick.Scope;

public final class CiceroneHolder__Factory implements Factory<CiceroneHolder> {
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

    public CiceroneHolder createInstance(Scope scope) {
        return new CiceroneHolder();
    }
}
