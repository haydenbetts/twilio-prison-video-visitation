package com.forasoft.homewavvisitor.presentation.presenter.account;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class HistoryPresenter__Factory implements Factory<HistoryPresenter> {
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

    public HistoryPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new HistoryPresenter((Context) targetScope.getInstance(Context.class), (Router) targetScope.getInstance(Router.class), (Constants) targetScope.getInstance(Constants.class), (AccountInteractor) targetScope.getInstance(AccountInteractor.class), (UserDao) targetScope.getInstance(UserDao.class), (NotificationDao) targetScope.getInstance(NotificationDao.class));
    }
}
