package com.forasoft.homewavvisitor.model.data.auth;

import io.reactivex.Flowable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010%\u001a\u00020&H&J\u0016\u0010'\u001a\u00020&2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020 0)H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007R\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u0014X¦\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0005\"\u0004\b\u001b\u0010\u0007R\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0005\"\u0004\b\u001e\u0010\u0007R\u001a\u0010\u001f\u001a\u0004\u0018\u00010 X¦\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006*"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "", "asps", "", "getAsps", "()Ljava/lang/String;", "setAsps", "(Ljava/lang/String;)V", "esur", "getEsur", "setEsur", "fcmToken", "getFcmToken", "setFcmToken", "isWelcomeScreensShown", "", "()Z", "setWelcomeScreensShown", "(Z)V", "lastSuccessRequest", "", "getLastSuccessRequest", "()J", "setLastSuccessRequest", "(J)V", "token", "getToken", "setToken", "tokenCandidate", "getTokenCandidate", "setTokenCandidate", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "getUser", "()Lcom/forasoft/homewavvisitor/model/data/auth/User;", "setUser", "(Lcom/forasoft/homewavvisitor/model/data/auth/User;)V", "logout", "", "subscribeForUsers", "flowable", "Lio/reactivex/Flowable;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AuthHolder.kt */
public interface AuthHolder {
    String getAsps();

    String getEsur();

    String getFcmToken();

    long getLastSuccessRequest();

    String getToken();

    String getTokenCandidate();

    User getUser();

    boolean isWelcomeScreensShown();

    void logout();

    void setAsps(String str);

    void setEsur(String str);

    void setFcmToken(String str);

    void setLastSuccessRequest(long j);

    void setToken(String str);

    void setTokenCandidate(String str);

    void setUser(User user);

    void setWelcomeScreensShown(boolean z);

    void subscribeForUsers(Flowable<User> flowable);
}
