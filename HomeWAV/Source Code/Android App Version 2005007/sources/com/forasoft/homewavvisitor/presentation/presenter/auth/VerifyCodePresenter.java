package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyCodeView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Channel;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import io.reactivex.Single;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B;\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\b\u0010\u0016\u001a\u00020\u0011H\u0007J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\bH\u0007R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyCodePresenter;", "Lmoxy/MvpPresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/VerifyCodeView;", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "channel", "", "authRepository", "Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/Analytics;)V", "handleError", "", "error", "Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "onBackPressed", "onCloseClicked", "onSendNewCodeClicked", "onSubmitClicked", "code", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: VerifyCodePresenter.kt */
public final class VerifyCodePresenter extends MvpPresenter<VerifyCodeView> {
    /* access modifiers changed from: private */
    public final Analytics analytics;
    /* access modifiers changed from: private */
    public final SignUpApi api;
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final AuthRepository authRepository;
    /* access modifiers changed from: private */
    public final String channel;
    /* access modifiers changed from: private */
    public final Router router;

    @Inject
    public VerifyCodePresenter(@Global Router router2, SignUpApi signUpApi, @Channel String str, AuthRepository authRepository2, AuthHolder authHolder2, Analytics analytics2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(signUpApi, "api");
        Intrinsics.checkParameterIsNotNull(str, Modules.CHANNEL_MODULE);
        Intrinsics.checkParameterIsNotNull(authRepository2, "authRepository");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        this.router = router2;
        this.api = signUpApi;
        this.channel = str;
        this.authRepository = authRepository2;
        this.authHolder = authHolder2;
        this.analytics = analytics2;
    }

    public final void onSubmitClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "code");
        Single<R> flatMap = this.authRepository.getSingleUser().flatMap(new VerifyCodePresenter$onSubmitClicked$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "authRepository.getSingle…y(it.id, channel, code) }");
        CommonKt.applyAsync(flatMap).subscribe(new VerifyCodePresenter$onSubmitClicked$2(this), new VerifyCodePresenter$onSubmitClicked$3(this));
    }

    public final void onSendNewCodeClicked() {
        Single<R> flatMap = this.authRepository.getSingleUser().flatMap(new VerifyCodePresenter$onSendNewCodeClicked$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "authRepository.getSingle…getCode(it.id, channel) }");
        CommonKt.applyAsync(flatMap).subscribe(new VerifyCodePresenter$onSendNewCodeClicked$2(this), new VerifyCodePresenter$onSendNewCodeClicked$3(this));
    }

    public final void onCloseClicked() {
        this.authRepository.simpleLogout();
        this.router.newRootScreen(new Screens.LoginScreen(false, 1, (DefaultConstructorMarker) null));
    }

    public final void onBackPressed() {
        this.router.exit();
    }

    /* access modifiers changed from: private */
    public final void handleError(ErrorCause errorCause) {
        String message;
        Map<String, String> errorList = errorCause != null ? errorCause.getErrorList() : null;
        String str = "Failed to send verification code";
        if (errorList == null || errorList.isEmpty()) {
            VerifyCodeView verifyCodeView = (VerifyCodeView) getViewState();
            if (!(errorCause == null || (message = errorCause.getMessage()) == null)) {
                str = message;
            }
            verifyCodeView.updateHint(str);
            return;
        }
        String str2 = errorList.get(CollectionsKt.first(errorList.keySet()));
        if (str2 != null) {
            str = str2;
        }
        ((VerifyCodeView) getViewState()).updateHint(str);
    }
}
