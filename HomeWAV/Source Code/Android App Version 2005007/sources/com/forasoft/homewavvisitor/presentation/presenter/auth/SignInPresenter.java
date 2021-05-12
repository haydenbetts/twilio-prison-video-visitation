package com.forasoft.homewavvisitor.presentation.presenter.auth;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.SignInView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import com.google.gson.stream.MalformedJsonException;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.net.SocketTimeoutException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010\u001b\u001a\u00020\r2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\"\u0010\u001f\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/auth/SignInPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/SignInView;", "router", "Lru/terrakok/cicerone/Router;", "authInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/Analytics;)V", "onCreateAccountClicked", "", "view", "Landroid/view/View;", "onEditFieldFocusChanged", "hasFocus", "", "onForgotPasswordClicked", "onLoginClicked", "email", "Landroid/text/Editable;", "password", "onLoginFailed", "error", "", "onLoginSuccess", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onPasswordEnter", "Landroid/widget/TextView;", "actionId", "", "event", "Landroid/view/KeyEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: SignInPresenter.kt */
public final class SignInPresenter extends BasePresenter<SignInView> {
    private final Analytics analytics;
    private final AuthHolder authHolder;
    private final AuthInteractor authInteractor;
    private final Router router;

    @Inject
    public SignInPresenter(@Global Router router2, AuthInteractor authInteractor2, AuthHolder authHolder2, Analytics analytics2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authInteractor2, "authInteractor");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        this.router = router2;
        this.authInteractor = authInteractor2;
        this.authHolder = authHolder2;
        this.analytics = analytics2;
    }

    public final void onLoginClicked(Editable editable, Editable editable2) {
        Intrinsics.checkParameterIsNotNull(editable, "email");
        Intrinsics.checkParameterIsNotNull(editable2, "password");
        boolean z = false;
        if (!(editable.length() == 0)) {
            if (editable2.length() == 0) {
                z = true;
            }
            if (!z) {
                ((SignInView) getViewState()).showLoading(true);
                CompositeDisposable disposables = getDisposables();
                SignInPresenter signInPresenter = this;
                Disposable subscribe = this.authInteractor.login(editable.toString(), editable2.toString()).subscribe(new SignInPresenter$sam$io_reactivex_functions_Consumer$0(new SignInPresenter$onLoginClicked$1(signInPresenter)), new SignInPresenter$sam$io_reactivex_functions_Consumer$0(new SignInPresenter$onLoginClicked$2(signInPresenter)));
                Intrinsics.checkExpressionValueIsNotNull(subscribe, "authInteractor.login(ema…ess, this::onLoginFailed)");
                DisposableKt.plusAssign(disposables, subscribe);
                return;
            }
        }
        ((SignInView) getViewState()).showError();
    }

    public final void onEditFieldFocusChanged(View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (z) {
            ((SignInView) getViewState()).hideError();
        }
    }

    /* access modifiers changed from: private */
    public final void onLoginFailed(Throwable th) {
        ((SignInView) getViewState()).showLoading(false);
        Timber.e(th);
        if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((SignInView) getViewState()).showLoading(false);
            ((SignInView) getViewState()).showServerError();
            return;
        }
        ((SignInView) getViewState()).showError();
    }

    public final boolean onPasswordEnter(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        ((SignInView) getViewState()).performLoginClick();
        return true;
    }

    /* access modifiers changed from: private */
    public final void onLoginSuccess(ApiResponse<User> apiResponse) {
        String str;
        if (apiResponse.getOk()) {
            User body = apiResponse.getBody();
            if ((body != null ? body.getId() : null) == null) {
                ((SignInView) getViewState()).showError();
                ((SignInView) getViewState()).showLoading(false);
                return;
            }
            Analytics analytics2 = this.analytics;
            String pin = apiResponse.getBody().getPin();
            if (pin == null) {
                Intrinsics.throwNpe();
            }
            analytics2.onLogin(pin);
            if (!apiResponse.getBody().getVerified()) {
                this.router.newRootScreen(Screens.VerifyMethodScreen.INSTANCE);
            } else if (this.authHolder.isWelcomeScreensShown()) {
                this.router.newRootScreen(new Screens.MainScreen((String) null, 1, (DefaultConstructorMarker) null));
            } else {
                this.router.newRootScreen(Screens.WelcomeScreen.INSTANCE);
            }
        } else {
            SignInView signInView = (SignInView) getViewState();
            ErrorCause errorCause = apiResponse.getErrorCause();
            if (errorCause == null || (str = errorCause.getMessage()) == null) {
                str = "Unknown error";
            }
            signInView.showMessage(str);
            ((SignInView) getViewState()).showLoading(false);
        }
    }

    public final void onForgotPasswordClicked(View view) {
        this.router.navigateTo(Screens.ForgotPasswordScreen.INSTANCE);
    }

    public final void onCreateAccountClicked(View view) {
        this.router.navigateTo(Screens.SignUpScreen.INSTANCE);
    }
}
