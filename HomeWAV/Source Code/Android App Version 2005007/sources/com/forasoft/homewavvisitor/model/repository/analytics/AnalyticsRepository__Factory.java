package com.forasoft.homewavvisitor.model.repository.analytics;

import com.forasoft.homewavvisitor.dao.UserAnalyticsDao;
import toothpick.Factory;
import toothpick.Scope;

public final class AnalyticsRepository__Factory implements Factory<AnalyticsRepository> {
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

    public AnalyticsRepository createInstance(Scope scope) {
        return new AnalyticsRepository((UserAnalyticsDao) getTargetScope(scope).getInstance(UserAnalyticsDao.class));
    }
}
