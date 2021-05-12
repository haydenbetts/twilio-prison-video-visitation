package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class TimeChooserPresenter__Factory implements Factory<TimeChooserPresenter> {
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

    public TimeChooserPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new TimeChooserPresenter((Inmate) targetScope.getInstance(Inmate.class), (Router) targetScope.getInstance(Router.class), (DaySlot) targetScope.getInstance(DaySlot.class), (HomewavApi) targetScope.getInstance(HomewavApi.class));
    }
}
