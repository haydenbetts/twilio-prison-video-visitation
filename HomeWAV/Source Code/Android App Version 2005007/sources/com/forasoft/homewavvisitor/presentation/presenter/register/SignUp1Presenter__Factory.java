package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.RxImagePicker;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterDataInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.model.repository.ImagesRepository;
import toothpick.Factory;
import toothpick.Scope;

public final class SignUp1Presenter__Factory implements Factory<SignUp1Presenter> {
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

    public SignUp1Presenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new SignUp1Presenter((RxImagePicker) targetScope.getInstance(RxImagePicker.class), (RegisterStepsInteractor) targetScope.getInstance(RegisterStepsInteractor.class), (RegisterDataInteractor) targetScope.getInstance(RegisterDataInteractor.class), (ImagesRepository) targetScope.getInstance(ImagesRepository.class));
    }
}
