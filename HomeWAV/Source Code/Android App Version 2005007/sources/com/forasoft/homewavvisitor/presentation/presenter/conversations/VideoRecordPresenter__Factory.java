package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.Factory;
import toothpick.Scope;

public final class VideoRecordPresenter__Factory implements Factory<VideoRecordPresenter> {
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

    public VideoRecordPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new VideoRecordPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (InmateDao) targetScope.getInstance(InmateDao.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.InmateId"));
    }
}
