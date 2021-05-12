package org.bytedeco.javacv;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallel {
    public static final String NUM_THREADS = "org.bytedeco.javacv.numthreads";
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public interface Looper {
        void loop(int i, int i2, int i3);
    }

    public static int getNumThreads() {
        try {
            String property = System.getProperty(NUM_THREADS);
            if (property != null) {
                return Integer.valueOf(property).intValue();
            }
            return getNumCores();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setNumThreads(int i) {
        System.setProperty(NUM_THREADS, Integer.toString(i));
    }

    public static int getNumCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static void run(Runnable... runnableArr) {
        if (runnableArr.length == 1) {
            runnableArr[0].run();
            return;
        }
        int length = runnableArr.length;
        Future[] futureArr = new Future[length];
        for (int i = 0; i < runnableArr.length; i++) {
            futureArr[i] = threadPool.submit(runnableArr[i]);
        }
        Throwable th = null;
        int i2 = 0;
        while (i2 < length) {
            try {
                Future future = futureArr[i2];
                if (!future.isDone()) {
                    future.get();
                }
                i2++;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (th != null) {
            for (int i3 = 0; i3 < length; i3++) {
                futureArr[i3].cancel(true);
            }
            throw new RuntimeException(th);
        }
    }

    public static void loop(int i, int i2, Looper looper) {
        loop(i, i2, getNumThreads(), looper);
    }

    public static void loop(int i, int i2, int i3, final Looper looper) {
        int i4 = i2 - i;
        if (i3 <= 0) {
            i3 = getNumCores();
        }
        int min = Math.min(i4, i3);
        Runnable[] runnableArr = new Runnable[min];
        final int i5 = 0;
        while (i5 < min) {
            final int i6 = ((i4 * i5) / min) + i;
            int i7 = i5 + 1;
            final int i8 = ((i4 * i7) / min) + i;
            runnableArr[i5] = new Runnable() {
                public void run() {
                    looper.loop(i6, i8, i5);
                }
            };
            i5 = i7;
        }
        run(runnableArr);
    }
}
