package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "K", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$distinctBy$1", f = "Channels.common.kt", i = {0, 1, 2, 2, 3, 3, 3}, l = {1455, 1455, 1458, 1459, 1461}, m = "invokeSuspend", n = {"keys", "keys", "keys", "e", "keys", "e", "k"}, s = {"L$1", "L$1", "L$1", "L$2", "L$1", "L$2", "L$4"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$distinctBy$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $selector;
    final /* synthetic */ ReceiveChannel $this_distinctBy;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$distinctBy$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$distinctBy$1 channelsKt__Channels_commonKt$distinctBy$1 = new ChannelsKt__Channels_commonKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__Channels_commonKt$distinctBy$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$distinctBy$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$distinctBy$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e3  */
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
            if (r1 == 0) goto L_0x0085
            if (r1 == r5) goto L_0x006d
            if (r1 == r4) goto L_0x0055
            if (r1 == r3) goto L_0x0037
            if (r1 != r2) goto L_0x002f
            java.lang.Object r1 = r13.L$4
            java.lang.Object r6 = r13.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r13.L$1
            java.util.HashSet r7 = (java.util.HashSet) r7
            java.lang.Object r8 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure
            if (r9 != 0) goto L_0x002a
            r9 = r8
            r8 = r13
            goto L_0x00f7
        L_0x002a:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x002f:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0037:
            java.lang.Object r1 = r13.L$3
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r13.L$2
            java.lang.Object r7 = r13.L$1
            java.util.HashSet r7 = (java.util.HashSet) r7
            java.lang.Object r8 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure
            if (r9 != 0) goto L_0x0050
            r9 = r8
            r8 = r13
            r11 = r6
            r6 = r1
            r1 = r11
            goto L_0x00dd
        L_0x0050:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x0055:
            java.lang.Object r1 = r13.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r13.L$1
            java.util.HashSet r6 = (java.util.HashSet) r6
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0068
            r8 = r7
            r7 = r13
            goto L_0x00c3
        L_0x0068:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x006d:
            java.lang.Object r1 = r13.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r13.L$1
            java.util.HashSet r6 = (java.util.HashSet) r6
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L_0x0080
            r8 = r7
            r7 = r13
            goto L_0x00ac
        L_0x0080:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        L_0x0085:
            boolean r1 = r14 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x0104
            kotlinx.coroutines.channels.ProducerScope r14 = r13.p$
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            kotlinx.coroutines.channels.ReceiveChannel r6 = r13.$this_distinctBy
            kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
            r7 = r13
        L_0x0097:
            r7.L$0 = r14
            r7.L$1 = r1
            r7.L$2 = r6
            r7.label = r5
            java.lang.Object r8 = r6.hasNext(r7)
            if (r8 != r0) goto L_0x00a6
            return r0
        L_0x00a6:
            r11 = r8
            r8 = r14
            r14 = r11
            r12 = r6
            r6 = r1
            r1 = r12
        L_0x00ac:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x0101
            r7.L$0 = r8
            r7.L$1 = r6
            r7.L$2 = r1
            r7.label = r4
            java.lang.Object r14 = r1.next(r7)
            if (r14 != r0) goto L_0x00c3
            return r0
        L_0x00c3:
            kotlin.jvm.functions.Function2 r9 = r7.$selector
            r7.L$0 = r8
            r7.L$1 = r6
            r7.L$2 = r14
            r7.L$3 = r1
            r7.label = r3
            java.lang.Object r9 = r9.invoke(r14, r7)
            if (r9 != r0) goto L_0x00d6
            return r0
        L_0x00d6:
            r11 = r1
            r1 = r14
            r14 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r11
        L_0x00dd:
            boolean r10 = r7.contains(r14)
            if (r10 != 0) goto L_0x00fd
            r8.L$0 = r9
            r8.L$1 = r7
            r8.L$2 = r1
            r8.L$3 = r6
            r8.L$4 = r14
            r8.label = r2
            java.lang.Object r1 = r9.send(r1, r8)
            if (r1 != r0) goto L_0x00f6
            return r0
        L_0x00f6:
            r1 = r14
        L_0x00f7:
            r14 = r7
            java.util.Collection r14 = (java.util.Collection) r14
            r14.add(r1)
        L_0x00fd:
            r1 = r7
            r7 = r8
            r14 = r9
            goto L_0x0097
        L_0x0101:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x0104:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r14 = r14.exception
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinctBy$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
