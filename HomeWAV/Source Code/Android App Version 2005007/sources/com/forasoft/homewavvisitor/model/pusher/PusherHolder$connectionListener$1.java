package com.forasoft.homewavvisitor.model.pusher;

import com.forasoft.homewavvisitor.model.data.State;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J,\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u000e\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fH\u0016¨\u0006\r"}, d2 = {"com/forasoft/homewavvisitor/model/pusher/PusherHolder$connectionListener$1", "Lcom/pusher/client/connection/ConnectionEventListener;", "onConnectionStateChange", "", "stateChange", "Lcom/pusher/client/connection/ConnectionStateChange;", "onError", "p0", "", "p1", "p2", "Ljava/lang/Exception;", "Lkotlin/Exception;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PusherHolder.kt */
public final class PusherHolder$connectionListener$1 implements ConnectionEventListener {
    final /* synthetic */ PusherHolder this$0;

    PusherHolder$connectionListener$1(PusherHolder pusherHolder) {
        this.this$0 = pusherHolder;
    }

    public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
        if (connectionStateChange != null) {
            PusherHolder pusherHolder = this.this$0;
            ConnectionState currentState = connectionStateChange.getCurrentState();
            Intrinsics.checkExpressionValueIsNotNull(currentState, "stateChange.currentState");
            pusherHolder.currentPusherState = currentState;
        }
        PusherHolder pusherHolder2 = this.this$0;
        State value = pusherHolder2.heartbeatRepository.getHeartbeatState().getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        pusherHolder2.restartHeartbeat(value);
    }

    public void onError(String str, String str2, Exception exc) {
        this.this$0.currentPusherState = ConnectionState.DISCONNECTED;
        PusherHolder pusherHolder = this.this$0;
        State value = pusherHolder.heartbeatRepository.getHeartbeatState().getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        pusherHolder.restartHeartbeat(value);
    }
}
