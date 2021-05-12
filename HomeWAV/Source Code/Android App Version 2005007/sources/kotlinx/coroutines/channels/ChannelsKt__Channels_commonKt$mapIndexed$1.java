package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$mapIndexed$1", f = "Channels.common.kt", i = {0, 1, 2, 2, 3, 3}, l = {1214, 1214, 1217, 1216, 1219}, m = "invokeSuspend", n = {"index", "index", "index", "e", "index", "e"}, s = {"I$0", "I$0", "I$0", "L$1", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$mapIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_mapIndexed;
    final /* synthetic */ Function3 $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$mapIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_mapIndexed = receiveChannel;
        this.$transform = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$mapIndexed$1 channelsKt__Channels_commonKt$mapIndexed$1 = new ChannelsKt__Channels_commonKt$mapIndexed$1(this.$this_mapIndexed, this.$transform, continuation);
        channelsKt__Channels_commonKt$mapIndexed$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$mapIndexed$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$mapIndexed$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f2  */
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
            if (r1 == 0) goto L_0x0086
            if (r1 == r5) goto L_0x006d
            if (r1 == r4) goto L_0x0054
            if (r1 == r3) goto L_0x0035
            if (r1 != r2) goto L_0x002d
            java.lang.Object r1 = r12.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r6 = r12.I$0
            java.lang.Object r7 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0028
            r13 = r12
            r11 = r6
            r6 = r1
            r1 = r11
            goto L_0x0095
        L_0x0028:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x002d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0035:
            java.lang.Object r1 = r12.L$3
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            java.lang.Object r6 = r12.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r12.L$1
            int r8 = r12.I$0
            java.lang.Object r9 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            boolean r10 = r13 instanceof kotlin.Result.Failure
            if (r10 != 0) goto L_0x004f
            r10 = r7
            r7 = r1
            r1 = r0
            r0 = r12
            goto L_0x00e1
        L_0x004f:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x0054:
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r6 = r12.I$0
            java.lang.Object r7 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0068
            r8 = r6
            r6 = r1
            r1 = r0
            r0 = r12
            goto L_0x00c4
        L_0x0068:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x006d:
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r6 = r12.I$0
            java.lang.Object r7 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0081
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r0
            r0 = r12
            goto L_0x00aa
        L_0x0081:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        L_0x0086:
            boolean r1 = r13 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x00fa
            kotlinx.coroutines.channels.ProducerScope r13 = r12.p$
            r1 = 0
            kotlinx.coroutines.channels.ReceiveChannel r6 = r12.$this_mapIndexed
            kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
            r7 = r13
            r13 = r12
        L_0x0095:
            r13.L$0 = r7
            r13.I$0 = r1
            r13.L$1 = r6
            r13.label = r5
            java.lang.Object r8 = r6.hasNext(r13)
            if (r8 != r0) goto L_0x00a4
            return r0
        L_0x00a4:
            r11 = r0
            r0 = r13
            r13 = r8
            r8 = r7
            r7 = r1
            r1 = r11
        L_0x00aa:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00f7
            r0.L$0 = r8
            r0.I$0 = r7
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r13 = r6.next(r0)
            if (r13 != r1) goto L_0x00c1
            return r1
        L_0x00c1:
            r11 = r8
            r8 = r7
            r7 = r11
        L_0x00c4:
            kotlin.jvm.functions.Function3 r9 = r0.$transform
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            int r8 = r8 + r5
            r0.L$0 = r7
            r0.I$0 = r8
            r0.L$1 = r13
            r0.L$2 = r6
            r0.L$3 = r7
            r0.label = r3
            java.lang.Object r9 = r9.invoke(r10, r13, r0)
            if (r9 != r1) goto L_0x00de
            return r1
        L_0x00de:
            r10 = r13
            r13 = r9
            r9 = r7
        L_0x00e1:
            r0.L$0 = r9
            r0.I$0 = r8
            r0.L$1 = r10
            r0.L$2 = r6
            r0.label = r2
            java.lang.Object r13 = r7.send(r13, r0)
            if (r13 != r1) goto L_0x00f2
            return r1
        L_0x00f2:
            r13 = r0
            r0 = r1
            r1 = r8
            r7 = r9
            goto L_0x0095
        L_0x00f7:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x00fa:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
