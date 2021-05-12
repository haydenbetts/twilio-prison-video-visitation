package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class PendingInmatesPresenter__Factory implements Factory<PendingInmatesPresenter> {
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

    public PendingInmatesPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PendingInmatesPresenter((Router) targetScope.getInstance(Router.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (InmateDao) targetScope.getInstance(InmateDao.class), (SignUpApi) targetScope.getInstance(SignUpApi.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class));
    }
}
