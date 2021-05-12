package com.forasoft.homewavvisitor.toothpick.provider.api;

import retrofit2.Retrofit;
import toothpick.Factory;
import toothpick.Scope;

public final class HomewavApiProvider__Factory implements Factory<HomewavApiProvider> {
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

    public HomewavApiProvider createInstance(Scope scope) {
        return new HomewavApiProvider((Retrofit) getTargetScope(scope).getInstance(Retrofit.class));
    }
}
