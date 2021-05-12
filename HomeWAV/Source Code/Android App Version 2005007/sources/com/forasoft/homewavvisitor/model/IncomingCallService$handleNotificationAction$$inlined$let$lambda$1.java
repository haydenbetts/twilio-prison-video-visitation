package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "kotlin.jvm.PlatformType", "accept", "com/forasoft/homewavvisitor/model/IncomingCallService$handleNotificationAction$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallService.kt */
final class IncomingCallService$handleNotificationAction$$inlined$let$lambda$1<T> implements Consumer<ApiResponse> {
    final /* synthetic */ String $callId$inlined;
    final /* synthetic */ IncomingCallService this$0;

    IncomingCallService$handleNotificationAction$$inlined$let$lambda$1(IncomingCallService incomingCallService, String str) {
        this.this$0 = incomingCallService;
        this.$callId$inlined = str;
    }

    public final void accept(ApiResponse apiResponse) {
        this.this$0.stopSelf();
    }
}
