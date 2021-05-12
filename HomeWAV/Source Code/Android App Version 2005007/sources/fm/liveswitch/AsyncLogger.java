package fm.liveswitch;

class AsyncLogger extends AsyncLoggerBase {
    private static ManagedAutoResetEvent __event = new ManagedAutoResetEvent();
    private static ManagedConcurrentQueue<LogEvent> __logQueue = new ManagedConcurrentQueue<>();
    private static boolean __logging;
    private static ManagedThread __loopThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
        public String getId() {
            return "fm.liveswitch.AsyncLogger.logLoop";
        }

        public void invoke(ManagedThread managedThread) {
            AsyncLogger.logLoop(managedThread);
        }
    });
    private static AtomicMutex __threadLock = new AtomicMutex();
    private static boolean __threadRunning;

    public AsyncLogger(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void doQueueLogEvent(LogEvent logEvent) {
        if (LogConfiguration.getHasProviders()) {
            if (!__threadRunning && __threadLock.tryLock()) {
                if (!__threadRunning) {
                    __threadRunning = true;
                    __loopThread.start();
                }
                __threadLock.release();
            }
            __logQueue.enqueue(logEvent);
            __event.set();
        }
    }

    public void flush() {
        while (true) {
            if (__logging || !__logQueue.getIsEmpty()) {
                ManagedThread.sleep(10);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void logLoop(ManagedThread managedThread) {
        while (__threadRunning) {
            __logging = true;
            while (!__logQueue.getIsEmpty()) {
                __logQueue.getCount();
                Holder holder = new Holder(null);
                boolean tryDequeue = __logQueue.tryDequeue(holder);
                LogEvent logEvent = (LogEvent) holder.getValue();
                if (tryDequeue) {
                    AsyncLoggerBase.processLogEvent(logEvent);
                }
            }
            __logging = false;
            __event.waitOne();
        }
    }
}
