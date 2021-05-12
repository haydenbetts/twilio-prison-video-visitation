package com.urbanairship;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public class CancelableOperation implements Cancelable, Runnable {
    /* access modifiers changed from: private */
    public final List<Cancelable> cancelables;
    private final Handler handler;
    private final Runnable internalRunnable;
    private boolean isCanceled;
    /* access modifiers changed from: private */
    public boolean isFinished;
    private boolean isRunning;
    /* access modifiers changed from: private */
    public final List<Runnable> runnables;

    /* access modifiers changed from: protected */
    public void onCancel() {
    }

    /* access modifiers changed from: protected */
    public void onRun() {
    }

    public CancelableOperation() {
        this((Looper) null);
    }

    public CancelableOperation(Looper looper) {
        Handler handler2;
        this.isFinished = false;
        this.isRunning = false;
        this.isCanceled = false;
        this.cancelables = new ArrayList();
        this.runnables = new ArrayList();
        if (looper != null) {
            this.handler = new Handler(looper);
        } else {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                handler2 = new Handler(Looper.getMainLooper());
            }
            this.handler = handler2;
        }
        this.internalRunnable = new Runnable() {
            public void run() {
                synchronized (CancelableOperation.this) {
                    if (!CancelableOperation.this.isDone()) {
                        CancelableOperation.this.onRun();
                        boolean unused = CancelableOperation.this.isFinished = true;
                        for (Runnable run : CancelableOperation.this.runnables) {
                            run.run();
                        }
                        CancelableOperation.this.cancelables.clear();
                        CancelableOperation.this.runnables.clear();
                    }
                }
            }
        };
    }

    public final boolean cancel() {
        return cancel(false);
    }

    public final boolean cancel(boolean z) {
        synchronized (this) {
            if (isDone()) {
                return false;
            }
            this.isCanceled = true;
            this.handler.removeCallbacks(this.internalRunnable);
            this.handler.post(new Runnable() {
                public void run() {
                    CancelableOperation.this.onCancel();
                }
            });
            for (Cancelable cancel : this.cancelables) {
                cancel.cancel(z);
            }
            this.cancelables.clear();
            this.runnables.clear();
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isDone()     // Catch:{ all -> 0x001a }
            if (r0 != 0) goto L_0x0018
            boolean r0 = r2.isRunning     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000c
            goto L_0x0018
        L_0x000c:
            r0 = 1
            r2.isRunning = r0     // Catch:{ all -> 0x001a }
            android.os.Handler r0 = r2.handler     // Catch:{ all -> 0x001a }
            java.lang.Runnable r1 = r2.internalRunnable     // Catch:{ all -> 0x001a }
            r0.post(r1)     // Catch:{ all -> 0x001a }
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            return
        L_0x0018:
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            return
        L_0x001a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.CancelableOperation.run():void");
    }

    public final boolean isDone() {
        boolean z;
        synchronized (this) {
            if (!this.isFinished) {
                if (!this.isCanceled) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public final boolean isCancelled() {
        boolean z;
        synchronized (this) {
            z = this.isCanceled;
        }
        return z;
    }

    public CancelableOperation addOnRun(Runnable runnable) {
        synchronized (this) {
            if (this.isFinished) {
                runnable.run();
            } else {
                this.runnables.add(runnable);
            }
        }
        return this;
    }

    public CancelableOperation addOnCancel(Cancelable cancelable) {
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

    public Handler getHandler() {
        return this.handler;
    }
}
