package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import toothpick.Factory;
import toothpick.Scope;

public final class TestVideoPresenter__Factory implements Factory<TestVideoPresenter> {
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

    public TestVideoPresenter createInstance(Scope scope) {
        return new TestVideoPresenter((HomewavRouter) getTargetScope(scope).getInstance(HomewavRouter.class));
    }
}
