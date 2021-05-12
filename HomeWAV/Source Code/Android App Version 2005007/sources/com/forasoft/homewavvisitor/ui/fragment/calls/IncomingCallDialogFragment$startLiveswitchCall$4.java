package com.forasoft.homewavvisitor.ui.fragment.calls;

import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$startLiveswitchCall$4<T> implements Consumer<ApiResponse<? extends CallWrapper>> {
    final /* synthetic */ Ref.ObjectRef $liveswitchData;
    final /* synthetic */ Ref.ObjectRef $wrapper;
    final /* synthetic */ IncomingCallDialogFragment this$0;

    IncomingCallDialogFragment$startLiveswitchCall$4(IncomingCallDialogFragment incomingCallDialogFragment, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2) {
        this.this$0 = incomingCallDialogFragment;
        this.$wrapper = objectRef;
        this.$liveswitchData = objectRef2;
    }

    public final void accept(ApiResponse<CallWrapper> apiResponse) {
        FragmentActivity activity = this.this$0.getActivity();
        if (!(activity instanceof CallListener)) {
            activity = null;
        }
        CallListener callListener = (CallListener) activity;
        if (callListener != null) {
            CallWrapper callWrapper = (CallWrapper) this.$wrapper.element;
            if (callWrapper == null) {
                Intrinsics.throwNpe();
            }
            LiveswitchCallDataResponse liveswitchCallDataResponse = (LiveswitchCallDataResponse) this.$liveswitchData.element;
            if (liveswitchCallDataResponse == null) {
                Intrinsics.throwNpe();
            }
            callListener.onLiveswitchCallAccepted(callWrapper, liveswitchCallDataResponse);
        }
    }
}
