package com.forasoft.homewavvisitor.model.repository;

import android.content.Context;
import toothpick.Factory;
import toothpick.Scope;

public final class ImagesRepository__Factory implements Factory<ImagesRepository> {
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

    public ImagesRepository createInstance(Scope scope) {
        return new ImagesRepository((Context) getTargetScope(scope).getInstance(Context.class));
    }
}
