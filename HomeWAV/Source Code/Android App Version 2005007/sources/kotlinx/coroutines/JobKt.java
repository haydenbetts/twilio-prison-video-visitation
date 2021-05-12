package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/JobKt__FutureKt", "kotlinx/coroutines/JobKt__JobKt"}, k = 4, mv = {1, 1, 13})
public final class JobKt {
    public static final DisposableHandle DisposableHandle(Function0<Unit> function0) {
        return JobKt__JobKt.DisposableHandle(function0);
    }

    public static final Job Job(Job job) {
        return JobKt__JobKt.Job(job);
    }

    public static final void cancel(CoroutineContext coroutineContext) {
        JobKt__JobKt.cancel(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancel() without cause", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
    public static final boolean cancel(CoroutineContext coroutineContext, Throwable th) {
        return JobKt__JobKt.cancel(coroutineContext, th);
    }

    public static final Object cancelAndJoin(Job job, Continuation<? super Unit> continuation) {
        return JobKt__JobKt.cancelAndJoin(job, continuation);
    }

    public static final void cancelChildren(CoroutineContext coroutineContext) {
        JobKt__JobKt.cancelChildren(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancelChildren() without cause", replaceWith = @ReplaceWith(expression = "cancelChildren()", imports = {}))
    public static final void cancelChildren(CoroutineContext coroutineContext, Throwable th) {
        JobKt__JobKt.cancelChildren(coroutineContext, th);
    }

    public static final void cancelChildren(Job job) {
        JobKt__JobKt.cancelChildren(job);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancelChildren() without cause", replaceWith = @ReplaceWith(expression = "cancelChildren()", imports = {}))
    public static final void cancelChildren(Job job, Throwable th) {
        JobKt__JobKt.cancelChildren(job, th);
    }

    public static final void cancelFutureOnCancellation(CancellableContinuation<?> cancellableContinuation, Future<?> future) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, future);
    }

    public static final DisposableHandle cancelFutureOnCompletion(Job job, Future<?> future) {
        return JobKt__FutureKt.cancelFutureOnCompletion(job, future);
    }

    public static final DisposableHandle disposeOnCompletion(Job job, DisposableHandle disposableHandle) {
        return JobKt__JobKt.disposeOnCompletion(job, disposableHandle);
    }

    public static final boolean isActive(CoroutineContext coroutineContext) {
        return JobKt__JobKt.isActive(coroutineContext);
    }
}
