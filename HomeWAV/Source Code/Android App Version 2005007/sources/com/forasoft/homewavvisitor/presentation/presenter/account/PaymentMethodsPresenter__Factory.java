package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import io.reactivex.subjects.BehaviorSubject;
import toothpick.Factory;
import toothpick.Scope;

public final class PaymentMethodsPresenter__Factory implements Factory<PaymentMethodsPresenter> {
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

    public PaymentMethodsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PaymentMethodsPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (BehaviorSubject) targetScope.getInstance(BehaviorSubject.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Cards"));
    }
}
