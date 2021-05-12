package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.data.ValidationError;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/CompletableSource;", "kotlin.jvm.PlatformType", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodPresenter.kt */
final class VerifyMethodPresenter$onEmailChanged$1<T, R> implements Function<ApiResponse<? extends User>, CompletableSource> {
    final /* synthetic */ VerifyMethodPresenter this$0;

    VerifyMethodPresenter$onEmailChanged$1(VerifyMethodPresenter verifyMethodPresenter) {
        this.this$0 = verifyMethodPresenter;
    }

    public final CompletableSource apply(final ApiResponse<User> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        if (apiResponse.getOk()) {
            return Completable.fromCallable(new Callable<Object>(this) {
                final /* synthetic */ VerifyMethodPresenter$onEmailChanged$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void call() {
                    AuthRepository access$getAuthRepository$p = this.this$0.this$0.authRepository;
                    Object body = apiResponse.getBody();
                    if (body == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getAuthRepository$p.saveUser((User) body);
                }
            });
        }
        return Completable.error((Throwable) new ValidationError(apiResponse.getErrorCause()));
    }
}
