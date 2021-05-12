package com.forasoft.homewavvisitor.toothpick.provider;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.google.gson.Gson;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class OkHttpClientProvider__Factory implements Factory<OkHttpClientProvider> {
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

    public OkHttpClientProvider createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new OkHttpClientProvider((Context) targetScope.getInstance(Context.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Router) targetScope.getInstance(Router.class), (MessageDao) targetScope.getInstance(MessageDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (VisitDao) targetScope.getInstance(VisitDao.class), (CallDao) targetScope.getInstance(CallDao.class), (CreditDao) targetScope.getInstance(CreditDao.class), (Settings) targetScope.getInstance(Settings.class), (Gson) targetScope.getInstance(Gson.class), (UserDao) targetScope.getInstance(UserDao.class));
    }
}
