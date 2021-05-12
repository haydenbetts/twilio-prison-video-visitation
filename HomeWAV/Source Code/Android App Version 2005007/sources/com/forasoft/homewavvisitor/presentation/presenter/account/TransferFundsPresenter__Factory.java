package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import toothpick.Factory;
import toothpick.Scope;

public final class TransferFundsPresenter__Factory implements Factory<TransferFundsPresenter> {
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

    public TransferFundsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new TransferFundsPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (SignUpApi) targetScope.getInstance(SignUpApi.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class));
    }
}
