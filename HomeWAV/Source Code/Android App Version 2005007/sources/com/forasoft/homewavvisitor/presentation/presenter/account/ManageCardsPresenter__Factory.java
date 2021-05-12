package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import io.reactivex.subjects.BehaviorSubject;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class ManageCardsPresenter__Factory implements Factory<ManageCardsPresenter> {
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

    public ManageCardsPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new ManageCardsPresenter((Router) targetScope.getInstance(Router.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (BehaviorSubject) targetScope.getInstance(BehaviorSubject.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Cards"));
    }
}
