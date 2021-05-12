package fm.liveswitch;

import java.util.ArrayList;

public abstract class PromiseBase<T> extends Future<T> implements IPromise {
    private String __id = Guid.newGuid().toString().replace("-", "");
    private ArrayList<IPromise> __pendingPromisesToReject = new ArrayList<>();
    private ArrayList<IPromise> __pendingPromisesToResolve = new ArrayList<>();
    private ArrayList<IAction1<Exception>> __pendingRejects = new ArrayList<>();
    private ArrayList<IAction1<T>> __pendingResolves = new ArrayList<>();
    private Object __stateLock = new Object();

    private void addReject(IPromise iPromise, IAction1<Exception> iAction1) {
        if (this.__pendingPromisesToReject == null) {
            this.__pendingPromisesToReject = new ArrayList<>();
        }
        if (this.__pendingRejects == null) {
            this.__pendingRejects = new ArrayList<>();
        }
        this.__pendingPromisesToReject.add(iPromise);
        this.__pendingRejects.add(iAction1);
    }

    private void addResolve(IPromise iPromise, IAction1<T> iAction1) {
        if (this.__pendingPromisesToResolve == null) {
            this.__pendingPromisesToResolve = new ArrayList<>();
        }
        if (this.__pendingResolves == null) {
            this.__pendingResolves = new ArrayList<>();
        }
        this.__pendingPromisesToResolve.add(iPromise);
        this.__pendingResolves.add(iAction1);
    }

    public static <R> Future<R> all(Future<R>[] futureArr) {
        Promise promise = new Promise();
        if (futureArr == null || ArrayExtensions.getLength((Object[]) futureArr) == 0) {
            promise.resolve((Object) null);
            return promise;
        }
        promise.doAll(futureArr, new AtomicInteger(ArrayExtensions.getLength((Object[]) futureArr)));
        return promise;
    }

    public boolean castAndResolve(Object obj) {
        return resolve(obj);
    }

    /* access modifiers changed from: protected */
    public <R> void doAll(Future<R>[] futureArr, final AtomicInteger atomicInteger) {
        AnonymousClass1 r1 = null;
        AnonymousClass2 r2 = null;
        for (Future<R> future : futureArr) {
            if (r1 == null) {
                r1 = new IAction1<R>() {
                    public void invoke(R r) {
                        if (atomicInteger.decrement() == 0) {
                            PromiseBase.this.resolve(null);
                        }
                    }
                };
            }
            if (r2 == null) {
                r2 = new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        PromiseBase.this.reject(exc);
                    }
                };
            }
            future.then((IAction1<R>) r1, (IAction1<Exception>) r2);
        }
    }

    private void doRejectAsync(final Promise<Object> promise, final Exception exc) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                if (PromiseBase.this.reject(exc)) {
                    promise.resolve(null);
                } else {
                    promise.reject(new Exception("Promise was already fulfilled."));
                }
            }
        });
    }

    private void doResolveAsync(final Promise<Object> promise, final T t) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                if (PromiseBase.this.resolve(t)) {
                    promise.resolve(null);
                } else {
                    promise.reject(new Exception("Promise was already fulfilled."));
                }
            }
        });
    }

    public String getId() {
        return this.__id;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (fm.liveswitch.Global.equals(super.getState(), fm.liveswitch.FutureState.Resolved) == false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        raiseResolve(r5, super.getResult(), r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        if (fm.liveswitch.Global.equals(super.getState(), fm.liveswitch.FutureState.Rejected) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        raiseReject(r6, super.getException(), r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void process(fm.liveswitch.IPromise r4, fm.liveswitch.IAction1<T> r5, fm.liveswitch.IAction1<java.lang.Exception> r6) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.__stateLock
            monitor-enter(r0)
            fm.liveswitch.FutureState r1 = super.getState()     // Catch:{ all -> 0x0040 }
            fm.liveswitch.FutureState r2 = fm.liveswitch.FutureState.Pending     // Catch:{ all -> 0x0040 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0017
            r3.addResolve(r4, r5)     // Catch:{ all -> 0x0040 }
            r3.addReject(r4, r6)     // Catch:{ all -> 0x0040 }
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            fm.liveswitch.FutureState r0 = super.getState()
            fm.liveswitch.FutureState r1 = fm.liveswitch.FutureState.Resolved
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 == 0) goto L_0x002c
            java.lang.Object r6 = super.getResult()
            r3.raiseResolve(r5, r6, r4)
            goto L_0x003f
        L_0x002c:
            fm.liveswitch.FutureState r5 = super.getState()
            fm.liveswitch.FutureState r0 = fm.liveswitch.FutureState.Rejected
            boolean r5 = fm.liveswitch.Global.equals(r5, r0)
            if (r5 == 0) goto L_0x003f
            java.lang.Exception r5 = super.getException()
            r3.raiseReject(r6, r5, r4)
        L_0x003f:
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.PromiseBase.process(fm.liveswitch.IPromise, fm.liveswitch.IAction1, fm.liveswitch.IAction1):void");
    }

    public PromiseBase() {
        super.setState(FutureState.Pending);
    }

    private void raiseReject(IAction1<Exception> iAction1, Exception exc, IPromise iPromise) {
        try {
            iAction1.invoke(exc);
        } catch (Exception e) {
            Log.error("Could not reject promise. Promise rejection callback threw an unhandled exception.", e);
            iPromise.reject(e);
        }
    }

    private void raiseRejects(Exception exc) {
        if (this.__pendingRejects != null) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__pendingRejects); i++) {
                raiseReject((IAction1) ArrayListExtensions.getItem(this.__pendingRejects).get(i), exc, (IPromise) ArrayListExtensions.getItem(this.__pendingPromisesToReject).get(i));
            }
        }
        reset();
    }

    private void raiseResolve(IAction1<T> iAction1, T t, IPromise iPromise) {
        try {
            iAction1.invoke(t);
        } catch (Exception e) {
            Log.error("Could not resolve promise. Promise resolution callback threw an unhandled exception.", e);
            iPromise.reject(e);
        }
    }

    private void raiseResolves(T t) {
        if (this.__pendingResolves != null) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__pendingResolves); i++) {
                raiseResolve((IAction1) ArrayListExtensions.getItem(this.__pendingResolves).get(i), t, (IPromise) ArrayListExtensions.getItem(this.__pendingPromisesToResolve).get(i));
            }
        }
        reset();
    }

    public boolean reject(Exception exc) {
        synchronized (this.__stateLock) {
            if (!Global.equals(super.getState(), FutureState.Pending)) {
                return false;
            }
            super.setException(exc);
            super.setState(FutureState.Rejected);
            raiseRejects(exc);
            return true;
        }
    }

    public Future<Object> rejectAsync(Exception exc) {
        Promise promise = new Promise();
        doRejectAsync(promise, exc);
        return promise;
    }

    public static <R> Future<R> rejectNow(Exception exc) {
        Promise promise = new Promise();
        promise.reject(exc);
        return promise;
    }

    private void reset() {
        this.__pendingPromisesToResolve.clear();
        this.__pendingPromisesToReject.clear();
        this.__pendingResolves.clear();
        this.__pendingRejects.clear();
    }

    public boolean resolve(T t) {
        synchronized (this.__stateLock) {
            if (!Global.equals(super.getState(), FutureState.Pending)) {
                return false;
            }
            super.setResult(t);
            super.setState(FutureState.Resolved);
            raiseResolves(t);
            return true;
        }
    }

    public Future<Object> resolveAsync(T t) {
        Promise promise = new Promise();
        doResolveAsync(promise, t);
        return promise;
    }

    public static Future<Object> resolveNow() {
        return resolveNow((Object) null);
    }

    public static <R> Future<R> resolveNow(R r) {
        Promise promise = new Promise();
        promise.resolve(r);
        return promise;
    }

    public static <R> Future<R> wrapPromise(IFunction0<Future<R>> iFunction0) {
        if (iFunction0 != null) {
            try {
                return iFunction0.invoke();
            } catch (Exception e) {
                return rejectNow(e);
            }
        } else {
            throw new RuntimeException(new Exception("callback cannot be null."));
        }
    }
}
