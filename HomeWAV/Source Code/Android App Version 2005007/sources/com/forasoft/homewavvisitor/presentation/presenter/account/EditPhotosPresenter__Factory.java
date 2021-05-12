package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.RxImagePicker;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.repository.ImagesRepository;
import ru.terrakok.cicerone.Router;
import toothpick.Factory;
import toothpick.Scope;

public final class EditPhotosPresenter__Factory implements Factory<EditPhotosPresenter> {
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

    public EditPhotosPresenter createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        return new EditPhotosPresenter((RxImagePicker) targetScope.getInstance(RxImagePicker.class), (Router) targetScope.getInstance(Router.class), (AccountInteractor) targetScope.getInstance(AccountInteractor.class), (AuthHolder) targetScope.getInstance(AuthHolder.class), (ImagesRepository) targetScope.getInstance(ImagesRepository.class));
    }
}
