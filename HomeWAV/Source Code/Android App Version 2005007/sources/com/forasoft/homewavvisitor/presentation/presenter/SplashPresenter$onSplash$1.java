package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.model.data.Empty;
import com.forasoft.homewavvisitor.model.data.VersionError;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Empty;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: SplashPresenter.kt */
final class SplashPresenter$onSplash$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ SplashPresenter this$0;

    SplashPresenter$onSplash$1(SplashPresenter splashPresenter) {
        this.this$0 = splashPresenter;
    }

    public final Single<User> apply(ApiResponse<? extends List<Empty>> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        if (apiResponse.getOk()) {
            return this.this$0.authInteractor.isSignedIn();
        }
        return Single.error((Throwable) new VersionError(apiResponse.getErrorCause()));
    }
}
