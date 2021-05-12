package com.forasoft.homewavvisitor.toothpick.provider.api;

import com.forasoft.homewavvisitor.model.server.apis.AccountApi;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/provider/api/AccountApiProvider;", "Ljavax/inject/Provider;", "Lcom/forasoft/homewavvisitor/model/server/apis/AccountApi;", "retrofit", "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)V", "get", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AccountApiProvider.kt */
public final class AccountApiProvider implements Provider<AccountApi> {
    private final Retrofit retrofit;

    @Inject
    public AccountApiProvider(Retrofit retrofit3) {
        Intrinsics.checkParameterIsNotNull(retrofit3, "retrofit");
        this.retrofit = retrofit3;
    }

    public AccountApi get() {
        Object create = this.retrofit.create(AccountApi.class);
        Intrinsics.checkExpressionValueIsNotNull(create, "retrofit.create(AccountApi::class.java)");
        return (AccountApi) create;
    }
}
