package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.LoginView;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014J\u0012\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010\r\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/auth/LoginPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/LoginView;", "router", "Lru/terrakok/cicerone/Router;", "authInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;)V", "onFirstViewAttach", "", "onLoggedIn", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onNotLoggedIn", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: LoginPresenter.kt */
public final class LoginPresenter extends BasePresenter<LoginView> {
    private final AuthInteractor authInteractor;
    private final Router router;

    @Inject
    public LoginPresenter(@Global Router router2, AuthInteractor authInteractor2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authInteractor2, "authInteractor");
        this.router = router2;
        this.authInteractor = authInteractor2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        LoginPresenter loginPresenter = this;
        Disposable subscribe = this.authInteractor.isSignedIn().subscribe(new LoginPresenter$sam$io_reactivex_functions_Consumer$0(new LoginPresenter$onFirstViewAttach$1(loginPresenter)), new LoginPresenter$sam$io_reactivex_functions_Consumer$0(new LoginPresenter$onFirstViewAttach$2(loginPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "authInteractor.isSignedI…dIn, this::onNotLoggedIn)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onNotLoggedIn(Throwable th) {
        this.router.newRootScreen(Screens.SignInScreen.INSTANCE);
    }

    /* access modifiers changed from: private */
    public final void onLoggedIn(User user) {
        Timber.d("getUserFlowable: %s", user);
        if (user == null || !user.getVerified()) {
            this.router.newRootScreen(Screens.VerifyMethodScreen.INSTANCE);
        } else {
            this.router.newRootScreen(new Screens.MainScreen((String) null, 1, (DefaultConstructorMarker) null));
        }
    }
}
