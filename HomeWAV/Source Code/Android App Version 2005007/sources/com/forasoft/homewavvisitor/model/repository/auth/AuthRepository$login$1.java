package com.forasoft.homewavvisitor.model.repository.auth;

import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AuthRepository.kt */
final class AuthRepository$login$1<T> implements Consumer<ApiResponse<? extends User>> {
    final /* synthetic */ AuthRepository this$0;

    AuthRepository$login$1(AuthRepository authRepository) {
        this.this$0 = authRepository;
    }

    public final void accept(ApiResponse<User> apiResponse) {
        User body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        User user = body;
        this.this$0.authHolder.setUser(user);
        this.this$0.userDao.saveUser(apiResponse.getBody());
        String fcmToken = this.this$0.authHolder.getFcmToken();
        if (fcmToken != null) {
            HomewavApi.DefaultImpls.saveToken$default(this.this$0.homewavApi, user.getId(), fcmToken, (String) null, 4, (Object) null).subscribeOn(Schedulers.io()).subscribe(AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE);
        }
    }
}
