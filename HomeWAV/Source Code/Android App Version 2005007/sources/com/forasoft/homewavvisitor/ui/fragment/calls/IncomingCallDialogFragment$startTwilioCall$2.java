package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "it", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$startTwilioCall$2<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ String $callId;
    final /* synthetic */ IncomingCallDialogFragment this$0;

    IncomingCallDialogFragment$startTwilioCall$2(IncomingCallDialogFragment incomingCallDialogFragment, String str) {
        this.this$0 = incomingCallDialogFragment;
        this.$callId = str;
    }

    public final Single<ApiResponse<CallWrapper>> apply(ApiResponse<CallWrapper> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        return HomewavApi.DefaultImpls.acceptCall$default(this.this$0.getApi(), this.$callId, (String) null, 2, (Object) null);
    }
}
