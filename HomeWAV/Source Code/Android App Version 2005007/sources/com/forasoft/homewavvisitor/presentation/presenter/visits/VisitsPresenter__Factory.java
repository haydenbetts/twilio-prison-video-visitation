package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class VisitsPresenter__Factory implements Factory<VisitsPresenter> {
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

    public VisitsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new VisitsPresenter((Router) targetScope.getInstance(Router.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (VisitDao) targetScope.getInstance(VisitDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
