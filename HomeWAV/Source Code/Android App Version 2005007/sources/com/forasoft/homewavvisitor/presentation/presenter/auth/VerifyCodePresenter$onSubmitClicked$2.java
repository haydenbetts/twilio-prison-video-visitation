package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyCodePresenter.kt */
final class VerifyCodePresenter$onSubmitClicked$2<T> implements Consumer<ApiResponse<? extends List<? extends Object>>> {
    final /* synthetic */ VerifyCodePresenter this$0;

    VerifyCodePresenter$onSubmitClicked$2(VerifyCodePresenter verifyCodePresenter) {
        this.this$0 = verifyCodePresenter;
    }

    public final void accept(ApiResponse<? extends List<? extends Object>> apiResponse) {
        if (apiResponse.getOk()) {
            final User user = this.this$0.authRepository.getUser();
            if (user != null) {
                user.setVerified(true);
            }
            Completable fromCallable = Completable.fromCallable(new Callable<Object>(this) {
                final /* synthetic */ VerifyCodePresenter$onSubmitClicked$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void call() {
                    AuthRepository access$getAuthRepository$p = this.this$0.this$0.authRepository;
                    User user = user;
                    if (user == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getAuthRepository$p.saveUser(user);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Completable.fromCallable…sitory.saveUser(user!!) }");
            CommonKt.applyAsync(fromCallable).subscribe(new Action(this) {
                final /* synthetic */ VerifyCodePresenter$onSubmitClicked$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    if (this.this$0.this$0.authHolder.isWelcomeScreensShown()) {
                        this.this$0.this$0.router.newRootScreen(new Screens.MainScreen((String) null, 1, (DefaultConstructorMarker) null));
                    } else {
                        this.this$0.this$0.router.newRootScreen(Screens.WelcomeScreen.INSTANCE);
                    }
                    this.this$0.this$0.analytics.onAccountCreated();
                }
            }, new Consumer<Throwable>(this) {
                final /* synthetic */ VerifyCodePresenter$onSubmitClicked$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void accept(Throwable th) {
                    this.this$0.this$0.handleError((ErrorCause) null);
                }
            });
            return;
        }
        this.this$0.handleError(apiResponse.getErrorCause());
    }
}
