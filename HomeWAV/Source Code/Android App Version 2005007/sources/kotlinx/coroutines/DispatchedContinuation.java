package kotlinx.coroutines;

import com.stripe.android.CustomerSession;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.UndispatchedEventLoop;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0007J\u0017\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\b¢\u0006\u0002\u0010\u001eJ\u0011\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\bJ\t\u0010#\u001a\u00020$H\bJ\u0016\u0010%\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\b¢\u0006\u0002\u0010\u001eJ\u0011\u0010&\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\bJ\u001e\u0010'\u001a\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\n\u0010*\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010+\u001a\u00020,H\u0016R\u001a\u0010\b\u001a\u0004\u0018\u00010\t8\u0000@\u0000X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\t8\u0000X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lkotlinx/coroutines/DispatchedContinuation;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/DispatchedTask;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "continuation", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "_state", "", "_state$annotations", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "countOrElement", "delegate", "getDelegate", "()Lkotlin/coroutines/Continuation;", "resumeMode", "", "getResumeMode", "()I", "setResumeMode", "(I)V", "dispatchYield", "", "value", "dispatchYield$kotlinx_coroutines_core", "(Ljava/lang/Object;)V", "resumeCancellable", "resumeCancellableWithException", "exception", "", "resumeCancelled", "", "resumeUndispatched", "resumeUndispatchedWithException", "resumeWith", "result", "Lkotlin/Result;", "takeState", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Dispatched.kt */
public final class DispatchedContinuation<T> implements Continuation<T>, DispatchedTask<T> {
    public Object _state = DispatchedKt.UNDEFINED;
    public final Continuation<T> continuation;
    public final Object countOrElement = ThreadContextKt.threadContextElements(getContext());
    public final CoroutineDispatcher dispatcher;
    private int resumeMode;

    public static /* synthetic */ void _state$annotations() {
    }

    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation<? super T> continuation2) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Intrinsics.checkParameterIsNotNull(continuation2, "continuation");
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation2;
    }

    public Throwable getExceptionalResult(Object obj) {
        return DispatchedTask.DefaultImpls.getExceptionalResult(this, obj);
    }

    public <T> T getSuccessfulResult(Object obj) {
        return DispatchedTask.DefaultImpls.getSuccessfulResult(this, obj);
    }

    public void run() {
        DispatchedTask.DefaultImpls.run(this);
    }

    public int getResumeMode() {
        return this.resumeMode;
    }

    public void setResumeMode(int i) {
        this.resumeMode = i;
    }

    public Object takeState() {
        Object obj = this._state;
        if (obj != DispatchedKt.UNDEFINED) {
            this._state = DispatchedKt.UNDEFINED;
            return obj;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public Continuation<T> getDelegate() {
        return this;
    }

    public void resumeWith(Object obj) {
        CoroutineContext context;
        Object updateThreadContext;
        CoroutineContext context2 = this.continuation.getContext();
        Object state = CompletedExceptionallyKt.toState(obj);
        if (this.dispatcher.isDispatchNeeded(context2)) {
            this._state = state;
            setResumeMode(0);
            this.dispatcher.dispatch(context2, this);
            return;
        }
        UndispatchedEventLoop undispatchedEventLoop = UndispatchedEventLoop.INSTANCE;
        UndispatchedEventLoop.EventLoop eventLoop = (UndispatchedEventLoop.EventLoop) UndispatchedEventLoop.threadLocalEventLoop.get();
        if (eventLoop.isActive) {
            this._state = state;
            setResumeMode(0);
            eventLoop.queue.addLast(this);
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(eventLoop, "eventLoop");
        try {
            eventLoop.isActive = true;
            context = getContext();
            updateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
            this.continuation.resumeWith(obj);
            Unit unit = Unit.INSTANCE;
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            while (true) {
                Runnable removeFirstOrNull = eventLoop.queue.removeFirstOrNull();
                if (removeFirstOrNull != null) {
                    removeFirstOrNull.run();
                } else {
                    eventLoop.isActive = false;
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                eventLoop.queue.clear();
                throw new DispatchException("Unexpected exception in undispatched event loop, clearing pending tasks", th);
            } catch (Throwable th2) {
                eventLoop.isActive = false;
                throw th2;
            }
        }
    }

    public final void resumeCancellable(T t) {
        boolean z;
        CoroutineContext context;
        Object updateThreadContext;
        if (this.dispatcher.isDispatchNeeded(getContext())) {
            this._state = t;
            setResumeMode(1);
            this.dispatcher.dispatch(getContext(), this);
            return;
        }
        UndispatchedEventLoop undispatchedEventLoop = UndispatchedEventLoop.INSTANCE;
        UndispatchedEventLoop.EventLoop eventLoop = (UndispatchedEventLoop.EventLoop) UndispatchedEventLoop.threadLocalEventLoop.get();
        if (eventLoop.isActive) {
            this._state = t;
            setResumeMode(1);
            eventLoop.queue.addLast(this);
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(eventLoop, "eventLoop");
        try {
            eventLoop.isActive = true;
            Job job = (Job) getContext().get(Job.Key);
            if (job == null || job.isActive()) {
                z = false;
            } else {
                Result.Companion companion = Result.Companion;
                resumeWith(Result.m3constructorimpl(ResultKt.createFailure(job.getCancellationException())));
                z = true;
            }
            if (!z) {
                context = getContext();
                updateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
                Continuation<T> continuation2 = this.continuation;
                Result.Companion companion2 = Result.Companion;
                continuation2.resumeWith(Result.m3constructorimpl(t));
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                InlineMarker.finallyEnd(1);
            }
            while (true) {
                Runnable removeFirstOrNull = eventLoop.queue.removeFirstOrNull();
                if (removeFirstOrNull != null) {
                    removeFirstOrNull.run();
                } else {
                    InlineMarker.finallyStart(1);
                    eventLoop.isActive = false;
                    InlineMarker.finallyEnd(1);
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                eventLoop.queue.clear();
                throw new DispatchException("Unexpected exception in undispatched event loop, clearing pending tasks", th);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                eventLoop.isActive = false;
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public final void resumeCancellableWithException(Throwable th) {
        boolean z;
        CoroutineContext context;
        Object updateThreadContext;
        Intrinsics.checkParameterIsNotNull(th, CustomerSession.EXTRA_EXCEPTION);
        CoroutineContext context2 = this.continuation.getContext();
        CompletedExceptionally completedExceptionally = new CompletedExceptionally(th);
        if (this.dispatcher.isDispatchNeeded(context2)) {
            this._state = new CompletedExceptionally(th);
            setResumeMode(1);
            this.dispatcher.dispatch(context2, this);
            return;
        }
        UndispatchedEventLoop undispatchedEventLoop = UndispatchedEventLoop.INSTANCE;
        UndispatchedEventLoop.EventLoop eventLoop = (UndispatchedEventLoop.EventLoop) UndispatchedEventLoop.threadLocalEventLoop.get();
        if (eventLoop.isActive) {
            this._state = completedExceptionally;
            setResumeMode(1);
            eventLoop.queue.addLast(this);
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(eventLoop, "eventLoop");
        try {
            eventLoop.isActive = true;
            Job job = (Job) getContext().get(Job.Key);
            if (job == null || job.isActive()) {
                z = false;
            } else {
                Result.Companion companion = Result.Companion;
                resumeWith(Result.m3constructorimpl(ResultKt.createFailure(job.getCancellationException())));
                z = true;
            }
            if (!z) {
                context = getContext();
                updateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
                Continuation<T> continuation2 = this.continuation;
                Result.Companion companion2 = Result.Companion;
                continuation2.resumeWith(Result.m3constructorimpl(ResultKt.createFailure(th)));
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                InlineMarker.finallyEnd(1);
            }
            while (true) {
                Runnable removeFirstOrNull = eventLoop.queue.removeFirstOrNull();
                if (removeFirstOrNull != null) {
                    removeFirstOrNull.run();
                } else {
                    InlineMarker.finallyStart(1);
                    eventLoop.isActive = false;
                    InlineMarker.finallyEnd(1);
                    return;
                }
            }
        } catch (Throwable th2) {
            try {
                eventLoop.queue.clear();
                throw new DispatchException("Unexpected exception in undispatched event loop, clearing pending tasks", th2);
            } catch (Throwable th3) {
                InlineMarker.finallyStart(1);
                eventLoop.isActive = false;
                InlineMarker.finallyEnd(1);
                throw th3;
            }
        }
    }

    public final boolean resumeCancelled() {
        Job job = (Job) getContext().get(Job.Key);
        if (job == null || job.isActive()) {
            return false;
        }
        Result.Companion companion = Result.Companion;
        resumeWith(Result.m3constructorimpl(ResultKt.createFailure(job.getCancellationException())));
        return true;
    }

    public final void resumeUndispatched(T t) {
        CoroutineContext context = getContext();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
        try {
            Continuation<T> continuation2 = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m3constructorimpl(t));
            Unit unit = Unit.INSTANCE;
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final void resumeUndispatchedWithException(Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, CustomerSession.EXTRA_EXCEPTION);
        CoroutineContext context = getContext();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
        try {
            Continuation<T> continuation2 = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m3constructorimpl(ResultKt.createFailure(th)));
            Unit unit = Unit.INSTANCE;
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final void dispatchYield$kotlinx_coroutines_core(T t) {
        CoroutineContext context = this.continuation.getContext();
        this._state = t;
        setResumeMode(1);
        this.dispatcher.dispatchYield(context, this);
    }

    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugKt.toDebugString(this.continuation) + ']';
    }
}
