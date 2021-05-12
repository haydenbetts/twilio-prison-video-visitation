package com.forasoft.homewavvisitor.model.pusher;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.google.gson.Gson;
import toothpick.Factory;
import toothpick.Scope;

public final class PusherHolder__Factory implements Factory<PusherHolder> {
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

    public PusherHolder createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PusherHolder((HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Gson) targetScope.getInstance(Gson.class));
    }
}
