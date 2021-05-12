package org.jetbrains.anko.sdk27.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "org/jetbrains/anko/sdk27/coroutines/__TextWatcher$onTextChanged$1", f = "ListenersWithCoroutines.kt", i = {}, l = {99, 101}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ListenersWithCoroutines.kt */
final class __TextWatcher$onTextChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $before;
    final /* synthetic */ int $count;
    final /* synthetic */ Function6 $handler;
    final /* synthetic */ CharSequence $s;
    final /* synthetic */ int $start;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    __TextWatcher$onTextChanged$1(Function6 function6, CharSequence charSequence, int i, int i2, int i3, Continuation continuation) {
        super(2, continuation);
        this.$handler = function6;
        this.$s = charSequence;
        this.$start = i;
        this.$before = i2;
        this.$count = i3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        __TextWatcher$onTextChanged$1 __textwatcher_ontextchanged_1 = new __TextWatcher$onTextChanged$1(this.$handler, this.$s, this.$start, this.$before, this.$count, continuation);
        __textwatcher_ontextchanged_1.p$ = (CoroutineScope) obj;
        return __textwatcher_ontextchanged_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((__TextWatcher$onTextChanged$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            Function6 function6 = this.$handler;
            CharSequence charSequence = this.$s;
            Integer boxInt = Boxing.boxInt(this.$start);
            Integer boxInt2 = Boxing.boxInt(this.$before);
            Integer boxInt3 = Boxing.boxInt(this.$count);
            this.label = 1;
            if (function6.invoke(coroutineScope, charSequence, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        return Unit.INSTANCE;
    }
}
