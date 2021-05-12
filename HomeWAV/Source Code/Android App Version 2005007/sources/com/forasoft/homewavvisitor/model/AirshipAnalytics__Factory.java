package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.analytics.AnalyticsRepository;
import com.urbanairship.UAirship;
import toothpick.Factory;
import toothpick.Scope;

public final class AirshipAnalytics__Factory implements Factory<AirshipAnalytics> {
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

    public AirshipAnalytics createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AirshipAnalytics((UAirship) targetScope.getInstance(UAirship.class), (AnalyticsRepository) targetScope.getInstance(AnalyticsRepository.class), (AuthHolder) targetScope.getInstance(AuthHolder.class));
    }
}
