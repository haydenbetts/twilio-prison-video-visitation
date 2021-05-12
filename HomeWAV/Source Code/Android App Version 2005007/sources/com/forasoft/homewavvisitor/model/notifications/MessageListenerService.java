package com.forasoft.homewavvisitor.model.notifications;

import air.HomeWAV.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.fcm.AirshipFirebaseIntegration;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 62\u00020\u0001:\u00016B\u0005¢\u0006\u0002\u0010\u0002JU\u0010!\u001a\n #*\u0004\u0018\u00010\"0\"2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\b\b\u0001\u0010'\u001a\u00020(2\b\b\u0001\u0010)\u001a\u00020(2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020,0+\"\u00020,H\u0002¢\u0006\u0002\u0010-J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020/H\u0016J\u0010\u00101\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0017J\u0010\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020,H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00067"}, d2 = {"Lcom/forasoft/homewavvisitor/model/notifications/MessageListenerService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "getApi", "()Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "setApi", "(Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "getAuthHolder", "()Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "setAuthHolder", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "getNotificationDao", "()Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "setNotificationDao", "(Lcom/forasoft/homewavvisitor/dao/NotificationDao;)V", "settings", "Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "getSettings", "()Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "setSettings", "(Lcom/forasoft/homewavvisitor/model/data/account/Settings;)V", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "getUserDao", "()Lcom/forasoft/homewavvisitor/dao/UserDao;", "setUserDao", "(Lcom/forasoft/homewavvisitor/dao/UserDao;)V", "buildNotification", "Landroid/app/Notification;", "kotlin.jvm.PlatformType", "deleteIntent", "Landroid/app/PendingIntent;", "contentIntent", "contentTitle", "", "contentText", "formatArgs", "", "", "(Landroid/app/PendingIntent;Landroid/app/PendingIntent;II[Ljava/lang/String;)Landroid/app/Notification;", "createAirshipNotificationHandler", "", "onCreate", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageListenerService.kt */
public final class MessageListenerService extends FirebaseMessagingService {
    public static final String AIRSHIP_MESSAGE = "airship";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TYPE_ADDED_FUNDS = "added_funds";
    public static final String TYPE_INMATE_ONLINE = "inmate_online";
    public static final String TYPE_LOW_BALANCE = "low_balance";
    public static final String TYPE_NEW_MESSAGE = "new_message";
    public static final String TYPE_REQUEST_FUNDS = "fund_request";
    public static final String TYPE_SCHEDULE_CHANGED = "schedule_changed";
    public static final String TYPE_VOIP_PUSH = "voip";
    @Inject
    public HomewavApi api;
    @Inject
    public AuthHolder authHolder;
    @Inject
    public NotificationDao notificationDao;
    @Inject
    public Settings settings;
    @Inject
    public UserDao userDao;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/notifications/MessageListenerService$Companion;", "", "()V", "AIRSHIP_MESSAGE", "", "TYPE_ADDED_FUNDS", "TYPE_INMATE_ONLINE", "TYPE_LOW_BALANCE", "TYPE_NEW_MESSAGE", "TYPE_REQUEST_FUNDS", "TYPE_SCHEDULE_CHANGED", "TYPE_VOIP_PUSH", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MessageListenerService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final NotificationDao getNotificationDao() {
        NotificationDao notificationDao2 = this.notificationDao;
        if (notificationDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationDao");
        }
        return notificationDao2;
    }

    public final void setNotificationDao(NotificationDao notificationDao2) {
        Intrinsics.checkParameterIsNotNull(notificationDao2, "<set-?>");
        this.notificationDao = notificationDao2;
    }

    public final UserDao getUserDao() {
        UserDao userDao2 = this.userDao;
        if (userDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userDao");
        }
        return userDao2;
    }

    public final void setUserDao(UserDao userDao2) {
        Intrinsics.checkParameterIsNotNull(userDao2, "<set-?>");
        this.userDao = userDao2;
    }

    public final Settings getSettings() {
        Settings settings2 = this.settings;
        if (settings2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
        }
        return settings2;
    }

    public final void setSettings(Settings settings2) {
        Intrinsics.checkParameterIsNotNull(settings2, "<set-?>");
        this.settings = settings2;
    }

    public final AuthHolder getAuthHolder() {
        AuthHolder authHolder2 = this.authHolder;
        if (authHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authHolder");
        }
        return authHolder2;
    }

    public final void setAuthHolder(AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "<set-?>");
        this.authHolder = authHolder2;
    }

    public final HomewavApi getApi() {
        HomewavApi homewavApi = this.api;
        if (homewavApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("api");
        }
        return homewavApi;
    }

    public final void setApi(HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(homewavApi, "<set-?>");
        this.api = homewavApi;
    }

    public void onCreate() {
        super.onCreate();
        Toothpick.inject(this, Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE));
        createAirshipNotificationHandler();
    }

    private final void createAirshipNotificationHandler() {
        UAirship shared = UAirship.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared, "UAirship.shared()");
        MessageListenerService$createAirshipNotificationHandler$notificationHandler$1 messageListenerService$createAirshipNotificationHandler$notificationHandler$1 = new MessageListenerService$createAirshipNotificationHandler$notificationHandler$1(this, shared, this, shared.getAirshipConfigOptions());
        PushManager pushManager = shared.getPushManager();
        Intrinsics.checkExpressionValueIsNotNull(pushManager, "airship.pushManager");
        pushManager.setNotificationProvider(messageListenerService$createAirshipNotificationHandler$notificationHandler$1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02b6, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02e8, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02e9, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02eb, code lost:
        r0 = r8.userDao;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ed, code lost:
        if (r0 != null) goto L_0x02f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02ef, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("userDao");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02f5, code lost:
        r0.getSingleUser().subscribeOn(io.reactivex.schedulers.Schedulers.newThread()).subscribe(new com.forasoft.homewavvisitor.model.notifications.MessageListenerService$onMessageReceived$2(r20, r9, r3, r13, r10, r11), com.forasoft.homewavvisitor.model.notifications.MessageListenerService$onMessageReceived$3.INSTANCE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMessageReceived(com.google.firebase.messaging.RemoteMessage r21) {
        /*
            r20 = this;
            r8 = r20
            r0 = r21
            java.lang.String r1 = "remoteMessage"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            java.util.Map r1 = r21.getData()
            java.util.Set r1 = r1.keySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r2 = r1 instanceof java.util.Collection
            r3 = 0
            r4 = 2
            r6 = 0
            if (r2 == 0) goto L_0x0025
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0025
        L_0x0023:
            r1 = 0
            goto L_0x0043
        L_0x0025:
            java.util.Iterator r1 = r1.iterator()
        L_0x0029:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0023
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r7 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r7)
            java.lang.String r7 = "com.urbanairship"
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r2, r7, r6, r4, r3)
            if (r2 == 0) goto L_0x0029
            r1 = 1
        L_0x0043:
            if (r1 == 0) goto L_0x004d
            android.content.Context r1 = r20.getApplicationContext()
            com.urbanairship.push.fcm.AirshipFirebaseIntegration.processMessageSync(r1, r0)
            return
        L_0x004d:
            java.util.Map r1 = r21.getData()
            java.lang.String r2 = "remoteMessage.data"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r9 = r21.getMessageId()
            if (r9 != 0) goto L_0x005f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x005f:
            java.lang.String r2 = "remoteMessage.messageId!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r2)
            java.lang.String r2 = "messageType"
            java.lang.Object r2 = r1.get(r2)
            if (r2 != 0) goto L_0x006f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x006f:
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r2 = "voip"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r2)
            java.lang.String r7 = "inmate_name"
            if (r2 == 0) goto L_0x00fa
            java.lang.String r0 = "call_id"
            java.lang.Object r0 = r1.get(r0)
            r10 = r0
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r0 = "protocol_calling"
            java.lang.Object r0 = r1.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "webrtc"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x009a
            com.forasoft.homewavvisitor.model.data.Protocol r0 = com.forasoft.homewavvisitor.model.data.Protocol.WEBRTC
            goto L_0x009c
        L_0x009a:
            com.forasoft.homewavvisitor.model.data.Protocol r0 = com.forasoft.homewavvisitor.model.data.Protocol.LIVESWITCH
        L_0x009c:
            r11 = r0
            java.lang.String r0 = "inmate_credit_balance"
            java.lang.Object r0 = r1.get(r0)
            r13 = r0
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r0 = "inmate_id"
            java.lang.Object r0 = r1.get(r0)
            r14 = r0
            java.lang.String r14 = (java.lang.String) r14
            java.lang.String r0 = "pub_id"
            java.lang.Object r0 = r1.get(r0)
            r15 = r0
            java.lang.String r15 = (java.lang.String) r15
            java.lang.Object r0 = r1.get(r7)
            r16 = r0
            java.lang.String r16 = (java.lang.String) r16
            com.forasoft.homewavvisitor.model.data.Call r0 = new com.forasoft.homewavvisitor.model.data.Call
            if (r10 != 0) goto L_0x00c7
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00c7:
            r12 = 0
            if (r14 != 0) goto L_0x00cd
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00cd:
            if (r15 != 0) goto L_0x00d2
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00d2:
            if (r16 != 0) goto L_0x00d7
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00d7:
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            android.content.Intent r1 = new android.content.Intent
            r2 = r8
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Class<com.forasoft.homewavvisitor.model.IncomingCallService> r3 = com.forasoft.homewavvisitor.model.IncomingCallService.class
            r1.<init>(r2, r3)
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            java.lang.String r2 = "call"
            r1.putExtra(r2, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r0 < r2) goto L_0x00f6
            r8.startForegroundService(r1)
            goto L_0x00f9
        L_0x00f6:
            r8.startService(r1)
        L_0x00f9:
            return
        L_0x00fa:
            org.json.JSONObject r2 = new org.json.JSONObject
            java.lang.String r11 = "customData"
            java.lang.Object r11 = r1.get(r11)
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = "itemName"
            if (r11 == 0) goto L_0x0109
            goto L_0x0110
        L_0x0109:
            java.lang.Object r1 = r1.get(r12)
            r11 = r1
            java.lang.String r11 = (java.lang.String) r11
        L_0x0110:
            if (r11 != 0) goto L_0x0115
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0115:
            r2.<init>(r11)
            java.lang.String r1 = "type"
            java.lang.String r11 = r2.optString(r1, r3)
            if (r11 != 0) goto L_0x0124
            r2.put(r1, r10)
        L_0x0124:
            java.lang.String r11 = r2.toString()
            java.lang.String r2 = "with(JSONObject((data[\"c…     toString()\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r2)
            long r13 = r21.getSentTime()
            java.lang.String r2 = "inmate_online"
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r2)
            java.lang.String r16 = "settings"
            if (r15 == 0) goto L_0x0149
            com.forasoft.homewavvisitor.model.data.account.Settings r15 = r8.settings
            if (r15 != 0) goto L_0x0143
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
        L_0x0143:
            boolean r15 = r15.getStatusNotification()
            if (r15 == 0) goto L_0x0188
        L_0x0149:
            java.lang.String r15 = "new_message"
            boolean r17 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r15)
            if (r17 == 0) goto L_0x015e
            com.forasoft.homewavvisitor.model.data.account.Settings r3 = r8.settings
            if (r3 != 0) goto L_0x0158
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
        L_0x0158:
            boolean r3 = r3.getMessageNotification()
            if (r3 == 0) goto L_0x0188
        L_0x015e:
            java.lang.String r3 = "low_balance"
            boolean r18 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r3)
            if (r18 == 0) goto L_0x0173
            com.forasoft.homewavvisitor.model.data.account.Settings r5 = r8.settings
            if (r5 != 0) goto L_0x016d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
        L_0x016d:
            boolean r5 = r5.getBalanceNotification()
            if (r5 == 0) goto L_0x0188
        L_0x0173:
            java.lang.String r5 = "schedule_changed"
            boolean r19 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r5)
            if (r19 == 0) goto L_0x0189
            com.forasoft.homewavvisitor.model.data.account.Settings r6 = r8.settings
            if (r6 != 0) goto L_0x0182
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
        L_0x0182:
            boolean r6 = r6.getScheduleNotification()
            if (r6 != 0) goto L_0x0189
        L_0x0188:
            return
        L_0x0189:
            com.forasoft.homewavvisitor.model.notifications.NotificationDismissReceiver$Companion r6 = com.forasoft.homewavvisitor.model.notifications.NotificationDismissReceiver.Companion
            r4 = r8
            android.content.Context r4 = (android.content.Context) r4
            int r0 = r9.hashCode()
            android.app.PendingIntent r4 = r6.getIntent(r4, r0)
            int r0 = r10.hashCode()
            java.lang.String r6 = "inmateName"
            switch(r0) {
                case -1489344888: goto L_0x02b8;
                case -847894763: goto L_0x0281;
                case 210284648: goto L_0x0245;
                case 544941297: goto L_0x0204;
                case 1152193804: goto L_0x01a1;
                default: goto L_0x019f;
            }
        L_0x019f:
            goto L_0x02e8
        L_0x01a1:
            boolean r0 = r10.equals(r5)
            if (r0 == 0) goto L_0x02e8
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r11)
            java.lang.String r1 = r0.getString(r1)
            java.lang.String r2 = r0.getString(r7)
            java.lang.String r3 = "time_slot"
            java.lang.String r0 = r0.getString(r3)
            java.lang.String r3 = "confirm"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 == 0) goto L_0x01e3
            r3 = 0
            r5 = 2131821018(0x7f1101da, float:1.9274767E38)
            r6 = 2131821021(0x7f1101dd, float:1.9274773E38)
            r1 = 2
            java.lang.String[] r7 = new java.lang.String[r1]
            r1 = 0
            r7[r1] = r2
            r1 = 1
            r7[r1] = r0
            r12 = 2
            r15 = 0
            r0 = r20
            r1 = r4
            r2 = r3
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r12
            r7 = r15
            android.app.Notification r0 = buildNotification$default(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x02b6
        L_0x01e3:
            r3 = 0
            r5 = 2131821018(0x7f1101da, float:1.9274767E38)
            r6 = 2131821020(0x7f1101dc, float:1.9274771E38)
            r1 = 2
            java.lang.String[] r7 = new java.lang.String[r1]
            r1 = 0
            r7[r1] = r2
            r1 = 1
            r7[r1] = r0
            r12 = 2
            r15 = 0
            r0 = r20
            r1 = r4
            r2 = r3
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r12
            r7 = r15
            android.app.Notification r0 = buildNotification$default(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x02b6
        L_0x0204:
            boolean r0 = r10.equals(r3)
            if (r0 == 0) goto L_0x02e8
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r11)
            java.lang.String r1 = r0.getString(r1)
            java.lang.String r2 = "below_two"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x0222
            r1 = 2131821000(0x7f1101c8, float:1.927473E38)
            r5 = 2131821000(0x7f1101c8, float:1.927473E38)
            goto L_0x0228
        L_0x0222:
            r1 = 2131821001(0x7f1101c9, float:1.9274733E38)
            r5 = 2131821001(0x7f1101c9, float:1.9274733E38)
        L_0x0228:
            java.lang.String r0 = r0.getString(r6)
            r2 = 0
            r3 = 2131821006(0x7f1101ce, float:1.9274743E38)
            r1 = 1
            java.lang.String[] r6 = new java.lang.String[r1]
            r1 = 0
            r6[r1] = r0
            r7 = 2
            r12 = 0
            r0 = r20
            r1 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r12
            android.app.Notification r0 = buildNotification$default(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x02b6
        L_0x0245:
            boolean r0 = r10.equals(r15)
            if (r0 == 0) goto L_0x02e8
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r11)
            java.lang.String r1 = r0.getString(r1)
            java.lang.String r0 = r0.getString(r6)
            r2 = 0
            r3 = 2131821010(0x7f1101d2, float:1.9274751E38)
            r5 = 2131821009(0x7f1101d1, float:1.927475E38)
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]
            r7 = 0
            r6[r7] = r0
            java.lang.String r0 = "s3_video"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x0270
            java.lang.String r1 = "video"
        L_0x0270:
            r0 = 1
            r6[r0] = r1
            r7 = 2
            r12 = 0
            r0 = r20
            r1 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r12
            android.app.Notification r0 = buildNotification$default(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x02b6
        L_0x0281:
            java.lang.String r0 = "fund_request"
            boolean r0 = r10.equals(r0)
            if (r0 == 0) goto L_0x02e8
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r11)
            java.lang.String r1 = r0.getString(r7)
            java.lang.String r2 = "value"
            java.lang.String r0 = r0.getString(r2)
            r2 = 0
            r3 = 2131821017(0x7f1101d9, float:1.9274765E38)
            r5 = 2131821016(0x7f1101d8, float:1.9274763E38)
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]
            r7 = 0
            r6[r7] = r1
            r1 = 1
            r6[r1] = r0
            r7 = 2
            r12 = 0
            r0 = r20
            r1 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r12
            android.app.Notification r0 = buildNotification$default(r0, r1, r2, r3, r4, r5, r6, r7)
        L_0x02b6:
            r3 = r0
            goto L_0x02e9
        L_0x02b8:
            boolean r0 = r10.equals(r2)
            if (r0 == 0) goto L_0x02e8
            r2 = 0
            r3 = 2131821005(0x7f1101cd, float:1.927474E38)
            r5 = 2131821004(0x7f1101cc, float:1.9274739E38)
            r0 = 1
            java.lang.String[] r6 = new java.lang.String[r0]
            java.util.Map r0 = r21.getData()
            java.lang.Object r0 = r0.get(r12)
            if (r0 != 0) goto L_0x02d5
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x02d5:
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            r6[r1] = r0
            r7 = 2
            r12 = 0
            r0 = r20
            r1 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r12
            android.app.Notification r0 = buildNotification$default(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x02b6
        L_0x02e8:
            r3 = 0
        L_0x02e9:
            if (r3 == 0) goto L_0x0316
            com.forasoft.homewavvisitor.dao.UserDao r0 = r8.userDao
            if (r0 != 0) goto L_0x02f5
            java.lang.String r1 = "userDao"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x02f5:
            io.reactivex.Single r0 = r0.getSingleUser()
            io.reactivex.Scheduler r1 = io.reactivex.schedulers.Schedulers.newThread()
            io.reactivex.Single r12 = r0.subscribeOn(r1)
            com.forasoft.homewavvisitor.model.notifications.MessageListenerService$onMessageReceived$2 r15 = new com.forasoft.homewavvisitor.model.notifications.MessageListenerService$onMessageReceived$2
            r0 = r15
            r1 = r20
            r2 = r9
            r4 = r13
            r6 = r10
            r7 = r11
            r0.<init>(r1, r2, r3, r4, r6, r7)
            io.reactivex.functions.Consumer r15 = (io.reactivex.functions.Consumer) r15
            com.forasoft.homewavvisitor.model.notifications.MessageListenerService$onMessageReceived$3 r0 = com.forasoft.homewavvisitor.model.notifications.MessageListenerService$onMessageReceived$3.INSTANCE
            io.reactivex.functions.Consumer r0 = (io.reactivex.functions.Consumer) r0
            r12.subscribe(r15, r0)
        L_0x0316:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.notifications.MessageListenerService.onMessageReceived(com.google.firebase.messaging.RemoteMessage):void");
    }

    public void onNewToken(String str) {
        String id;
        Intrinsics.checkParameterIsNotNull(str, "token");
        Timber.d("onTokenRefresh", new Object[0]);
        AirshipFirebaseIntegration.processNewToken(this);
        AuthHolder authHolder2 = this.authHolder;
        if (authHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authHolder");
        }
        authHolder2.setFcmToken(str);
        AuthHolder authHolder3 = this.authHolder;
        if (authHolder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authHolder");
        }
        User user = authHolder3.getUser();
        if (user != null && (id = user.getId()) != null) {
            HomewavApi homewavApi = this.api;
            if (homewavApi == null) {
                Intrinsics.throwUninitializedPropertyAccessException("api");
            }
            HomewavApi.DefaultImpls.saveToken$default(homewavApi, id, str, (String) null, 4, (Object) null).subscribeOn(Schedulers.io()).subscribe(MessageListenerService$onNewToken$1.INSTANCE, MessageListenerService$onNewToken$2.INSTANCE);
        }
    }

    static /* synthetic */ Notification buildNotification$default(MessageListenerService messageListenerService, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, int i2, String[] strArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            pendingIntent = null;
        }
        PendingIntent pendingIntent3 = pendingIntent;
        if ((i3 & 2) != 0) {
            Context context = messageListenerService;
            pendingIntent2 = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        }
        return messageListenerService.buildNotification(pendingIntent3, pendingIntent2, i, i2, strArr);
    }

    private final Notification buildNotification(PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, int i2, String... strArr) {
        NotificationCompat.Builder contentText = new NotificationCompat.Builder(this, "homewav").setSmallIcon(R.drawable.ic_notification).setColor(ContextExtensionsKt.getColorResource(this, R.color.colorAccent)).setContentTitle(getResources().getString(i)).setContentText(getResources().getString(i2, Arrays.copyOf(strArr, strArr.length)));
        if (pendingIntent != null) {
            contentText.setDeleteIntent(pendingIntent);
        }
        if (pendingIntent2 != null) {
            contentText.setContentIntent(pendingIntent2);
        }
        return contentText.setAutoCancel(true).build();
    }
}
