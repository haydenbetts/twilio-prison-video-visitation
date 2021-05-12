package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.model.data.CallStatus;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "test"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$onViewCreated$4<T> implements Predicate<ApiResponse<? extends CallStatusResponse>> {
    public static final IncomingCallDialogFragment$onViewCreated$4 INSTANCE = new IncomingCallDialogFragment$onViewCreated$4();

    IncomingCallDialogFragment$onViewCreated$4() {
    }

    public final boolean test(ApiResponse<CallStatusResponse> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        if (apiResponse.getBody() == null || apiResponse.getBody().getStatus() == CallStatus.MISSED) {
            return true;
        }
        return false;
    }
}
