package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class S3MediaLoader__Factory implements Factory<S3MediaLoader> {
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

    public S3MediaLoader createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new S3MediaLoader((String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.InmateId"), (InmateDao) targetScope.getInstance(InmateDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
