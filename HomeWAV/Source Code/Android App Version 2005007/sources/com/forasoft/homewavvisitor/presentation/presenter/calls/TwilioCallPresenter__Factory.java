package com.forasoft.homewavvisitor.presentation.presenter.calls;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class TwilioCallPresenter__Factory implements Factory<TwilioCallPresenter> {
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

    public TwilioCallPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new TwilioCallPresenter((Context) targetScope.getInstance(Context.class), (Router) targetScope.getInstance(Router.class), (CallWrapper) targetScope.getInstance(CallWrapper.class), (CallDao) targetScope.getInstance(CallDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (Analytics) targetScope.getInstance(Analytics.class), (PusherHolder) targetScope.getInstance(PusherHolder.class));
    }
}
