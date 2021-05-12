package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class CreateAccountPresenter__Factory implements Factory<CreateAccountPresenter> {
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

    public CreateAccountPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new CreateAccountPresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (RegisterStepsInteractor) targetScope.getInstance(RegisterStepsInteractor.class), (AuthHolder) targetScope.getInstance(AuthHolder.class));
    }
}
