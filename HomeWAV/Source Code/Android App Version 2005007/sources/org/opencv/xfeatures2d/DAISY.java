package org.opencv.xfeatures2d;

import org.opencv.core.Mat;
import org.opencv.features2d.Feature2D;

public class DAISY extends Feature2D {
    public static final int NRM_FULL = 102;
    public static final int NRM_NONE = 100;
    public static final int NRM_PARTIAL = 101;
    public static final int NRM_SIFT = 103;

    private static native long create_0(float f, int i, int i2, int i3, long j, boolean z, boolean z2);

    private static native long create_1(float f, int i, int i2, int i3, long j, boolean z);

    private static native long create_2(float f, int i, int i2, int i3, long j);

    private static native long create_3(float f, int i, int i2, int i3);

    private static native long create_5(float f, int i, int i2);

    private static native long create_6(float f, int i);

    private static native long create_7(float f);

    private static native long create_8();

    private static native void delete(long j);

    protected DAISY(long j) {
        super(j);
    }

    public static DAISY __fromPtr__(long j) {
        return new DAISY(j);
    }

    public static DAISY create(float f, int i, int i2, int i3, Mat mat, boolean z, boolean z2) {
        return __fromPtr__(create_0(f, i, i2, i3, mat.nativeObj, z, z2));
    }

    public static DAISY create(float f, int i, int i2, int i3, Mat mat, boolean z) {
        return __fromPtr__(create_1(f, i, i2, i3, mat.nativeObj, z));
    }

    public static DAISY create(float f, int i, int i2, int i3, Mat mat) {
        return __fromPtr__(create_2(f, i, i2, i3, mat.nativeObj));
    }

    public static DAISY create(float f, int i, int i2, int i3) {
        return __fromPtr__(create_3(f, i, i2, i3));
    }

    public static DAISY create(float f, int i, int i2) {
        return __fromPtr__(create_5(f, i, i2));
    }

    public static DAISY create(float f, int i) {
        return __fromPtr__(create_6(f, i));
    }

    public static DAISY create(float f) {
        return __fromPtr__(create_7(f));
    }

    public static DAISY create() {
        return __fromPtr__(create_8());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
