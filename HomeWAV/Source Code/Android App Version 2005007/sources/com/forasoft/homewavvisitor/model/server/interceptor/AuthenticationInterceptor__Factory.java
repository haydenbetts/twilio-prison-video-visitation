package com.forasoft.homewavvisitor.model.server.interceptor;

import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.toothpick.provider.OkHttpClientProvider;
import com.google.gson.Gson;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class AuthenticationInterceptor__Factory implements Factory<AuthenticationInterceptor> {
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

    public AuthenticationInterceptor createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AuthenticationInterceptor((AuthHolder) targetScope.getInstance(AuthHolder.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (MessageDao) targetScope.getInstance(MessageDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (VisitDao) targetScope.getInstance(VisitDao.class), (CallDao) targetScope.getInstance(CallDao.class), (CreditDao) targetScope.getInstance(CreditDao.class), (Router) targetScope.getInstance(Router.class), (Settings) targetScope.getInstance(Settings.class), (OkHttpClientProvider) targetScope.getInstance(OkHttpClientProvider.class), (Gson) targetScope.getInstance(Gson.class), (UserDao) targetScope.getInstance(UserDao.class));
    }
}
