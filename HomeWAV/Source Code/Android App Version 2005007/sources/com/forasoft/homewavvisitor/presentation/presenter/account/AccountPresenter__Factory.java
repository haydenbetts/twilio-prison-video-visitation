package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class AccountPresenter__Factory implements Factory<AccountPresenter> {
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

    public AccountPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AccountPresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (Router) targetScope.getInstance(Router.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (AuthInteractor) targetScope.getInstance(AuthInteractor.class), (AccountInteractor) targetScope.getInstance(AccountInteractor.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (SignUpApi) targetScope.getInstance(SignUpApi.class), (BusNotifier) targetScope.getInstance(BusNotifier.class));
    }
}
