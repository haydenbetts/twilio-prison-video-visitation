package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$dropWhile$1", f = "Channels.common.kt", i = {2, 3, 6}, l = {610, 610, 612, 613, 614, 610, 618, 617}, m = "invokeSuspend", n = {"e", "e", "e"}, s = {"L$1", "L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$dropWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_dropWhile;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$dropWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_dropWhile = receiveChannel;
        this.$predicate = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$dropWhile$1 channelsKt__Channels_commonKt$dropWhile$1 = new ChannelsKt__Channels_commonKt$dropWhile$1(this.$this_dropWhile, this.$predicate, continuation);
        channelsKt__Channels_commonKt$dropWhile$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$dropWhile$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$dropWhile$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ab, code lost:
        r2.L$0 = r8;
        r2.L$1 = r1;
        r2.label = 1;
        r3 = r1.hasNext(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b6, code lost:
        if (r3 != r0) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b9, code lost:
        r6 = r3;
        r3 = r8;
        r8 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c2, code lost:
        if (((java.lang.Boolean) r8).booleanValue() == false) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c4, code lost:
        r2.L$0 = r3;
        r2.L$1 = r1;
        r2.label = 2;
        r8 = r1.next(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00cf, code lost:
        if (r8 != r0) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d1, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d2, code lost:
        r4 = r2.$predicate;
        r2.L$0 = r3;
        r2.L$1 = r8;
        r2.L$2 = r1;
        r2.label = 3;
        r4 = r4.invoke(r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e1, code lost:
        if (r4 != r0) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e3, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e4, code lost:
        r6 = r4;
        r4 = r8;
        r8 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ed, code lost:
        if (((java.lang.Boolean) r8).booleanValue() != false) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ef, code lost:
        r2.L$0 = r3;
        r2.L$1 = r4;
        r2.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fa, code lost:
        if (r3.send(r4, r2) != r0) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fc, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fd, code lost:
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fe, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0100, code lost:
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0102, code lost:
        r1 = r2.$this_dropWhile.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0109, code lost:
        r2.L$0 = r3;
        r2.L$1 = r1;
        r2.label = 5;
        r8 = r1.hasNext(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0114, code lost:
        if (r8 != r0) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0116, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011d, code lost:
        if (((java.lang.Boolean) r8).booleanValue() == false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x011f, code lost:
        r2.L$0 = r3;
        r2.L$1 = r1;
        r2.label = 6;
        r8 = r1.next(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012a, code lost:
        if (r8 != r0) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x012c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x012d, code lost:
        r6 = r3;
        r3 = r2;
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0130, code lost:
        r3.L$0 = r2;
        r3.L$1 = r8;
        r3.L$2 = r1;
        r3.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013d, code lost:
        if (r2.send(r8, r3) != r0) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0140, code lost:
        r6 = r3;
        r3 = r2;
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0146, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L_0x009e;
                case 1: goto L_0x008a;
                case 2: goto L_0x0076;
                case 3: goto L_0x005f;
                case 4: goto L_0x004f;
                case 5: goto L_0x003a;
                case 6: goto L_0x0026;
                case 7: goto L_0x0011;
                default: goto L_0x0009;
            }
        L_0x0009:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0011:
            java.lang.Object r1 = r7.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            boolean r3 = r8 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0021
            r3 = r2
            r2 = r7
            goto L_0x0109
        L_0x0021:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x0026:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            boolean r3 = r8 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0035
            r3 = r7
            goto L_0x0130
        L_0x0035:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x003a:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            boolean r3 = r8 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x004a
            r3 = r2
            r2 = r7
            goto L_0x0117
        L_0x004a:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x004f:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x005a
            r2 = r7
            goto L_0x00fe
        L_0x005a:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x005f:
            java.lang.Object r1 = r7.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r2 = r7.L$1
            java.lang.Object r3 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r3 = (kotlinx.coroutines.channels.ProducerScope) r3
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L_0x0071
            r4 = r2
            r2 = r7
            goto L_0x00e7
        L_0x0071:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x0076:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            boolean r3 = r8 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0085
            r3 = r2
            r2 = r7
            goto L_0x00d2
        L_0x0085:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x008a:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            boolean r3 = r8 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0099
            r3 = r2
            r2 = r7
            goto L_0x00bc
        L_0x0099:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L_0x009e:
            boolean r1 = r8 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x0147
            kotlinx.coroutines.channels.ProducerScope r8 = r7.p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r7.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r2 = r7
        L_0x00ab:
            r2.L$0 = r8
            r2.L$1 = r1
            r3 = 1
            r2.label = r3
            java.lang.Object r3 = r1.hasNext(r2)
            if (r3 != r0) goto L_0x00b9
            return r0
        L_0x00b9:
            r6 = r3
            r3 = r8
            r8 = r6
        L_0x00bc:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0102
            r2.L$0 = r3
            r2.L$1 = r1
            r8 = 2
            r2.label = r8
            java.lang.Object r8 = r1.next(r2)
            if (r8 != r0) goto L_0x00d2
            return r0
        L_0x00d2:
            kotlin.jvm.functions.Function2 r4 = r2.$predicate
            r2.L$0 = r3
            r2.L$1 = r8
            r2.L$2 = r1
            r5 = 3
            r2.label = r5
            java.lang.Object r4 = r4.invoke(r8, r2)
            if (r4 != r0) goto L_0x00e4
            return r0
        L_0x00e4:
            r6 = r4
            r4 = r8
            r8 = r6
        L_0x00e7:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L_0x0100
            r2.L$0 = r3
            r2.L$1 = r4
            r8 = 4
            r2.label = r8
            java.lang.Object r8 = r3.send(r4, r2)
            if (r8 != r0) goto L_0x00fd
            return r0
        L_0x00fd:
            r1 = r3
        L_0x00fe:
            r3 = r1
            goto L_0x0102
        L_0x0100:
            r8 = r3
            goto L_0x00ab
        L_0x0102:
            kotlinx.coroutines.channels.ReceiveChannel r8 = r2.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r8 = r8.iterator()
            r1 = r8
        L_0x0109:
            r2.L$0 = r3
            r2.L$1 = r1
            r8 = 5
            r2.label = r8
            java.lang.Object r8 = r1.hasNext(r2)
            if (r8 != r0) goto L_0x0117
            return r0
        L_0x0117:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0144
            r2.L$0 = r3
            r2.L$1 = r1
            r8 = 6
            r2.label = r8
            java.lang.Object r8 = r1.next(r2)
            if (r8 != r0) goto L_0x012d
            return r0
        L_0x012d:
            r6 = r3
            r3 = r2
            r2 = r6
        L_0x0130:
            r3.L$0 = r2
            r3.L$1 = r8
            r3.L$2 = r1
            r4 = 7
            r3.label = r4
            java.lang.Object r8 = r2.send(r8, r3)
            if (r8 != r0) goto L_0x0140
            return r0
        L_0x0140:
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x0109
        L_0x0144:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0147:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$dropWhile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
