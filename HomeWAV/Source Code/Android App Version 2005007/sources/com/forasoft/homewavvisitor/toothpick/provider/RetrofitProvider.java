package com.forasoft.homewavvisitor.toothpick.provider;

import com.forasoft.homewavvisitor.toothpick.qualifier.ServerPath;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/provider/RetrofitProvider;", "Ljavax/inject/Provider;", "Lretrofit2/Retrofit;", "okHttpClient", "Lokhttp3/OkHttpClient;", "gson", "Lcom/google/gson/Gson;", "serverPath", "", "(Lokhttp3/OkHttpClient;Lcom/google/gson/Gson;Ljava/lang/String;)V", "get", "kotlin.jvm.PlatformType", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RetrofitProvider.kt */
public final class RetrofitProvider implements Provider<Retrofit> {
    private final Gson gson;
    private final OkHttpClient okHttpClient;
    private final String serverPath;

    @Inject
    public RetrofitProvider(OkHttpClient okHttpClient2, Gson gson2, @ServerPath String str) {
        Intrinsics.checkParameterIsNotNull(okHttpClient2, "okHttpClient");
        Intrinsics.checkParameterIsNotNull(gson2, "gson");
        Intrinsics.checkParameterIsNotNull(str, "serverPath");
        this.okHttpClient = okHttpClient2;
        this.gson = gson2;
        this.serverPath = str;
    }

    public Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create(this.gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(this.serverPath);
        builder.client(this.okHttpClient);
        return builder.build();
    }
}
