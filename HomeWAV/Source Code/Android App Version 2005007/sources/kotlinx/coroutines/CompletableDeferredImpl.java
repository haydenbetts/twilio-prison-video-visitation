package kotlinx.coroutines;

import com.stripe.android.CustomerSession;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u000f\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u0011\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\r\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJH\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001f2\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0\"\u0012\u0006\u0012\u0004\u0018\u00010#0!H\u0016ø\u0001\u0000¢\u0006\u0002\u0010$R\u0014\u0010\b\u001a\u00020\t8TX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\t8PX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lkotlinx/coroutines/CompletableDeferredImpl;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlinx/coroutines/selects/SelectClause1;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "cancelsParent", "", "getCancelsParent", "()Z", "onAwait", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "getCompleted", "()Ljava/lang/Object;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CompletableDeferred.kt */
final class CompletableDeferredImpl<T> extends JobSupport implements CompletableDeferred<T>, SelectClause1<T> {
    /* access modifiers changed from: protected */
    public boolean getCancelsParent() {
        return true;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }

    public CompletableDeferredImpl(Job job) {
        super(true);
        initParentJobInternal$kotlinx_coroutines_core(job);
    }

    public T getCompleted() {
        return getCompletedInternal$kotlinx_coroutines_core();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object await(kotlin.coroutines.Continuation<? super T> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.CompletableDeferredImpl$await$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            kotlinx.coroutines.CompletableDeferredImpl$await$1 r0 = (kotlinx.coroutines.CompletableDeferredImpl$await$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.CompletableDeferredImpl$await$1 r0 = new kotlinx.coroutines.CompletableDeferredImpl$await$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.CompletableDeferredImpl r0 = (kotlinx.coroutines.CompletableDeferredImpl) r0
            boolean r0 = r5 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L_0x002f
            goto L_0x004b
        L_0x002f:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r5 = r5.exception
            throw r5
        L_0x0034:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x003c:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x004c
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.awaitInternal$kotlinx_coroutines_core(r0)
            if (r5 != r1) goto L_0x004b
            return r1
        L_0x004b:
            return r5
        L_0x004c:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r5 = r5.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CompletableDeferredImpl.await(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public SelectClause1<T> getOnAwait() {
        return this;
    }

    public <R> void registerSelectClause1(SelectInstance<? super R> selectInstance, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        registerSelectClause1Internal$kotlinx_coroutines_core(selectInstance, function2);
    }

    public boolean complete(T t) {
        return makeCompleting$kotlinx_coroutines_core(t);
    }

    public boolean completeExceptionally(Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, CustomerSession.EXTRA_EXCEPTION);
        return makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(th));
    }
}
