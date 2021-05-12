package com.forasoft.homewavvisitor.model.repository.register;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import toothpick.Factory;
import toothpick.Scope;

public final class RegisterRepository__Factory implements Factory<RegisterRepository> {
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

    public RegisterRepository createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new RegisterRepository((Context) targetScope.getInstance(Context.class), (UserDao) targetScope.getInstance(UserDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (SignUpApi) targetScope.getInstance(SignUpApi.class), (Constants) targetScope.getInstance(Constants.class));
    }
}
