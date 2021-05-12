package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$flatMap$1", f = "Channels.common.kt", i = {2, 3}, l = {1100, 1100, 1102, 1101, 1104}, m = "invokeSuspend", n = {"e", "e"}, s = {"L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$flatMap$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_flatMap;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$flatMap$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_flatMap = receiveChannel;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$flatMap$1 channelsKt__Channels_commonKt$flatMap$1 = new ChannelsKt__Channels_commonKt$flatMap$1(this.$this_flatMap, this.$transform, continuation);
        channelsKt__Channels_commonKt$flatMap$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$flatMap$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$flatMap$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x006f
            if (r1 == r5) goto L_0x005b
            if (r1 == r4) goto L_0x0047
            if (r1 == r3) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            java.lang.Object r1 = r12.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r13 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0022
            r13 = r6
            goto L_0x007b
        L_0x0022:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x0027:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x002f:
            java.lang.Object r1 = r12.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$1
            java.lang.Object r7 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0042
            r8 = r6
            r6 = r7
            r7 = r12
            goto L_0x00b8
        L_0x0042:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x0047:
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r13 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0056
            r7 = r6
            r6 = r12
            goto L_0x00a1
        L_0x0056:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x005b:
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r13 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x006a
            r7 = r6
            r6 = r12
            goto L_0x008c
        L_0x006a:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x006f:
            boolean r1 = r13 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x00d2
            kotlinx.coroutines.channels.ProducerScope r13 = r12.p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r12.$this_flatMap
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x007b:
            r6 = r12
        L_0x007c:
            r6.L$0 = r13
            r6.L$1 = r1
            r6.label = r5
            java.lang.Object r7 = r1.hasNext(r6)
            if (r7 != r0) goto L_0x0089
            return r0
        L_0x0089:
            r10 = r7
            r7 = r13
            r13 = r10
        L_0x008c:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00cf
            r6.L$0 = r7
            r6.L$1 = r1
            r6.label = r4
            java.lang.Object r13 = r1.next(r6)
            if (r13 != r0) goto L_0x00a1
            return r0
        L_0x00a1:
            kotlin.jvm.functions.Function2 r8 = r6.$transform
            r6.L$0 = r7
            r6.L$1 = r13
            r6.L$2 = r1
            r6.label = r3
            java.lang.Object r8 = r8.invoke(r13, r6)
            if (r8 != r0) goto L_0x00b2
            return r0
        L_0x00b2:
            r10 = r8
            r8 = r13
            r13 = r10
            r11 = r7
            r7 = r6
            r6 = r11
        L_0x00b8:
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            r9 = r6
            kotlinx.coroutines.channels.SendChannel r9 = (kotlinx.coroutines.channels.SendChannel) r9
            r7.L$0 = r6
            r7.L$1 = r8
            r7.L$2 = r1
            r7.label = r2
            java.lang.Object r13 = kotlinx.coroutines.channels.ChannelsKt.toChannel(r13, r9, r7)
            if (r13 != r0) goto L_0x00cc
            return r0
        L_0x00cc:
            r13 = r6
            r6 = r7
            goto L_0x007c
        L_0x00cf:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x00d2:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$flatMap$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
