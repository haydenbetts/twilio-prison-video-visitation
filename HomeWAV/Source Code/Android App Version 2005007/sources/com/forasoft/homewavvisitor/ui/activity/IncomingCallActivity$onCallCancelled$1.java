package com.forasoft.homewavvisitor.ui.activity;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallActivity.kt */
final class IncomingCallActivity$onCallCancelled$1 implements Runnable {
    final /* synthetic */ IncomingCallActivity this$0;

    IncomingCallActivity$onCallCancelled$1(IncomingCallActivity incomingCallActivity) {
        this.this$0 = incomingCallActivity;
    }

    public final void run() {
        this.this$0.getRouter().exit();
    }
}
