package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$onViewCreated$5<T> implements Consumer<ApiResponse<? extends CallStatusResponse>> {
    final /* synthetic */ IncomingCallDialogFragment this$0;

    IncomingCallDialogFragment$onViewCreated$5(IncomingCallDialogFragment incomingCallDialogFragment) {
        this.this$0 = incomingCallDialogFragment;
    }

    public final void accept(ApiResponse<CallStatusResponse> apiResponse) {
        FragmentActivity activity = this.this$0.getActivity();
        if (!(activity instanceof CallListener)) {
            activity = null;
        }
        CallListener callListener = (CallListener) activity;
        if (callListener != null) {
            callListener.onCallCancelled();
        }
        if (this.this$0.getActivity() instanceof MainActivity) {
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            ContextKt.showSnackbar((Activity) requireActivity, "Inmate disconnected");
            this.this$0.dismissAllowingStateLoss();
        }
    }
}
