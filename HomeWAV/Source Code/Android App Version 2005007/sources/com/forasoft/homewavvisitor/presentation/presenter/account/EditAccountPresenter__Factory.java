package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class EditAccountPresenter__Factory implements Factory<EditAccountPresenter> {
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

    public EditAccountPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new EditAccountPresenter((Router) targetScope.getInstance(Router.class), (AccountInteractor) targetScope.getInstance(AccountInteractor.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (Constants) targetScope.getInstance(Constants.class));
    }
}
