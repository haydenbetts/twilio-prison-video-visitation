package com.forasoft.homewavvisitor.model;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class IncomingCallService__Factory implements Factory<IncomingCallService> {
    private MemberInjector<IncomingCallService> memberInjector = new IncomingCallService__MemberInjector();

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

    public IncomingCallService createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        IncomingCallService incomingCallService = new IncomingCallService();
        this.memberInjector.inject(incomingCallService, targetScope);
        return incomingCallService;
    }
}
