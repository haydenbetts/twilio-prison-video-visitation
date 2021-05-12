package com.forasoft.homewavvisitor.presentation.presenter.register;

import air.HomeWAV.R;
import android.net.Uri;
import com.forasoft.homewavvisitor.model.RxImagePicker;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterDataInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.model.repository.ImagesRepository;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.register.SignUp1View;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0012\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0012J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010\u001b\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\rH\u0002J\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010\u001e\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u001f\u001a\u00020\u0012H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp1Presenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp1View;", "rxImagePicker", "Lcom/forasoft/homewavvisitor/model/RxImagePicker;", "registerStepsInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;", "registerDataInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterDataInteractor;", "imagesRepository", "Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;", "(Lcom/forasoft/homewavvisitor/model/RxImagePicker;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterDataInteractor;Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;)V", "profileImagePath", "Landroid/net/Uri;", "userIDImagePath", "canMoveToNextStep", "", "changeStep", "", "checkNextAvailable", "onImageLoadingFailed", "error", "", "onNextClicked", "onPhotoIdImageClicked", "src", "", "onPhotoIdImagePicked", "imageUri", "onProfileImageClicked", "onProfileImagePicked", "saveStepData", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: SignUp1Presenter.kt */
public final class SignUp1Presenter extends BasePresenter<SignUp1View> {
    private final ImagesRepository imagesRepository;
    private Uri profileImagePath;
    private final RegisterDataInteractor registerDataInteractor;
    private final RegisterStepsInteractor registerStepsInteractor;
    private final RxImagePicker rxImagePicker;
    private Uri userIDImagePath;

    @Inject
    public SignUp1Presenter(RxImagePicker rxImagePicker2, RegisterStepsInteractor registerStepsInteractor2, RegisterDataInteractor registerDataInteractor2, ImagesRepository imagesRepository2) {
        Intrinsics.checkParameterIsNotNull(rxImagePicker2, "rxImagePicker");
        Intrinsics.checkParameterIsNotNull(registerStepsInteractor2, "registerStepsInteractor");
        Intrinsics.checkParameterIsNotNull(registerDataInteractor2, "registerDataInteractor");
        Intrinsics.checkParameterIsNotNull(imagesRepository2, "imagesRepository");
        this.rxImagePicker = rxImagePicker2;
        this.registerStepsInteractor = registerStepsInteractor2;
        this.registerDataInteractor = registerDataInteractor2;
        this.imagesRepository = imagesRepository2;
    }

    private final boolean canMoveToNextStep() {
        return (this.userIDImagePath == null || this.profileImagePath == null) ? false : true;
    }

    private final void saveStepData() {
        RegisterDataInteractor registerDataInteractor2 = this.registerDataInteractor;
        Uri uri = this.userIDImagePath;
        if (uri == null) {
            Intrinsics.throwNpe();
        }
        Uri uri2 = this.profileImagePath;
        if (uri2 == null) {
            Intrinsics.throwNpe();
        }
        registerDataInteractor2.saveStep1Data(uri, uri2);
    }

    public final void onProfileImageClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "src");
        RxImagePicker.Sources sources = Intrinsics.areEqual((Object) str, (Object) AvatarUpdaterDialog.CAMERA) ? RxImagePicker.Sources.CAMERA : RxImagePicker.Sources.GALLERY;
        CompositeDisposable disposables = getDisposables();
        SignUp1Presenter signUp1Presenter = this;
        Disposable subscribe = this.rxImagePicker.requestImage(sources).subscribe(new SignUp1Presenter$sam$io_reactivex_functions_Consumer$0(new SignUp1Presenter$onProfileImageClicked$1(signUp1Presenter)), new SignUp1Presenter$sam$io_reactivex_functions_Consumer$0(new SignUp1Presenter$onProfileImageClicked$2(signUp1Presenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "rxImagePicker.requestIma…is::onImageLoadingFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onProfileImagePicked(Uri uri) {
        if (uri != null) {
            this.profileImagePath = ImagesRepository.getScaledImage$default(this.imagesRepository, uri, 0, 0, 6, (Object) null);
            checkNextAvailable();
            ((SignUp1View) getViewState()).showProfileImage(this.profileImagePath);
        }
    }

    public final void onPhotoIdImageClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "src");
        RxImagePicker.Sources sources = Intrinsics.areEqual((Object) str, (Object) AvatarUpdaterDialog.CAMERA) ? RxImagePicker.Sources.CAMERA : RxImagePicker.Sources.GALLERY;
        CompositeDisposable disposables = getDisposables();
        SignUp1Presenter signUp1Presenter = this;
        Disposable subscribe = this.rxImagePicker.requestImage(sources).subscribe(new SignUp1Presenter$sam$io_reactivex_functions_Consumer$0(new SignUp1Presenter$onPhotoIdImageClicked$1(signUp1Presenter)), new SignUp1Presenter$sam$io_reactivex_functions_Consumer$0(new SignUp1Presenter$onPhotoIdImageClicked$2(signUp1Presenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "rxImagePicker.requestIma…is::onImageLoadingFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onPhotoIdImagePicked(Uri uri) {
        if (uri != null) {
            this.userIDImagePath = ImagesRepository.getScaledImage$default(this.imagesRepository, uri, 0, 0, 6, (Object) null);
            checkNextAvailable();
            ((SignUp1View) getViewState()).showPhotoIdImage(this.userIDImagePath);
        }
    }

    private final void checkNextAvailable() {
        if (canMoveToNextStep()) {
            ((SignUp1View) getViewState()).activateNextButton();
        } else {
            ((SignUp1View) getViewState()).blockNextButton();
        }
    }

    /* access modifiers changed from: private */
    public final void onImageLoadingFailed(Throwable th) {
        checkNextAvailable();
        if ((th != null ? th.getMessage() : null) == null) {
            ((SignUp1View) getViewState()).showMessage((int) R.string.error_loading_image);
            return;
        }
        SignUp1View signUp1View = (SignUp1View) getViewState();
        String message = th.getMessage();
        if (message == null) {
            Intrinsics.throwNpe();
        }
        signUp1View.showMessage(message);
    }

    public final void onNextClicked() {
        saveStepData();
        changeStep();
    }

    private final void changeStep() {
        this.registerStepsInteractor.moveToNextStep();
    }
}
