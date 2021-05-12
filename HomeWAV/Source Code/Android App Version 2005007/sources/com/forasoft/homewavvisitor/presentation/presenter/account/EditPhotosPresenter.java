package com.forasoft.homewavvisitor.presentation.presenter.account;

import air.HomeWAV.R;
import android.net.Uri;
import com.forasoft.homewavvisitor.model.RxImagePicker;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.repository.ImagesRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.EditPhotosView;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0006\u0010\u001a\u001a\u00020\u000fJ\u0012\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0014J\u0012\u0010\u001f\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010\u001dH\u0002J\u0006\u0010!\u001a\u00020\u0019J\u000e\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$J\u0012\u0010%\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010\u0015H\u0002J\u0016\u0010'\u001a\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020)0(H\u0002J\u000e\u0010*\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$J\u0012\u0010+\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010,\u001a\u00020\u0019H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditPhotosPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/EditPhotosView;", "rxImagePicker", "Lcom/forasoft/homewavvisitor/model/RxImagePicker;", "router", "Lru/terrakok/cicerone/Router;", "accountInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "imagesRepository", "Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;", "(Lcom/forasoft/homewavvisitor/model/RxImagePicker;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;)V", "exitOnSuccess", "", "getExitOnSuccess", "()Z", "setExitOnSuccess", "(Z)V", "profileImagePath", "Landroid/net/Uri;", "userIDImagePath", "canMoveToNextStep", "checkNextAvailable", "", "onBackPressed", "onEditFailed", "it", "", "onFirstViewAttach", "onImageLoadingFailed", "error", "onNextClicked", "onPhotoIdImageClicked", "src", "", "onPhotoIdImagePicked", "imageUri", "onProfileEditSuccess", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onProfileImageClicked", "onProfileImagePicked", "saveStepData", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: EditPhotosPresenter.kt */
public final class EditPhotosPresenter extends BasePresenter<EditPhotosView> {
    private final AccountInteractor accountInteractor;
    private final AuthHolder authHolder;
    private boolean exitOnSuccess = true;
    private final ImagesRepository imagesRepository;
    private Uri profileImagePath;
    private final Router router;
    private final RxImagePicker rxImagePicker;
    private Uri userIDImagePath;

    @Inject
    public EditPhotosPresenter(RxImagePicker rxImagePicker2, Router router2, AccountInteractor accountInteractor2, AuthHolder authHolder2, ImagesRepository imagesRepository2) {
        Intrinsics.checkParameterIsNotNull(rxImagePicker2, "rxImagePicker");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(accountInteractor2, "accountInteractor");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(imagesRepository2, "imagesRepository");
        this.rxImagePicker = rxImagePicker2;
        this.router = router2;
        this.accountInteractor = accountInteractor2;
        this.authHolder = authHolder2;
        this.imagesRepository = imagesRepository2;
    }

    public final boolean getExitOnSuccess() {
        return this.exitOnSuccess;
    }

    public final void setExitOnSuccess(boolean z) {
        this.exitOnSuccess = z;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        String photoProfileUrl = user.getPhotoProfileUrl();
        if (photoProfileUrl != null) {
            onProfileImagePicked(Uri.parse(photoProfileUrl));
        }
        String photoIdUrl = user.getPhotoIdUrl();
        if (photoIdUrl != null) {
            onPhotoIdImagePicked(Uri.parse(photoIdUrl));
        }
        ((EditPhotosView) getViewState()).showApproveDialog();
    }

    private final boolean canMoveToNextStep() {
        return (this.userIDImagePath == null && this.profileImagePath == null) ? false : true;
    }

    private final void saveStepData() {
        ((EditPhotosView) getViewState()).showProgress();
        CompositeDisposable disposables = getDisposables();
        AccountInteractor accountInteractor2 = this.accountInteractor;
        Uri uri = this.profileImagePath;
        Uri uri2 = this.userIDImagePath;
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        Single<ApiResponse<User>> editPhotos = accountInteractor2.editPhotos(uri, uri2, user.getUsername());
        EditPhotosPresenter editPhotosPresenter = this;
        Disposable subscribe = editPhotos.subscribe(new EditPhotosPresenter$sam$io_reactivex_functions_Consumer$0(new EditPhotosPresenter$saveStepData$1(editPhotosPresenter)), new EditPhotosPresenter$sam$io_reactivex_functions_Consumer$0(new EditPhotosPresenter$saveStepData$2(editPhotosPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountInteractor.editPh…cess, this::onEditFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onEditFailed(Throwable th) {
        String localizedMessage;
        ((EditPhotosView) getViewState()).hideProgress();
        Timber.e(th);
        if (th != null && (localizedMessage = th.getLocalizedMessage()) != null) {
            ((EditPhotosView) getViewState()).showMessage(localizedMessage);
        }
    }

    /* access modifiers changed from: private */
    public final void onProfileEditSuccess(ApiResponse<User> apiResponse) {
        ((EditPhotosView) getViewState()).hideProgress();
        ((EditPhotosView) getViewState()).showMessage("Photos changed successfully");
        if (this.exitOnSuccess) {
            this.router.exit();
        }
    }

    public final void onProfileImageClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "src");
        RxImagePicker.Sources sources = Intrinsics.areEqual((Object) str, (Object) AvatarUpdaterDialog.CAMERA) ? RxImagePicker.Sources.CAMERA : RxImagePicker.Sources.GALLERY;
        CompositeDisposable disposables = getDisposables();
        EditPhotosPresenter editPhotosPresenter = this;
        Disposable subscribe = this.rxImagePicker.requestImage(sources).subscribe(new EditPhotosPresenter$sam$io_reactivex_functions_Consumer$0(new EditPhotosPresenter$onProfileImageClicked$1(editPhotosPresenter)), new EditPhotosPresenter$sam$io_reactivex_functions_Consumer$0(new EditPhotosPresenter$onProfileImageClicked$2(editPhotosPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "rxImagePicker.requestIma…is::onImageLoadingFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onProfileImagePicked(Uri uri) {
        if (uri != null) {
            this.profileImagePath = ImagesRepository.getScaledImage$default(this.imagesRepository, uri, 0, 0, 6, (Object) null);
            checkNextAvailable();
            ((EditPhotosView) getViewState()).showProfileImage(this.profileImagePath);
        }
    }

    public final void onPhotoIdImageClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "src");
        RxImagePicker.Sources sources = Intrinsics.areEqual((Object) str, (Object) AvatarUpdaterDialog.CAMERA) ? RxImagePicker.Sources.CAMERA : RxImagePicker.Sources.GALLERY;
        CompositeDisposable disposables = getDisposables();
        EditPhotosPresenter editPhotosPresenter = this;
        Disposable subscribe = this.rxImagePicker.requestImage(sources).subscribe(new EditPhotosPresenter$sam$io_reactivex_functions_Consumer$0(new EditPhotosPresenter$onPhotoIdImageClicked$1(editPhotosPresenter)), new EditPhotosPresenter$sam$io_reactivex_functions_Consumer$0(new EditPhotosPresenter$onPhotoIdImageClicked$2(editPhotosPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "rxImagePicker.requestIma…is::onImageLoadingFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onPhotoIdImagePicked(Uri uri) {
        if (uri != null) {
            this.userIDImagePath = ImagesRepository.getScaledImage$default(this.imagesRepository, uri, 0, 0, 6, (Object) null);
            checkNextAvailable();
            ((EditPhotosView) getViewState()).showPhotoIdImage(this.userIDImagePath);
        }
    }

    private final void checkNextAvailable() {
        if (canMoveToNextStep()) {
            ((EditPhotosView) getViewState()).activateNextButton();
        } else {
            ((EditPhotosView) getViewState()).blockNextButton();
        }
    }

    /* access modifiers changed from: private */
    public final void onImageLoadingFailed(Throwable th) {
        checkNextAvailable();
        if ((th != null ? th.getMessage() : null) == null) {
            ((EditPhotosView) getViewState()).showMessage((int) R.string.error_loading_image);
            return;
        }
        EditPhotosView editPhotosView = (EditPhotosView) getViewState();
        String message = th.getMessage();
        if (message == null) {
            Intrinsics.throwNpe();
        }
        editPhotosView.showMessage(message);
    }

    public final void onNextClicked() {
        saveStepData();
    }

    public final boolean onBackPressed() {
        ((EditPhotosView) getViewState()).hideProgress();
        this.router.exit();
        return true;
    }
}
