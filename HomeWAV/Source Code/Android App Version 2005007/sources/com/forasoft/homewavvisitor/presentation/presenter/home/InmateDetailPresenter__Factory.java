package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import io.reactivex.subjects.BehaviorSubject;
import toothpick.Factory;
import toothpick.Scope;

public final class InmateDetailPresenter__Factory implements Factory<InmateDetailPresenter> {
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

    public InmateDetailPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new InmateDetailPresenter((HomewavRouter) targetScope.getInstance(HomewavRouter.class), (Inmate) targetScope.getInstance(Inmate.class), (Analytics) targetScope.getInstance(Analytics.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (AccountInteractor) targetScope.getInstance(AccountInteractor.class), (BehaviorSubject) targetScope.getInstance(BehaviorSubject.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class));
    }
}
