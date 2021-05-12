package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import java.io.File;
import toothpick.Factory;
import toothpick.Scope;

public final class ConversationPresenter__Factory implements Factory<ConversationPresenter> {
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

    public ConversationPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new ConversationPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Settings) targetScope.getInstance(Settings.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.InmateId"), (File) targetScope.getInstance(File.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (PusherHolder) targetScope.getInstance(PusherHolder.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (MessageDao) targetScope.getInstance(MessageDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (CallDao) targetScope.getInstance(CallDao.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (Analytics) targetScope.getInstance(Analytics.class));
    }
}
