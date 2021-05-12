package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class SignUp4Presenter__Factory implements Factory<SignUp4Presenter> {
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

    public SignUp4Presenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new SignUp4Presenter((Router) targetScope.getInstance(Router.class), (SignUpApi) targetScope.getInstance(SignUpApi.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (InmateDao) targetScope.getInstance(InmateDao.class), (PaymentGateway) targetScope.getInstance(PaymentGateway.class), (NotificationDao) targetScope.getInstance(NotificationDao.class), (HeartbeatRepository) targetScope.getInstance(HeartbeatRepository.class));
    }
}
