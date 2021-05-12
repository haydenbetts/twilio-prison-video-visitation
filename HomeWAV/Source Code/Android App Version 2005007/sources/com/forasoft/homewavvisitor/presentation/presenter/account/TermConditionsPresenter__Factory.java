package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class TermConditionsPresenter__Factory implements Factory<TermConditionsPresenter> {
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

    public TermConditionsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new TermConditionsPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
