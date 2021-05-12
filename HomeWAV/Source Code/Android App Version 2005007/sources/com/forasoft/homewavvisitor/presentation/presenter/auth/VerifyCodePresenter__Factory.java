package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class VerifyCodePresenter__Factory implements Factory<VerifyCodePresenter> {
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

    public VerifyCodePresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new VerifyCodePresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (SignUpApi) targetScope.getInstance(SignUpApi.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Channel"), (AuthRepository) targetScope.getInstance(AuthRepository.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Analytics) targetScope.getInstance(Analytics.class));
    }
}
