package org.opencv.tracking;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Rect2d;

public class Tracker extends Algorithm {
    private static native void delete(long j);

    private static native boolean init_0(long j, long j2, double d, double d2, double d3, double d4);

    private static native boolean update_0(long j, long j2, double[] dArr);

    protected Tracker(long j) {
        super(j);
    }

    public static Tracker __fromPtr__(long j) {
        return new Tracker(j);
    }

    public boolean init(Mat mat, Rect2d rect2d) {
        return init_0(this.nativeObj, mat.nativeObj, rect2d.x, rect2d.y, rect2d.width, rect2d.height);
    }

    public boolean update(Mat mat, Rect2d rect2d) {
        double[] dArr = new double[4];
        boolean update_0 = update_0(this.nativeObj, mat.nativeObj, dArr);
        if (rect2d != null) {
            rect2d.x = dArr[0];
            rect2d.y = dArr[1];
            rect2d.width = dArr[2];
            rect2d.height = dArr[3];
        }
        return update_0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
