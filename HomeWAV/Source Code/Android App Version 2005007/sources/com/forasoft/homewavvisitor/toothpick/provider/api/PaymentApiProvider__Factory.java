package com.forasoft.homewavvisitor.toothpick.provider.api;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import toothpick.Factory;
import toothpick.Scope;

public final class PaymentApiProvider__Factory implements Factory<PaymentApiProvider> {
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

    public PaymentApiProvider createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PaymentApiProvider((OkHttpClient) targetScope.getInstance(OkHttpClient.class), (Gson) targetScope.getInstance(Gson.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.ServerPath"));
    }
}
