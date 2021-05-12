package com.forasoft.homewavvisitor.presentation.presenter.account;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.register.AddConnectionInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class AddConnectionPresenter__Factory implements Factory<AddConnectionPresenter> {
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

    public AddConnectionPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AddConnectionPresenter((Router) targetScope.getInstance(Router.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (UserDao) targetScope.getInstance(UserDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (BusNotifier) targetScope.getInstance(BusNotifier.class), (Context) targetScope.getInstance(Context.class), (FacilitiesSubjectWrapper) targetScope.getInstance(FacilitiesSubjectWrapper.class), (AddConnectionInteractor) targetScope.getInstance(AddConnectionInteractor.class), (RegisterStepsInteractor) targetScope.getInstance(RegisterStepsInteractor.class));
    }
}
