package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.data.payment.PaymentStateHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import toothpick.Factory;
import toothpick.Scope;

public final class AddCardPresenter__Factory implements Factory<AddCardPresenter> {
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

    public AddCardPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AddCardPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (PaymentStateHolder) targetScope.getInstance(PaymentStateHolder.class));
    }
}
