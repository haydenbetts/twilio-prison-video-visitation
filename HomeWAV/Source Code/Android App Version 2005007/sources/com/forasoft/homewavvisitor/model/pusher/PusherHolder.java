package com.forasoft.homewavvisitor.model.pusher;

import androidx.work.WorkRequest;
import com.forasoft.homewavvisitor.BuildConfig;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.util.HttpAuthorizer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0002\u0011\u0019\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0002J\u000e\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020 J\u0010\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020%2\b\b\u0002\u0010-\u001a\u00020\u001cJ\u0010\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020%2\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u00102\u001a\u00020%2\u0006\u0010(\u001a\u00020 J\u0018\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\n2\b\b\u0002\u00105\u001a\u00020\u001cJ\u0006\u00106\u001a\u00020%R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020 0\u001fj\b\u0012\u0004\u0012\u00020 `!X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "gson", "Lcom/google/gson/Gson;", "(Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/google/gson/Gson;)V", "PUSHER_EVENT", "", "channel", "Lcom/pusher/client/channel/PresenceChannel;", "channelName", "getChannelName", "()Ljava/lang/String;", "connectionListener", "com/forasoft/homewavvisitor/model/pusher/PusherHolder$connectionListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder$connectionListener$1;", "currentPusherState", "Lcom/pusher/client/connection/ConnectionState;", "currentUserId", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "eventListener", "com/forasoft/homewavvisitor/model/pusher/PusherHolder$eventListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder$eventListener$1;", "isAppVisible", "", "isConnectionRequested", "observersMap", "Ljava/util/HashSet;", "Lcom/forasoft/homewavvisitor/model/pusher/PusherObserver;", "Lkotlin/collections/HashSet;", "pusherClient", "Lcom/pusher/client/Pusher;", "connectIfNeeded", "", "initPusherClient", "listenEvents", "observer", "onEvent", "event", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "release", "isFromVisibleApp", "restartHeartbeat", "state", "Lcom/forasoft/homewavvisitor/model/data/State;", "sendEventToObservers", "stopListenEvents", "subscribe", "userId", "doHeartbeat", "unsubscribeFromChannel", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PusherHolder.kt */
public final class PusherHolder {
    private final String PUSHER_EVENT = "new_notification";
    private final AuthHolder authHolder;
    private PresenceChannel channel;
    /* access modifiers changed from: private */
    public final PusherHolder$connectionListener$1 connectionListener = new PusherHolder$connectionListener$1(this);
    /* access modifiers changed from: private */
    public ConnectionState currentPusherState = ConnectionState.DISCONNECTED;
    private String currentUserId;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final PusherHolder$eventListener$1 eventListener = new PusherHolder$eventListener$1(this);
    /* access modifiers changed from: private */
    public final Gson gson;
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    private boolean isAppVisible;
    /* access modifiers changed from: private */
    public boolean isConnectionRequested;
    private final HashSet<PusherObserver> observersMap = new HashSet<>();
    /* access modifiers changed from: private */
    public Pusher pusherClient;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PusherEvent.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PusherEvent.Type.new_call.ordinal()] = 1;
            iArr[PusherEvent.Type.new_message.ordinal()] = 2;
            iArr[PusherEvent.Type.changed_credits.ordinal()] = 3;
            iArr[PusherEvent.Type.call_disconnected.ordinal()] = 4;
            iArr[PusherEvent.Type.admin_message.ordinal()] = 5;
            iArr[PusherEvent.Type.admin_warning.ordinal()] = 6;
            iArr[PusherEvent.Type.chat_warning_on.ordinal()] = 7;
            iArr[PusherEvent.Type.chat_warning_off.ordinal()] = 8;
            iArr[PusherEvent.Type.visitor_login.ordinal()] = 9;
            iArr[PusherEvent.Type.switch_back_to_heartbeat.ordinal()] = 10;
        }
    }

    @Inject
    public PusherHolder(HeartbeatRepository heartbeatRepository2, AuthHolder authHolder2, Gson gson2) {
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(gson2, "gson");
        this.heartbeatRepository = heartbeatRepository2;
        this.authHolder = authHolder2;
        this.gson = gson2;
    }

    private final String getChannelName() {
        return "presence-visitor_" + this.currentUserId;
    }

    private final void connectIfNeeded() {
        if (!this.isConnectionRequested) {
            this.heartbeatRepository.doHeartbeat();
            CompositeDisposable compositeDisposable = this.disposables;
            Disposable subscribe = this.heartbeatRepository.getHeartbeatState().subscribe(new PusherHolder$connectIfNeeded$1(this));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear…eartbeat(state)\n        }");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
        }
    }

    /* access modifiers changed from: private */
    public final void restartHeartbeat(State state) {
        if (this.currentPusherState != ConnectionState.DISCONNECTED && this.currentPusherState != ConnectionState.CONNECTED) {
            return;
        }
        if (this.currentPusherState != ConnectionState.DISCONNECTED || !this.isConnectionRequested) {
            Map<String, String> status = state.getStatus();
            boolean z = false;
            if (!status.isEmpty()) {
                Iterator<Map.Entry<String, String>> it = status.entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual((Object) (String) it.next().getValue(), (Object) "green")) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z) {
                this.heartbeatRepository.startPusherHeartbeatWithInmates();
            } else {
                this.heartbeatRepository.startPusherHeartbeatWithoutInmates();
            }
        } else {
            this.heartbeatRepository.startShortHeartbeat();
        }
    }

    public static /* synthetic */ void subscribe$default(PusherHolder pusherHolder, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        pusherHolder.subscribe(str, z);
    }

    public final void subscribe(String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "userId");
        unsubscribeFromChannel();
        this.currentUserId = str;
        initPusherClient();
        if (z) {
            connectIfNeeded();
            this.isAppVisible = true;
        } else {
            Pusher pusher = this.pusherClient;
            if (pusher != null) {
                pusher.connect();
            }
        }
        Pusher pusher2 = this.pusherClient;
        PresenceChannel subscribePresence = pusher2 != null ? pusher2.subscribePresence(getChannelName(), this.eventListener, new String[0]) : null;
        this.channel = subscribePresence;
        if (subscribePresence != null) {
            subscribePresence.bind(this.PUSHER_EVENT, this.eventListener);
        }
    }

    private final void initPusherClient() {
        HttpAuthorizer httpAuthorizer = new HttpAuthorizer("https://app.homewav.com/api/app/visitor/pusher-auth/" + this.currentUserId);
        httpAuthorizer.setHeaders(MapsKt.mapOf(TuplesKt.to("Cookie", this.authHolder.getToken() + ';' + this.authHolder.getEsur() + ';' + this.authHolder.getAsps())));
        this.pusherClient = new Pusher(BuildConfig.PUSHER_KEY, new PusherOptions().setAuthorizer(httpAuthorizer).setActivityTimeout(WorkRequest.MIN_BACKOFF_MILLIS).setPongTimeout(WorkRequest.MIN_BACKOFF_MILLIS));
    }

    public final void unsubscribeFromChannel() {
        if (this.currentUserId != null) {
            Pusher pusher = this.pusherClient;
            if (pusher != null) {
                pusher.unsubscribe(getChannelName());
            }
            Pusher pusher2 = this.pusherClient;
            if (pusher2 != null) {
                pusher2.disconnect();
            }
            this.pusherClient = null;
            this.currentUserId = null;
        }
    }

    /* access modifiers changed from: private */
    public final void onEvent(PusherEvent pusherEvent) {
        switch (WhenMappings.$EnumSwitchMapping$0[pusherEvent.getType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                sendEventToObservers(pusherEvent);
                return;
            case 10:
                unsubscribeFromChannel();
                this.currentPusherState = ConnectionState.DISCONNECTED;
                this.heartbeatRepository.startShortHeartbeat();
                sendEventToObservers(pusherEvent);
                return;
            default:
                this.heartbeatRepository.doHeartbeat();
                return;
        }
    }

    private final void sendEventToObservers(PusherEvent pusherEvent) {
        for (PusherObserver onEvent : CollectionsKt.toList(this.observersMap)) {
            onEvent.onEvent(pusherEvent);
        }
    }

    public final void listenEvents(PusherObserver pusherObserver) {
        Intrinsics.checkParameterIsNotNull(pusherObserver, "observer");
        this.observersMap.add(pusherObserver);
    }

    public final void stopListenEvents(PusherObserver pusherObserver) {
        Intrinsics.checkParameterIsNotNull(pusherObserver, "observer");
        this.observersMap.remove(pusherObserver);
    }

    public static /* synthetic */ void release$default(PusherHolder pusherHolder, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        pusherHolder.release(z);
    }

    public final void release(boolean z) {
        if (z || !this.isAppVisible) {
            if (this.isAppVisible) {
                this.isAppVisible = false;
            }
            this.heartbeatRepository.release();
            this.disposables.clear();
            unsubscribeFromChannel();
            this.isConnectionRequested = false;
        }
    }
}
