package org.bytedeco.javacv;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.bytedeco.ffmpeg.avcodec.Cb_PointerPointer_int;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;

public class FFmpegLockCallback {
    private static boolean initialized = false;
    /* access modifiers changed from: private */
    public static HashMap<Integer, Lock> lockArray = new HashMap<>();
    private static Cb_PointerPointer_int lockCallback = ((Cb_PointerPointer_int) new Cb_PointerPointer_int() {
        public int call(@Cast({"void**"}) PointerPointer pointerPointer, @Cast({"AVLockOp"}) int i) {
            if (i == 0) {
                int incrementAndGet = FFmpegLockCallback.lockCounter.incrementAndGet();
                new IntPointer((Pointer) pointerPointer).put(0, incrementAndGet);
                FFmpegLockCallback.lockArray.put(Integer.valueOf(incrementAndGet), new ReentrantLock());
                return 0;
            } else if (i == 1) {
                Lock lock = (Lock) FFmpegLockCallback.lockArray.get(Integer.valueOf(new IntPointer((Pointer) pointerPointer).get(0)));
                if (lock == null) {
                    System.err.println("Lock not found!");
                    return -1;
                }
                lock.lock();
                return 0;
            } else if (i == 2) {
                Lock lock2 = (Lock) FFmpegLockCallback.lockArray.get(Integer.valueOf(new IntPointer((Pointer) pointerPointer).get(0)));
                if (lock2 == null) {
                    System.err.println("Lock not found!");
                    return -1;
                }
                lock2.unlock();
                return 0;
            } else if (i != 3) {
                return -1;
            } else {
                FFmpegLockCallback.lockArray.remove(Integer.valueOf(new IntPointer((Pointer) pointerPointer).get(0)));
                pointerPointer.put(0, (Pointer) null);
                return 0;
            }
        }
    }.retainReference());
    /* access modifiers changed from: private */
    public static AtomicInteger lockCounter = new AtomicInteger(0);

    public static synchronized void init() {
        synchronized (FFmpegLockCallback.class) {
            if (!initialized) {
                initialized = true;
                avcodec.av_lockmgr_register(lockCallback);
            }
        }
    }
}
