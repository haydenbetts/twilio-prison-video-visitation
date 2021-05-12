package com.urbanairship.util;

import android.os.Handler;
import android.os.Looper;
import com.urbanairship.AirshipExecutors;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class RetryingExecutor implements Executor {
    private static final long INITIAL_BACKOFF_MILLIS = 30000;
    private static final long MAX_BACKOFF_MILLIS = 300000;
    public static final int RESULT_CANCEL = 2;
    public static final int RESULT_FINISHED = 0;
    public static final int RESULT_RETRY = 1;
    /* access modifiers changed from: private */
    public final Executor executor;
    /* access modifiers changed from: private */
    public boolean isPaused = false;
    /* access modifiers changed from: private */
    public final List<Runnable> pendingRunnables = new ArrayList();
    /* access modifiers changed from: private */
    public final Handler scheduler;

    public interface Operation {
        int run();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    public RetryingExecutor(Handler handler, Executor executor2) {
        this.scheduler = handler;
        this.executor = executor2;
    }

    public static RetryingExecutor newSerialExecutor(Looper looper) {
        return new RetryingExecutor(new Handler(looper), AirshipExecutors.newSerialExecutor());
    }

    public void execute(final Runnable runnable) {
        execute((Operation) new Operation() {
            public int run() {
                runnable.run();
                return 0;
            }
        });
    }

    public void execute(Operation operation) {
        execute(operation, 30000);
    }

    public void execute(Operation... operationArr) {
        execute((Operation) new ChainedOperations(Arrays.asList(operationArr)));
    }

    /* access modifiers changed from: private */
    public void execute(final Operation operation, final long j) {
        this.executor.execute(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
                if (r2.run() != 1) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
                com.urbanairship.util.RetryingExecutor.access$400(r7.this$0).postAtTime(new com.urbanairship.util.RetryingExecutor.AnonymousClass2.AnonymousClass1(r7), com.urbanairship.util.RetryingExecutor.access$300(r7.this$0), android.os.SystemClock.uptimeMillis() + r3);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    com.urbanairship.util.RetryingExecutor r0 = com.urbanairship.util.RetryingExecutor.this
                    java.util.List r0 = r0.pendingRunnables
                    monitor-enter(r0)
                    com.urbanairship.util.RetryingExecutor r1 = com.urbanairship.util.RetryingExecutor.this     // Catch:{ all -> 0x0040 }
                    boolean r1 = r1.isPaused     // Catch:{ all -> 0x0040 }
                    if (r1 == 0) goto L_0x001a
                    com.urbanairship.util.RetryingExecutor r1 = com.urbanairship.util.RetryingExecutor.this     // Catch:{ all -> 0x0040 }
                    java.util.List r1 = r1.pendingRunnables     // Catch:{ all -> 0x0040 }
                    r1.add(r7)     // Catch:{ all -> 0x0040 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                    return
                L_0x001a:
                    monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                    com.urbanairship.util.RetryingExecutor$Operation r0 = r2
                    int r0 = r0.run()
                    r1 = 1
                    if (r0 != r1) goto L_0x003f
                    com.urbanairship.util.RetryingExecutor r0 = com.urbanairship.util.RetryingExecutor.this
                    android.os.Handler r0 = r0.scheduler
                    com.urbanairship.util.RetryingExecutor$2$1 r1 = new com.urbanairship.util.RetryingExecutor$2$1
                    r1.<init>()
                    com.urbanairship.util.RetryingExecutor r2 = com.urbanairship.util.RetryingExecutor.this
                    java.util.concurrent.Executor r2 = r2.executor
                    long r3 = android.os.SystemClock.uptimeMillis()
                    long r5 = r3
                    long r3 = r3 + r5
                    r0.postAtTime(r1, r2, r3)
                L_0x003f:
                    return
                L_0x0040:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.util.RetryingExecutor.AnonymousClass2.run():void");
            }
        });
    }

    public void setPaused(boolean z) {
        if (z != this.isPaused) {
            synchronized (this.pendingRunnables) {
                this.isPaused = z;
                if (!z && !this.pendingRunnables.isEmpty()) {
                    ArrayList<Runnable> arrayList = new ArrayList<>(this.pendingRunnables);
                    this.pendingRunnables.clear();
                    for (Runnable execute : arrayList) {
                        this.executor.execute(execute);
                    }
                }
            }
        }
    }

    private class ChainedOperations implements Operation {
        private final List<? extends Operation> operations;

        ChainedOperations(List<? extends Operation> list) {
            this.operations = new ArrayList(list);
        }

        public int run() {
            if (this.operations.isEmpty()) {
                return 0;
            }
            int run = ((Operation) this.operations.get(0)).run();
            int i = 1;
            if (run != 1) {
                i = 2;
                if (run != 2) {
                    this.operations.remove(0);
                    RetryingExecutor.this.execute((Operation) this);
                    return 0;
                }
            }
            return i;
        }
    }
}
