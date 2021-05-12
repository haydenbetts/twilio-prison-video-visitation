package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class RefundsPresenter__Factory implements Factory<RefundsPresenter> {
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

    public RefundsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new RefundsPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (InmateDao) targetScope.getInstance(InmateDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
