package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import java.util.Map;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class DateChooserPresenter__Factory implements Factory<DateChooserPresenter> {
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

    public DateChooserPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new DateChooserPresenter((Router) targetScope.getInstance(Router.class), (Map) targetScope.getInstance(Map.class), (Inmate) targetScope.getInstance(Inmate.class), (VisitDao) targetScope.getInstance(VisitDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
