package com.forasoft.homewavvisitor.presentation.presenter.register;

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

public final class AddConnectionSignUpPresenter__Factory implements Factory<AddConnectionSignUpPresenter> {
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

    public AddConnectionSignUpPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new AddConnectionSignUpPresenter((Context) targetScope.getInstance(Context.class), (Router) targetScope.getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global"), (AuthHolder) targetScope.getInstance(AuthHolder.class), (UserDao) targetScope.getInstance(UserDao.class), (HomewavApi) targetScope.getInstance(HomewavApi.class), (BusNotifier) targetScope.getInstance(BusNotifier.class), (FacilitiesSubjectWrapper) targetScope.getInstance(FacilitiesSubjectWrapper.class), (AddConnectionInteractor) targetScope.getInstance(AddConnectionInteractor.class), (RegisterStepsInteractor) targetScope.getInstance(RegisterStepsInteractor.class));
    }
}
