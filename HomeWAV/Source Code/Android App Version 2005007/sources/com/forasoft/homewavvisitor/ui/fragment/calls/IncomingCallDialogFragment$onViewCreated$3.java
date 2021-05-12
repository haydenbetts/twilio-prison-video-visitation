package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "it", "", "apply", "(Ljava/lang/Long;)Lio/reactivex/Observable;"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$onViewCreated$3<T, R> implements Function<T, ObservableSource<? extends R>> {
    final /* synthetic */ IncomingCallDialogFragment this$0;

    IncomingCallDialogFragment$onViewCreated$3(IncomingCallDialogFragment incomingCallDialogFragment) {
        this.this$0 = incomingCallDialogFragment;
    }

    public final Observable<ApiResponse<CallStatusResponse>> apply(Long l) {
        Intrinsics.checkParameterIsNotNull(l, "it");
        HomewavApi api = this.this$0.getApi();
        String access$getCallId$p = this.this$0.callId;
        if (access$getCallId$p == null) {
            Intrinsics.throwNpe();
        }
        return api.getCallStatus(access$getCallId$p);
    }
}
