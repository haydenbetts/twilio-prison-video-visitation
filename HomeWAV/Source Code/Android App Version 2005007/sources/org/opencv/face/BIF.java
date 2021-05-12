package org.opencv.face;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class BIF extends Algorithm {
    private static native void compute_0(long j, long j2, long j3);

    private static native long create_0(int i, int i2);

    private static native long create_1(int i);

    private static native long create_2();

    private static native void delete(long j);

    private static native int getNumBands_0(long j);

    private static native int getNumRotations_0(long j);

    protected BIF(long j) {
        super(j);
    }

    public static BIF __fromPtr__(long j) {
        return new BIF(j);
    }

    public static BIF create(int i, int i2) {
        return __fromPtr__(create_0(i, i2));
    }

    public static BIF create(int i) {
        return __fromPtr__(create_1(i));
    }

    public static BIF create() {
        return __fromPtr__(create_2());
    }

    public int getNumBands() {
        return getNumBands_0(this.nativeObj);
    }

    public int getNumRotations() {
        return getNumRotations_0(this.nativeObj);
    }

    public void compute(Mat mat, Mat mat2) {
        compute_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
