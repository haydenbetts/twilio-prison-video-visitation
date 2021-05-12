package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006 \u0007*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "V", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallPresenter.kt */
final class CallPresenter$pusherListener$1$onEvent$2<T> implements Consumer<ApiResponse<? extends CallStatusResponse>> {
    final /* synthetic */ CallPresenter$pusherListener$1 this$0;

    CallPresenter$pusherListener$1$onEvent$2(CallPresenter$pusherListener$1 callPresenter$pusherListener$1) {
        this.this$0 = callPresenter$pusherListener$1;
    }

    public final void accept(ApiResponse<CallStatusResponse> apiResponse) {
        this.this$0.this$0.stopCall();
    }
}
