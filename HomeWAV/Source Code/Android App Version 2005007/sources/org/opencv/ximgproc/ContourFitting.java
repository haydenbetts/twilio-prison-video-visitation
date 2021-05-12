package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class ContourFitting extends Algorithm {
    private static native void delete(long j);

    private static native void estimateTransformation_0(long j, long j2, long j3, long j4, double[] dArr, boolean z);

    private static native void estimateTransformation_1(long j, long j2, long j3, long j4, double[] dArr);

    private static native int getCtrSize_0(long j);

    private static native int getFDSize_0(long j);

    private static native void setCtrSize_0(long j, int i);

    private static native void setFDSize_0(long j, int i);

    protected ContourFitting(long j) {
        super(j);
    }

    public static ContourFitting __fromPtr__(long j) {
        return new ContourFitting(j);
    }

    public int getCtrSize() {
        return getCtrSize_0(this.nativeObj);
    }

    public int getFDSize() {
        return getFDSize_0(this.nativeObj);
    }

    public void estimateTransformation(Mat mat, Mat mat2, Mat mat3, double[] dArr, boolean z) {
        double[] dArr2 = new double[1];
        estimateTransformation_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, dArr2, z);
        if (dArr != null) {
            dArr[0] = dArr2[0];
        }
    }

    public void estimateTransformation(Mat mat, Mat mat2, Mat mat3, double[] dArr) {
        double[] dArr2 = new double[1];
        estimateTransformation_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, dArr2);
        if (dArr != null) {
            dArr[0] = dArr2[0];
        }
    }

    public void setCtrSize(int i) {
        setCtrSize_0(this.nativeObj, i);
    }

    public void setFDSize(int i) {
        setFDSize_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
