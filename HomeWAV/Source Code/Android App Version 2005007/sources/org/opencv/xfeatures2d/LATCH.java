package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class LATCH extends Feature2D {
    private static native long create_0(int i, boolean z, int i2, double d);

    private static native long create_1(int i, boolean z, int i2);

    private static native long create_2(int i, boolean z);

    private static native long create_3(int i);

    private static native long create_4();

    private static native void delete(long j);

    protected LATCH(long j) {
        super(j);
    }

    public static LATCH __fromPtr__(long j) {
        return new LATCH(j);
    }

    public static LATCH create(int i, boolean z, int i2, double d) {
        return __fromPtr__(create_0(i, z, i2, d));
    }

    public static LATCH create(int i, boolean z, int i2) {
        return __fromPtr__(create_1(i, z, i2));
    }

    public static LATCH create(int i, boolean z) {
        return __fromPtr__(create_2(i, z));
    }

    public static LATCH create(int i) {
        return __fromPtr__(create_3(i));
    }

    public static LATCH create() {
        return __fromPtr__(create_4());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
