package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/BroadcastKt$broadcast$1", f = "Broadcast.kt", i = {2}, l = {23, 23, 25, 24}, m = "invokeSuspend", n = {"e"}, s = {"L$1"})
/* compiled from: Broadcast.kt */
final class BroadcastKt$broadcast$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_broadcast;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BroadcastKt$broadcast$1(ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$this_broadcast = receiveChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        BroadcastKt$broadcast$1 broadcastKt$broadcast$1 = new BroadcastKt$broadcast$1(this.$this_broadcast, continuation);
        broadcastKt$broadcast$1.p$ = (ProducerScope) obj;
        return broadcastKt$broadcast$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BroadcastKt$broadcast$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0057
            if (r1 == r4) goto L_0x0041
            if (r1 == r3) goto L_0x002b
            if (r1 != r2) goto L_0x0023
            java.lang.Object r1 = r8.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L_0x001e
            goto L_0x0064
        L_0x001e:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r9 = r9.exception
            throw r9
        L_0x0023:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x002b:
            java.lang.Object r1 = r8.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L_0x003c
            r6 = r5
            r5 = r1
            r1 = r0
            r0 = r8
            goto L_0x008d
        L_0x003c:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r9 = r9.exception
            throw r9
        L_0x0041:
            java.lang.Object r1 = r8.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L_0x0052
            r6 = r5
            r5 = r1
            r1 = r0
            r0 = r8
            goto L_0x0078
        L_0x0052:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r9 = r9.exception
            throw r9
        L_0x0057:
            boolean r1 = r9 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x00a4
            kotlinx.coroutines.channels.ProducerScope r9 = r8.p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r8.$this_broadcast
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r5 = r9
        L_0x0064:
            r9 = r8
        L_0x0065:
            r9.L$0 = r5
            r9.L$1 = r1
            r9.label = r4
            java.lang.Object r6 = r1.hasNext(r9)
            if (r6 != r0) goto L_0x0072
            return r0
        L_0x0072:
            r7 = r0
            r0 = r9
            r9 = r6
            r6 = r5
            r5 = r1
            r1 = r7
        L_0x0078:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00a1
            r0.L$0 = r6
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r9 = r5.next(r0)
            if (r9 != r1) goto L_0x008d
            return r1
        L_0x008d:
            r0.L$0 = r6
            r0.L$1 = r9
            r0.L$2 = r5
            r0.label = r2
            java.lang.Object r9 = r6.send(r9, r0)
            if (r9 != r1) goto L_0x009c
            return r1
        L_0x009c:
            r9 = r0
            r0 = r1
            r1 = r5
            r5 = r6
            goto L_0x0065
        L_0x00a1:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00a4:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r9 = r9.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastKt$broadcast$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
