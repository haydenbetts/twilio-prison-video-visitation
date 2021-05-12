package fm.liveswitch;

public class ManagedTimer {
    private static ILog __log = Log.getLogger(ManagedTimer.class);
    private IAction0 __callback = null;
    private Object __lock = new Object();
    private volatile boolean __requestCancel = false;
    private ManagedThread __thread = null;
    private boolean _inTick;
    private int _interval;
    private boolean _running;

    public static int perSecondInterval(double d) {
        return (int) (1000.0d / d);
    }

    public void changeInterval(int i) {
        synchronized (this.__lock) {
            setInterval(i);
        }
    }

    private void doStopAsync(final Promise<Object> promise) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                ManagedTimer.this.stop(true);
                promise.resolve(null);
            }
        });
    }

    public int getInterval() {
        return this._interval;
    }

    public boolean getInTick() {
        return this._inTick;
    }

    public boolean getRunning() {
        return this._running;
    }

    /* access modifiers changed from: private */
    public void innerLoop(ManagedThread managedThread) {
        int elapsedMilliseconds;
        ManagedStopwatch managedStopwatch = new ManagedStopwatch();
        managedStopwatch.start();
        long j = 0;
        while (true) {
            managedThread.loopBegin();
            int interval = getInterval();
            while (true) {
                elapsedMilliseconds = (int) (managedStopwatch.getElapsedMilliseconds() - j);
                if (elapsedMilliseconds < interval) {
                    break;
                }
                if (this.__requestCancel) {
                    synchronized (this.__lock) {
                        if (this.__requestCancel) {
                            setRunning(false);
                        }
                    }
                }
                setInTick(true);
                try {
                    this.__callback.invoke();
                } catch (Exception e) {
                    __log.error("Unhandled error during timer callback.", e);
                }
                setInTick(false);
                j += (long) interval;
            }
            if (this.__requestCancel) {
                synchronized (this.__lock) {
                    if (this.__requestCancel) {
                        setRunning(false);
                        return;
                    }
                }
            }
            int i = interval - elapsedMilliseconds;
            if (i > 50) {
                ManagedThread.sleep(i);
            } else if (i > 0) {
                ManagedThread.sleep(1);
            }
            managedThread.loopEnd();
        }
    }

    public ManagedTimer(int i, IAction0 iAction0) {
        if (iAction0 != null) {
            setInterval(i);
            this.__callback = iAction0;
            return;
        }
        throw new RuntimeException(new Exception("Callback cannot be null."));
    }

    private void setInterval(int i) {
        this._interval = i;
    }

    private void setInTick(boolean z) {
        this._inTick = z;
    }

    private void setRunning(boolean z) {
        this._running = z;
    }

    public boolean start() {
        synchronized (this.__lock) {
            this.__requestCancel = false;
            if (getRunning()) {
                return false;
            }
            setRunning(true);
            ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                public String getId() {
                    return "fm.liveswitch.ManagedTimer.innerLoop";
                }

                public void invoke(ManagedThread managedThread) {
                    ManagedTimer.this.innerLoop(managedThread);
                }
            });
            this.__thread = managedThread;
            managedThread.start();
            return true;
        }
    }

    public boolean stop() {
        return stop(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        r4 = r3.__lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        if (getRunning() == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001d, code lost:
        if (r3.__requestCancel != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0022, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0023, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0024, code lost:
        if (r0 == false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0027, code lost:
        fm.liveswitch.ManagedThread.sleep(10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        if (r4 == false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean stop(boolean r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.__lock
            monitor-enter(r0)
            boolean r1 = r3.getRunning()     // Catch:{ all -> 0x0031 }
            r2 = 0
            if (r1 != 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return r2
        L_0x000c:
            r1 = 1
            r3.__requestCancel = r1     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            if (r4 == 0) goto L_0x0030
        L_0x0012:
            java.lang.Object r4 = r3.__lock
            monitor-enter(r4)
            boolean r0 = r3.getRunning()     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0022
            boolean r0 = r3.__requestCancel     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r0 = 0
            goto L_0x0023
        L_0x0022:
            r0 = 1
        L_0x0023:
            monitor-exit(r4)     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0027
            goto L_0x0030
        L_0x0027:
            r4 = 10
            fm.liveswitch.ManagedThread.sleep(r4)
            goto L_0x0012
        L_0x002d:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x002d }
            throw r0
        L_0x0030:
            return r1
        L_0x0031:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ManagedTimer.stop(boolean):boolean");
    }

    public Future<Object> stopAsync() {
        Promise promise = new Promise();
        try {
            doStopAsync(promise);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }
}
