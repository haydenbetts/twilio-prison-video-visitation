package com.forasoft.homewavvisitor.model.server.interceptor;

import android.os.Handler;
import android.os.Looper;
import androidx.work.WorkManager;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.toothpick.provider.OkHttpClientProvider;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashSet;
import java.util.Iterator;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001Bg\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fH\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010)\u001a\u00020\u001fH\u0016J\b\u0010,\u001a\u00020&H\u0002J\u0012\u0010-\u001a\u00020&2\b\u0010.\u001a\u0004\u0018\u00010\"H\u0002J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0002J\u0010\u00102\u001a\u0002002\u0006\u00103\u001a\u00020+H\u0002J\b\u00104\u001a\u000200H\u0003J\u0012\u00105\u001a\u0002002\b\u0010.\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u001b\u001a\u00020\u001cXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001ej\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f` X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"XD¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\"XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/AuthenticationInterceptor;", "Lokhttp3/Interceptor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "messageDao", "Lcom/forasoft/homewavvisitor/dao/MessageDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "creditDao", "Lcom/forasoft/homewavvisitor/dao/CreditDao;", "router", "Lru/terrakok/cicerone/Router;", "settings", "Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "clientProvider", "Lcom/forasoft/homewavvisitor/toothpick/provider/OkHttpClientProvider;", "gson", "Lcom/google/gson/Gson;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/MessageDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/dao/CallDao;Lcom/forasoft/homewavvisitor/dao/CreditDao;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/account/Settings;Lcom/forasoft/homewavvisitor/toothpick/provider/OkHttpClientProvider;Lcom/google/gson/Gson;Lcom/forasoft/homewavvisitor/dao/UserDao;)V", "SESSION_LIFETIME", "", "failedRequests", "Ljava/util/HashSet;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/collections/HashSet;", "loginError", "", "sessionExpiredError", "sessionNotRefeched", "tryingToRefetchSession", "", "attachAuthCookie", "Lokhttp3/Request;", "chain", "intercept", "Lokhttp3/Response;", "isCanToRefetch", "isSessionExpired", "body", "logout", "", "retryFailedRequests", "saveTokenCandidate", "originalResponse", "tryToRefetchSession", "updateUser", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AuthenticationInterceptor.kt */
public final class AuthenticationInterceptor implements Interceptor {
    private final long SESSION_LIFETIME = 1440000;
    private final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final CallDao callDao;
    private final OkHttpClientProvider clientProvider;
    /* access modifiers changed from: private */
    public final CreditDao creditDao;
    private final HashSet<Interceptor.Chain> failedRequests = new HashSet<>();
    private final Gson gson;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    private final String loginError = "You must be logged in to access the requested page";
    /* access modifiers changed from: private */
    public final MessageDao messageDao;
    /* access modifiers changed from: private */
    public final NotificationDao notificationDao;
    /* access modifiers changed from: private */
    public final Router router;
    private final String sessionExpiredError = "Session expired, please login again.";
    private final String sessionNotRefeched = "Unable to refetch session. Please log in again";
    private final Settings settings;
    private boolean tryingToRefetchSession;
    private final UserDao userDao;
    /* access modifiers changed from: private */
    public final VisitDao visitDao;

    @Inject
    public AuthenticationInterceptor(AuthHolder authHolder2, NotificationDao notificationDao2, MessageDao messageDao2, InmateDao inmateDao2, VisitDao visitDao2, CallDao callDao2, CreditDao creditDao2, Router router2, Settings settings2, OkHttpClientProvider okHttpClientProvider, Gson gson2, UserDao userDao2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(messageDao2, "messageDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        Intrinsics.checkParameterIsNotNull(creditDao2, "creditDao");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(settings2, "settings");
        Intrinsics.checkParameterIsNotNull(okHttpClientProvider, "clientProvider");
        Intrinsics.checkParameterIsNotNull(gson2, "gson");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        this.authHolder = authHolder2;
        this.notificationDao = notificationDao2;
        this.messageDao = messageDao2;
        this.inmateDao = inmateDao2;
        this.visitDao = visitDao2;
        this.callDao = callDao2;
        this.creditDao = creditDao2;
        this.router = router2;
        this.settings = settings2;
        this.clientProvider = okHttpClientProvider;
        this.gson = gson2;
        this.userDao = userDao2;
    }

    public Response intercept(Interceptor.Chain chain) {
        String str;
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Response proceed = chain.proceed(attachAuthCookie(chain));
        ResponseBody body = proceed.body();
        if (body == null || (str = body.string()) == null) {
            str = "";
        }
        if (!isSessionExpired(str)) {
            saveTokenCandidate(proceed);
        } else if (isCanToRefetch()) {
            tryToRefetchSession();
        } else {
            this.failedRequests.add(chain);
            if (!this.tryingToRefetchSession) {
                logout();
            }
        }
        Response.Builder newBuilder = proceed.newBuilder();
        ResponseBody.Companion companion = ResponseBody.Companion;
        ResponseBody body2 = proceed.body();
        return newBuilder.body(companion.create(str, body2 != null ? body2.contentType() : null)).build();
    }

    private final Request attachAuthCookie(Interceptor.Chain chain) {
        if (this.authHolder.getToken() == null) {
            return chain.request();
        }
        Request.Builder newBuilder = chain.request().newBuilder();
        return newBuilder.removeHeader("Cookie").addHeader("Cookie", this.authHolder.getToken() + ';' + this.authHolder.getEsur() + ';' + this.authHolder.getAsps()).build();
    }

    private final void tryToRefetchSession() {
        this.tryingToRefetchSession = true;
        Response execute = this.clientProvider.get().newCall(new Request.Builder().url("https://app.homewav.com/api/app/refetch-session").build()).execute();
        ResponseBody body = execute.body();
        String string = body != null ? body.string() : null;
        if (string == null || !StringsKt.contains$default((CharSequence) string, (CharSequence) this.sessionNotRefeched, false, 2, (Object) null)) {
            saveTokenCandidate(execute);
            updateUser(string);
            this.tryingToRefetchSession = false;
            retryFailedRequests();
            return;
        }
        logout();
    }

    private final void retryFailedRequests() {
        Iterator<Interceptor.Chain> it = this.failedRequests.iterator();
        while (it.hasNext()) {
            Interceptor.Chain next = it.next();
            if (next != null) {
                Intrinsics.checkExpressionValueIsNotNull(next, "it");
                intercept(next);
            }
        }
        this.failedRequests.clear();
    }

    private final boolean isSessionExpired(String str) {
        if (str == null || !StringsKt.contains$default((CharSequence) str, (CharSequence) this.loginError, false, 2, (Object) null)) {
            return str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) this.sessionExpiredError, false, 2, (Object) null);
        }
        return true;
    }

    private final boolean isCanToRefetch() {
        if (!this.tryingToRefetchSession && this.authHolder.getAsps() != null && this.authHolder.getEsur() != null && this.authHolder.getLastSuccessRequest() > 0 && System.currentTimeMillis() - this.authHolder.getLastSuccessRequest() > this.SESSION_LIFETIME) {
            return true;
        }
        return false;
    }

    private final void logout() {
        this.failedRequests.clear();
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new AuthenticationInterceptor$logout$1(this), 31, (Object) null);
        this.authHolder.logout();
        this.settings.clear();
        WorkManager.getInstance().cancelAllWork();
        new Handler(Looper.getMainLooper()).post(new AuthenticationInterceptor$logout$2(this));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void saveTokenCandidate(okhttp3.Response r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Set-Cookie"
            java.util.List r1 = r9.headers(r0)
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ 1
            if (r1 == 0) goto L_0x00be
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.List r9 = r9.headers(r0)
            java.util.Iterator r9 = r9.iterator()
        L_0x001d:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x002d
            java.lang.Object r0 = r9.next()
            java.lang.String r0 = (java.lang.String) r0
            r1.add(r0)
            goto L_0x001d
        L_0x002d:
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r9 = r8.authHolder
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r0 = r1.iterator()
        L_0x0035:
            boolean r2 = r0.hasNext()
            r3 = 2
            r4 = 0
            r5 = 0
            if (r2 == 0) goto L_0x0052
            java.lang.Object r2 = r0.next()
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r7 = "PHPSESSID"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, (boolean) r4, (int) r3, (java.lang.Object) r5)
            if (r6 == 0) goto L_0x0035
            goto L_0x0053
        L_0x0052:
            r2 = r5
        L_0x0053:
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0058
            goto L_0x005e
        L_0x0058:
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r0 = r8.authHolder
            java.lang.String r2 = r0.getToken()
        L_0x005e:
            r9.setToken(r2)
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r9 = r8.authHolder
            java.util.Iterator r0 = r1.iterator()
        L_0x0067:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0081
            java.lang.Object r2 = r0.next()
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r7 = "esur"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, (boolean) r4, (int) r3, (java.lang.Object) r5)
            if (r6 == 0) goto L_0x0067
            goto L_0x0082
        L_0x0081:
            r2 = r5
        L_0x0082:
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0087
            goto L_0x008d
        L_0x0087:
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r0 = r8.authHolder
            java.lang.String r2 = r0.getEsur()
        L_0x008d:
            r9.setEsur(r2)
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r9 = r8.authHolder
            java.util.Iterator r0 = r1.iterator()
        L_0x0096:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00b0
            java.lang.Object r1 = r0.next()
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.String r6 = "asps"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r2 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r2, (java.lang.CharSequence) r6, (boolean) r4, (int) r3, (java.lang.Object) r5)
            if (r2 == 0) goto L_0x0096
            r5 = r1
        L_0x00b0:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L_0x00b5
            goto L_0x00bb
        L_0x00b5:
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r0 = r8.authHolder
            java.lang.String r5 = r0.getAsps()
        L_0x00bb:
            r9.setAsps(r5)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.server.interceptor.AuthenticationInterceptor.saveTokenCandidate(okhttp3.Response):void");
    }

    private final void updateUser(String str) {
        if (str != null) {
            JsonElement parse = new JsonParser().parse(str);
            if (parse != null) {
                User user = (User) this.gson.fromJson((JsonElement) ((JsonObject) parse).getAsJsonObject("body"), User.class);
                UserDao userDao2 = this.userDao;
                if (user == null) {
                    Intrinsics.throwNpe();
                }
                userDao2.saveUser(user);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.google.gson.JsonObject");
        }
    }
}
