package com.forasoft.homewavvisitor.toothpick.provider.api;

import retrofit2.Retrofit;
import toothpick.Factory;
import toothpick.Scope;

public final class SignUpApiProvider__Factory implements Factory<SignUpApiProvider> {
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

    public SignUpApiProvider createInstance(Scope scope) {
        return new SignUpApiProvider((Retrofit) getTargetScope(scope).getInstance(Retrofit.class));
    }
}
