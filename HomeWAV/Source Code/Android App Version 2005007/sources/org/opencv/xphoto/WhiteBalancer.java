package org.opencv.xphoto;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class WhiteBalancer extends Algorithm {
    private static native void balanceWhite_0(long j, long j2, long j3);

    private static native void delete(long j);

    protected WhiteBalancer(long j) {
        super(j);
    }

    public static WhiteBalancer __fromPtr__(long j) {
        return new WhiteBalancer(j);
    }

    public void balanceWhite(Mat mat, Mat mat2) {
        balanceWhite_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
