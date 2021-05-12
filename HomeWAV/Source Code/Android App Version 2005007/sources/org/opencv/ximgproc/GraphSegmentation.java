package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class GraphSegmentation extends Algorithm {
    private static native void delete(long j);

    private static native float getK_0(long j);

    private static native int getMinSize_0(long j);

    private static native double getSigma_0(long j);

    private static native void processImage_0(long j, long j2, long j3);

    private static native void setK_0(long j, float f);

    private static native void setMinSize_0(long j, int i);

    private static native void setSigma_0(long j, double d);

    protected GraphSegmentation(long j) {
        super(j);
    }

    public static GraphSegmentation __fromPtr__(long j) {
        return new GraphSegmentation(j);
    }

    public double getSigma() {
        return getSigma_0(this.nativeObj);
    }

    public float getK() {
        return getK_0(this.nativeObj);
    }

    public int getMinSize() {
        return getMinSize_0(this.nativeObj);
    }

    public void processImage(Mat mat, Mat mat2) {
        processImage_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void setK(float f) {
        setK_0(this.nativeObj, f);
    }

    public void setMinSize(int i) {
        setMinSize_0(this.nativeObj, i);
    }

    public void setSigma(double d) {
        setSigma_0(this.nativeObj, d);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
