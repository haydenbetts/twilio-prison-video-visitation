package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/model/IncomingCallService$pusherListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherObserver;", "onEvent", "", "event", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: IncomingCallService.kt */
public final class IncomingCallService$pusherListener$1 implements PusherObserver {
    final /* synthetic */ IncomingCallService this$0;

    IncomingCallService$pusherListener$1(IncomingCallService incomingCallService) {
        this.this$0 = incomingCallService;
    }

    public void onEvent(PusherEvent pusherEvent) {
        Intrinsics.checkParameterIsNotNull(pusherEvent, "event");
        if (pusherEvent.getType() == PusherEvent.Type.call_disconnected) {
            this.this$0.getRingtone().stop();
            this.this$0.getPusherHolder().release(false);
            this.this$0.stopSelf();
        }
    }
}
