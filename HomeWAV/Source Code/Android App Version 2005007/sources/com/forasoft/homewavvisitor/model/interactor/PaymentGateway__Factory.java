package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.PaymentRepository;
import com.forasoft.homewavvisitor.model.server.apis.PaymentApi;
import toothpick.Factory;
import toothpick.Scope;

public final class PaymentGateway__Factory implements Factory<PaymentGateway> {
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

    public PaymentGateway createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PaymentGateway((PaymentRepository) targetScope.getInstance(PaymentRepository.class), (InmateDao) targetScope.getInstance(InmateDao.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (PaymentApi) targetScope.getInstance(PaymentApi.class));
    }
}
