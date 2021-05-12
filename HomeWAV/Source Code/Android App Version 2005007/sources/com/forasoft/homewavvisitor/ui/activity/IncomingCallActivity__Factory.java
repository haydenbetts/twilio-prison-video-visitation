package com.forasoft.homewavvisitor.ui.activity;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class IncomingCallActivity__Factory implements Factory<IncomingCallActivity> {
    private MemberInjector<IncomingCallActivity> memberInjector = new IncomingCallActivity__MemberInjector();

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

    public IncomingCallActivity createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        IncomingCallActivity incomingCallActivity = new IncomingCallActivity();
        this.memberInjector.inject(incomingCallActivity, targetScope);
        return incomingCallActivity;
    }
}
