package org.opencv.xfeatures2d;

import org.opencv.core.MatOfInt;
import org.opencv.features2d.Feature2D;

public class FREAK extends Feature2D {
    private static native long create_0(boolean z, boolean z2, float f, int i, long j);

    private static native long create_1(boolean z, boolean z2, float f, int i);

    private static native long create_2(boolean z, boolean z2, float f);

    private static native long create_3(boolean z, boolean z2);

    private static native long create_4(boolean z);

    private static native long create_5();

    private static native void delete(long j);

    protected FREAK(long j) {
        super(j);
    }

    public static FREAK __fromPtr__(long j) {
        return new FREAK(j);
    }

    public static FREAK create(boolean z, boolean z2, float f, int i, MatOfInt matOfInt) {
        return __fromPtr__(create_0(z, z2, f, i, matOfInt.nativeObj));
    }

    public static FREAK create(boolean z, boolean z2, float f, int i) {
        return __fromPtr__(create_1(z, z2, f, i));
    }

    public static FREAK create(boolean z, boolean z2, float f) {
        return __fromPtr__(create_2(z, z2, f));
    }

    public static FREAK create(boolean z, boolean z2) {
        return __fromPtr__(create_3(z, z2));
    }

    public static FREAK create(boolean z) {
        return __fromPtr__(create_4(z));
    }

    public static FREAK create() {
        return __fromPtr__(create_5());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
