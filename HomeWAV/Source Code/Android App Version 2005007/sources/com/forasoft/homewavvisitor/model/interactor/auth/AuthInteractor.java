package com.forasoft.homewavvisitor.model.interactor.auth;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.urbanairship.util.Attributes;
import io.reactivex.Flowable;
import io.reactivex.Single;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010\u0005\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u00060\u0006JR\u0010\t\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0007 \b*\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n0\n \b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0007 \b*\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n0\n\u0018\u00010\u00060\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0014\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011JJ\u0010\u0012\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0001 \b*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n0\n \b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0001 \b*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n0\n\u0018\u00010\u00060\u00062\u0006\u0010\u0013\u001a\u00020\fJ*\u0010\u0014\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u00150\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "", "authRepository", "Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;", "(Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;)V", "isSignedIn", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "login", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "username", "", "password", "logout", "", "doOnComplete", "Lkotlin/Function0;", "resetPassword", "email", "subscribeForUserUpdates", "Lio/reactivex/Flowable;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AuthInteractor.kt */
public final class AuthInteractor {
    private final AuthRepository authRepository;

    @Inject
    public AuthInteractor(AuthRepository authRepository2) {
        Intrinsics.checkParameterIsNotNull(authRepository2, "authRepository");
        this.authRepository = authRepository2;
    }

    public final Single<User> isSignedIn() {
        return CommonKt.applyAsync(this.authRepository.getSingleUser());
    }

    public final Single<ApiResponse<User>> login(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str2, "password");
        return CommonKt.applyAsync(this.authRepository.login(str, str2));
    }

    public final Single<ApiResponse<Object>> resetPassword(String str) {
        Intrinsics.checkParameterIsNotNull(str, "email");
        return CommonKt.applyAsync(this.authRepository.resetPassword(str));
    }

    public final Flowable<User> subscribeForUserUpdates() {
        return CommonKt.applyAsync(this.authRepository.getUserFlowable());
    }

    public final void logout(Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "doOnComplete");
        this.authRepository.logout(function0);
    }
}
