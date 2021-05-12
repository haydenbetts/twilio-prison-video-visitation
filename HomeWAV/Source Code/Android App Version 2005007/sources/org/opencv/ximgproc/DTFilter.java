package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class DTFilter extends Algorithm {
    private static native void delete(long j);

    private static native void filter_0(long j, long j2, long j3, int i);

    private static native void filter_1(long j, long j2, long j3);

    protected DTFilter(long j) {
        super(j);
    }

    public static DTFilter __fromPtr__(long j) {
        return new DTFilter(j);
    }

    public void filter(Mat mat, Mat mat2, int i) {
        filter_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, i);
    }

    public void filter(Mat mat, Mat mat2) {
        filter_1(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
