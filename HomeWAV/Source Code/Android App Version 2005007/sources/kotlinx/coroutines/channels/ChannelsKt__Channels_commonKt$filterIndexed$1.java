package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$filterIndexed$1", f = "Channels.common.kt", i = {0, 1, 2, 2, 3, 3}, l = {654, 654, 657, 654, 656}, m = "invokeSuspend", n = {"index", "index", "index", "e", "index", "e"}, s = {"I$0", "I$0", "I$0", "L$1", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filterIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $predicate;
    final /* synthetic */ ReceiveChannel $this_filterIndexed;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$filterIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_filterIndexed = receiveChannel;
        this.$predicate = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$filterIndexed$1 channelsKt__Channels_commonKt$filterIndexed$1 = new ChannelsKt__Channels_commonKt$filterIndexed$1(this.$this_filterIndexed, this.$predicate, continuation);
        channelsKt__Channels_commonKt$filterIndexed$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$filterIndexed$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$filterIndexed$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x007e
            if (r1 == r5) goto L_0x0068
            if (r1 == r4) goto L_0x0052
            if (r1 == r3) goto L_0x0036
            if (r1 != r2) goto L_0x002e
            java.lang.Object r1 = r13.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r6 = r13.I$0
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0029
            r14 = r7
            r7 = r13
            r11 = r6
            r6 = r1
            r1 = r11
            goto L_0x008c
        L_0x0029:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x002e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0036:
            java.lang.Object r1 = r13.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r13.L$1
            int r7 = r13.I$0
            java.lang.Object r8 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure
            if (r9 != 0) goto L_0x004d
            r9 = r13
            r11 = r6
            r6 = r1
            r1 = r7
            r7 = r11
            goto L_0x00d7
        L_0x004d:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x0052:
            java.lang.Object r1 = r13.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r6 = r13.I$0
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0063
            r8 = r7
            r7 = r13
            goto L_0x00b8
        L_0x0063:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x0068:
            java.lang.Object r1 = r13.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r6 = r13.I$0
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0079
            r8 = r7
            r7 = r13
            goto L_0x00a1
        L_0x0079:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x007e:
            boolean r1 = r14 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x00f6
            kotlinx.coroutines.channels.ProducerScope r14 = r13.p$
            r1 = 0
            kotlinx.coroutines.channels.ReceiveChannel r6 = r13.$this_filterIndexed
            kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
            r7 = r13
        L_0x008c:
            r7.L$0 = r14
            r7.I$0 = r1
            r7.L$1 = r6
            r7.label = r5
            java.lang.Object r8 = r6.hasNext(r7)
            if (r8 != r0) goto L_0x009b
            return r0
        L_0x009b:
            r11 = r8
            r8 = r14
            r14 = r11
            r12 = r6
            r6 = r1
            r1 = r12
        L_0x00a1:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x00f3
            r7.L$0 = r8
            r7.I$0 = r6
            r7.L$1 = r1
            r7.label = r4
            java.lang.Object r14 = r1.next(r7)
            if (r14 != r0) goto L_0x00b8
            return r0
        L_0x00b8:
            kotlin.jvm.functions.Function3 r9 = r7.$predicate
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            int r6 = r6 + r5
            r7.L$0 = r8
            r7.I$0 = r6
            r7.L$1 = r14
            r7.L$2 = r1
            r7.label = r3
            java.lang.Object r9 = r9.invoke(r10, r14, r7)
            if (r9 != r0) goto L_0x00d0
            return r0
        L_0x00d0:
            r11 = r7
            r7 = r14
            r14 = r9
            r9 = r11
            r12 = r6
            r6 = r1
            r1 = r12
        L_0x00d7:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x00f0
            r9.L$0 = r8
            r9.I$0 = r1
            r9.L$1 = r7
            r9.L$2 = r6
            r9.label = r2
            java.lang.Object r14 = r8.send(r7, r9)
            if (r14 != r0) goto L_0x00f0
            return r0
        L_0x00f0:
            r14 = r8
            r7 = r9
            goto L_0x008c
        L_0x00f3:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x00f6:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
