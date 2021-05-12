package com.forasoft.homewavvisitor.toothpick.provider.api;

import retrofit2.Retrofit;
import toothpick.Factory;
import toothpick.Scope;

public final class AccountApiProvider__Factory implements Factory<AccountApiProvider> {
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

    public AccountApiProvider createInstance(Scope scope) {
        return new AccountApiProvider((Retrofit) getTargetScope(scope).getInstance(Retrofit.class));
    }
}
