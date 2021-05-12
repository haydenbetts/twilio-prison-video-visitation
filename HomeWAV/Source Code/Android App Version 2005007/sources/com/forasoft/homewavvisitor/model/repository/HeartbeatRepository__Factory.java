package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class HeartbeatRepository__Factory implements Factory<HeartbeatRepository> {
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

    public HeartbeatRepository createInstance(Scope scope) {
        return new HeartbeatRepository((HomewavApi) getTargetScope(scope).getInstance(HomewavApi.class));
    }
}
