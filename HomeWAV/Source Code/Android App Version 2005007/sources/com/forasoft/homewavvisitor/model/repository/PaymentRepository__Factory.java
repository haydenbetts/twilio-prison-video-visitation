package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.AppInfo;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.server.apis.PaymentApi;
import toothpick.Factory;
import toothpick.Scope;

public final class PaymentRepository__Factory implements Factory<PaymentRepository> {
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

    public PaymentRepository createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PaymentRepository((PaymentApi) targetScope.getInstance(PaymentApi.class), (CreditDao) targetScope.getInstance(CreditDao.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (AppInfo) targetScope.getInstance(AppInfo.class));
    }
}
