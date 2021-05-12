package org.jetbrains.anko.sdk27.coroutines;

import android.view.View;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "org/jetbrains/anko/sdk27/coroutines/__View_OnAttachStateChangeListener$onViewDetachedFromWindow$1", f = "ListenersWithCoroutines.kt", i = {}, l = {56, 58}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ListenersWithCoroutines.kt */
final class __View_OnAttachStateChangeListener$onViewDetachedFromWindow$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $handler;
    final /* synthetic */ View $v;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    __View_OnAttachStateChangeListener$onViewDetachedFromWindow$1(Function3 function3, View view, Continuation continuation) {
        super(2, continuation);
        this.$handler = function3;
        this.$v = view;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        __View_OnAttachStateChangeListener$onViewDetachedFromWindow$1 __view_onattachstatechangelistener_onviewdetachedfromwindow_1 = new __View_OnAttachStateChangeListener$onViewDetachedFromWindow$1(this.$handler, this.$v, continuation);
        __view_onattachstatechangelistener_onviewdetachedfromwindow_1.p$ = (CoroutineScope) obj;
        return __view_onattachstatechangelistener_onviewdetachedfromwindow_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((__View_OnAttachStateChangeListener$onViewDetachedFromWindow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            CoroutineScope coroutineScope = this.p$;
            Function3 function3 = this.$handler;
            View view = this.$v;
            this.label = 1;
            if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        return Unit.INSTANCE;
    }
}
