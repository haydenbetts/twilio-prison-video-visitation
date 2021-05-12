package com.forasoft.homewavvisitor.ui.activity.welcome;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class WelcomePresenter__Factory implements Factory<WelcomePresenter> {
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

    public WelcomePresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new WelcomePresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (AuthHolder) targetScope.getInstance(AuthHolder.class));
    }
}
