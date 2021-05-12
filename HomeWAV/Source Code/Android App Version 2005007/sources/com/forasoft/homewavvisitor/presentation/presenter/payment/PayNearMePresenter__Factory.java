package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.PaymentRequestData;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class PayNearMePresenter__Factory implements Factory<PayNearMePresenter> {
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

    public PayNearMePresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new PayNearMePresenter((Router) targetScope.getInstance(Router.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (PaymentRequestData) targetScope.getInstance(PaymentRequestData.class));
    }
}
