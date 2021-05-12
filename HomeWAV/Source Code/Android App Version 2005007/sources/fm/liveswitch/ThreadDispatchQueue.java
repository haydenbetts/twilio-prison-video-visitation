package fm.liveswitch;

public class ThreadDispatchQueue<T> implements IDispatchQueue<T> {
    private static ILog __log = Log.getLogger(ThreadDispatchQueue.class);
    private IAction1<T> __action;
    private volatile boolean __completed;
    private ManagedAutoResetEvent __itemAdded = new ManagedAutoResetEvent();
    private ManagedConcurrentQueue<T> __items = new ManagedConcurrentQueue<>();
    private ManagedThread __thread;
    private ManagedAutoResetEvent __threadExited = new ManagedAutoResetEvent();

    public void destroy() {
        this.__completed = true;
    }

    public void enqueue(T t) {
        if (!this.__completed) {
            this.__items.enqueue(t);
            this.__itemAdded.set();
        }
    }

    public long getQueueCount() {
        return (long) this.__items.getCount();
    }

    /* access modifiers changed from: private */
    public void loop(ManagedThread managedThread) {
        Object obj = null;
        while (true) {
            if (!this.__completed || !this.__items.getIsEmpty()) {
                Holder holder = new Holder(obj);
                boolean tryDequeue = this.__items.tryDequeue(holder);
                Object value = holder.getValue();
                if (tryDequeue) {
                    try {
                        this.__action.invoke(value);
                    } catch (Exception e) {
                        __log.error("Exception thrown processing dispatch queue item.", e);
                    }
                } else {
                    this.__itemAdded.waitOne();
                }
                obj = value;
            } else {
                this.__threadExited.set();
                return;
            }
        }
    }

    public ThreadDispatchQueue(IAction1<T> iAction1) {
        this.__action = iAction1;
        ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
            public String getId() {
                return "fm.liveswitch.ThreadDispatchQueue<T>.loop";
            }

            public void invoke(ManagedThread managedThread) {
                ThreadDispatchQueue.this.loop(managedThread);
            }
        });
        this.__thread = managedThread;
        managedThread.start();
    }

    public void waitForCompletion() {
        this.__threadExited.waitOne();
    }
}
