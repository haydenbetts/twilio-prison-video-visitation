package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.AccountApi;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import io.reactivex.subjects.BehaviorSubject;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class HomePresenter__Factory implements Factory<HomePresenter> {
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

    public HomePresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new HomePresenter((Router) targetScope.getInstance(Router.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (InmateDao) targetScope.getInstance(InmateDao.class), (VisitDao) targetScope.getInstance(VisitDao.class), (AccountApi) targetScope.getInstance(AccountApi.class), (BehaviorSubject) targetScope.getInstance(BehaviorSubject.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class), (Analytics) targetScope.getInstance(Analytics.class), (BusNotifier) targetScope.getInstance(BusNotifier.class));
    }
}
