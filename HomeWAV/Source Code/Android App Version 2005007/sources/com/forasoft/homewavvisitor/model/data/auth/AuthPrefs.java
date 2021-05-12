package com.forasoft.homewavvisitor.model.data.auth;

import android.content.Context;
import android.content.SharedPreferences;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.urbanairship.analytics.data.EventsStorage;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u00105\u001a\n 7*\u0004\u0018\u000106062\b\b\u0002\u00108\u001a\u00020\bH\u0002J\b\u00109\u001a\u00020:H\u0016J\u0016\u0010;\u001a\u00020:2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020-0=H\u0016R\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bXD¢\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b8V@VX\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R(\u0010\u0016\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b8V@VX\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R(\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b8V@VX\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u001c8V@VX\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\"\u001a\u00020!2\u0006\u0010\u0010\u001a\u00020!8V@VX\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R(\u0010'\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b8V@VX\u000e¢\u0006\f\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R*\u0010*\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b8V@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0013\"\u0004\b,\u0010\u0015R*\u0010.\u001a\u0004\u0018\u00010-2\b\u0010\u0010\u001a\u0004\u0018\u00010-8V@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/auth/AuthPrefs;", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "context", "Landroid/content/Context;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "(Landroid/content/Context;Lcom/forasoft/homewavvisitor/dao/UserDao;)V", "KEY_ASPS", "", "KEY_ESUR", "KEY_FCM_TOKEN", "KEY_REQUEST_TIME", "KEY_SESSION_ID", "PREFS_NAME", "SERVICE_PREFS_NAME", "WELCOME_SCREENS_SHOWN", "value", "asps", "getAsps", "()Ljava/lang/String;", "setAsps", "(Ljava/lang/String;)V", "esur", "getEsur", "setEsur", "fcmToken", "getFcmToken", "setFcmToken", "", "isWelcomeScreensShown", "()Z", "setWelcomeScreensShown", "(Z)V", "", "lastSuccessRequest", "getLastSuccessRequest", "()J", "setLastSuccessRequest", "(J)V", "token", "getToken", "setToken", "tokenCandidate", "getTokenCandidate", "setTokenCandidate", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "user", "getUser", "()Lcom/forasoft/homewavvisitor/model/data/auth/User;", "setUser", "(Lcom/forasoft/homewavvisitor/model/data/auth/User;)V", "userUpdateSubscription", "Lio/reactivex/disposables/CompositeDisposable;", "getSharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "prefs", "logout", "", "subscribeForUsers", "flowable", "Lio/reactivex/Flowable;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AuthPrefs.kt */
public final class AuthPrefs implements AuthHolder {
    private final String KEY_ASPS = "asps";
    private final String KEY_ESUR = "esur";
    private final String KEY_FCM_TOKEN = "fcm_token";
    private final String KEY_REQUEST_TIME = "success_request_at";
    private final String KEY_SESSION_ID = EventsStorage.Events.COLUMN_NAME_SESSION_ID;
    private final String PREFS_NAME = "auth_data";
    private final String SERVICE_PREFS_NAME = "service_data";
    private final String WELCOME_SCREENS_SHOWN = "welcome_screens_shown";
    private final Context context;
    private String tokenCandidate;
    private User user;
    /* access modifiers changed from: private */
    public final UserDao userDao;
    private CompositeDisposable userUpdateSubscription;

    @Inject
    public AuthPrefs(Context context2, UserDao userDao2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        this.context = context2;
        this.userDao = userDao2;
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        this.userUpdateSubscription = compositeDisposable;
        Disposable subscribe = userDao2.getUser().subscribe(new Consumer<User>(this) {
            final /* synthetic */ AuthPrefs this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(User user) {
                this.this$0.setUser(user);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "userDao.getUser()\n      …er = it\n                }");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    private final synchronized SharedPreferences getSharedPreferences(String str) {
        return this.context.getSharedPreferences(str, 0);
    }

    static /* synthetic */ SharedPreferences getSharedPreferences$default(AuthPrefs authPrefs, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authPrefs.PREFS_NAME;
        }
        return authPrefs.getSharedPreferences(str);
    }

    public synchronized String getToken() {
        return getSharedPreferences$default(this, (String) null, 1, (Object) null).getString(this.KEY_SESSION_ID, (String) null);
    }

    public synchronized void setToken(String str) {
        getSharedPreferences$default(this, (String) null, 1, (Object) null).edit().putString(this.KEY_SESSION_ID, str).apply();
    }

    public synchronized String getFcmToken() {
        return getSharedPreferences$default(this, (String) null, 1, (Object) null).getString(this.KEY_FCM_TOKEN, (String) null);
    }

    public synchronized void setFcmToken(String str) {
        getSharedPreferences$default(this, (String) null, 1, (Object) null).edit().putString(this.KEY_FCM_TOKEN, str).apply();
    }

    public synchronized String getEsur() {
        return getSharedPreferences$default(this, (String) null, 1, (Object) null).getString(this.KEY_ESUR, (String) null);
    }

    public synchronized void setEsur(String str) {
        getSharedPreferences$default(this, (String) null, 1, (Object) null).edit().putString(this.KEY_ESUR, str).apply();
    }

    public synchronized String getAsps() {
        return getSharedPreferences$default(this, (String) null, 1, (Object) null).getString(this.KEY_ASPS, (String) null);
    }

    public synchronized void setAsps(String str) {
        getSharedPreferences$default(this, (String) null, 1, (Object) null).edit().putString(this.KEY_ASPS, str).apply();
    }

    public synchronized User getUser() {
        return this.user;
    }

    public synchronized void setUser(User user2) {
        this.user = user2;
    }

    public synchronized boolean isWelcomeScreensShown() {
        return getSharedPreferences(this.SERVICE_PREFS_NAME).getBoolean(this.WELCOME_SCREENS_SHOWN, false);
    }

    public synchronized void setWelcomeScreensShown(boolean z) {
        getSharedPreferences(this.SERVICE_PREFS_NAME).edit().putBoolean(this.WELCOME_SCREENS_SHOWN, z).apply();
    }

    public synchronized void subscribeForUsers(Flowable<User> flowable) {
        Intrinsics.checkParameterIsNotNull(flowable, "flowable");
        CompositeDisposable compositeDisposable = this.userUpdateSubscription;
        Disposable subscribe = flowable.subscribe((Consumer<? super User>) new AuthPrefs$subscribeForUsers$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "flowable.subscribe({\n   …     user = it\n        })");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    public synchronized String getTokenCandidate() {
        return this.tokenCandidate;
    }

    public synchronized void setTokenCandidate(String str) {
        this.tokenCandidate = str;
    }

    public synchronized long getLastSuccessRequest() {
        return getSharedPreferences$default(this, (String) null, 1, (Object) null).getLong(this.KEY_REQUEST_TIME, -1);
    }

    public synchronized void setLastSuccessRequest(long j) {
        getSharedPreferences$default(this, (String) null, 1, (Object) null).edit().putLong(this.KEY_REQUEST_TIME, j).apply();
    }

    public synchronized void logout() {
        this.userUpdateSubscription.clear();
        setUser((User) null);
        getSharedPreferences$default(this, (String) null, 1, (Object) null).edit().clear().apply();
        Single.fromCallable(new AuthPrefs$logout$1(this)).subscribeOn(Schedulers.io()).subscribe();
    }
}
