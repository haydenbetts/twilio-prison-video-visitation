package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import toothpick.Factory;
import toothpick.Scope;

public final class ConversationsPresenter__Factory implements Factory<ConversationsPresenter> {
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

    public ConversationsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new ConversationsPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (BusNotifier) targetScope.getInstance(BusNotifier.class));
    }
}
