package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class RidgeDetectionFilter extends Algorithm {
    private static native long create_0(int i, int i2, int i3, int i4, int i5, double d, double d2, int i6);

    private static native long create_1(int i, int i2, int i3, int i4, int i5, double d, double d2);

    private static native long create_2(int i, int i2, int i3, int i4, int i5, double d);

    private static native long create_3(int i, int i2, int i3, int i4, int i5);

    private static native long create_4(int i, int i2, int i3, int i4);

    private static native long create_5(int i, int i2, int i3);

    private static native long create_6(int i, int i2);

    private static native long create_7(int i);

    private static native long create_8();

    private static native void delete(long j);

    private static native void getRidgeFilteredImage_0(long j, long j2, long j3);

    protected RidgeDetectionFilter(long j) {
        super(j);
    }

    public static RidgeDetectionFilter __fromPtr__(long j) {
        return new RidgeDetectionFilter(j);
    }

    public static RidgeDetectionFilter create(int i, int i2, int i3, int i4, int i5, double d, double d2, int i6) {
        return __fromPtr__(create_0(i, i2, i3, i4, i5, d, d2, i6));
    }

    public static RidgeDetectionFilter create(int i, int i2, int i3, int i4, int i5, double d, double d2) {
        return __fromPtr__(create_1(i, i2, i3, i4, i5, d, d2));
    }

    public static RidgeDetectionFilter create(int i, int i2, int i3, int i4, int i5, double d) {
        return __fromPtr__(create_2(i, i2, i3, i4, i5, d));
    }

    public static RidgeDetectionFilter create(int i, int i2, int i3, int i4, int i5) {
        return __fromPtr__(create_3(i, i2, i3, i4, i5));
    }

    public static RidgeDetectionFilter create(int i, int i2, int i3, int i4) {
        return __fromPtr__(create_4(i, i2, i3, i4));
    }

    public static RidgeDetectionFilter create(int i, int i2, int i3) {
        return __fromPtr__(create_5(i, i2, i3));
    }

    public static RidgeDetectionFilter create(int i, int i2) {
        return __fromPtr__(create_6(i, i2));
    }

    public static RidgeDetectionFilter create(int i) {
        return __fromPtr__(create_7(i));
    }

    public static RidgeDetectionFilter create() {
        return __fromPtr__(create_8());
    }

    public void getRidgeFilteredImage(Mat mat, Mat mat2) {
        getRidgeFilteredImage_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
