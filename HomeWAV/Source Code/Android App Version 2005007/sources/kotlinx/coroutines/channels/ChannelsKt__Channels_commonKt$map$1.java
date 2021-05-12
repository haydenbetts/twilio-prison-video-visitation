package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$map$1", f = "Channels.common.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, l = {1193, 1193, 1195, 1196, 1897}, m = "invokeSuspend", n = {"$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "it", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "it"}, s = {"L$1", "L$3", "L$4", "L$5", "L$1", "L$3", "L$4", "L$5", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_map;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
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
    ChannelsKt__Channels_commonKt$map$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_map = receiveChannel;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$map$1 channelsKt__Channels_commonKt$map$1 = new ChannelsKt__Channels_commonKt$map$1(this.$this_map, this.$transform, continuation);
        channelsKt__Channels_commonKt$map$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$map$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$map$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x011a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012c A[Catch:{ all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0191  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r3 == 0) goto L_0x00f0
            if (r3 == r7) goto L_0x00c1
            if (r3 == r6) goto L_0x008e
            if (r3 == r5) goto L_0x0050
            if (r3 != r4) goto L_0x0048
            java.lang.Object r3 = r1.L$6
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r11 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ec }
            if (r14 != 0) goto L_0x0043
            r4 = r3
            r0 = r8
            r3 = r9
            r8 = r11
            r15 = r12
            r11 = r13
            r5 = 4
            r14 = 3
            r9 = r1
            goto L_0x0197
        L_0x0043:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00ec }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00ec }
            throw r0     // Catch:{ all -> 0x00ec }
        L_0x0048:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0050:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ProducerScope r3 = (kotlinx.coroutines.channels.ProducerScope) r3
            java.lang.Object r8 = r1.L$8
            java.lang.Object r9 = r1.L$7
            java.lang.Object r10 = r1.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$4
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r13 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r1.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r14 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r14
            java.lang.Object r15 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r15 = (kotlinx.coroutines.channels.ReceiveChannel) r15
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            boolean r5 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0089 }
            if (r5 != 0) goto L_0x0084
            r6 = r4
            r5 = r8
            r4 = r10
            r8 = r11
            r11 = r1
            r10 = r9
            r9 = r12
            r12 = r13
            r13 = r14
            r14 = 3
            goto L_0x0175
        L_0x0084:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0089 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0089 }
            throw r0     // Catch:{ all -> 0x0089 }
        L_0x0089:
            r0 = move-exception
            r2 = r0
            r10 = r13
            goto L_0x01af
        L_0x008e:
            java.lang.Object r3 = r1.L$6
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r1.L$4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r8 = r1.L$3
            r10 = r8
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r8 = r1.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r8 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r8
            java.lang.Object r9 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r11 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            boolean r12 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ec }
            if (r12 != 0) goto L_0x00bc
            r12 = r10
            r10 = r1
            r16 = r4
            r4 = r3
            r3 = r11
            r11 = r9
        L_0x00b6:
            r9 = r8
            r8 = r5
            r5 = r16
            goto L_0x014c
        L_0x00bc:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00ec }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00ec }
            throw r0     // Catch:{ all -> 0x00ec }
        L_0x00c1:
            java.lang.Object r3 = r1.L$6
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r1.L$4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r8 = r1.L$3
            r10 = r8
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r8 = r1.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r8 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r8
            java.lang.Object r9 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r11 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            boolean r12 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ec }
            if (r12 != 0) goto L_0x00e7
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r1
            goto L_0x0124
        L_0x00e7:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00ec }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00ec }
            throw r0     // Catch:{ all -> 0x00ec }
        L_0x00ec:
            r0 = move-exception
            r2 = r0
            goto L_0x01af
        L_0x00f0:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x01b6
            kotlinx.coroutines.channels.ProducerScope r0 = r1.p$
            kotlinx.coroutines.channels.ReceiveChannel r10 = r1.$this_map
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r4 = r10.iterator()     // Catch:{ all -> 0x00ec }
            r11 = r0
            r8 = r1
            r9 = r8
            r0 = r10
            r5 = r0
        L_0x0104:
            r9.L$0 = r11     // Catch:{ all -> 0x01ac }
            r9.L$1 = r10     // Catch:{ all -> 0x01ac }
            r9.L$2 = r8     // Catch:{ all -> 0x01ac }
            r9.L$3 = r5     // Catch:{ all -> 0x01ac }
            r9.L$4 = r3     // Catch:{ all -> 0x01ac }
            r9.L$5 = r0     // Catch:{ all -> 0x01ac }
            r9.L$6 = r4     // Catch:{ all -> 0x01ac }
            r9.label = r7     // Catch:{ all -> 0x01ac }
            java.lang.Object r12 = r4.hasNext(r8)     // Catch:{ all -> 0x01ac }
            if (r12 != r2) goto L_0x011b
            return r2
        L_0x011b:
            r16 = r4
            r4 = r0
            r0 = r12
            r12 = r11
            r11 = r5
            r5 = r3
            r3 = r16
        L_0x0124:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01a8 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01a8 }
            if (r0 == 0) goto L_0x01a0
            r9.L$0 = r12     // Catch:{ all -> 0x01a8 }
            r9.L$1 = r10     // Catch:{ all -> 0x01a8 }
            r9.L$2 = r8     // Catch:{ all -> 0x01a8 }
            r9.L$3 = r11     // Catch:{ all -> 0x01a8 }
            r9.L$4 = r5     // Catch:{ all -> 0x01a8 }
            r9.L$5 = r4     // Catch:{ all -> 0x01a8 }
            r9.L$6 = r3     // Catch:{ all -> 0x01a8 }
            r9.label = r6     // Catch:{ all -> 0x01a8 }
            java.lang.Object r0 = r3.next(r8)     // Catch:{ all -> 0x01a8 }
            if (r0 != r2) goto L_0x0143
            return r2
        L_0x0143:
            r16 = r4
            r4 = r3
            r3 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            goto L_0x00b6
        L_0x014c:
            kotlin.jvm.functions.Function2 r13 = r10.$transform     // Catch:{ all -> 0x019c }
            r10.L$0 = r3     // Catch:{ all -> 0x019c }
            r10.L$1 = r11     // Catch:{ all -> 0x019c }
            r10.L$2 = r9     // Catch:{ all -> 0x019c }
            r10.L$3 = r12     // Catch:{ all -> 0x019c }
            r10.L$4 = r8     // Catch:{ all -> 0x019c }
            r10.L$5 = r5     // Catch:{ all -> 0x019c }
            r10.L$6 = r4     // Catch:{ all -> 0x019c }
            r10.L$7 = r0     // Catch:{ all -> 0x019c }
            r10.L$8 = r0     // Catch:{ all -> 0x019c }
            r10.L$9 = r3     // Catch:{ all -> 0x019c }
            r14 = 3
            r10.label = r14     // Catch:{ all -> 0x019c }
            java.lang.Object r13 = r13.invoke(r0, r10)     // Catch:{ all -> 0x019c }
            if (r13 != r2) goto L_0x016c
            return r2
        L_0x016c:
            r6 = r3
            r15 = r11
            r11 = r10
            r10 = r0
            r0 = r13
            r13 = r9
            r9 = r8
            r8 = r5
            r5 = r10
        L_0x0175:
            r11.L$0 = r6     // Catch:{ all -> 0x019c }
            r11.L$1 = r15     // Catch:{ all -> 0x019c }
            r11.L$2 = r13     // Catch:{ all -> 0x019c }
            r11.L$3 = r12     // Catch:{ all -> 0x019c }
            r11.L$4 = r9     // Catch:{ all -> 0x019c }
            r11.L$5 = r8     // Catch:{ all -> 0x019c }
            r11.L$6 = r4     // Catch:{ all -> 0x019c }
            r11.L$7 = r10     // Catch:{ all -> 0x019c }
            r11.L$8 = r5     // Catch:{ all -> 0x019c }
            r5 = 4
            r11.label = r5     // Catch:{ all -> 0x019c }
            java.lang.Object r0 = r3.send(r0, r11)     // Catch:{ all -> 0x019c }
            if (r0 != r2) goto L_0x0191
            return r2
        L_0x0191:
            r0 = r8
            r3 = r9
            r9 = r11
            r10 = r12
            r8 = r13
            r11 = r6
        L_0x0197:
            r5 = r10
            r10 = r15
            r6 = 2
            goto L_0x0104
        L_0x019c:
            r0 = move-exception
            r2 = r0
            r10 = r12
            goto L_0x01af
        L_0x01a0:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01a8 }
            r11.cancel(r5)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01a8:
            r0 = move-exception
            r2 = r0
            r10 = r11
            goto L_0x01af
        L_0x01ac:
            r0 = move-exception
            r2 = r0
            r10 = r5
        L_0x01af:
            throw r2     // Catch:{ all -> 0x01b0 }
        L_0x01b0:
            r0 = move-exception
            r3 = r0
            r10.cancel(r2)
            throw r3
        L_0x01b6:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
