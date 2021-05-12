package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class MainPresenter__Factory implements Factory<MainPresenter> {
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

    public MainPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new MainPresenter((AuthHolder) targetScope.getInstance(AuthHolder.class), (AuthInteractor) targetScope.getInstance(AuthInteractor.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (HomewavRouter) targetScope.getInstance(HomewavRouter.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (PusherHolder) targetScope.getInstance(PusherHolder.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
