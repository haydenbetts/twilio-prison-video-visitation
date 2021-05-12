package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$filter$1", f = "Channels.common.kt", i = {2, 3}, l = {634, 634, 636, 634, 635}, m = "invokeSuspend", n = {"e", "e"}, s = {"L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filter$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_filter;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$filter$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_filter = receiveChannel;
        this.$predicate = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$filter$1 channelsKt__Channels_commonKt$filter$1 = new ChannelsKt__Channels_commonKt$filter$1(this.$this_filter, this.$predicate, continuation);
        channelsKt__Channels_commonKt$filter$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$filter$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$filter$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0087 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bc  */
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
            if (r1 == 0) goto L_0x006e
            if (r1 == r5) goto L_0x005a
            if (r1 == r4) goto L_0x0046
            if (r1 == r3) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0022
            r11 = r6
            goto L_0x007a
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
            if (r8 != 0) goto L_0x0041
            r8 = r6
            r6 = r10
            goto L_0x00b4
        L_0x0041:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x0046:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0055
            r7 = r6
            r6 = r10
            goto L_0x00a0
        L_0x0055:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x005a:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L_0x0069
            r7 = r6
            r6 = r10
            goto L_0x008b
        L_0x0069:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        L_0x006e:
            boolean r1 = r11 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x00d0
            kotlinx.coroutines.channels.ProducerScope r11 = r10.p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r10.$this_filter
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x007a:
            r6 = r10
        L_0x007b:
            r6.L$0 = r11
            r6.L$1 = r1
            r6.label = r5
            java.lang.Object r7 = r1.hasNext(r6)
            if (r7 != r0) goto L_0x0088
            return r0
        L_0x0088:
            r9 = r7
            r7 = r11
            r11 = r9
        L_0x008b:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00cd
            r6.L$0 = r7
            r6.L$1 = r1
            r6.label = r4
            java.lang.Object r11 = r1.next(r6)
            if (r11 != r0) goto L_0x00a0
            return r0
        L_0x00a0:
            kotlin.jvm.functions.Function2 r8 = r6.$predicate
            r6.L$0 = r7
            r6.L$1 = r11
            r6.L$2 = r1
            r6.label = r3
            java.lang.Object r8 = r8.invoke(r11, r6)
            if (r8 != r0) goto L_0x00b1
            return r0
        L_0x00b1:
            r9 = r8
            r8 = r11
            r11 = r9
        L_0x00b4:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00cb
            r6.L$0 = r7
            r6.L$1 = r8
            r6.L$2 = r1
            r6.label = r2
            java.lang.Object r11 = r7.send(r8, r6)
            if (r11 != r0) goto L_0x00cb
            return r0
        L_0x00cb:
            r11 = r7
            goto L_0x007b
        L_0x00cd:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00d0:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r11 = r11.exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filter$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
