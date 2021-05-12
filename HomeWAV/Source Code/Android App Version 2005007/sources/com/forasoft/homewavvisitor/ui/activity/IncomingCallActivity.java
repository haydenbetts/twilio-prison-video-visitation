package com.forasoft.homewavvisitor.ui.activity;

import air.HomeWAV.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.toothpick.DI;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0017\u0018\u0000 -2\u00020\u00012\u00020\u0002:\u0001-B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0016J\u0012\u0010\"\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020 H\u0014J\b\u0010&\u001a\u00020 H\u0016J\u0018\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006."}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/IncomingCallActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/CallListener;", "()V", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "getHeartbeatRepository", "()Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "setHeartbeatRepository", "(Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "isExitDisabled", "", "navigator", "Lru/terrakok/cicerone/Navigator;", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "getPusherHolder", "()Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "setPusherHolder", "(Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;)V", "pusherListener", "com/forasoft/homewavvisitor/ui/activity/IncomingCallActivity$pusherListener$1", "Lcom/forasoft/homewavvisitor/ui/activity/IncomingCallActivity$pusherListener$1;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "getRouter", "()Lcom/forasoft/homewavvisitor/HomewavRouter;", "setRouter", "(Lcom/forasoft/homewavvisitor/HomewavRouter;)V", "onCallCancelled", "", "onCallDeclined", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDialogButtonClicked", "onLiveswitchCallAccepted", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "onTwilioCallAccepted", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: IncomingCallActivity.kt */
public final class IncomingCallActivity extends BaseActivity implements CallListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRA_CALL = "extra call";
    private HashMap _$_findViewCache;
    @Inject
    public HeartbeatRepository heartbeatRepository;
    private boolean isExitDisabled;
    private final Navigator navigator = new SupportAppNavigator(this, R.id.container);
    @Inject
    public PusherHolder pusherHolder;
    private final IncomingCallActivity$pusherListener$1 pusherListener = new IncomingCallActivity$pusherListener$1(this);
    @Inject
    public HomewavRouter router;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/IncomingCallActivity$Companion;", "", "()V", "EXTRA_CALL", "", "getCallIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: IncomingCallActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PendingIntent getCallIntent(Context context, Call call) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
            Intent putExtra = new Intent(context, IncomingCallActivity.class).putExtra(IncomingCallActivity.EXTRA_CALL, call);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, Incoming…utExtra(EXTRA_CALL, call)");
            PendingIntent activity = PendingIntent.getActivity(context, Integer.parseInt(call.getId()), putExtra, 1073741824);
            Intrinsics.checkExpressionValueIsNotNull(activity, "PendingIntent.getActivit…dingIntent.FLAG_ONE_SHOT)");
            return activity;
        }
    }

    public final HomewavRouter getRouter() {
        HomewavRouter homewavRouter = this.router;
        if (homewavRouter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        return homewavRouter;
    }

    public final void setRouter(HomewavRouter homewavRouter) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "<set-?>");
        this.router = homewavRouter;
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

    public final HeartbeatRepository getHeartbeatRepository() {
        HeartbeatRepository heartbeatRepository2 = this.heartbeatRepository;
        if (heartbeatRepository2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("heartbeatRepository");
        }
        return heartbeatRepository2;
    }

    public final void setHeartbeatRepository(HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "<set-?>");
        this.heartbeatRepository = heartbeatRepository2;
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Toothpick.inject(this, Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE));
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(NavigatorHolder.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…igatorHolder::class.java)");
        setNavigatorHolder((NavigatorHolder) instance);
        if (Build.VERSION.SDK_INT < 27) {
            getWindow().addFlags(2621440);
        } else {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        }
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_incoming_call);
        PusherHolder pusherHolder2 = this.pusherHolder;
        if (pusherHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        pusherHolder2.listenEvents(this.pusherListener);
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        Call call = extras != null ? (Call) extras.getParcelable(EXTRA_CALL) : null;
        if (call != null) {
            HomewavRouter homewavRouter = this.router;
            if (homewavRouter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("router");
            }
            homewavRouter.newRootScreen(new Screens.IncomingCallScreen(call));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PusherHolder pusherHolder2 = this.pusherHolder;
        if (pusherHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        pusherHolder2.stopListenEvents(this.pusherListener);
        super.onDestroy();
    }

    public void onTwilioCallAccepted(CallWrapper callWrapper) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        HomewavRouter homewavRouter = this.router;
        if (homewavRouter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        homewavRouter.newRootScreen(new Screens.TwilioCallScreen(callWrapper));
        PusherHolder pusherHolder2 = this.pusherHolder;
        if (pusherHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        pusherHolder2.stopListenEvents(this.pusherListener);
    }

    public void onLiveswitchCallAccepted(CallWrapper callWrapper, LiveswitchCallDataResponse liveswitchCallDataResponse) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
        HomewavRouter homewavRouter = this.router;
        if (homewavRouter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        homewavRouter.newRootScreen(new Screens.LiveswitchCallScreen(callWrapper, liveswitchCallDataResponse));
        PusherHolder pusherHolder2 = this.pusherHolder;
        if (pusherHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pusherHolder");
        }
        pusherHolder2.stopListenEvents(this.pusherListener);
    }

    public void onDialogButtonClicked() {
        this.isExitDisabled = true;
    }

    public void onCallDeclined() {
        HomewavRouter homewavRouter = this.router;
        if (homewavRouter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        homewavRouter.exit();
    }

    public void onCallCancelled() {
        if (!this.isExitDisabled) {
            this.isExitDisabled = true;
            ContextKt.showSnackbar((Activity) this, "Inmate disconnected");
            new Handler().postDelayed(new IncomingCallActivity$onCallCancelled$1(this), 2000);
        }
    }
}
