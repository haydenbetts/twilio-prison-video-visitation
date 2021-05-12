package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class TutorialsPresenter__Factory implements Factory<TutorialsPresenter> {
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

    public TutorialsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new TutorialsPresenter((Router) targetScope.getInstance(Router.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
