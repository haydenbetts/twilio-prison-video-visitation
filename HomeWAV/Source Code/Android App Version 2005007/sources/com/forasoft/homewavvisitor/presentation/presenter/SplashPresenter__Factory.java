package com.forasoft.homewavvisitor.presentation.presenter;

import android.content.Context;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class SplashPresenter__Factory implements Factory<SplashPresenter> {
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

    public SplashPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new SplashPresenter((Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (Context) targetScope.getInstance(Context.class), (AuthInteractor) targetScope.getInstance(AuthInteractor.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (Analytics) targetScope.getInstance(Analytics.class));
    }
}
