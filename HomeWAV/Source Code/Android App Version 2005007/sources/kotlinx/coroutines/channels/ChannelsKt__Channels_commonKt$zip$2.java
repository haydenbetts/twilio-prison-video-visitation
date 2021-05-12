package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$zip$2", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4}, l = {1881, 1881, 1884, 1885, 1886, 1887}, m = "invokeSuspend", n = {"otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "element1", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "element1", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "element1", "element2"}, s = {"L$1", "L$2", "L$4", "L$5", "L$6", "L$1", "L$2", "L$4", "L$5", "L$6", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $other;
    final /* synthetic */ ReceiveChannel $this_zip;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    private ProducerScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_zip = receiveChannel;
        this.$other = receiveChannel2;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$zip$2 channelsKt__Channels_commonKt$zip$2 = new ChannelsKt__Channels_commonKt$zip$2(this.$this_zip, this.$other, this.$transform, continuation);
        channelsKt__Channels_commonKt$zip$2.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$zip$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$zip$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x018f A[Catch:{ all -> 0x0090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0235 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r3 == 0) goto L_0x0143
            if (r3 == r8) goto L_0x010a
            if (r3 == r7) goto L_0x00d5
            if (r3 == r6) goto L_0x0095
            if (r3 == r5) goto L_0x0054
            if (r3 != r4) goto L_0x004c
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r9 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$5
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r12 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r12
            java.lang.Object r13 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r14 = (kotlinx.coroutines.channels.ChannelIterator) r14
            java.lang.Object r15 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r15 = (kotlinx.coroutines.channels.ProducerScope) r15
            boolean r4 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x013f }
            if (r4 != 0) goto L_0x0047
            r6 = r3
            r7 = r15
            r0 = 5
            r15 = 4
            r3 = r2
            r2 = r1
            goto L_0x0236
        L_0x0047:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x013f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x013f }
            throw r0     // Catch:{ all -> 0x013f }
        L_0x004c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0054:
            java.lang.Object r3 = r1.L$9
            java.lang.Object r4 = r1.L$8
            java.lang.Object r9 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$5
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r13 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r13
            java.lang.Object r14 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r15 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r15 = (kotlinx.coroutines.channels.ChannelIterator) r15
            java.lang.Object r5 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            boolean r6 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0090 }
            if (r6 != 0) goto L_0x008b
            r7 = r5
            r6 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r15
            r15 = 4
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            goto L_0x0210
        L_0x008b:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0090 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0090 }
            throw r0     // Catch:{ all -> 0x0090 }
        L_0x0090:
            r0 = move-exception
            r2 = r0
            r11 = r12
            goto L_0x0251
        L_0x0095:
            java.lang.Object r3 = r1.L$9
            java.lang.Object r4 = r1.L$8
            java.lang.Object r5 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r9 = r1.L$5
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r1.L$4
            r11 = r10
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r10 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r10
            java.lang.Object r12 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r14 = (kotlinx.coroutines.channels.ProducerScope) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x013f }
            if (r15 != 0) goto L_0x00d0
            r15 = r0
            r0 = r3
            r3 = r2
            r2 = r1
            r16 = r5
            r5 = r4
            r4 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r6
            r6 = r16
            goto L_0x01d7
        L_0x00d0:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x013f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x013f }
            throw r0     // Catch:{ all -> 0x013f }
        L_0x00d5:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r1.L$5
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r1.L$4
            r11 = r6
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r6 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r6 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r6
            java.lang.Object r9 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x013f }
            if (r13 != 0) goto L_0x0105
            r13 = r10
            r14 = r12
            r10 = r6
            r12 = r9
            r6 = r4
            r9 = r5
            r5 = r3
            r3 = r2
            r2 = r1
            goto L_0x01b0
        L_0x0105:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x013f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x013f }
            throw r0     // Catch:{ all -> 0x013f }
        L_0x010a:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r1.L$5
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r1.L$4
            r11 = r6
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r6 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r6 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r6
            java.lang.Object r9 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x013f }
            if (r13 != 0) goto L_0x013a
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            goto L_0x0187
        L_0x013a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x013f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x013f }
            throw r0     // Catch:{ all -> 0x013f }
        L_0x013f:
            r0 = move-exception
            r2 = r0
            goto L_0x0251
        L_0x0143:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0258
            kotlinx.coroutines.channels.ProducerScope r0 = r1.p$
            kotlinx.coroutines.channels.ReceiveChannel r3 = r1.$other
            kotlinx.coroutines.channels.ChannelIterator r3 = r3.iterator()
            kotlinx.coroutines.channels.ReceiveChannel r11 = r1.$this_zip
            r4 = 0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlinx.coroutines.channels.ChannelIterator r5 = r11.iterator()     // Catch:{ all -> 0x013f }
            r12 = r0
            r0 = r1
            r6 = r0
            r10 = r3
            r3 = r5
            r9 = r11
            r5 = r4
            r4 = r9
        L_0x0160:
            r0.L$0 = r12     // Catch:{ all -> 0x013f }
            r0.L$1 = r10     // Catch:{ all -> 0x013f }
            r0.L$2 = r9     // Catch:{ all -> 0x013f }
            r0.L$3 = r6     // Catch:{ all -> 0x013f }
            r0.L$4 = r11     // Catch:{ all -> 0x013f }
            r0.L$5 = r5     // Catch:{ all -> 0x013f }
            r0.L$6 = r4     // Catch:{ all -> 0x013f }
            r0.L$7 = r3     // Catch:{ all -> 0x013f }
            r0.label = r8     // Catch:{ all -> 0x013f }
            java.lang.Object r13 = r3.hasNext(r6)     // Catch:{ all -> 0x013f }
            if (r13 != r2) goto L_0x0179
            return r2
        L_0x0179:
            r16 = r2
            r2 = r0
            r0 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r16
        L_0x0187:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0090 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x0249
            r2.L$0 = r13     // Catch:{ all -> 0x0090 }
            r2.L$1 = r11     // Catch:{ all -> 0x0090 }
            r2.L$2 = r10     // Catch:{ all -> 0x0090 }
            r2.L$3 = r9     // Catch:{ all -> 0x0090 }
            r2.L$4 = r12     // Catch:{ all -> 0x0090 }
            r2.L$5 = r6     // Catch:{ all -> 0x0090 }
            r2.L$6 = r5     // Catch:{ all -> 0x0090 }
            r2.L$7 = r4     // Catch:{ all -> 0x0090 }
            r2.label = r7     // Catch:{ all -> 0x0090 }
            java.lang.Object r0 = r4.next(r9)     // Catch:{ all -> 0x0090 }
            if (r0 != r3) goto L_0x01a8
            return r3
        L_0x01a8:
            r14 = r13
            r13 = r11
            r11 = r12
            r12 = r10
            r10 = r9
            r9 = r6
            r6 = r5
            r5 = r4
        L_0x01b0:
            r2.L$0 = r14     // Catch:{ all -> 0x013f }
            r2.L$1 = r13     // Catch:{ all -> 0x013f }
            r2.L$2 = r12     // Catch:{ all -> 0x013f }
            r2.L$3 = r10     // Catch:{ all -> 0x013f }
            r2.L$4 = r11     // Catch:{ all -> 0x013f }
            r2.L$5 = r9     // Catch:{ all -> 0x013f }
            r2.L$6 = r6     // Catch:{ all -> 0x013f }
            r2.L$7 = r5     // Catch:{ all -> 0x013f }
            r2.L$8 = r0     // Catch:{ all -> 0x013f }
            r2.L$9 = r0     // Catch:{ all -> 0x013f }
            r4 = 3
            r2.label = r4     // Catch:{ all -> 0x013f }
            java.lang.Object r15 = r13.hasNext(r2)     // Catch:{ all -> 0x013f }
            if (r15 != r3) goto L_0x01ce
            return r3
        L_0x01ce:
            r4 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r6
            r6 = r5
            r5 = r0
        L_0x01d7:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x0090 }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x0090 }
            if (r15 != 0) goto L_0x01e7
            r7 = r4
            r4 = r9
            r5 = r10
            r9 = r13
            r10 = r14
            r0 = 5
            r15 = 4
            goto L_0x023f
        L_0x01e7:
            r2.L$0 = r4     // Catch:{ all -> 0x0090 }
            r2.L$1 = r14     // Catch:{ all -> 0x0090 }
            r2.L$2 = r13     // Catch:{ all -> 0x0090 }
            r2.L$3 = r11     // Catch:{ all -> 0x0090 }
            r2.L$4 = r12     // Catch:{ all -> 0x0090 }
            r2.L$5 = r10     // Catch:{ all -> 0x0090 }
            r2.L$6 = r9     // Catch:{ all -> 0x0090 }
            r2.L$7 = r6     // Catch:{ all -> 0x0090 }
            r2.L$8 = r5     // Catch:{ all -> 0x0090 }
            r2.L$9 = r0     // Catch:{ all -> 0x0090 }
            r15 = 4
            r2.label = r15     // Catch:{ all -> 0x0090 }
            java.lang.Object r7 = r14.next(r2)     // Catch:{ all -> 0x0090 }
            if (r7 != r3) goto L_0x0205
            return r3
        L_0x0205:
            r16 = r4
            r4 = r0
            r0 = r7
            r7 = r16
            r17 = r12
            r12 = r11
            r11 = r17
        L_0x0210:
            kotlin.jvm.functions.Function2 r8 = r2.$transform     // Catch:{ all -> 0x013f }
            java.lang.Object r8 = r8.invoke(r4, r0)     // Catch:{ all -> 0x013f }
            r2.L$0 = r7     // Catch:{ all -> 0x013f }
            r2.L$1 = r14     // Catch:{ all -> 0x013f }
            r2.L$2 = r13     // Catch:{ all -> 0x013f }
            r2.L$3 = r12     // Catch:{ all -> 0x013f }
            r2.L$4 = r11     // Catch:{ all -> 0x013f }
            r2.L$5 = r10     // Catch:{ all -> 0x013f }
            r2.L$6 = r9     // Catch:{ all -> 0x013f }
            r2.L$7 = r6     // Catch:{ all -> 0x013f }
            r2.L$8 = r5     // Catch:{ all -> 0x013f }
            r2.L$9 = r4     // Catch:{ all -> 0x013f }
            r2.L$10 = r0     // Catch:{ all -> 0x013f }
            r0 = 5
            r2.label = r0     // Catch:{ all -> 0x013f }
            java.lang.Object r4 = r7.send(r8, r2)     // Catch:{ all -> 0x013f }
            if (r4 != r3) goto L_0x0236
            return r3
        L_0x0236:
            r4 = r9
            r5 = r10
            r9 = r13
            r10 = r14
            r16 = r12
            r12 = r11
            r11 = r16
        L_0x023f:
            r0 = r2
            r2 = r3
            r3 = r6
            r6 = r11
            r11 = r12
            r8 = 1
            r12 = r7
            r7 = 2
            goto L_0x0160
        L_0x0249:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0090 }
            r12.cancel(r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0251:
            throw r2     // Catch:{ all -> 0x0252 }
        L_0x0252:
            r0 = move-exception
            r3 = r0
            r11.cancel(r2)
            throw r3
        L_0x0258:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
