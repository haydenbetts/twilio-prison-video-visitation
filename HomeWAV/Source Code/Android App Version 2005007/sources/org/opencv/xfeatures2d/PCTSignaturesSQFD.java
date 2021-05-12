package org.opencv.xfeatures2d;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.utils.Converters;

public class PCTSignaturesSQFD extends Algorithm {
    private static native float computeQuadraticFormDistance_0(long j, long j2, long j3);

    private static native void computeQuadraticFormDistances_0(long j, long j2, long j3, long j4);

    private static native long create_0(int i, int i2, float f);

    private static native long create_1(int i, int i2);

    private static native long create_2(int i);

    private static native long create_3();

    private static native void delete(long j);

    protected PCTSignaturesSQFD(long j) {
        super(j);
    }

    public static PCTSignaturesSQFD __fromPtr__(long j) {
        return new PCTSignaturesSQFD(j);
    }

    public static PCTSignaturesSQFD create(int i, int i2, float f) {
        return __fromPtr__(create_0(i, i2, f));
    }

    public static PCTSignaturesSQFD create(int i, int i2) {
        return __fromPtr__(create_1(i, i2));
    }

    public static PCTSignaturesSQFD create(int i) {
        return __fromPtr__(create_2(i));
    }

    public static PCTSignaturesSQFD create() {
        return __fromPtr__(create_3());
    }

    public float computeQuadraticFormDistance(Mat mat, Mat mat2) {
        return computeQuadraticFormDistance_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void computeQuadraticFormDistances(Mat mat, List<Mat> list, MatOfFloat matOfFloat) {
        computeQuadraticFormDistances_0(this.nativeObj, mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, matOfFloat.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
