package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class InmateChooserPresenter__Factory implements Factory<InmateChooserPresenter> {
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

    public InmateChooserPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new InmateChooserPresenter((Router) targetScope.getInstance(Router.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (InmateDao) targetScope.getInstance(InmateDao.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class));
    }
}
