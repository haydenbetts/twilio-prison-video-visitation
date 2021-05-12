package com.forasoft.homewavvisitor.ui.fragment.calls;

import air.HomeWAV.R;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroid/view/View;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.forasoft.homewavvisitor.ui.fragment.calls.IncomingCallDialogFragment$onViewCreated$10", f = "IncomingCallDialogFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$onViewCreated$10 extends SuspendLambda implements Function3<CoroutineScope, View, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    private View p$0;
    final /* synthetic */ IncomingCallDialogFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    IncomingCallDialogFragment$onViewCreated$10(IncomingCallDialogFragment incomingCallDialogFragment, Continuation continuation) {
        super(3, continuation);
        this.this$0 = incomingCallDialogFragment;
    }

    public final Continuation<Unit> create(CoroutineScope coroutineScope, View view, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        IncomingCallDialogFragment$onViewCreated$10 incomingCallDialogFragment$onViewCreated$10 = new IncomingCallDialogFragment$onViewCreated$10(this.this$0, continuation);
        incomingCallDialogFragment$onViewCreated$10.p$ = coroutineScope;
        incomingCallDialogFragment$onViewCreated$10.p$0 = view;
        return incomingCallDialogFragment$onViewCreated$10;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((IncomingCallDialogFragment$onViewCreated$10) create((CoroutineScope) obj, (View) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity activity = this.this$0.getActivity();
            if (!(activity instanceof CallListener)) {
                activity = null;
            }
            CallListener callListener = (CallListener) activity;
            if (callListener != null) {
                callListener.onDialogButtonClicked();
            }
            this.this$0.showCallStatus(R.string.call_status_disconnecting);
            IncomingCallDialogFragment incomingCallDialogFragment = this.this$0;
            String access$getCallId$p = incomingCallDialogFragment.callId;
            if (access$getCallId$p == null) {
                Intrinsics.throwNpe();
            }
            incomingCallDialogFragment.cancelCall(access$getCallId$p);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
