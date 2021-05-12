package com.forasoft.homewavvisitor.presentation.presenter.calls;

import android.content.Context;
import android.os.Handler;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.presentation.view.calls.TwilioCallView;
import com.forasoft.homewavvisitor.ui.fragment.calls.SimpleRemoteParticipantListener;
import com.forasoft.homewavvisitor.ui.fragment.calls.SimpleRoomListener;
import com.twilio.video.CameraCapturer;
import com.twilio.video.ConnectOptions;
import com.twilio.video.LocalAudioTrack;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.NetworkQualityLevel;
import com.twilio.video.RemoteAudioTrack;
import com.twilio.video.RemoteAudioTrackPublication;
import com.twilio.video.RemoteDataTrack;
import com.twilio.video.RemoteDataTrackPublication;
import com.twilio.video.RemoteParticipant;
import com.twilio.video.RemoteVideoTrack;
import com.twilio.video.RemoteVideoTrackPublication;
import com.twilio.video.Room;
import com.twilio.video.TwilioException;
import com.twilio.video.Video;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B?\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0002J\u0014\u0010&\u001a\u00020\u001f2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010%H\u0002J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020%H\u0002J\u001e\u0010*\u001a\u00020\u001f2\b\b\u0002\u0010+\u001a\u00020%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010%H\u0007J\u0010\u0010,\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010-\u001a\u00020\u001fH\u0016J\u001a\u0010.\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0006\u00101\u001a\u00020\u001fJ\u0018\u00102\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0016J \u00103\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J\u0006\u00108\u001a\u00020\u001fJ\b\u00109\u001a\u00020\u001fH\u0002J\b\u0010:\u001a\u00020\u001fH\u0016R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/calls/TwilioCallPresenter;", "Lcom/forasoft/homewavvisitor/presentation/presenter/calls/CallPresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/TwilioCallView;", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/SimpleRoomListener;", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/SimpleRemoteParticipantListener;", "context", "Landroid/content/Context;", "router", "Lru/terrakok/cicerone/Router;", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "(Landroid/content/Context;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;Lcom/forasoft/homewavvisitor/dao/CallDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/model/Analytics;Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;)V", "duration", "", "isDisconnectRequested", "", "localAudioTrack", "Lcom/twilio/video/LocalAudioTrack;", "localVideoTrack", "Lcom/twilio/video/LocalVideoTrack;", "room", "Lcom/twilio/video/Room;", "addRemoteParticipant", "", "remoteParticipant", "Lcom/twilio/video/RemoteParticipant;", "checkRemainingTime", "connectToRoom", "accessToken", "", "exit", "exitMessage", "exitWithMessage", "message", "onBackPressed", "cause", "onConnected", "onDestroy", "onDisconnected", "twilioException", "Lcom/twilio/video/TwilioException;", "onEndCallClicked", "onParticipantConnected", "onVideoTrackSubscribed", "remoteVideoTrackPublication", "Lcom/twilio/video/RemoteVideoTrackPublication;", "remoteVideoTrack", "Lcom/twilio/video/RemoteVideoTrack;", "startCall", "startCheckIn", "stopCall", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: TwilioCallPresenter.kt */
public final class TwilioCallPresenter extends CallPresenter<TwilioCallView> implements SimpleRoomListener, SimpleRemoteParticipantListener {
    private final Analytics analytics;
    /* access modifiers changed from: private */
    public final HomewavApi api;
    /* access modifiers changed from: private */
    public final CallDao callDao;
    private final Context context;
    /* access modifiers changed from: private */
    public long duration;
    /* access modifiers changed from: private */
    public boolean isDisconnectRequested;
    private LocalAudioTrack localAudioTrack;
    private LocalVideoTrack localVideoTrack;
    private Room room;
    /* access modifiers changed from: private */
    public final Router router;
    /* access modifiers changed from: private */
    public final CallWrapper wrapper;

    public /* synthetic */ void onDominantSpeakerChanged(Room room2, RemoteParticipant remoteParticipant) {
        Room.Listener.CC.$default$onDominantSpeakerChanged(this, room2, remoteParticipant);
    }

    public /* synthetic */ void onNetworkQualityLevelChanged(RemoteParticipant remoteParticipant, NetworkQualityLevel networkQualityLevel) {
        RemoteParticipant.Listener.CC.$default$onNetworkQualityLevelChanged(this, remoteParticipant, networkQualityLevel);
    }

    public void onAudioTrackDisabled(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackDisabled(this, remoteParticipant, remoteAudioTrackPublication);
    }

    public void onAudioTrackEnabled(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackEnabled(this, remoteParticipant, remoteAudioTrackPublication);
    }

    public void onAudioTrackPublished(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackPublished(this, remoteParticipant, remoteAudioTrackPublication);
    }

    public void onAudioTrackSubscribed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrack, "remoteAudioTrack");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackSubscribed(this, remoteParticipant, remoteAudioTrackPublication, remoteAudioTrack);
    }

    public void onAudioTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, TwilioException twilioException) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackSubscriptionFailed(this, remoteParticipant, remoteAudioTrackPublication, twilioException);
    }

    public void onAudioTrackUnpublished(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackUnpublished(this, remoteParticipant, remoteAudioTrackPublication);
    }

    public void onAudioTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        Intrinsics.checkParameterIsNotNull(remoteAudioTrack, "remoteAudioTrack");
        SimpleRemoteParticipantListener.DefaultImpls.onAudioTrackUnsubscribed(this, remoteParticipant, remoteAudioTrackPublication, remoteAudioTrack);
    }

    public void onConnectFailure(Room room2, TwilioException twilioException) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        SimpleRoomListener.DefaultImpls.onConnectFailure(this, room2, twilioException);
    }

    public void onDataTrackPublished(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onDataTrackPublished(this, remoteParticipant, remoteDataTrackPublication);
    }

    public void onDataTrackSubscribed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        Intrinsics.checkParameterIsNotNull(remoteDataTrack, "remoteDataTrack");
        SimpleRemoteParticipantListener.DefaultImpls.onDataTrackSubscribed(this, remoteParticipant, remoteDataTrackPublication, remoteDataTrack);
    }

    public void onDataTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, TwilioException twilioException) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        SimpleRemoteParticipantListener.DefaultImpls.onDataTrackSubscriptionFailed(this, remoteParticipant, remoteDataTrackPublication, twilioException);
    }

    public void onDataTrackUnpublished(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onDataTrackUnpublished(this, remoteParticipant, remoteDataTrackPublication);
    }

    public void onDataTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        Intrinsics.checkParameterIsNotNull(remoteDataTrack, "remoteDataTrack");
        SimpleRemoteParticipantListener.DefaultImpls.onDataTrackUnsubscribed(this, remoteParticipant, remoteDataTrackPublication, remoteDataTrack);
    }

    public void onParticipantDisconnected(Room room2, RemoteParticipant remoteParticipant) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        SimpleRoomListener.DefaultImpls.onParticipantDisconnected(this, room2, remoteParticipant);
    }

    public void onReconnected(Room room2) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        SimpleRoomListener.DefaultImpls.onReconnected(this, room2);
    }

    public void onReconnecting(Room room2, TwilioException twilioException) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        SimpleRoomListener.DefaultImpls.onReconnecting(this, room2, twilioException);
    }

    public void onRecordingStarted(Room room2) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        SimpleRoomListener.DefaultImpls.onRecordingStarted(this, room2);
    }

    public void onRecordingStopped(Room room2) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        SimpleRoomListener.DefaultImpls.onRecordingStopped(this, room2);
    }

    public void onVideoTrackDisabled(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onVideoTrackDisabled(this, remoteParticipant, remoteVideoTrackPublication);
    }

    public void onVideoTrackEnabled(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onVideoTrackEnabled(this, remoteParticipant, remoteVideoTrackPublication);
    }

    public void onVideoTrackPublished(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onVideoTrackPublished(this, remoteParticipant, remoteVideoTrackPublication);
    }

    public void onVideoTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, TwilioException twilioException) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        SimpleRemoteParticipantListener.DefaultImpls.onVideoTrackSubscriptionFailed(this, remoteParticipant, remoteVideoTrackPublication, twilioException);
    }

    public void onVideoTrackUnpublished(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        SimpleRemoteParticipantListener.DefaultImpls.onVideoTrackUnpublished(this, remoteParticipant, remoteVideoTrackPublication);
    }

    public void onVideoTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrack, "remoteVideoTrack");
        SimpleRemoteParticipantListener.DefaultImpls.onVideoTrackUnsubscribed(this, remoteParticipant, remoteVideoTrackPublication, remoteVideoTrack);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Inject
    public TwilioCallPresenter(Context context2, Router router2, CallWrapper callWrapper, CallDao callDao2, HomewavApi homewavApi, Analytics analytics2, PusherHolder pusherHolder) {
        super(callWrapper, homewavApi, pusherHolder);
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        Intrinsics.checkParameterIsNotNull(pusherHolder, "pusherHolder");
        this.context = context2;
        this.router = router2;
        this.wrapper = callWrapper;
        this.callDao = callDao2;
        this.api = homewavApi;
        this.analytics = analytics2;
    }

    public final void startCall() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(HomewavApi.DefaultImpls.getTwilioToken$default(this.api, this.wrapper.getCall().getPubid(), (String) null, 2, (Object) null)).subscribe(new TwilioCallPresenter$startCall$1(this), new TwilioCallPresenter$startCall$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getTwilioToken(wrapp…oken\")\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void connectToRoom(String str) {
        CameraCapturer cameraCapturer = new CameraCapturer(this.context, CameraCapturer.CameraSource.FRONT_CAMERA);
        this.localAudioTrack = LocalAudioTrack.create(this.context, true);
        this.localVideoTrack = LocalVideoTrack.create(this.context, true, cameraCapturer);
        ((TwilioCallView) getViewState()).displayVisitorVideo(this.localVideoTrack);
        ConnectOptions build = new ConnectOptions.Builder(str).roomName(this.wrapper.getCall().getPubid()).audioTracks(CollectionsKt.listOf(this.localAudioTrack)).videoTracks(CollectionsKt.listOf(this.localVideoTrack)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "ConnectOptions.Builder(a…\n                .build()");
        Video.connect(this.context, build, this);
    }

    /* access modifiers changed from: private */
    public final void checkRemainingTime() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new TwilioCallPresenter$checkRemainingTime$1(this)).map(new TwilioCallPresenter$checkRemainingTime$2(this)).takeWhile(new TwilioCallPresenter$checkRemainingTime$3(this)).doOnNext(new TwilioCallPresenter$checkRemainingTime$4(this)).subscribe(new TwilioCallPresenter$checkRemainingTime$5(this), TwilioCallPresenter$checkRemainingTime$6.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, …RemainingTime(it) }, { })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void startCheckIn() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.MINUTES, Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).takeWhile(new TwilioCallPresenter$startCheckIn$1(this)).doOnNext(new TwilioCallPresenter$startCheckIn$2(this)).subscribe(TwilioCallPresenter$startCheckIn$3.INSTANCE, TwilioCallPresenter$startCheckIn$4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, …       .subscribe({}, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public void stopCall() {
        onBackPressed$default(this, (String) null, (String) null, 3, (Object) null);
    }

    public void onConnected(Room room2) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        this.room = room2;
        List<RemoteParticipant> remoteParticipants = room2.getRemoteParticipants();
        Intrinsics.checkExpressionValueIsNotNull(remoteParticipants, "room.remoteParticipants");
        RemoteParticipant remoteParticipant = (RemoteParticipant) CollectionsKt.firstOrNull(remoteParticipants);
        if (remoteParticipant != null) {
            addRemoteParticipant(remoteParticipant);
        }
    }

    private final void addRemoteParticipant(RemoteParticipant remoteParticipant) {
        List<RemoteVideoTrackPublication> remoteVideoTracks = remoteParticipant.getRemoteVideoTracks();
        Intrinsics.checkExpressionValueIsNotNull(remoteVideoTracks, "remoteParticipant.remoteVideoTracks");
        RemoteVideoTrackPublication remoteVideoTrackPublication = (RemoteVideoTrackPublication) CollectionsKt.firstOrNull(remoteVideoTracks);
        if (remoteVideoTrackPublication != null && remoteVideoTrackPublication.isTrackSubscribed()) {
            ((TwilioCallView) getViewState()).displayInmateVideo(remoteVideoTrackPublication.getRemoteVideoTrack());
        }
        remoteParticipant.setListener(this);
    }

    public void onParticipantConnected(Room room2, RemoteParticipant remoteParticipant) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        List<RemoteParticipant> remoteParticipants = room2.getRemoteParticipants();
        Intrinsics.checkExpressionValueIsNotNull(remoteParticipants, "room.remoteParticipants");
        RemoteParticipant remoteParticipant2 = (RemoteParticipant) CollectionsKt.firstOrNull(remoteParticipants);
        if (remoteParticipant2 != null) {
            addRemoteParticipant(remoteParticipant2);
        }
    }

    public void onVideoTrackSubscribed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack) {
        Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        Intrinsics.checkParameterIsNotNull(remoteVideoTrack, "remoteVideoTrack");
        addRemoteParticipant(remoteParticipant);
    }

    public void onDisconnected(Room room2, TwilioException twilioException) {
        Intrinsics.checkParameterIsNotNull(room2, "room");
        if (!this.isDisconnectRequested) {
            if (!(twilioException == null || twilioException.getCode() == 53118)) {
                this.analytics.onDroppedCall();
            }
            this.room = room2;
            onBackPressed$default(this, "RoomDisconnected", (String) null, 2, (Object) null);
        }
    }

    public final void onEndCallClicked() {
        onBackPressed$default(this, (String) null, (String) null, 3, (Object) null);
    }

    public static /* synthetic */ void onBackPressed$default(TwilioCallPresenter twilioCallPresenter, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "HangupRequested";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        twilioCallPresenter.onBackPressed(str, str2);
    }

    public final void onBackPressed(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "cause");
        Room room2 = this.room;
        if (room2 != null) {
            room2.disconnect();
        }
        LocalAudioTrack localAudioTrack2 = this.localAudioTrack;
        if (localAudioTrack2 != null) {
            localAudioTrack2.release();
        }
        LocalVideoTrack localVideoTrack2 = this.localVideoTrack;
        if (localVideoTrack2 != null) {
            localVideoTrack2.release();
        }
        CommonKt.applyAsync(HomewavApi.DefaultImpls.endWebrtcCall$default(this.api, this.wrapper.getCall().getPubid(), str, (String) null, 4, (Object) null)).subscribe(new TwilioCallPresenter$onBackPressed$1(this, str2), new TwilioCallPresenter$onBackPressed$2(this, str2));
    }

    static /* synthetic */ void exit$default(TwilioCallPresenter twilioCallPresenter, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        twilioCallPresenter.exit(str);
    }

    /* access modifiers changed from: private */
    public final void exit(String str) {
        if (!this.isDisconnectRequested) {
            this.isDisconnectRequested = true;
            if (str == null) {
                this.router.exit();
            } else {
                exitWithMessage(str);
            }
        }
    }

    private final void exitWithMessage(String str) {
        ((TwilioCallView) getViewState()).showMessage(str);
        new Handler().postDelayed(new TwilioCallPresenter$exitWithMessage$1(this), 2000);
    }

    public void onDestroy() {
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new TwilioCallPresenter$onDestroy$1(this), 31, (Object) null);
        super.onDestroy();
    }
}
