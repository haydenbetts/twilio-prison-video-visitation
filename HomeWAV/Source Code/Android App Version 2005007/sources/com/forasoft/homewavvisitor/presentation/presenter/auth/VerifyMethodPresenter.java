package com.forasoft.homewavvisitor.presentation.presenter.auth;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyMethodView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\rJ\b\u0010\u0016\u001a\u00020\u000fH\u0014J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\rJ\u000e\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyMethodPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/VerifyMethodView;", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "accountInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "authRepository", "Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;)V", "visitorId", "", "handleErrors", "", "error", "Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "onBackPressed", "onCloseClicked", "onEmailChanged", "newEmail", "onFirstViewAttach", "onPhoneChanged", "newPhone", "onSendClicked", "selectedChannel", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: VerifyMethodPresenter.kt */
public final class VerifyMethodPresenter extends BasePresenter<VerifyMethodView> {
    private final AccountInteractor accountInteractor;
    private final SignUpApi api;
    /* access modifiers changed from: private */
    public final AuthRepository authRepository;
    /* access modifiers changed from: private */
    public final Router router;
    /* access modifiers changed from: private */
    public String visitorId;

    @Inject
    public VerifyMethodPresenter(@Global Router router2, SignUpApi signUpApi, AccountInteractor accountInteractor2, AuthRepository authRepository2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(signUpApi, "api");
        Intrinsics.checkParameterIsNotNull(accountInteractor2, "accountInteractor");
        Intrinsics.checkParameterIsNotNull(authRepository2, "authRepository");
        this.router = router2;
        this.api = signUpApi;
        this.accountInteractor = accountInteractor2;
        this.authRepository = authRepository2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.authRepository.getSingleUser()).subscribe(new VerifyMethodPresenter$onFirstViewAttach$1(this), VerifyMethodPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "authRepository.getSingle…(it.email)\n        }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onSendClicked(int i) {
        String str = i == R.id.btn_phone ? "sms" : "email";
        String str2 = this.visitorId;
        if (str2 != null) {
            CompositeDisposable disposables = getDisposables();
            Disposable subscribe = CommonKt.applyAsync(this.api.getCode(str2, str)).subscribe(new VerifyMethodPresenter$onSendClicked$$inlined$let$lambda$1(this, str), new VerifyMethodPresenter$onSendClicked$$inlined$let$lambda$2(this, str));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getCode(it, channel)…ng.label_server_error) })");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    public final void onEmailChanged(String str) {
        Intrinsics.checkParameterIsNotNull(str, "newEmail");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = AccountInteractor.editAccount$default(this.accountInteractor, (String) null, (String) null, (String) null, str, (String) null, (String) null, (String) null, (String) null, (String) null, (Boolean) null, 1015, (Object) null).observeOn(Schedulers.io()).flatMapCompletable(new VerifyMethodPresenter$onEmailChanged$1(this)).doOnComplete(new VerifyMethodPresenter$onEmailChanged$2(this, str)).observeOn(AndroidSchedulers.mainThread()).subscribe(new VerifyMethodPresenter$onEmailChanged$3(this, str), new VerifyMethodPresenter$onEmailChanged$4(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountInteractor.editAc…     }\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onPhoneChanged(String str) {
        Intrinsics.checkParameterIsNotNull(str, "newPhone");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = AccountInteractor.editAccount$default(this.accountInteractor, (String) null, (String) null, (String) null, (String) null, str, (String) null, (String) null, (String) null, (String) null, (Boolean) null, 1007, (Object) null).observeOn(Schedulers.io()).flatMapCompletable(new VerifyMethodPresenter$onPhoneChanged$1(this)).doOnComplete(new VerifyMethodPresenter$onPhoneChanged$2(this, str)).observeOn(AndroidSchedulers.mainThread()).subscribe(new VerifyMethodPresenter$onPhoneChanged$3(this, str), new VerifyMethodPresenter$onPhoneChanged$4(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountInteractor.editAc…     }\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onCloseClicked() {
        this.authRepository.simpleLogout();
        this.router.newRootScreen(new Screens.LoginScreen(false, 1, (DefaultConstructorMarker) null));
    }

    public final void onBackPressed() {
        this.router.exit();
    }

    /* access modifiers changed from: private */
    public final void handleErrors(ErrorCause errorCause) {
        String message;
        Map<String, String> errorList = errorCause != null ? errorCause.getErrorList() : null;
        String str = "Couldn't update info, try later";
        if (errorList == null || errorList.isEmpty()) {
            VerifyMethodView verifyMethodView = (VerifyMethodView) getViewState();
            if (!(errorCause == null || (message = errorCause.getMessage()) == null)) {
                str = message;
            }
            verifyMethodView.showMessage(str);
            return;
        }
        String str2 = errorList.get(CollectionsKt.first(errorList.keySet()));
        if (str2 != null) {
            str = str2;
        }
        ((VerifyMethodView) getViewState()).showMessage(StringsKt.removeSuffix(StringsKt.removePrefix(str, (CharSequence) "\""), (CharSequence) "\""));
    }
}
