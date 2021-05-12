package fm.liveswitch.android;

import android.os.Handler;
import android.os.Looper;
import fm.liveswitch.IAction0;

public class Utility {

    interface RunnableWithException extends Runnable {
        Exception getException();
    }

    public static void dispatchToMainThread(IAction0 iAction0) {
        dispatchToMainThread(iAction0, false);
    }

    public static void dispatchToMainThread(final IAction0 iAction0, final boolean z) {
        Exception exception;
        final Object obj = new Object();
        AnonymousClass1 r1 = new RunnableWithException() {
            private Exception exception;

            public Exception getException() {
                return this.exception;
            }

            public void run() {
                try {
                    iAction0.invoke();
                } catch (Exception e) {
                    this.exception = e;
                }
                if (z) {
                    synchronized (obj) {
                        obj.notify();
                    }
                }
            }
        };
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper.getThread() == Thread.currentThread()) {
            r1.run();
            z = true;
        } else {
            synchronized (obj) {
                new Handler(mainLooper).post(r1);
                if (z) {
                    try {
                        obj.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (z && (exception = r1.getException()) != null) {
            throw new RuntimeException(exception);
        }
    }
}
