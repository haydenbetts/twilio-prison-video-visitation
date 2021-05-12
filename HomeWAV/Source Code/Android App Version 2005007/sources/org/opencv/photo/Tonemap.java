package org.opencv.photo;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class Tonemap extends Algorithm {
    private static native void delete(long j);

    private static native float getGamma_0(long j);

    private static native void process_0(long j, long j2, long j3);

    private static native void setGamma_0(long j, float f);

    protected Tonemap(long j) {
        super(j);
    }

    public static Tonemap __fromPtr__(long j) {
        return new Tonemap(j);
    }

    public float getGamma() {
        return getGamma_0(this.nativeObj);
    }

    public void process(Mat mat, Mat mat2) {
        process_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void setGamma(float f) {
        setGamma_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
