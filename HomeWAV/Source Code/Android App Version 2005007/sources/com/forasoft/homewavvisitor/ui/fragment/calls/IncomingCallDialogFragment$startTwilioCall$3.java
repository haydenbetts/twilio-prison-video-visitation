package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$startTwilioCall$3<T> implements Consumer<ApiResponse<? extends CallWrapper>> {
    final /* synthetic */ Ref.ObjectRef $wrapper;

    IncomingCallDialogFragment$startTwilioCall$3(Ref.ObjectRef objectRef) {
        this.$wrapper = objectRef;
    }

    public final void accept(ApiResponse<CallWrapper> apiResponse) {
        Ref.ObjectRef objectRef = this.$wrapper;
        T body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        objectRef.element = (CallWrapper) body;
    }
}
