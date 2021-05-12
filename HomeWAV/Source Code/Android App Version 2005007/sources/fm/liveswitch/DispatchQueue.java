package fm.liveswitch;

import java.util.ArrayList;

public class DispatchQueue<T> implements IDispatchQueue<T> {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(DispatchQueue.class);
    /* access modifiers changed from: private */
    public IAction1<T> __action;
    /* access modifiers changed from: private */
    public boolean __active;
    /* access modifiers changed from: private */
    public ManagedCondition __condition;
    /* access modifiers changed from: private */
    public IFunction1<T, Future<Object>> __funcPromise;
    /* access modifiers changed from: private */
    public ArrayList<T> __items;
    /* access modifiers changed from: private */
    public ManagedAutoReleasePool __nestedPool;
    /* access modifiers changed from: private */
    public ManagedAutoReleasePool __pool;
    /* access modifiers changed from: private */
    public AtomicLong __queueCount;
    /* access modifiers changed from: private */
    public ManagedAutoResetEvent __resetEvent;

    public void destroy() {
    }

    private void dispatch() {
        ManagedThread.dispatch(new IAction0() {
            /* JADX WARNING: Code restructure failed: missing block: B:10:0x003e, code lost:
                r0 = r1.iterator();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
                if (r0.hasNext() == false) goto L_0x0094;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
                r1 = r0.next();
                fm.liveswitch.DispatchQueue.access$500(r4.this$0).decrement();
                fm.liveswitch.DispatchQueue.access$600(r4.this$0).loopBegin();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
                if (fm.liveswitch.DispatchQueue.access$700(r4.this$0) == null) goto L_0x0070;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
                fm.liveswitch.DispatchQueue.access$700(r4.this$0).invoke(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x0070, code lost:
                ((fm.liveswitch.Future) fm.liveswitch.DispatchQueue.access$800(r4.this$0).invoke(r1)).waitForPromise();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0080, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x0081, code lost:
                fm.liveswitch.DispatchQueue.access$900().error("Uncaught exception from dispatch queue task.", r1);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void invoke() {
                /*
                    r4 = this;
                L_0x0000:
                    fm.liveswitch.DispatchQueue r0 = fm.liveswitch.DispatchQueue.this
                    fm.liveswitch.ManagedAutoReleasePool r0 = r0.__pool
                    r0.loopBegin()
                    fm.liveswitch.DispatchQueue r0 = fm.liveswitch.DispatchQueue.this
                    fm.liveswitch.ManagedCondition r0 = r0.__condition
                    monitor-enter(r0)
                    fm.liveswitch.DispatchQueue r1 = fm.liveswitch.DispatchQueue.this     // Catch:{ all -> 0x009f }
                    java.util.ArrayList r1 = r1.__items     // Catch:{ all -> 0x009f }
                    int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x009f }
                    if (r1 != 0) goto L_0x002d
                    fm.liveswitch.DispatchQueue r1 = fm.liveswitch.DispatchQueue.this     // Catch:{ all -> 0x009f }
                    r2 = 0
                    boolean unused = r1.__active = r2     // Catch:{ all -> 0x009f }
                    fm.liveswitch.DispatchQueue r1 = fm.liveswitch.DispatchQueue.this     // Catch:{ all -> 0x009f }
                    fm.liveswitch.ManagedAutoResetEvent r1 = r1.__resetEvent     // Catch:{ all -> 0x009f }
                    r1.set()     // Catch:{ all -> 0x009f }
                    monitor-exit(r0)     // Catch:{ all -> 0x009f }
                    return
                L_0x002d:
                    fm.liveswitch.DispatchQueue r1 = fm.liveswitch.DispatchQueue.this     // Catch:{ all -> 0x009f }
                    java.util.ArrayList r1 = r1.__items     // Catch:{ all -> 0x009f }
                    fm.liveswitch.DispatchQueue r2 = fm.liveswitch.DispatchQueue.this     // Catch:{ all -> 0x009f }
                    java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x009f }
                    r3.<init>()     // Catch:{ all -> 0x009f }
                    java.util.ArrayList unused = r2.__items = r3     // Catch:{ all -> 0x009f }
                    monitor-exit(r0)     // Catch:{ all -> 0x009f }
                    java.util.Iterator r0 = r1.iterator()
                L_0x0042:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L_0x0094
                    java.lang.Object r1 = r0.next()
                    fm.liveswitch.DispatchQueue r2 = fm.liveswitch.DispatchQueue.this
                    fm.liveswitch.AtomicLong r2 = r2.__queueCount
                    r2.decrement()
                    fm.liveswitch.DispatchQueue r2 = fm.liveswitch.DispatchQueue.this
                    fm.liveswitch.ManagedAutoReleasePool r2 = r2.__nestedPool
                    r2.loopBegin()
                    fm.liveswitch.DispatchQueue r2 = fm.liveswitch.DispatchQueue.this     // Catch:{ Exception -> 0x0080 }
                    fm.liveswitch.IAction1 r2 = r2.__action     // Catch:{ Exception -> 0x0080 }
                    if (r2 == 0) goto L_0x0070
                    fm.liveswitch.DispatchQueue r2 = fm.liveswitch.DispatchQueue.this     // Catch:{ Exception -> 0x0080 }
                    fm.liveswitch.IAction1 r2 = r2.__action     // Catch:{ Exception -> 0x0080 }
                    r2.invoke(r1)     // Catch:{ Exception -> 0x0080 }
                    goto L_0x008a
                L_0x0070:
                    fm.liveswitch.DispatchQueue r2 = fm.liveswitch.DispatchQueue.this     // Catch:{ Exception -> 0x0080 }
                    fm.liveswitch.IFunction1 r2 = r2.__funcPromise     // Catch:{ Exception -> 0x0080 }
                    java.lang.Object r1 = r2.invoke(r1)     // Catch:{ Exception -> 0x0080 }
                    fm.liveswitch.Future r1 = (fm.liveswitch.Future) r1     // Catch:{ Exception -> 0x0080 }
                    r1.waitForPromise()     // Catch:{ Exception -> 0x0080 }
                    goto L_0x008a
                L_0x0080:
                    r1 = move-exception
                    fm.liveswitch.ILog r2 = fm.liveswitch.DispatchQueue.__log
                    java.lang.String r3 = "Uncaught exception from dispatch queue task."
                    r2.error((java.lang.String) r3, (java.lang.Exception) r1)
                L_0x008a:
                    fm.liveswitch.DispatchQueue r1 = fm.liveswitch.DispatchQueue.this
                    fm.liveswitch.ManagedAutoReleasePool r1 = r1.__nestedPool
                    r1.loopEnd()
                    goto L_0x0042
                L_0x0094:
                    fm.liveswitch.DispatchQueue r0 = fm.liveswitch.DispatchQueue.this
                    fm.liveswitch.ManagedAutoReleasePool r0 = r0.__pool
                    r0.loopEnd()
                    goto L_0x0000
                L_0x009f:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x009f }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.DispatchQueue.AnonymousClass1.invoke():void");
            }
        });
    }

    private DispatchQueue() {
        this.__items = new ArrayList<>();
        this.__condition = new ManagedCondition();
        this.__active = false;
        this.__queueCount = new AtomicLong();
        this.__resetEvent = new ManagedAutoResetEvent();
        this.__pool = new ManagedAutoReleasePool();
        this.__nestedPool = new ManagedAutoReleasePool();
    }

    public DispatchQueue(IAction1<T> iAction1) {
        this();
        this.__action = iAction1;
    }

    public DispatchQueue(IFunction1<T, Future<Object>> iFunction1) {
        this();
        this.__funcPromise = iFunction1;
    }

    public void enqueue(T t) {
        boolean z;
        this.__queueCount.increment();
        synchronized (this.__condition) {
            this.__items.add(t);
            z = true;
            if (!this.__active) {
                this.__active = true;
            } else {
                z = false;
            }
        }
        if (z) {
            dispatch();
        }
    }

    public long getQueueCount() {
        return this.__queueCount.getValue();
    }

    public void waitForDrain() {
        while (getQueueCount() != 0) {
            this.__resetEvent.waitOne();
        }
    }
}
