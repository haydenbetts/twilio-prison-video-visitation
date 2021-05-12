package com.forasoft.homewavvisitor.model.data.auth;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.UserDao;
import toothpick.Factory;
import toothpick.Scope;

public final class AuthPrefs__Factory implements Factory<AuthPrefs> {
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

    public AuthPrefs createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AuthPrefs((Context) targetScope.getInstance(Context.class), (UserDao) targetScope.getInstance(UserDao.class));
    }
}
