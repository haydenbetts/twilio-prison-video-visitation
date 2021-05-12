package org.opencv.img_hash;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class ImgHashBase extends Algorithm {
    private static native double compare_0(long j, long j2, long j3);

    private static native void compute_0(long j, long j2, long j3);

    private static native void delete(long j);

    protected ImgHashBase(long j) {
        super(j);
    }

    public static ImgHashBase __fromPtr__(long j) {
        return new ImgHashBase(j);
    }

    public double compare(Mat mat, Mat mat2) {
        return compare_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void compute(Mat mat, Mat mat2) {
        compute_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
