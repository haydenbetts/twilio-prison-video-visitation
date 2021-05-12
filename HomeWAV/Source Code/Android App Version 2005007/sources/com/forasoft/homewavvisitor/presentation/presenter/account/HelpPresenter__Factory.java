package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class HelpPresenter__Factory implements Factory<HelpPresenter> {
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

    public HelpPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new HelpPresenter((Router) targetScope.getInstance(Router.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (NotificationDao) targetScope.getInstance(NotificationDao.class));
    }
}
