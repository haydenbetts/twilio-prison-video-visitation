package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$takeWhile$1", f = "Channels.common.kt", i = {2, 3}, l = {864, 864, 866, 867, 865}, m = "invokeSuspend", n = {"e", "e"}, s = {"L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$takeWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_takeWhile;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$takeWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_takeWhile = receiveChannel;
        this.$predicate = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$takeWhile$1 channelsKt__Channels_commonKt$takeWhile$1 = new ChannelsKt__Channels_commonKt$takeWhile$1(this.$this_takeWhile, this.$predicate, continuation);
        channelsKt__Channels_commonKt$takeWhile$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$takeWhile$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$takeWhile$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0089 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0070
            if (r1 == r5) goto L_0x005c
            if (r1 == r4) goto L_0x0048
            if (r1 == r3) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0022
            r11 = r6
            goto L_0x007c
        L_0x0022:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x0027:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x002f:
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$1
            java.lang.Object r7 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r11 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0043
            r8 = r10
            r9 = r7
            r7 = r6
        L_0x0040:
            r6 = r9
            goto L_0x00b8
        L_0x0043:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x0048:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0057
            r7 = r6
            r6 = r10
            goto L_0x00a2
        L_0x0057:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x005c:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x006b
            r7 = r6
            r6 = r10
            goto L_0x008d
        L_0x006b:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x0070:
            boolean r1 = r11 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x00d8
            kotlinx.coroutines.channels.ProducerScope r11 = r10.p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r10.$this_takeWhile
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x007c:
            r6 = r10
        L_0x007d:
            r6.L$0 = r11
            r6.L$1 = r1
            r6.label = r5
            java.lang.Object r7 = r1.hasNext(r6)
            if (r7 != r0) goto L_0x008a
            return r0
        L_0x008a:
            r9 = r7
            r7 = r11
            r11 = r9
        L_0x008d:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00d5
            r6.L$0 = r7
            r6.L$1 = r1
            r6.label = r4
            java.lang.Object r11 = r1.next(r6)
            if (r11 != r0) goto L_0x00a2
            return r0
        L_0x00a2:
            kotlin.jvm.functions.Function2 r8 = r6.$predicate
            r6.L$0 = r7
            r6.L$1 = r11
            r6.L$2 = r1
            r6.label = r3
            java.lang.Object r8 = r8.invoke(r11, r6)
            if (r8 != r0) goto L_0x00b3
            return r0
        L_0x00b3:
            r9 = r7
            r7 = r11
            r11 = r8
            r8 = r6
            goto L_0x0040
        L_0x00b8:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L_0x00c3
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00c3:
            r8.L$0 = r6
            r8.L$1 = r7
            r8.L$2 = r1
            r8.label = r2
            java.lang.Object r11 = r6.send(r7, r8)
            if (r11 != r0) goto L_0x00d2
            return r0
        L_0x00d2:
            r11 = r6
            r6 = r8
            goto L_0x007d
        L_0x00d5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00d8:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$takeWhile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
