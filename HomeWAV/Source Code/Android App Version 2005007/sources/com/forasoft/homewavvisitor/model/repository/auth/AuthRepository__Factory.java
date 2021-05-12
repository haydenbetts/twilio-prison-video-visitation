package com.forasoft.homewavvisitor.model.repository.auth;

import com.forasoft.homewavvisitor.AppInfo;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import toothpick.Factory;
import toothpick.Scope;

public final class AuthRepository__Factory implements Factory<AuthRepository> {
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

    public AuthRepository createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AuthRepository((SignUpApi) targetScope.getInstance(SignUpApi.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (AppInfo) targetScope.getInstance(AppInfo.class), (Analytics) targetScope.getInstance(Analytics.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Settings) targetScope.getInstance(Settings.class), (UserDao) targetScope.getInstance(UserDao.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (MessageDao) targetScope.getInstance(MessageDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (VisitDao) targetScope.getInstance(VisitDao.class), (CallDao) targetScope.getInstance(CallDao.class), (CreditDao) targetScope.getInstance(CreditDao.class), (PusherHolder) targetScope.getInstance(PusherHolder.class));
    }
}
