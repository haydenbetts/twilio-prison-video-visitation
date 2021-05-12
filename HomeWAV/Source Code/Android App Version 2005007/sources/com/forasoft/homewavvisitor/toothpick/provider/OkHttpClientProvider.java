package com.forasoft.homewavvisitor.toothpick.provider;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.server.interceptor.AuthenticationInterceptor;
import com.forasoft.homewavvisitor.model.server.interceptor.CloseConnectionInterceptor;
import com.forasoft.homewavvisitor.model.server.interceptor.SessionNotExpiredInterceptor;
import com.google.gson.Gson;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dBg\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/provider/OkHttpClientProvider;", "Ljavax/inject/Provider;", "Lokhttp3/OkHttpClient;", "context", "Landroid/content/Context;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "router", "Lru/terrakok/cicerone/Router;", "messageDao", "Lcom/forasoft/homewavvisitor/dao/MessageDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "creditDao", "Lcom/forasoft/homewavvisitor/dao/CreditDao;", "settings", "Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "gson", "Lcom/google/gson/Gson;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "(Landroid/content/Context;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/dao/MessageDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/dao/CallDao;Lcom/forasoft/homewavvisitor/dao/CreditDao;Lcom/forasoft/homewavvisitor/model/data/account/Settings;Lcom/google/gson/Gson;Lcom/forasoft/homewavvisitor/dao/UserDao;)V", "get", "ConfinedLogger", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: OkHttpClientProvider.kt */
public final class OkHttpClientProvider implements Provider<OkHttpClient> {
    private final AuthHolder authHolder;
    private final CallDao callDao;
    private final Context context;
    private final CreditDao creditDao;
    private final Gson gson;
    private final InmateDao inmateDao;
    private final MessageDao messageDao;
    private final NotificationDao notificationDao;
    private final Router router;
    private final Settings settings;
    private final UserDao userDao;
    private final VisitDao visitDao;

    @Inject
    public OkHttpClientProvider(Context context2, AuthHolder authHolder2, Router router2, MessageDao messageDao2, InmateDao inmateDao2, NotificationDao notificationDao2, VisitDao visitDao2, CallDao callDao2, CreditDao creditDao2, Settings settings2, Gson gson2, UserDao userDao2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(messageDao2, "messageDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        Intrinsics.checkParameterIsNotNull(creditDao2, "creditDao");
        Intrinsics.checkParameterIsNotNull(settings2, "settings");
        Intrinsics.checkParameterIsNotNull(gson2, "gson");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        this.context = context2;
        this.authHolder = authHolder2;
        this.router = router2;
        this.messageDao = messageDao2;
        this.inmateDao = inmateDao2;
        this.notificationDao = notificationDao2;
        this.visitDao = visitDao2;
        this.callDao = callDao2;
        this.creditDao = creditDao2;
        this.settings = settings2;
        this.gson = gson2;
        this.userDao = userDao2;
    }

    public OkHttpClient get() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheDir = this.context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        builder.cache(new Cache(cacheDir, 20480));
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(90, TimeUnit.SECONDS);
        builder.writeTimeout(90, TimeUnit.SECONDS);
        builder.addInterceptor(new SessionNotExpiredInterceptor(this.authHolder));
        builder.addInterceptor(new AuthenticationInterceptor(this.authHolder, this.notificationDao, this.messageDao, this.inmateDao, this.visitDao, this.callDao, this.creditDao, this.router, this.settings, this, this.gson, this.userDao));
        builder.addInterceptor(new CloseConnectionInterceptor());
        return builder.build();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/provider/OkHttpClientProvider$ConfinedLogger;", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "()V", "log", "", "message", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: OkHttpClientProvider.kt */
    private static final class ConfinedLogger implements HttpLoggingInterceptor.Logger {
        public void log(String str) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            if (str.length() > 4000) {
                Timber.d(StringsKt.substring(str, new IntRange(0, 4000)), new Object[0]);
            } else {
                Timber.d(str, new Object[0]);
            }
        }
    }
}
