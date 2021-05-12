package kotlinx.coroutines;

import com.stripe.android.CustomerSession;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\"\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\f2\u0006\u0010$\u001a\u00020\u0006H\u0004J\u0010\u0010%\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020(H\u0016J\n\u0010)\u001a\u0004\u0018\u00010\fH\u0001J\u0010\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u001dH\u0002J\u0017\u0010,\u001a\u00020 2\b\u0010'\u001a\u0004\u0018\u00010(H\u0000¢\u0006\u0002\b-J/\u0010.\u001a\u00020 2'\u0010/\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020 00j\u0002`3J\u001f\u00104\u001a\u0002052\u0014\u00106\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020 00H\bJ1\u00107\u001a\u0002082'\u0010/\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020 00j\u0002`3H\u0002J\b\u00109\u001a\u00020:H\u0014J\u001a\u0010;\u001a\u00020 2\b\u0010<\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0004J\u001e\u0010=\u001a\u00020 2\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?H\u0016ø\u0001\u0000¢\u0006\u0002\u0010@J\u001d\u0010A\u001a\u00020 2\u0006\u0010+\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u0006H\u0000¢\u0006\u0002\bBJ\b\u0010C\u001a\u00020:H\u0002J\n\u0010D\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010E\u001a\u00020:H\u0016J\b\u0010F\u001a\u00020\u0010H\u0002J\b\u0010G\u001a\u00020\u0010H\u0002J\u001a\u0010H\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\fH\u0004J\"\u0010I\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"2\b\u0010<\u001a\u0004\u0018\u00010\f2\u0006\u0010$\u001a\u00020\u0006H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\f8@X\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0002\u0004\n\u0002\b\u0019¨\u0006J"}, d2 = {"Lkotlinx/coroutines/AbstractContinuation;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/DispatchedTask;", "delegate", "resumeMode", "", "(Lkotlin/coroutines/Continuation;I)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "getDelegate", "()Lkotlin/coroutines/Continuation;", "isActive", "", "()Z", "isCancelled", "isCompleted", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "getResumeMode", "()I", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "cancel", "cause", "", "cancelImpl", "completeStateUpdate", "", "expect", "Lkotlinx/coroutines/NotCompleted;", "update", "mode", "dispatchResume", "getContinuationCancellationCause", "parent", "Lkotlinx/coroutines/Job;", "getResult", "handleException", "exception", "initParentJobInternal", "initParentJobInternal$kotlinx_coroutines_core", "invokeOnCancellation", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "loopOnState", "", "block", "makeHandler", "Lkotlinx/coroutines/CancelHandler;", "nameString", "", "resumeImpl", "proposedUpdate", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "resumeWithExceptionMode", "resumeWithExceptionMode$kotlinx_coroutines_core", "stateString", "takeState", "toString", "tryResume", "trySuspend", "tryUpdateStateToFinal", "updateStateToFinal", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractContinuation.kt */
public abstract class AbstractContinuation<T> implements Continuation<T>, DispatchedTask<T> {
    private static final AtomicIntegerFieldUpdater _decision$FU;
    private static final AtomicReferenceFieldUpdater _state$FU;
    private volatile int _decision = 0;
    private volatile Object _state = AbstractContinuationKt.ACTIVE;
    private final Continuation<T> delegate;
    private volatile DisposableHandle parentHandle;
    private final int resumeMode;

    static {
        Class<AbstractContinuation> cls = AbstractContinuation.class;
        _decision$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_decision");
        _state$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_state");
    }

    public AbstractContinuation(Continuation<? super T> continuation, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "delegate");
        this.delegate = continuation;
        this.resumeMode = i;
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

    public final Continuation<T> getDelegate() {
        return this.delegate;
    }

    public final int getResumeMode() {
        return this.resumeMode;
    }

    public final Object getState$kotlinx_coroutines_core() {
        return this._state;
    }

    public final boolean isActive() {
        return getState$kotlinx_coroutines_core() instanceof NotCompleted;
    }

    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    public final boolean isCancelled() {
        return getState$kotlinx_coroutines_core() instanceof CancelledContinuation;
    }

    public final void initParentJobInternal$kotlinx_coroutines_core(Job job) {
        if (!(this.parentHandle == null)) {
            throw new IllegalStateException("Check failed.".toString());
        } else if (job == null) {
            this.parentHandle = NonDisposableHandle.INSTANCE;
        } else {
            job.start();
            DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation(job, this), 2, (Object) null);
            this.parentHandle = invokeOnCompletion$default;
            if (isCompleted()) {
                invokeOnCompletion$default.dispose();
                this.parentHandle = NonDisposableHandle.INSTANCE;
            }
        }
    }

    public Object takeState() {
        return getState$kotlinx_coroutines_core();
    }

    public final boolean cancel(Throwable th) {
        return cancelImpl(th);
    }

    public Throwable getContinuationCancellationCause(Job job) {
        Intrinsics.checkParameterIsNotNull(job, "parent");
        return job.getCancellationException();
    }

    public final Object getResult() {
        if (trySuspend()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return getSuccessfulResult(state$kotlinx_coroutines_core);
        }
        throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
    }

    public void resumeWith(Object obj) {
        resumeImpl(CompletedExceptionallyKt.toState(obj), this.resumeMode);
    }

    public final void resumeWithExceptionMode$kotlinx_coroutines_core(Throwable th, int i) {
        Intrinsics.checkParameterIsNotNull(th, CustomerSession.EXTRA_EXCEPTION);
        resumeImpl(new CompletedExceptionally(th), i);
    }

    public final void invokeOnCancellation(Function1<? super Throwable, Unit> function1) {
        Object state$kotlinx_coroutines_core;
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        Throwable th = null;
        CancelHandler cancelHandler = null;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Active) {
                if (cancelHandler == null) {
                    cancelHandler = makeHandler(function1);
                }
            } else if (state$kotlinx_coroutines_core instanceof CancelHandler) {
                throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + state$kotlinx_coroutines_core).toString());
            } else if (state$kotlinx_coroutines_core instanceof CancelledContinuation) {
                if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                    state$kotlinx_coroutines_core = null;
                }
                CompletedExceptionally completedExceptionally = (CompletedExceptionally) state$kotlinx_coroutines_core;
                if (completedExceptionally != null) {
                    th = completedExceptionally.cause;
                }
                function1.invoke(th);
                return;
            } else {
                return;
            }
        } while (!_state$FU.compareAndSet(this, state$kotlinx_coroutines_core, cancelHandler));
    }

    private final CancelHandler makeHandler(Function1<? super Throwable, Unit> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void dispatchResume(int i) {
        if (!tryResume()) {
            DispatchedKt.dispatch(this, i);
        }
    }

    /* access modifiers changed from: protected */
    public final Void loopOnState(Function1<Object, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "block");
        while (true) {
            function1.invoke(getState$kotlinx_coroutines_core());
        }
    }

    private final boolean updateStateToFinal(NotCompleted notCompleted, Object obj, int i) {
        if (!tryUpdateStateToFinal(notCompleted, obj)) {
            return false;
        }
        completeStateUpdate(notCompleted, obj, i);
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean tryUpdateStateToFinal(NotCompleted notCompleted, Object obj) {
        Intrinsics.checkParameterIsNotNull(notCompleted, "expect");
        if (!(!(obj instanceof NotCompleted))) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (!_state$FU.compareAndSet(this, notCompleted, obj)) {
            return false;
        } else {
            DisposableHandle disposableHandle = this.parentHandle;
            if (disposableHandle != null) {
                disposableHandle.dispose();
                this.parentHandle = NonDisposableHandle.INSTANCE;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final void completeStateUpdate(NotCompleted notCompleted, Object obj, int i) {
        Intrinsics.checkParameterIsNotNull(notCompleted, "expect");
        Throwable th = null;
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(obj instanceof CompletedExceptionally) ? null : obj);
        if ((obj instanceof CancelledContinuation) && (notCompleted instanceof CancelHandler)) {
            try {
                CancelHandler cancelHandler = (CancelHandler) notCompleted;
                if (completedExceptionally != null) {
                    th = completedExceptionally.cause;
                }
                cancelHandler.invoke(th);
            } catch (Throwable th2) {
                handleException(new CompletionHandlerException("Exception in completion handler " + notCompleted + " for " + this, th2));
            }
        }
        dispatchResume(i);
    }

    private final void handleException(Throwable th) {
        CoroutineExceptionHandlerKt.handleCoroutineException$default(getContext(), th, (Job) null, 4, (Object) null);
    }

    public String toString() {
        return nameString() + '{' + stateString() + "}@" + DebugKt.getHexAddress(this);
    }

    /* access modifiers changed from: protected */
    public String nameString() {
        return DebugKt.getClassSimpleName(this);
    }

    private final String stateString() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof NotCompleted) {
            return "Active";
        }
        if (state$kotlinx_coroutines_core instanceof CancelledContinuation) {
            return "Cancelled";
        }
        return state$kotlinx_coroutines_core instanceof CompletedExceptionally ? "CompletedExceptionally" : "Completed";
    }

    public final boolean cancelImpl(Throwable th) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof NotCompleted)) {
                return false;
            }
        } while (!updateStateToFinal((NotCompleted) state$kotlinx_coroutines_core, new CancelledContinuation(this, th), 0));
        return true;
    }

    private final boolean trySuspend() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 1));
        return true;
    }

    private final boolean tryResume() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 2));
        return true;
    }

    /* access modifiers changed from: protected */
    public final void resumeImpl(Object obj, int i) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof NotCompleted)) {
                if (!(state$kotlinx_coroutines_core instanceof CancelledContinuation)) {
                    throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
                } else if (obj instanceof CompletedExceptionally) {
                    handleException(((CompletedExceptionally) obj).cause);
                    return;
                } else {
                    return;
                }
            }
        } while (!updateStateToFinal((NotCompleted) state$kotlinx_coroutines_core, obj, i));
    }
}
