package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: TickerChannels.kt */
public final class TickerChannelsKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TickerMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
            iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
        }
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }

    public static final ReceiveChannel<Unit> ticker(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode) {
        long j3 = j2;
        CoroutineContext coroutineContext2 = coroutineContext;
        Intrinsics.checkParameterIsNotNull(coroutineContext2, "context");
        Intrinsics.checkParameterIsNotNull(tickerMode, "mode");
        boolean z = true;
        if (j >= 0) {
            if (j3 < 0) {
                z = false;
            }
            if (z) {
                return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext2), 0, new TickerChannelsKt$ticker$3(tickerMode, j, j2, (Continuation) null));
            }
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j2 + " ms").toString());
        }
        throw new IllegalArgumentException(("Expected non-negative delay, but has " + j + " ms").toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0175 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ java.lang.Object fixedPeriodTicker(long r21, long r23, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            r0 = r23
            r2 = r26
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1
            if (r3 == 0) goto L_0x0018
            r3 = r2
            kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1 r3 = (kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0018
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1 r3 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1
            r3.<init>(r2)
        L_0x001d:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r5 == 0) goto L_0x00a7
            if (r5 == r9) goto L_0x008d
            if (r5 == r8) goto L_0x0076
            if (r5 == r7) goto L_0x0058
            if (r5 != r6) goto L_0x0050
            long r0 = r3.J$5
            long r0 = r3.J$4
            long r0 = r3.J$3
            long r9 = r3.J$2
            java.lang.Object r5 = r3.L$0
            kotlinx.coroutines.channels.SendChannel r5 = (kotlinx.coroutines.channels.SendChannel) r5
            long r11 = r3.J$1
            long r13 = r3.J$0
            boolean r15 = r2 instanceof kotlin.Result.Failure
            if (r15 != 0) goto L_0x004b
            r2 = r4
            r4 = 4
            goto L_0x017a
        L_0x004b:
            kotlin.Result$Failure r2 = (kotlin.Result.Failure) r2
            java.lang.Throwable r0 = r2.exception
            throw r0
        L_0x0050:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0058:
            long r0 = r3.J$6
            long r0 = r3.J$5
            long r0 = r3.J$4
            long r0 = r3.J$3
            long r9 = r3.J$2
            java.lang.Object r5 = r3.L$0
            kotlinx.coroutines.channels.SendChannel r5 = (kotlinx.coroutines.channels.SendChannel) r5
            long r11 = r3.J$1
            long r13 = r3.J$0
            boolean r15 = r2 instanceof kotlin.Result.Failure
            if (r15 != 0) goto L_0x0071
            r8 = 3
            goto L_0x0144
        L_0x0071:
            kotlin.Result$Failure r2 = (kotlin.Result.Failure) r2
            java.lang.Throwable r0 = r2.exception
            throw r0
        L_0x0076:
            long r0 = r3.J$3
            long r9 = r3.J$2
            java.lang.Object r5 = r3.L$0
            kotlinx.coroutines.channels.SendChannel r5 = (kotlinx.coroutines.channels.SendChannel) r5
            long r11 = r3.J$1
            long r13 = r3.J$0
            boolean r15 = r2 instanceof kotlin.Result.Failure
            if (r15 != 0) goto L_0x0088
            goto L_0x00f0
        L_0x0088:
            kotlin.Result$Failure r2 = (kotlin.Result.Failure) r2
            java.lang.Throwable r0 = r2.exception
            throw r0
        L_0x008d:
            long r0 = r3.J$2
            java.lang.Object r5 = r3.L$0
            kotlinx.coroutines.channels.SendChannel r5 = (kotlinx.coroutines.channels.SendChannel) r5
            long r9 = r3.J$1
            long r11 = r3.J$0
            boolean r13 = r2 instanceof kotlin.Result.Failure
            if (r13 != 0) goto L_0x00a2
            r2 = r5
            r18 = r0
            r0 = r9
            r9 = r18
            goto L_0x00cf
        L_0x00a2:
            kotlin.Result$Failure r2 = (kotlin.Result.Failure) r2
            java.lang.Throwable r0 = r2.exception
            throw r0
        L_0x00a7:
            boolean r5 = r2 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L_0x018b
            kotlinx.coroutines.TimeSource r2 = kotlinx.coroutines.TimeSourceKt.getTimeSource()
            long r10 = r2.nanoTime()
            long r12 = kotlinx.coroutines.EventLoopKt.delayToNanos(r23)
            long r10 = r10 + r12
            r12 = r21
            r3.J$0 = r12
            r3.J$1 = r0
            r2 = r25
            r3.L$0 = r2
            r3.J$2 = r10
            r3.label = r9
            java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r0, r3)
            if (r5 != r4) goto L_0x00cd
            return r4
        L_0x00cd:
            r9 = r10
            r11 = r12
        L_0x00cf:
            long r13 = kotlinx.coroutines.EventLoopKt.delayToNanos(r11)
        L_0x00d3:
            long r9 = r9 + r13
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r3.J$0 = r11
            r3.J$1 = r0
            r3.L$0 = r2
            r3.J$2 = r9
            r3.J$3 = r13
            r3.label = r8
            java.lang.Object r5 = r2.send(r5, r3)
            if (r5 != r4) goto L_0x00e9
            return r4
        L_0x00e9:
            r5 = r2
            r18 = r0
            r0 = r13
            r13 = r11
            r11 = r18
        L_0x00f0:
            kotlinx.coroutines.TimeSource r2 = kotlinx.coroutines.TimeSourceKt.getTimeSource()
            long r6 = r2.nanoTime()
            r2 = r4
            r21 = r5
            long r4 = r9 - r6
            r22 = r9
            r8 = 0
            long r4 = kotlin.ranges.RangesKt.coerceAtLeast((long) r4, (long) r8)
            int r10 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x014e
            int r10 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x014e
            r9 = r22
            long r8 = r6 - r9
            long r8 = r8 % r0
            long r8 = r0 - r8
            r22 = r4
            long r4 = r6 + r8
            r24 = r6
            long r6 = kotlinx.coroutines.EventLoopKt.delayNanosToMillis(r8)
            r3.J$0 = r13
            r3.J$1 = r11
            r10 = r21
            r3.L$0 = r10
            r3.J$2 = r4
            r3.J$3 = r0
            r16 = r4
            r4 = r24
            r3.J$4 = r4
            r4 = r22
            r3.J$5 = r4
            r3.J$6 = r8
            r8 = 3
            r3.label = r8
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r6, r3)
            if (r4 != r2) goto L_0x0140
            return r2
        L_0x0140:
            r4 = r2
            r5 = r10
            r9 = r16
        L_0x0144:
            r2 = r5
            r5 = r4
            r4 = 4
            r18 = r11
            r11 = r13
            r13 = r0
            r0 = r18
            goto L_0x0185
        L_0x014e:
            r15 = r21
            r8 = 3
            r18 = r4
            r4 = r6
            r6 = r18
            long r8 = kotlinx.coroutines.EventLoopKt.delayNanosToMillis(r6)
            r3.J$0 = r13
            r3.J$1 = r11
            r3.L$0 = r15
            r24 = r11
            r10 = r22
            r3.J$2 = r10
            r3.J$3 = r0
            r3.J$4 = r4
            r3.J$5 = r6
            r4 = 4
            r3.label = r4
            java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r8, r3)
            if (r5 != r2) goto L_0x0176
            return r2
        L_0x0176:
            r9 = r10
            r5 = r15
            r11 = r24
        L_0x017a:
            r18 = r5
            r5 = r2
            r2 = r18
            r19 = r11
            r11 = r13
            r13 = r0
            r0 = r19
        L_0x0185:
            r4 = r5
            r6 = 4
            r7 = 3
            r8 = 2
            goto L_0x00d3
        L_0x018b:
            kotlin.Result$Failure r2 = (kotlin.Result.Failure) r2
            java.lang.Throwable r0 = r2.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0092 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ java.lang.Object fixedDelayTicker(long r8, long r10, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = (kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x006f
            if (r2 == r5) goto L_0x005c
            if (r2 == r4) goto L_0x004a
            if (r2 != r3) goto L_0x0042
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            long r9 = r0.J$1
            long r11 = r0.J$0
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x003d
        L_0x0038:
            r6 = r11
            r12 = r8
            r10 = r9
            r8 = r6
            goto L_0x0082
        L_0x003d:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r8 = r13.exception
            throw r8
        L_0x0042:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x004a:
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            long r9 = r0.J$1
            long r11 = r0.J$0
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0057
            goto L_0x0097
        L_0x0057:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r8 = r13.exception
            throw r8
        L_0x005c:
            java.lang.Object r8 = r0.L$0
            r12 = r8
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            long r10 = r0.J$1
            long r8 = r0.J$0
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x006a
            goto L_0x0082
        L_0x006a:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r8 = r13.exception
            throw r8
        L_0x006f:
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x00a6
            r0.J$0 = r8
            r0.J$1 = r10
            r0.L$0 = r12
            r0.label = r5
            java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r10, r0)
            if (r13 != r1) goto L_0x0082
            return r1
        L_0x0082:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            r0.J$0 = r8
            r0.J$1 = r10
            r0.L$0 = r12
            r0.label = r4
            java.lang.Object r13 = r12.send(r13, r0)
            if (r13 != r1) goto L_0x0093
            return r1
        L_0x0093:
            r6 = r8
            r8 = r12
            r9 = r10
            r11 = r6
        L_0x0097:
            r0.J$0 = r11
            r0.J$1 = r9
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r11, r0)
            if (r13 != r1) goto L_0x0038
            return r1
        L_0x00a6:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r8 = r13.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
