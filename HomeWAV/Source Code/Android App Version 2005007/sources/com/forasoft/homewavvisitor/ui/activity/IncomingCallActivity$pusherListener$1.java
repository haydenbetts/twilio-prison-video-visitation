package com.forasoft.homewavvisitor.ui.activity;

import android.os.Handler;
import android.os.Looper;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/ui/activity/IncomingCallActivity$pusherListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherObserver;", "onEvent", "", "event", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: IncomingCallActivity.kt */
public final class IncomingCallActivity$pusherListener$1 implements PusherObserver {
    final /* synthetic */ IncomingCallActivity this$0;

    IncomingCallActivity$pusherListener$1(IncomingCallActivity incomingCallActivity) {
        this.this$0 = incomingCallActivity;
    }

    public void onEvent(PusherEvent pusherEvent) {
        Intrinsics.checkParameterIsNotNull(pusherEvent, "event");
        if (pusherEvent.getType() == PusherEvent.Type.call_disconnected) {
            new Handler(Looper.getMainLooper()).post(new IncomingCallActivity$pusherListener$1$onEvent$1(this));
        }
    }
}
