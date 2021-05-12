package lib.android.paypal.com.magnessdk.network;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class i {
    private static final Object a = new Object();
    private static i b;
    private ThreadPoolExecutor c;

    private i() {
        try {
            this.c = new ThreadPoolExecutor(10, 10, 60000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(256), new ThreadPoolExecutor.DiscardPolicy());
        } catch (Exception unused) {
        }
    }

    static i a() {
        i iVar;
        synchronized (a) {
            if (b == null) {
                b = new i();
            }
            iVar = b;
        }
        return iVar;
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar) {
        this.c.execute(hVar);
    }
}
