package com.forasoft.homewavvisitor.model;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class HeartbeatService__Factory implements Factory<HeartbeatService> {
    private MemberInjector<HeartbeatService> memberInjector = new HeartbeatService__MemberInjector();

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

    public HeartbeatService createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        HeartbeatService heartbeatService = new HeartbeatService();
        this.memberInjector.inject(heartbeatService, targetScope);
        return heartbeatService;
    }
}
