package com.forasoft.homewavvisitor.model.system;

import android.content.Context;
import toothpick.Factory;
import toothpick.Scope;

public final class RingtoneManager__Factory implements Factory<RingtoneManager> {
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

    public RingtoneManager createInstance(Scope scope) {
        return new RingtoneManager((Context) getTargetScope(scope).getInstance(Context.class));
    }
}
