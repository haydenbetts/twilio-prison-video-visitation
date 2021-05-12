package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013J\u0006\u0010\u0017\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\u0013J\u0006\u0010\u0019\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "WITHOUT_PUSHER_HEARTBEAT_INTERVAL", "", "WITH_PUSHER_HEARTBEAT_INTERVAL", "WITH_PUSHER_HEARTBEAT_SHORT_INTERVAL", "currentHeartbeatInterval", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "heartbeatState", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/forasoft/homewavvisitor/model/data/State;", "getHeartbeatState", "()Lio/reactivex/subjects/BehaviorSubject;", "requestDisposables", "doHeartbeat", "", "release", "startHeartbeat", "startPusherHeartbeatWithInmates", "startPusherHeartbeatWithoutInmates", "startShortHeartbeat", "stopHeartbeat", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HeartbeatRepository.kt */
public final class HeartbeatRepository {
    private final long WITHOUT_PUSHER_HEARTBEAT_INTERVAL = 5;
    private final long WITH_PUSHER_HEARTBEAT_INTERVAL = 240;
    private final long WITH_PUSHER_HEARTBEAT_SHORT_INTERVAL = 60;
    private final HomewavApi api;
    private long currentHeartbeatInterval = 5;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final BehaviorSubject<State> heartbeatState;
    private final CompositeDisposable requestDisposables = new CompositeDisposable();

    @Inject
    public HeartbeatRepository(HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.api = homewavApi;
        BehaviorSubject<State> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.heartbeatState = create;
    }

    public final BehaviorSubject<State> getHeartbeatState() {
        return this.heartbeatState;
    }

    public final void startHeartbeat() {
        CompositeDisposable compositeDisposable = this.disposables;
        Disposable subscribe = Observable.interval(0, this.currentHeartbeatInterval, TimeUnit.SECONDS, Schedulers.computation()).subscribe(new HeartbeatRepository$startHeartbeat$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0L, …bscribe { doHeartbeat() }");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    public final void stopHeartbeat() {
        this.disposables.clear();
    }

    public final void doHeartbeat() {
        CompositeDisposable compositeDisposable = this.requestDisposables;
        Disposable subscribe = CommonKt.applyAsync(HomewavApi.DefaultImpls.heartbeat$default(this.api, (String) null, 1, (Object) null)).filter(HeartbeatRepository$doHeartbeat$1.INSTANCE).map(HeartbeatRepository$doHeartbeat$2.INSTANCE).subscribe(new HeartbeatRepository$sam$io_reactivex_functions_Consumer$0(new HeartbeatRepository$doHeartbeat$3(this.heartbeatState)), HeartbeatRepository$doHeartbeat$4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.heartbeat()\n        …rtbeatState::onNext, { })");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    public final void startShortHeartbeat() {
        long j = this.currentHeartbeatInterval;
        long j2 = this.WITHOUT_PUSHER_HEARTBEAT_INTERVAL;
        if (j != j2) {
            this.currentHeartbeatInterval = j2;
            stopHeartbeat();
            startHeartbeat();
        }
    }

    public final void startPusherHeartbeatWithoutInmates() {
        long j = this.currentHeartbeatInterval;
        long j2 = this.WITH_PUSHER_HEARTBEAT_INTERVAL;
        if (j != j2) {
            this.currentHeartbeatInterval = j2;
            stopHeartbeat();
            startHeartbeat();
        }
    }

    public final void startPusherHeartbeatWithInmates() {
        long j = this.currentHeartbeatInterval;
        long j2 = this.WITH_PUSHER_HEARTBEAT_SHORT_INTERVAL;
        if (j != j2) {
            this.currentHeartbeatInterval = j2;
            stopHeartbeat();
            startHeartbeat();
        }
    }

    public final void release() {
        this.heartbeatState.onNext(new State(MapsKt.emptyMap(), CollectionsKt.emptyList(), MapsKt.emptyMap(), "0"));
        stopHeartbeat();
        this.currentHeartbeatInterval = this.WITHOUT_PUSHER_HEARTBEAT_INTERVAL;
        this.requestDisposables.clear();
    }
}
