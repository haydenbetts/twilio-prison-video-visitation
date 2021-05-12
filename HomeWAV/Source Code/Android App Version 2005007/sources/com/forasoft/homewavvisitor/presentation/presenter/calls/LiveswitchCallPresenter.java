package com.forasoft.homewavvisitor.presentation.presenter.calls;

import android.content.Context;
import android.os.Handler;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.liveswitch.AecContext;
import com.forasoft.homewavvisitor.model.liveswitch.CameraLocalMedia;
import com.forasoft.homewavvisitor.model.liveswitch.RemoteMedia;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.presentation.view.calls.LiveswitchCallView;
import fm.liveswitch.AudioStream;
import fm.liveswitch.Channel;
import fm.liveswitch.Client;
import fm.liveswitch.ConnectionInfo;
import fm.liveswitch.ConnectionState;
import fm.liveswitch.ConnectionStats;
import fm.liveswitch.Future;
import fm.liveswitch.IAction1;
import fm.liveswitch.LocalMedia;
import fm.liveswitch.Promise;
import fm.liveswitch.SfuDownstreamConnection;
import fm.liveswitch.SfuUpstreamConnection;
import fm.liveswitch.SimulcastMode;
import fm.liveswitch.VideoStream;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BA\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0002J\b\u0010,\u001a\u00020*H\u0002J\u0014\u0010-\u001a\u00020*2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/H\u0002J\u0010\u00100\u001a\u00020*2\u0006\u00101\u001a\u00020/H\u0002J\b\u00102\u001a\u00020*H\u0002J\u001e\u00103\u001a\u00020*2\b\b\u0002\u00104\u001a\u00020/2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/H\u0007J\b\u00105\u001a\u00020*H\u0016J\u0006\u00106\u001a\u00020*J\u0010\u00107\u001a\u00020*2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<0;H\u0002J\b\u0010=\u001a\u00020*H\u0002J\u0006\u0010>\u001a\u00020*J\b\u0010?\u001a\u00020*H\u0002J\b\u0010@\u001a\u00020*H\u0002J\b\u0010A\u001a\u00020*H\u0002J\b\u0010B\u001a\u00020*H\u0002J\b\u0010C\u001a\u00020*H\u0016R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/calls/LiveswitchCallPresenter;", "Lcom/forasoft/homewavvisitor/presentation/presenter/calls/CallPresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/LiveswitchCallView;", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "context", "Landroid/content/Context;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "router", "Lru/terrakok/cicerone/Router;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "(Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;Landroid/content/Context;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;Lcom/forasoft/homewavvisitor/dao/CallDao;Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;)V", "aecContext", "Lcom/forasoft/homewavvisitor/model/liveswitch/AecContext;", "getAecContext", "()Lcom/forasoft/homewavvisitor/model/liveswitch/AecContext;", "aecContext$delegate", "Lkotlin/Lazy;", "downstreamConnection", "Lfm/liveswitch/SfuDownstreamConnection;", "duration", "", "isDisconnectRequested", "", "liveswitchChannel", "Lfm/liveswitch/Channel;", "liveswitchClient", "Lfm/liveswitch/Client;", "liveswitchMessageChannel", "localMedia", "Lcom/forasoft/homewavvisitor/model/liveswitch/CameraLocalMedia;", "remoteMedia", "Lcom/forasoft/homewavvisitor/model/liveswitch/RemoteMedia;", "upstreamConnection", "Lfm/liveswitch/SfuUpstreamConnection;", "checkConnection", "", "checkRemainingTime", "closeMediaSession", "exit", "exitMessage", "", "exitWithMessage", "message", "listenRemoteEvents", "onBackPressed", "cause", "onDestroy", "onEndCallClicked", "receiveRemoteMedia", "remoteInfo", "Lfm/liveswitch/ConnectionInfo;", "requestAndDisplayLocalStream", "Lfm/liveswitch/Promise;", "Lfm/liveswitch/LocalMedia;", "sendLocalMedia", "startCall", "startCallFlow", "startCheckIn", "startCheckingConnection", "startMediaSession", "stopCall", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: LiveswitchCallPresenter.kt */
public final class LiveswitchCallPresenter extends CallPresenter<LiveswitchCallView> {
    private final Lazy aecContext$delegate = LazyKt.lazy(LiveswitchCallPresenter$aecContext$2.INSTANCE);
    /* access modifiers changed from: private */
    public final HomewavApi api;
    /* access modifiers changed from: private */
    public final CallDao callDao;
    /* access modifiers changed from: private */
    public final LiveswitchCallDataResponse callData;
    private final Context context;
    private SfuDownstreamConnection downstreamConnection;
    /* access modifiers changed from: private */
    public long duration;
    private boolean isDisconnectRequested;
    /* access modifiers changed from: private */
    public Channel liveswitchChannel;
    private Client liveswitchClient;
    /* access modifiers changed from: private */
    public Channel liveswitchMessageChannel;
    /* access modifiers changed from: private */
    public CameraLocalMedia localMedia;
    /* access modifiers changed from: private */
    public RemoteMedia remoteMedia;
    /* access modifiers changed from: private */
    public final Router router;
    private SfuUpstreamConnection upstreamConnection;
    /* access modifiers changed from: private */
    public final CallWrapper wrapper;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionState.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ConnectionState.Closing.ordinal()] = 1;
            iArr[ConnectionState.Failing.ordinal()] = 2;
        }
    }

    private final AecContext getAecContext() {
        return (AecContext) this.aecContext$delegate.getValue();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Inject
    public LiveswitchCallPresenter(CallWrapper callWrapper, Context context2, HomewavApi homewavApi, Router router2, @Named("callData") LiveswitchCallDataResponse liveswitchCallDataResponse, CallDao callDao2, PusherHolder pusherHolder) {
        super(callWrapper, homewavApi, pusherHolder);
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        Intrinsics.checkParameterIsNotNull(pusherHolder, "pusherHolder");
        this.wrapper = callWrapper;
        this.context = context2;
        this.api = homewavApi;
        this.router = router2;
        this.callData = liveswitchCallDataResponse;
        this.callDao = callDao2;
    }

    public void stopCall() {
        onBackPressed$default(this, (String) null, (String) null, 3, (Object) null);
    }

    public final void startCall() {
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new LiveswitchCallPresenter$startCall$1(this), 31, (Object) null);
        startCheckIn();
        checkRemainingTime();
        startCallFlow();
        startCheckingConnection();
    }

    private final void startCheckIn() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.MINUTES, Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new LiveswitchCallPresenter$startCheckIn$1(this)).subscribe(LiveswitchCallPresenter$startCheckIn$2.INSTANCE, LiveswitchCallPresenter$startCheckIn$3.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, …       .subscribe({}, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final void checkRemainingTime() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new LiveswitchCallPresenter$checkRemainingTime$1(this)).map(new LiveswitchCallPresenter$checkRemainingTime$2(this)).doOnNext(new LiveswitchCallPresenter$checkRemainingTime$3(this)).subscribe(new LiveswitchCallPresenter$checkRemainingTime$4(this), LiveswitchCallPresenter$checkRemainingTime$5.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, …eRemainingTime(it) }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final void startCallFlow() {
        requestAndDisplayLocalStream().then(new LiveswitchCallPresenter$startCallFlow$1(this), (IAction1<Exception>) LiveswitchCallPresenter$startCallFlow$2.INSTANCE);
    }

    private final Promise<LocalMedia> requestAndDisplayLocalStream() {
        Promise<LocalMedia> promise = new Promise<>();
        this.localMedia = new CameraLocalMedia(this.context, false, false, getAecContext(), false, 16, (DefaultConstructorMarker) null);
        LiveswitchCallView liveswitchCallView = (LiveswitchCallView) getViewState();
        CameraLocalMedia cameraLocalMedia = this.localMedia;
        if (cameraLocalMedia == null) {
            Intrinsics.throwNpe();
        }
        liveswitchCallView.displayVisitorVideo(cameraLocalMedia);
        CameraLocalMedia cameraLocalMedia2 = this.localMedia;
        if (cameraLocalMedia2 == null) {
            Intrinsics.throwNpe();
        }
        cameraLocalMedia2.start().then(new LiveswitchCallPresenter$requestAndDisplayLocalStream$1(this, promise), (IAction1<Exception>) new LiveswitchCallPresenter$requestAndDisplayLocalStream$2(this));
        return promise;
    }

    /* access modifiers changed from: private */
    public final void startMediaSession() {
        Future<Channel[]> register;
        Client client = new Client(this.callData.getLiveswitchServerUrl(), this.callData.getApplicationId(), this.callData.getUserId(), this.callData.getDeviceId(), (String) null, new String[0]);
        this.liveswitchClient = client;
        if (client != null) {
            client.setUserAlias("visitor");
        }
        Client client2 = this.liveswitchClient;
        if (client2 != null) {
            client2.setDisableWebSockets(false);
        }
        Client client3 = this.liveswitchClient;
        if (client3 != null && (register = client3.register(this.callData.getAccessToken())) != null) {
            register.then((IAction1<Channel[]>) new LiveswitchCallPresenter$startMediaSession$1(this), (IAction1<Exception>) new LiveswitchCallPresenter$startMediaSession$2(this));
        }
    }

    /* access modifiers changed from: private */
    public final void sendLocalMedia() {
        CameraLocalMedia cameraLocalMedia;
        if (this.liveswitchChannel != null && (cameraLocalMedia = this.localMedia) != null) {
            AudioStream audioStream = new AudioStream((LocalMedia) cameraLocalMedia);
            VideoStream videoStream = new VideoStream((LocalMedia) this.localMedia);
            videoStream.setSimulcastMode(SimulcastMode.Disabled);
            Channel channel = this.liveswitchChannel;
            SfuUpstreamConnection createSfuUpstreamConnection = channel != null ? channel.createSfuUpstreamConnection(audioStream, videoStream) : null;
            this.upstreamConnection = createSfuUpstreamConnection;
            if (createSfuUpstreamConnection != null) {
                createSfuUpstreamConnection.open();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void listenRemoteEvents() {
        Channel channel = this.liveswitchChannel;
        if (channel != null) {
            if (channel != null) {
                channel.addOnRemoteUpstreamConnectionOpen(new LiveswitchCallPresenter$listenRemoteEvents$1(this));
            }
            Channel channel2 = this.liveswitchChannel;
            if (channel2 != null) {
                channel2.addOnRemoteUpstreamConnectionClose(new LiveswitchCallPresenter$listenRemoteEvents$2(this));
            }
            Channel channel3 = this.liveswitchMessageChannel;
            if (channel3 != null) {
                channel3.addOnMessage(new LiveswitchCallPresenter$listenRemoteEvents$3(this));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void receiveRemoteMedia(ConnectionInfo connectionInfo) {
        this.remoteMedia = new RemoteMedia(this.context, getAecContext());
        LiveswitchCallView liveswitchCallView = (LiveswitchCallView) getViewState();
        RemoteMedia remoteMedia2 = this.remoteMedia;
        if (remoteMedia2 == null) {
            Intrinsics.throwNpe();
        }
        liveswitchCallView.displayInmateVideo(remoteMedia2);
        AudioStream audioStream = new AudioStream((LocalMedia) this.localMedia, (fm.liveswitch.RemoteMedia) this.remoteMedia);
        VideoStream videoStream = new VideoStream((LocalMedia) this.localMedia, (fm.liveswitch.RemoteMedia) this.remoteMedia);
        videoStream.setSimulcastMode(SimulcastMode.Disabled);
        Channel channel = this.liveswitchChannel;
        SfuDownstreamConnection createSfuDownstreamConnection = channel != null ? channel.createSfuDownstreamConnection(connectionInfo, audioStream, videoStream) : null;
        this.downstreamConnection = createSfuDownstreamConnection;
        if (createSfuDownstreamConnection != null) {
            createSfuDownstreamConnection.open();
        }
        SfuDownstreamConnection sfuDownstreamConnection = this.downstreamConnection;
        if (sfuDownstreamConnection != null) {
            sfuDownstreamConnection.addOnStateChange(new LiveswitchCallPresenter$receiveRemoteMedia$1(this));
        }
    }

    private final void startCheckingConnection() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new LiveswitchCallPresenter$startCheckingConnection$1(this), LiveswitchCallPresenter$startCheckingConnection$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, … checkConnection() }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void checkConnection() {
        Future<ConnectionStats> stats;
        SfuDownstreamConnection sfuDownstreamConnection = this.downstreamConnection;
        if (sfuDownstreamConnection != null && (stats = sfuDownstreamConnection.getStats()) != null) {
            stats.then((IAction1<ConnectionStats>) new LiveswitchCallPresenter$checkConnection$1(this));
        }
    }

    public final void onEndCallClicked() {
        onBackPressed$default(this, (String) null, (String) null, 3, (Object) null);
    }

    public static /* synthetic */ void onBackPressed$default(LiveswitchCallPresenter liveswitchCallPresenter, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "HangupRequested";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        liveswitchCallPresenter.onBackPressed(str, str2);
    }

    public final void onBackPressed(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "cause");
        CommonKt.applyAsync(HomewavApi.DefaultImpls.endWebrtcCall$default(this.api, this.wrapper.getCall().getPubid(), str, (String) null, 4, (Object) null)).subscribe(new LiveswitchCallPresenter$onBackPressed$1(this, str2), new LiveswitchCallPresenter$onBackPressed$2(this, str2));
    }

    static /* synthetic */ void exit$default(LiveswitchCallPresenter liveswitchCallPresenter, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        liveswitchCallPresenter.exit(str);
    }

    /* access modifiers changed from: private */
    public final void exit(String str) {
        if (!this.isDisconnectRequested) {
            this.isDisconnectRequested = true;
            closeMediaSession();
            if (str == null) {
                this.router.exit();
            } else {
                exitWithMessage(str);
            }
        }
    }

    private final void exitWithMessage(String str) {
        ((LiveswitchCallView) getViewState()).showMessage(str);
        new Handler().postDelayed(new LiveswitchCallPresenter$exitWithMessage$1(this), 2000);
    }

    private final void closeMediaSession() {
        String str;
        Future stop;
        SfuUpstreamConnection sfuUpstreamConnection = this.upstreamConnection;
        if (sfuUpstreamConnection != null) {
            sfuUpstreamConnection.close();
        }
        SfuDownstreamConnection sfuDownstreamConnection = this.downstreamConnection;
        if (sfuDownstreamConnection != null) {
            sfuDownstreamConnection.close();
        }
        Client client = this.liveswitchClient;
        if (client != null) {
            client.unregister();
        }
        LiveswitchCallView liveswitchCallView = (LiveswitchCallView) getViewState();
        CameraLocalMedia cameraLocalMedia = this.localMedia;
        if (cameraLocalMedia == null || (str = cameraLocalMedia.getId()) == null) {
            str = "";
        }
        liveswitchCallView.removeVisitorVideo(str);
        CameraLocalMedia cameraLocalMedia2 = this.localMedia;
        if (cameraLocalMedia2 != null && (stop = cameraLocalMedia2.stop()) != null) {
            stop.then(new LiveswitchCallPresenter$closeMediaSession$1(this));
        }
    }

    public void onDestroy() {
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new LiveswitchCallPresenter$onDestroy$1(this), 31, (Object) null);
        super.onDestroy();
    }
}
