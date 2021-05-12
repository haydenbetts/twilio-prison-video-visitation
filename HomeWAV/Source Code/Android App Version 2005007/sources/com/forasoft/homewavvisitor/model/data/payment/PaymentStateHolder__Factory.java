package com.forasoft.homewavvisitor.model.data.payment;

import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import toothpick.Factory;
import toothpick.Scope;

public final class PaymentStateHolder__Factory implements Factory<PaymentStateHolder> {
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

    public PaymentStateHolder createInstance(Scope scope) {
        return new PaymentStateHolder((PaymentGateway) getTargetScope(scope).getInstance(PaymentGateway.class));
    }
}
