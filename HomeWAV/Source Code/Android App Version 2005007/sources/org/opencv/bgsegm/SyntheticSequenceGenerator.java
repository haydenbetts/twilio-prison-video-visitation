package org.opencv.bgsegm;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class SyntheticSequenceGenerator extends Algorithm {
    private static native long SyntheticSequenceGenerator_0(long j, long j2, double d, double d2, double d3, double d4);

    private static native void delete(long j);

    private static native void getNextFrame_0(long j, long j2, long j3);

    protected SyntheticSequenceGenerator(long j) {
        super(j);
    }

    public static SyntheticSequenceGenerator __fromPtr__(long j) {
        return new SyntheticSequenceGenerator(j);
    }

    public SyntheticSequenceGenerator(Mat mat, Mat mat2, double d, double d2, double d3, double d4) {
        super(SyntheticSequenceGenerator_0(mat.nativeObj, mat2.nativeObj, d, d2, d3, d4));
    }

    public void getNextFrame(Mat mat, Mat mat2) {
        getNextFrame_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
