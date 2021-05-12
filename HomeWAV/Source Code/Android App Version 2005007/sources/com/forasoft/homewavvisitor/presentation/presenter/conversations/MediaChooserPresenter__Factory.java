package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.MediaLoader;
import com.forasoft.homewavvisitor.model.repository.ImagesRepository;
import toothpick.Factory;
import toothpick.Scope;

public final class MediaChooserPresenter__Factory implements Factory<MediaChooserPresenter> {
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

    public MediaChooserPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new MediaChooserPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (String) targetScope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.InmateId"), (MediaLoader) targetScope.getInstance(MediaLoader.class), (InmateDao) targetScope.getInstance(InmateDao.class), (ImagesRepository) targetScope.getInstance(ImagesRepository.class));
    }
}
