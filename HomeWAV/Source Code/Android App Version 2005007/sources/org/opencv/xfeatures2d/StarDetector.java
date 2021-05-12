package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class StarDetector extends Feature2D {
    private static native long create_0(int i, int i2, int i3, int i4, int i5);

    private static native long create_1(int i, int i2, int i3, int i4);

    private static native long create_2(int i, int i2, int i3);

    private static native long create_3(int i, int i2);

    private static native long create_4(int i);

    private static native long create_5();

    private static native void delete(long j);

    protected StarDetector(long j) {
        super(j);
    }

    public static StarDetector __fromPtr__(long j) {
        return new StarDetector(j);
    }

    public static StarDetector create(int i, int i2, int i3, int i4, int i5) {
        return __fromPtr__(create_0(i, i2, i3, i4, i5));
    }

    public static StarDetector create(int i, int i2, int i3, int i4) {
        return __fromPtr__(create_1(i, i2, i3, i4));
    }

    public static StarDetector create(int i, int i2, int i3) {
        return __fromPtr__(create_2(i, i2, i3));
    }

    public static StarDetector create(int i, int i2) {
        return __fromPtr__(create_3(i, i2));
    }

    public static StarDetector create(int i) {
        return __fromPtr__(create_4(i));
    }

    public static StarDetector create() {
        return __fromPtr__(create_5());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
