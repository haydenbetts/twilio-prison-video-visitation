package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterDataInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class SignUp2Presenter__Factory implements Factory<SignUp2Presenter> {
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

    public SignUp2Presenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new SignUp2Presenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (RegisterStepsInteractor) targetScope.getInstance(RegisterStepsInteractor.class), (RegisterDataInteractor) targetScope.getInstance(RegisterDataInteractor.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Constants) targetScope.getInstance(Constants.class));
    }
}
