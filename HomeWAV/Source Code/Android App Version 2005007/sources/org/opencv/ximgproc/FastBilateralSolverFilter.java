package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class FastBilateralSolverFilter extends Algorithm {
    private static native void delete(long j);

    private static native void filter_0(long j, long j2, long j3, long j4);

    protected FastBilateralSolverFilter(long j) {
        super(j);
    }

    public static FastBilateralSolverFilter __fromPtr__(long j) {
        return new FastBilateralSolverFilter(j);
    }

    public void filter(Mat mat, Mat mat2, Mat mat3) {
        filter_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
