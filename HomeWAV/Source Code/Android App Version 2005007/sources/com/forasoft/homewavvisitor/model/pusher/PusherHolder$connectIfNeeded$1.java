package com.forasoft.homewavvisitor.model.pusher;

import com.forasoft.homewavvisitor.model.data.State;
import com.pusher.client.Pusher;
import com.pusher.client.connection.ConnectionState;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "state", "Lcom/forasoft/homewavvisitor/model/data/State;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: PusherHolder.kt */
final class PusherHolder$connectIfNeeded$1<T> implements Consumer<State> {
    final /* synthetic */ PusherHolder this$0;

    PusherHolder$connectIfNeeded$1(PusherHolder pusherHolder) {
        this.this$0 = pusherHolder;
    }

    public final void accept(State state) {
        if (state != null) {
            if (!this.this$0.isConnectionRequested) {
                Pusher access$getPusherClient$p = this.this$0.pusherClient;
                if (access$getPusherClient$p != null) {
                    access$getPusherClient$p.connect(this.this$0.connectionListener, new ConnectionState[0]);
                }
                this.this$0.isConnectionRequested = true;
                return;
            }
            this.this$0.restartHeartbeat(state);
        }
    }
}
