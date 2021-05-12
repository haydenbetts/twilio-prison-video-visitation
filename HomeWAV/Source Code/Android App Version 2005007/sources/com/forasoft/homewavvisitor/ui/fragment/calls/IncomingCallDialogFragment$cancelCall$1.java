package com.forasoft.homewavvisitor.ui.fragment.calls;

import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$cancelCall$1<T> implements Consumer<ApiResponse> {
    final /* synthetic */ IncomingCallDialogFragment this$0;

    IncomingCallDialogFragment$cancelCall$1(IncomingCallDialogFragment incomingCallDialogFragment) {
        this.this$0 = incomingCallDialogFragment;
    }

    public final void accept(ApiResponse apiResponse) {
        FragmentActivity activity = this.this$0.getActivity();
        if (!(activity instanceof CallListener)) {
            activity = null;
        }
        CallListener callListener = (CallListener) activity;
        if (callListener != null) {
            callListener.onCallDeclined();
        }
        this.this$0.dismissAllowingStateLoss();
    }
}
