package fm.liveswitch;

import java.util.concurrent.ForkJoinPool;

public class ManagedThread {
    private static ForkJoinPool threadPool = new ForkJoinPool();
    private ManagedAutoReleasePool pool = new ManagedAutoReleasePool();
    private Thread thread;

    public ManagedThread(final IAction1<ManagedThread> iAction1) {
        AnonymousClass1 r0 = new Thread() {
            public void run() {
                iAction1.invoke(this);
            }
        };
        this.thread = r0;
        r0.setDaemon(true);
    }

    public void start() {
        this.thread.start();
    }

    public void loopBegin() {
        this.pool.loopBegin();
    }

    public void loopEnd() {
        this.pool.loopEnd();
    }

    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static void sleep(int i) {
        try {
            Thread.sleep((long) i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void dispatch(final IAction0 iAction0) {
        threadPool.execute(new Runnable() {
            public void run() {
                iAction0.invoke();
            }
        });
    }
}
