package com.urbanairship;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PendingResult<T> implements Cancelable, Future<T> {
    private final List<Cancelable> cancelables = new ArrayList();
    private boolean isCanceled;
    /* access modifiers changed from: private */
    public T result;
    private final List<CancelableOperation> resultCallbacks = new ArrayList();
    private boolean resultSet;
    /* access modifiers changed from: private */
    public boolean runCallbacks = true;

    public final boolean cancel() {
        return cancel(false);
    }

    public boolean cancel(boolean z) {
        synchronized (this) {
            if (isCancelled()) {
                return true;
            }
            this.runCallbacks = false;
            for (CancelableOperation cancel : this.resultCallbacks) {
                cancel.cancel(z);
            }
            this.resultCallbacks.clear();
            if (isDone()) {
                return false;
            }
            this.isCanceled = true;
            notifyAll();
            for (Cancelable cancel2 : this.cancelables) {
                cancel2.cancel(z);
            }
            this.cancelables.clear();
            return true;
        }
    }

    public void setResult(T t) {
        synchronized (this) {
            if (!isDone()) {
                this.result = t;
                this.resultSet = true;
                this.cancelables.clear();
                notifyAll();
                for (CancelableOperation run : this.resultCallbacks) {
                    run.run();
                }
                this.resultCallbacks.clear();
            }
        }
    }

    public T getResult() {
        T t;
        synchronized (this) {
            t = this.result;
        }
        return t;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this) {
            z = this.isCanceled;
        }
        return z;
    }

    public boolean isDone() {
        boolean z;
        synchronized (this) {
            if (!this.isCanceled) {
                if (!this.resultSet) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public T get() throws InterruptedException, ExecutionException {
        synchronized (this) {
            if (isDone()) {
                T t = this.result;
                return t;
            }
            wait();
            T t2 = this.result;
            return t2;
        }
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        synchronized (this) {
            if (isDone()) {
                T t = this.result;
                return t;
            }
            wait(timeUnit.toMillis(j));
            T t2 = this.result;
            return t2;
        }
    }

    public PendingResult<T> addCancelable(Cancelable cancelable) {
        synchronized (this) {
            if (isCancelled()) {
                cancelable.cancel();
            }
            if (!isDone()) {
                this.cancelables.add(cancelable);
            }
        }
        return this;
    }

    public PendingResult<T> addResultCallback(ResultCallback<T> resultCallback) {
        return addResultCallback(Looper.myLooper(), resultCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.urbanairship.PendingResult<T> addResultCallback(android.os.Looper r2, final com.urbanairship.ResultCallback<T> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isCancelled()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0021
            boolean r0 = r1.runCallbacks     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x000c
            goto L_0x0021
        L_0x000c:
            com.urbanairship.PendingResult$1 r0 = new com.urbanairship.PendingResult$1     // Catch:{ all -> 0x0023 }
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0023 }
            boolean r2 = r1.isDone()     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x001a
            r0.run()     // Catch:{ all -> 0x0023 }
        L_0x001a:
            java.util.List<com.urbanairship.CancelableOperation> r2 = r1.resultCallbacks     // Catch:{ all -> 0x0023 }
            r2.add(r0)     // Catch:{ all -> 0x0023 }
            monitor-exit(r1)     // Catch:{ all -> 0x0023 }
            return r1
        L_0x0021:
            monitor-exit(r1)     // Catch:{ all -> 0x0023 }
            return r1
        L_0x0023:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0023 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.PendingResult.addResultCallback(android.os.Looper, com.urbanairship.ResultCallback):com.urbanairship.PendingResult");
    }
}
