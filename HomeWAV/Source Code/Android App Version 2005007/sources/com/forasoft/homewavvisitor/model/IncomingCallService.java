package com.forasoft.homewavvisitor.model;

import air.HomeWAV.R;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.work.WorkRequest;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.system.RingtoneManager;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.IncomingCallActivity;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27ServicesKt;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0016\u0018\u0000 02\u00020\u0001:\u00010B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0012\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010*\u001a\u00020%H\u0016J\b\u0010+\u001a\u00020%H\u0016J \u0010,\u001a\u00020-2\u0006\u0010&\u001a\u00020'2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00198\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u00061"}, d2 = {"Lcom/forasoft/homewavvisitor/model/IncomingCallService;", "Landroid/app/Service;", "()V", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "getApi", "()Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "setApi", "(Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "getAuthHolder", "()Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "setAuthHolder", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "getPusherHolder", "()Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "setPusherHolder", "(Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;)V", "pusherListener", "com/forasoft/homewavvisitor/model/IncomingCallService$pusherListener$1", "Lcom/forasoft/homewavvisitor/model/IncomingCallService$pusherListener$1;", "ringtone", "Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;", "getRingtone", "()Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;", "setRingtone", "(Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;)V", "buildNotification", "Landroid/app/Notification;", "pendingIntent", "Landroid/app/PendingIntent;", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "handleNotificationAction", "", "intent", "Landroid/content/Intent;", "onBind", "Landroid/os/IBinder;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: IncomingCallService.kt */
public final class IncomingCallService extends Service {
    public static final String CHANNEL_ID = "701";
    public static final String CHANNEL_NAME = "HomeWAV Incoming Call";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int INCOMING_CALL_NOTIFICATION_ID = 701;
    public static final String SKIP_CALL_DIALOG = "skip_call_dialog";
    /* access modifiers changed from: private */
    public static IncomingCallService instance;
    @Inject
    public HomewavApi api;
    @Inject
    public AuthHolder authHolder;
    @Inject
    public PusherHolder pusherHolder;
    private final IncomingCallService$pusherListener$1 pusherListener = new IncomingCallService$pusherListener$1(this);
    @Inject
    public RingtoneManager ringtone;

    public IBinder onBind(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        return null;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/IncomingCallService$Companion;", "", "()V", "CHANNEL_ID", "", "CHANNEL_NAME", "INCOMING_CALL_NOTIFICATION_ID", "", "SKIP_CALL_DIALOG", "instance", "Lcom/forasoft/homewavvisitor/model/IncomingCallService;", "getInstance", "()Lcom/forasoft/homewavvisitor/model/IncomingCallService;", "setInstance", "(Lcom/forasoft/homewavvisitor/model/IncomingCallService;)V", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: IncomingCallService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IncomingCallService getInstance() {
            return IncomingCallService.instance;
        }

        public final void setInstance(IncomingCallService incomingCallService) {
            IncomingCallService.instance = incomingCallService;
        }
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

    public final RingtoneManager getRingtone() {
        RingtoneManager ringtoneManager = this.ringtone;
        if (ringtoneManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ringtone");
        }
        return ringtoneManager;
    }

    public final void setRingtone(RingtoneManager ringtoneManager) {
        Intrinsics.checkParameterIsNotNull(ringtoneManager, "<set-?>");
        this.ringtone = ringtoneManager;
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

    public final PusherHolder getPusherHolder() {
        PusherHolder pusherHolder2 = this.pusherHolder;
        if (pusherHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        return pusherHolder2;
    }

    public final void setPusherHolder(PusherHolder pusherHolder2) {
        Intrinsics.checkParameterIsNotNull(pusherHolder2, "<set-?>");
        this.pusherHolder = pusherHolder2;
    }

    public void onCreate() {
        super.onCreate();
        Toothpick.inject(this, Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE));
        instance = this;
        RingtoneManager ringtoneManager = this.ringtone;
        if (ringtoneManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ringtone");
        }
        ringtoneManager.play();
        AuthHolder authHolder2 = this.authHolder;
        if (authHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authHolder");
        }
        User user = authHolder2.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id != null) {
            PusherHolder pusherHolder2 = this.pusherHolder;
            if (pusherHolder2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
            }
            pusherHolder2.subscribe(visitor_id, false);
        }
        PusherHolder pusherHolder3 = this.pusherHolder;
        if (pusherHolder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        pusherHolder3.listenEvents(this.pusherListener);
    }

    public void onDestroy() {
        super.onDestroy();
        instance = null;
        PusherHolder pusherHolder2 = this.pusherHolder;
        if (pusherHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        pusherHolder2.stopListenEvents(this.pusherListener);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Object systemService = getSystemService("keyguard");
        if (systemService != null) {
            if (((KeyguardManager) systemService).isKeyguardLocked()) {
                Object systemService2 = getSystemService("power");
                if (systemService2 != null) {
                    ((PowerManager) systemService2).newWakeLock(805306394, getPackageName() + "IncomingCallLock").acquire(WorkRequest.MIN_BACKOFF_MILLIS);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
                }
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 4);
                notificationChannel.setSound((Uri) null, (AudioAttributes) null);
                Sdk27ServicesKt.getNotificationManager(this).createNotificationChannel(notificationChannel);
            }
            Call call = (Call) intent.getParcelableExtra(NotificationCompat.CATEGORY_CALL);
            if (call == null) {
                return 3;
            }
            startForeground(INCOMING_CALL_NOTIFICATION_ID, buildNotification(IncomingCallActivity.Companion.getCallIntent(this, call), call));
            return 3;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.KeyguardManager");
    }

    private final Notification buildNotification(PendingIntent pendingIntent, Call call) {
        int i;
        int i2;
        Context context = this;
        NotificationCompat.Builder sound = new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle(getResources().getString(R.string.notification_incoming_call_title)).setContentText(getResources().getString(R.string.notification_incoming_call_body, new Object[]{call.getInmate_name()})).setContentIntent(pendingIntent).setSmallIcon(R.drawable.ic_notification).setColor(ContextExtensionsKt.getColorResource(this, R.color.colorAccent)).setShowWhen(false).setPriority(2).setSound((Uri) null);
        if (Build.VERSION.SDK_INT >= 21) {
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            Configuration configuration = resources.getConfiguration();
            Intrinsics.checkExpressionValueIsNotNull(configuration, "resources.configuration");
            int i3 = configuration.uiMode & 48;
            int i4 = R.color.white;
            if (i3 == 32) {
                i2 = R.layout.call_notification_dark;
                i = R.color.white;
                i4 = 17170444;
            } else {
                i2 = R.layout.call_notification_light;
                i = R.color.toolbarColor;
            }
            RemoteViews remoteViews = new RemoteViews(getPackageName(), i2);
            Context baseContext = getBaseContext();
            Intrinsics.checkExpressionValueIsNotNull(baseContext, "baseContext");
            remoteViews.setImageViewBitmap(R.id.avatar, ContextExtensionsKt.createTextBitmap(baseContext, StringExtensionsKt.getAsInitials(call.getInmate_name()), R.dimen.initials_size_large, i4, i));
            remoteViews.setTextViewText(R.id.name, call.getInmate_name());
            remoteViews.setTextViewText(R.id.subtitle, getResources().getString(R.string.notification_remaining, new Object[]{call.getInmateCredits()}));
            Intent intent = new Intent(context, IncomingCallActionsReceiver.class);
            intent.setAction(getPackageName() + ".DECLINE_CALL");
            intent.putExtra("call_id", call.getId());
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 268435456);
            sound.addAction(R.drawable.ic_decline, getResources().getString(R.string.call_decline), broadcast);
            remoteViews.setOnClickPendingIntent(R.id.btn_decline, broadcast);
            Intent intent2 = new Intent(context, IncomingCallActionsReceiver.class);
            intent2.setAction(getPackageName() + ".ANSWER_CALL");
            intent2.putExtra(IncomingCallActivity.EXTRA_CALL, call);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 0, intent2, 268435456);
            sound.addAction(R.drawable.ic_call, getResources().getString(R.string.call_answer), broadcast2);
            remoteViews.setOnClickPendingIntent(R.id.btn_answer, broadcast2);
            sound.setCustomBigContentView(remoteViews);
            sound.setCustomHeadsUpContentView(remoteViews);
            sound.setCategory(NotificationCompat.CATEGORY_CALL);
            sound.setFullScreenIntent(pendingIntent, true);
        }
        Notification build = sound.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
        return build;
    }

    public final void handleNotificationAction(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        stopForeground(true);
        String action = intent.getAction();
        if (Intrinsics.areEqual((Object) action, (Object) getPackageName() + ".HIDE_NOTIFICATION")) {
            stopSelf();
            return;
        }
        if (Intrinsics.areEqual((Object) action, (Object) getPackageName() + ".DECLINE_CALL")) {
            String stringExtra = intent.getStringExtra("call_id");
            if (stringExtra != null) {
                HomewavApi homewavApi = this.api;
                if (homewavApi == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("api");
                }
                homewavApi.cancelCall(stringExtra).subscribeOn(Schedulers.io()).subscribe(new IncomingCallService$handleNotificationAction$$inlined$let$lambda$1(this, stringExtra), new IncomingCallService$handleNotificationAction$$inlined$let$lambda$2(this, stringExtra));
            }
            RingtoneManager ringtoneManager = this.ringtone;
            if (ringtoneManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ringtone");
            }
            ringtoneManager.stop();
            PusherHolder pusherHolder2 = this.pusherHolder;
            if (pusherHolder2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
            }
            pusherHolder2.release(false);
            return;
        }
        if (Intrinsics.areEqual((Object) action, (Object) getPackageName() + ".ANSWER_CALL")) {
            Intent addFlags = new Intent(this, IncomingCallActivity.class).addFlags(268435456);
            Intrinsics.checkExpressionValueIsNotNull(addFlags, "Intent(this, IncomingCal…t.FLAG_ACTIVITY_NEW_TASK)");
            addFlags.putExtra(SKIP_CALL_DIALOG, true);
            addFlags.putExtra(IncomingCallActivity.EXTRA_CALL, intent.getParcelableExtra(IncomingCallActivity.EXTRA_CALL));
            startActivity(addFlags);
        }
    }
}
