package com.forasoft.homewavvisitor.toothpick.provider.api;

import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/provider/api/HomewavApiProvider;", "Ljavax/inject/Provider;", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "retrofit", "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)V", "get", "kotlin.jvm.PlatformType", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HomewavApiProvider.kt */
public final class HomewavApiProvider implements Provider<HomewavApi> {
    private final Retrofit retrofit;

    @Inject
    public HomewavApiProvider(Retrofit retrofit3) {
        Intrinsics.checkParameterIsNotNull(retrofit3, "retrofit");
        this.retrofit = retrofit3;
    }

    public HomewavApi get() {
        return (HomewavApi) this.retrofit.create(HomewavApi.class);
    }
}
