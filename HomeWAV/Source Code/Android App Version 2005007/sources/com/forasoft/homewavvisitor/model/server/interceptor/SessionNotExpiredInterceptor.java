package com.forasoft.homewavvisitor.model.server.interceptor;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/SessionNotExpiredInterceptor;", "Lokhttp3/Interceptor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "loginError", "", "sessionExpiredError", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "saveNotExpiredRequest", "", "requestUrl", "bodyString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SessionNotExpiredInterceptor.kt */
public final class SessionNotExpiredInterceptor implements Interceptor {
    private final AuthHolder authHolder;
    private final String loginError = "You must be logged in to access the requested page";
    private final String sessionExpiredError = "Session expired, please login again.";

    public SessionNotExpiredInterceptor(AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.authHolder = authHolder2;
    }

    public Response intercept(Interceptor.Chain chain) {
        String str;
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        ResponseBody body = proceed.body();
        if (body == null || (str = body.string()) == null) {
            str = "";
        }
        saveNotExpiredRequest(request.url().encodedPath(), str);
        Response.Builder newBuilder = proceed.newBuilder();
        ResponseBody.Companion companion = ResponseBody.Companion;
        ResponseBody body2 = proceed.body();
        return newBuilder.body(companion.create(str, body2 != null ? body2.contentType() : null)).build();
    }

    private final void saveNotExpiredRequest(String str, String str2) {
        if (str2 != null) {
            CharSequence charSequence = str2;
            if (!StringsKt.contains$default(charSequence, (CharSequence) this.loginError, false, 2, (Object) null) && !StringsKt.contains$default(charSequence, (CharSequence) this.sessionExpiredError, false, 2, (Object) null) && StringsKt.contains$default((CharSequence) str, (CharSequence) "heartbeat", false, 2, (Object) null)) {
                this.authHolder.setLastSuccessRequest(System.currentTimeMillis());
            }
        }
    }
}
