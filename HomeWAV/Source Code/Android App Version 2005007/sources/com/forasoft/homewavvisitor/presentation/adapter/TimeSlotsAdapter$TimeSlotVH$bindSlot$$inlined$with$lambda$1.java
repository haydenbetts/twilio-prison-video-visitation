package com.forasoft.homewavvisitor.presentation.adapter;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.presentation.adapter.TimeSlotsAdapter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroid/view/View;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/forasoft/homewavvisitor/presentation/adapter/TimeSlotsAdapter$TimeSlotVH$bindSlot$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: TimeSlotsAdapter.kt */
final class TimeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1 extends SuspendLambda implements Function3<CoroutineScope, View, Continuation<? super Unit>, Object> {
    final /* synthetic */ TimeSlot $timeSlot$inlined;
    int label;
    private CoroutineScope p$;
    private View p$0;
    final /* synthetic */ TimeSlotsAdapter.TimeSlotVH this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TimeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1(Continuation continuation, TimeSlotsAdapter.TimeSlotVH timeSlotVH, TimeSlot timeSlot) {
        super(3, continuation);
        this.this$0 = timeSlotVH;
        this.$timeSlot$inlined = timeSlot;
    }

    public final Continuation<Unit> create(CoroutineScope coroutineScope, View view, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        TimeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1 timeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1 = new TimeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1(continuation, this.this$0, this.$timeSlot$inlined);
        timeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1.p$ = coroutineScope;
        timeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1.p$0 = view;
        return timeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((TimeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1) create((CoroutineScope) obj, (View) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int access$getSelectedPosition$p = this.this$0.this$0.selectedPosition;
            this.this$0.this$0.selectedPosition = this.this$0.getAdapterPosition();
            if (!(access$getSelectedPosition$p == -1 || access$getSelectedPosition$p == this.this$0.getAdapterPosition())) {
                this.this$0.this$0.notifyItemChanged(access$getSelectedPosition$p);
            }
            if (access$getSelectedPosition$p != this.this$0.this$0.selectedPosition) {
                this.this$0.this$0.notifyItemChanged(this.this$0.this$0.selectedPosition);
            }
            this.this$0.this$0.onClickListener.invoke(Boxing.boxLong(this.$timeSlot$inlined.getTimestamp()));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
