package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class LUCID extends Feature2D {
    private static native long create_0(int i, int i2);

    private static native long create_1(int i);

    private static native long create_2();

    private static native void delete(long j);

    protected LUCID(long j) {
        super(j);
    }

    public static LUCID __fromPtr__(long j) {
        return new LUCID(j);
    }

    public static LUCID create(int i, int i2) {
        return __fromPtr__(create_0(i, i2));
    }

    public static LUCID create(int i) {
        return __fromPtr__(create_1(i));
    }

    public static LUCID create() {
        return __fromPtr__(create_2());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
