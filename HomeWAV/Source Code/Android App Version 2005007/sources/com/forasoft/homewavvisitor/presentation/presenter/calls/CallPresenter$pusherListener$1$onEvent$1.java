package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.model.data.CallStatus;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "V", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "test"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallPresenter.kt */
final class CallPresenter$pusherListener$1$onEvent$1<T> implements Predicate<ApiResponse<? extends CallStatusResponse>> {
    public static final CallPresenter$pusherListener$1$onEvent$1 INSTANCE = new CallPresenter$pusherListener$1$onEvent$1();

    CallPresenter$pusherListener$1$onEvent$1() {
    }

    public final boolean test(ApiResponse<CallStatusResponse> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        CallStatusResponse body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        return body.getStatus() == CallStatus.COMPLETE;
    }
}
