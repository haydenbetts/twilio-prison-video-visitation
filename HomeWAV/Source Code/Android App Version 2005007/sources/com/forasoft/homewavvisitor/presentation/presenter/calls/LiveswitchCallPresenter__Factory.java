package com.forasoft.homewavvisitor.presentation.presenter.calls;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class LiveswitchCallPresenter__Factory implements Factory<LiveswitchCallPresenter> {
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

    public LiveswitchCallPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new LiveswitchCallPresenter((CallWrapper) targetScope.getInstance(CallWrapper.class), (Context) targetScope.getInstance(Context.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (Router) targetScope.getInstance(Router.class), (LiveswitchCallDataResponse) targetScope.getInstance(LiveswitchCallDataResponse.class, "callData"), (CallDao) targetScope.getInstance(CallDao.class), (PusherHolder) targetScope.getInstance(PusherHolder.class));
    }
}
