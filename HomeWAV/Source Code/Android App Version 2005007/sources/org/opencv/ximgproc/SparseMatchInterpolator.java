package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class SparseMatchInterpolator extends Algorithm {
    private static native void delete(long j);

    private static native void interpolate_0(long j, long j2, long j3, long j4, long j5, long j6);

    protected SparseMatchInterpolator(long j) {
        super(j);
    }

    public static SparseMatchInterpolator __fromPtr__(long j) {
        return new SparseMatchInterpolator(j);
    }

    public void interpolate(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        interpolate_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
