package com.forasoft.homewavvisitor.presentation.presenter.auth;

import air.HomeWAV.R;
import android.text.Editable;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.ForgotPasswordView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/auth/ForgotPasswordPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/ForgotPasswordView;", "router", "Lru/terrakok/cicerone/Router;", "authInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;)V", "onBackPressed", "", "onPasswordReset", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "onResetError", "error", "", "onSubmitClicked", "email", "Landroid/text/Editable;", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: ForgotPasswordPresenter.kt */
public final class ForgotPasswordPresenter extends BasePresenter<ForgotPasswordView> {
    private final AuthInteractor authInteractor;
    private final Router router;

    @Inject
    public ForgotPasswordPresenter(@Global Router router2, AuthInteractor authInteractor2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authInteractor2, "authInteractor");
        this.router = router2;
        this.authInteractor = authInteractor2;
    }

    public final void onSubmitClicked(Editable editable) {
        boolean z = true;
        ((ForgotPasswordView) getViewState()).disableReset(true);
        CharSequence charSequence = editable;
        if (!(charSequence == null || charSequence.length() == 0)) {
            z = false;
        }
        if (z) {
            ((ForgotPasswordView) getViewState()).showMessage((int) R.string.error_email_empty);
            return;
        }
        CompositeDisposable disposables = getDisposables();
        ForgotPasswordPresenter forgotPasswordPresenter = this;
        Disposable subscribe = this.authInteractor.resetPassword(editable.toString()).subscribe(new ForgotPasswordPresenter$sam$io_reactivex_functions_Consumer$0(new ForgotPasswordPresenter$onSubmitClicked$1(forgotPasswordPresenter)), new ForgotPasswordPresenter$sam$io_reactivex_functions_Consumer$0(new ForgotPasswordPresenter$onSubmitClicked$2(forgotPasswordPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "authInteractor.resetPass…      this::onResetError)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onResetError(Throwable th) {
        ((ForgotPasswordView) getViewState()).disableReset(false);
        Timber.e(th);
        String localizedMessage = th.getLocalizedMessage();
        Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "error.localizedMessage");
        ((ForgotPasswordView) getViewState()).showMessage(localizedMessage);
    }

    /* access modifiers changed from: private */
    public final void onPasswordReset(ApiResponse<? extends Object> apiResponse) {
        String message;
        ((ForgotPasswordView) getViewState()).disableReset(false);
        if (apiResponse.getOk()) {
            ((ForgotPasswordView) getViewState()).showMessage("A reset password request has been sent to your e-mail.");
        } else {
            ErrorCause errorCause = apiResponse.getErrorCause();
            if (!(errorCause == null || (message = errorCause.getMessage()) == null)) {
                ((ForgotPasswordView) getViewState()).showMessage(message);
            }
        }
        this.router.exit();
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }
}
