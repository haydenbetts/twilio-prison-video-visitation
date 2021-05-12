package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.payment.PaymentStateHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class ChooseFundsPresenter__Factory implements Factory<ChooseFundsPresenter> {
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

    public ChooseFundsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new ChooseFundsPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (PaymentStateHolder) targetScope.getInstance(PaymentStateHolder.class), (Analytics) targetScope.getInstance(Analytics.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
