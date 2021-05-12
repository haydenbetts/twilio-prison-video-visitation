package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.google.gson.Gson;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class NotificationsPresenter__Factory implements Factory<NotificationsPresenter> {
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

    public NotificationsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new NotificationsPresenter((Router) targetScope.getInstance(Router.class), (Gson) targetScope.getInstance(Gson.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class));
    }
}
