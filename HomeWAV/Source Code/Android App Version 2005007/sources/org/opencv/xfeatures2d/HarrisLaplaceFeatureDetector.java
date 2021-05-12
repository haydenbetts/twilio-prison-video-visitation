package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class HarrisLaplaceFeatureDetector extends Feature2D {
    private static native long create_0(int i, float f, float f2, int i2, int i3);

    private static native long create_1(int i, float f, float f2, int i2);

    private static native long create_2(int i, float f, float f2);

    private static native long create_3(int i, float f);

    private static native long create_4(int i);

    private static native long create_5();

    private static native void delete(long j);

    protected HarrisLaplaceFeatureDetector(long j) {
        super(j);
    }

    public static HarrisLaplaceFeatureDetector __fromPtr__(long j) {
        return new HarrisLaplaceFeatureDetector(j);
    }

    public static HarrisLaplaceFeatureDetector create(int i, float f, float f2, int i2, int i3) {
        return __fromPtr__(create_0(i, f, f2, i2, i3));
    }

    public static HarrisLaplaceFeatureDetector create(int i, float f, float f2, int i2) {
        return __fromPtr__(create_1(i, f, f2, i2));
    }

    public static HarrisLaplaceFeatureDetector create(int i, float f, float f2) {
        return __fromPtr__(create_2(i, f, f2));
    }

    public static HarrisLaplaceFeatureDetector create(int i, float f) {
        return __fromPtr__(create_3(i, f));
    }

    public static HarrisLaplaceFeatureDetector create(int i) {
        return __fromPtr__(create_4(i));
    }

    public static HarrisLaplaceFeatureDetector create() {
        return __fromPtr__(create_5());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
