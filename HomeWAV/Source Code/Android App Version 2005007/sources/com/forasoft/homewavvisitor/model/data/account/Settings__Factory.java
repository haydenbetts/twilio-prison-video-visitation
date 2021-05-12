package com.forasoft.homewavvisitor.model.data.account;

import android.content.Context;
import toothpick.Factory;
import toothpick.Scope;

public final class Settings__Factory implements Factory<Settings> {
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

    public Settings createInstance(Scope scope) {
        return new Settings((Context) getTargetScope(scope).getInstance(Context.class));
    }
}
