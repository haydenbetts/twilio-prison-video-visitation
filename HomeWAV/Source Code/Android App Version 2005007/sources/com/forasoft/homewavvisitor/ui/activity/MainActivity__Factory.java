package com.forasoft.homewavvisitor.ui.activity;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class MainActivity__Factory implements Factory<MainActivity> {
    private MemberInjector<MainActivity> memberInjector = new MainActivity__MemberInjector();

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

    public MainActivity createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        MainActivity mainActivity = new MainActivity();
        this.memberInjector.inject(mainActivity, targetScope);
        return mainActivity;
    }
}
