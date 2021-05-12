package com.forasoft.homewavvisitor.toothpick.provider;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import toothpick.Factory;
import toothpick.Scope;

public final class RetrofitProvider__Factory implements Factory<RetrofitProvider> {
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

    public RetrofitProvider createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new RetrofitProvider((OkHttpClient) targetScope.getInstance(OkHttpClient.class), (Gson) targetScope.getInstance(Gson.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.ServerPath"));
    }
}
