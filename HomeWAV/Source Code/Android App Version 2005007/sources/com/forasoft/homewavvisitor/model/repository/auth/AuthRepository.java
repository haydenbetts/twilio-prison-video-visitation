package com.forasoft.homewavvisitor.model.repository.auth;

import androidx.work.WorkManager;
import com.forasoft.homewavvisitor.AppInfo;
import com.forasoft.homewavvisitor.BuildConfig;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.urbanairship.UAirship;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.util.Attributes;
import io.reactivex.Flowable;
import io.reactivex.Notification;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001Bw\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\b\u0010\"\u001a\u0004\u0018\u00010!J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020!0$J\"\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0&0 2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(J\u0016\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0-H\u0007J\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010&0 2\u0006\u0010/\u001a\u00020(J\u000e\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020!J\u0006\u00102\u001a\u00020+R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/auth/AuthRepository;", "", "signUpApi", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "homewavApi", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "appInfo", "Lcom/forasoft/homewavvisitor/AppInfo;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "settings", "Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "messageDao", "Lcom/forasoft/homewavvisitor/dao/MessageDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "creditDao", "Lcom/forasoft/homewavvisitor/dao/CreditDao;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "(Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/AppInfo;Lcom/forasoft/homewavvisitor/model/Analytics;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/data/account/Settings;Lcom/forasoft/homewavvisitor/dao/UserDao;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/MessageDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/dao/CallDao;Lcom/forasoft/homewavvisitor/dao/CreditDao;Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;)V", "getSingleUser", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "getUser", "getUserFlowable", "Lio/reactivex/Flowable;", "login", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "username", "", "password", "logout", "", "doOnComplete", "Lkotlin/Function0;", "resetPassword", "email", "saveUser", "user", "simpleLogout", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AuthRepository.kt */
public final class AuthRepository {
    private final Analytics analytics;
    private final AppInfo appInfo;
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final CallDao callDao;
    /* access modifiers changed from: private */
    public final CreditDao creditDao;
    /* access modifiers changed from: private */
    public final HomewavApi homewavApi;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final MessageDao messageDao;
    /* access modifiers changed from: private */
    public final NotificationDao notificationDao;
    private final PusherHolder pusherHolder;
    private final Settings settings;
    private final SignUpApi signUpApi;
    /* access modifiers changed from: private */
    public final UserDao userDao;
    /* access modifiers changed from: private */
    public final VisitDao visitDao;

    @Inject
    public AuthRepository(SignUpApi signUpApi2, HomewavApi homewavApi2, AppInfo appInfo2, Analytics analytics2, AuthHolder authHolder2, Settings settings2, UserDao userDao2, NotificationDao notificationDao2, MessageDao messageDao2, InmateDao inmateDao2, VisitDao visitDao2, CallDao callDao2, CreditDao creditDao2, PusherHolder pusherHolder2) {
        Intrinsics.checkParameterIsNotNull(signUpApi2, "signUpApi");
        Intrinsics.checkParameterIsNotNull(homewavApi2, "homewavApi");
        Intrinsics.checkParameterIsNotNull(appInfo2, "appInfo");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(settings2, "settings");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(messageDao2, "messageDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        Intrinsics.checkParameterIsNotNull(creditDao2, "creditDao");
        Intrinsics.checkParameterIsNotNull(pusherHolder2, "pusherHolder");
        this.signUpApi = signUpApi2;
        this.homewavApi = homewavApi2;
        this.appInfo = appInfo2;
        this.analytics = analytics2;
        this.authHolder = authHolder2;
        this.settings = settings2;
        this.userDao = userDao2;
        this.notificationDao = notificationDao2;
        this.messageDao = messageDao2;
        this.inmateDao = inmateDao2;
        this.visitDao = visitDao2;
        this.callDao = callDao2;
        this.creditDao = creditDao2;
        this.pusherHolder = pusherHolder2;
    }

    public final Single<ApiResponse<User>> login(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str2, "password");
        Single<ApiResponse<User>> doOnSuccess = SignUpApi.DefaultImpls.login$default(this.signUpApi, str, str2, this.appInfo.getVersionName(), this.authHolder.getFcmToken(), this.authHolder.getFcmToken(), (String) null, 32, (Object) null).doOnSuccess(new AuthRepository$login$1(this));
        Intrinsics.checkExpressionValueIsNotNull(doOnSuccess, "signUpApi.login(username…{})\n                    }");
        return doOnSuccess;
    }

    public final Single<ApiResponse<Object>> resetPassword(String str) {
        Intrinsics.checkParameterIsNotNull(str, "email");
        return this.signUpApi.resetPassword(str);
    }

    public final Flowable<User> getUserFlowable() {
        Flowable<User> user = this.userDao.getUser();
        this.authHolder.subscribeForUsers(user);
        Flowable<User> doOnEach = user.doOnEach((Consumer<? super Notification<User>>) AuthRepository$getUserFlowable$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(doOnEach, "flowable.doOnEach {\n    …serFlowable: \")\n        }");
        return doOnEach;
    }

    public final User getUser() {
        return this.authHolder.getUser();
    }

    public final void saveUser(User user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.userDao.saveUser(user);
    }

    public final Single<User> getSingleUser() {
        return this.userDao.getSingleUser();
    }

    public final void simpleLogout() {
        this.authHolder.logout();
        this.pusherHolder.unsubscribeFromChannel();
    }

    public final void logout(Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "doOnComplete");
        HomewavApi homewavApi2 = this.homewavApi;
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        HomewavApi.DefaultImpls.saveToken$default(homewavApi2, user.getId(), "", (String) null, 4, (Object) null).subscribeOn(Schedulers.io()).subscribe(AuthRepository$logout$1.INSTANCE, AuthRepository$logout$2.INSTANCE);
        HomewavApi homewavApi3 = this.homewavApi;
        UAirship shared = UAirship.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared, "UAirship.shared()");
        AirshipChannel channel = shared.getChannel();
        Intrinsics.checkExpressionValueIsNotNull(channel, "UAirship.shared().channel");
        String id = channel.getId();
        if (id == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(id, "UAirship.shared().channel.id!!");
        CommonKt.applyAsync(homewavApi3.getAirshipChannelTags(BuildConfig.AIRSHIP_SCHEME_NAME, id)).subscribe(new AuthRepository$logout$3(this, function0), AuthRepository$logout$4.INSTANCE);
        this.analytics.onLogout();
        this.settings.clear();
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new AuthRepository$logout$5(this), 31, (Object) null);
        WorkManager.getInstance().cancelAllWork();
        this.pusherHolder.unsubscribeFromChannel();
    }
}
