package com.forasoft.homewavvisitor.model;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/model/HeartbeatService;", "Landroid/app/Service;", "()V", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "getApi", "()Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "setApi", "(Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "disposable", "Lio/reactivex/disposables/Disposable;", "handleHeartbeatError", "", "throwable", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HeartbeatService.kt */
public final class HeartbeatService extends Service {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final BehaviorSubject<State> heartbeatState;
    @Inject
    public HomewavApi api;
    private Disposable disposable;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/model/HeartbeatService$Companion;", "", "()V", "heartbeatState", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/forasoft/homewavvisitor/model/data/State;", "getHeartbeatState", "()Lio/reactivex/subjects/BehaviorSubject;", "start", "", "context", "Landroid/content/Context;", "stop", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: HeartbeatService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            context.startService(new Intent(context, HeartbeatService.class));
        }

        public final void stop(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            context.stopService(new Intent(context, HeartbeatService.class));
        }

        public final BehaviorSubject<State> getHeartbeatState() {
            return HeartbeatService.heartbeatState;
        }
    }

    static {
        BehaviorSubject<State> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        heartbeatState = create;
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
        heartbeatState.onNext(new State(MapsKt.emptyMap(), CollectionsKt.emptyList(), MapsKt.emptyMap(), "0"));
        Toothpick.inject(this, Toothpick.openScope(DI.SERVER_SCOPE));
        Disposable subscribe = Observable.interval(0, 5, TimeUnit.SECONDS, Schedulers.computation()).subscribe(new HeartbeatService$onCreate$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, …beatService.start(this) }");
        this.disposable = subscribe;
        Timber.d("onCreate", new Object[0]);
    }

    public void onDestroy() {
        super.onDestroy();
        Disposable disposable2 = this.disposable;
        if (disposable2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disposable");
        }
        disposable2.dispose();
        Timber.d("onDestroy", new Object[0]);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Timber.d("onStartCommand " + i2, new Object[0]);
        HomewavApi homewavApi = this.api;
        if (homewavApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("api");
        }
        CommonKt.applyAsync(HomewavApi.DefaultImpls.heartbeat$default(homewavApi, (String) null, 1, (Object) null)).filter(HeartbeatService$onStartCommand$1.INSTANCE).map(HeartbeatService$onStartCommand$2.INSTANCE).subscribe(new HeartbeatService$sam$io_reactivex_functions_Consumer$0(new HeartbeatService$onStartCommand$3(heartbeatState)), new HeartbeatService$sam$io_reactivex_functions_Consumer$0(new HeartbeatService$onStartCommand$4(this)));
        return 2;
    }

    /* access modifiers changed from: private */
    public final void handleHeartbeatError(Throwable th) {
        Timber.d("Heartbeat error " + th.getMessage(), new Object[0]);
    }
}
