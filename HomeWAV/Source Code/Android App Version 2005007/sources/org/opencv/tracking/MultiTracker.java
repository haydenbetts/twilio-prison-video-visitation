package org.opencv.tracking;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect2d;
import org.opencv.core.Rect2d;

public class MultiTracker extends Algorithm {
    private static native long MultiTracker_0();

    private static native boolean add_0(long j, long j2, long j3, double d, double d2, double d3, double d4);

    private static native long create_0();

    private static native void delete(long j);

    private static native long getObjects_0(long j);

    private static native boolean update_0(long j, long j2, long j3);

    protected MultiTracker(long j) {
        super(j);
    }

    public static MultiTracker __fromPtr__(long j) {
        return new MultiTracker(j);
    }

    public MultiTracker() {
        super(MultiTracker_0());
    }

    public static MultiTracker create() {
        return __fromPtr__(create_0());
    }

    public boolean add(Tracker tracker, Mat mat, Rect2d rect2d) {
        Rect2d rect2d2 = rect2d;
        return add_0(this.nativeObj, tracker.getNativeObjAddr(), mat.nativeObj, rect2d2.x, rect2d2.y, rect2d2.width, rect2d2.height);
    }

    public boolean update(Mat mat, MatOfRect2d matOfRect2d) {
        return update_0(this.nativeObj, mat.nativeObj, matOfRect2d.nativeObj);
    }

    public MatOfRect2d getObjects() {
        return MatOfRect2d.fromNativeAddr(getObjects_0(this.nativeObj));
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
