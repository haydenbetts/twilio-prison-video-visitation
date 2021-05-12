package com.forasoft.homewavvisitor.presentation.presenter.visits;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class VisitDetailsPresenter__Factory implements Factory<VisitDetailsPresenter> {
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

    public VisitDetailsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new VisitDetailsPresenter((Context) targetScope.getInstance(Context.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.VisitId"), (Router) targetScope.getInstance(Router.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (VisitDao) targetScope.getInstance(VisitDao.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class));
    }
}
