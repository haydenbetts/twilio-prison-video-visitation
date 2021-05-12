package com.forasoft.homewavvisitor.model;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.InmateDao;
import toothpick.Factory;
import toothpick.Scope;

public final class MediaStoreMediaLoader__Factory implements Factory<MediaStoreMediaLoader> {
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

    public MediaStoreMediaLoader createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new MediaStoreMediaLoader((Context) targetScope.getInstance(Context.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.InmateId"), (InmateDao) targetScope.getInstance(InmateDao.class));
    }
}
