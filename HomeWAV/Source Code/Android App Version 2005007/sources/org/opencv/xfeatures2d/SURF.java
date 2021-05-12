package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class SURF extends Feature2D {
    private static native long create_0(double d, int i, int i2, boolean z, boolean z2);

    private static native long create_1(double d, int i, int i2, boolean z);

    private static native long create_2(double d, int i, int i2);

    private static native long create_3(double d, int i);

    private static native long create_4(double d);

    private static native long create_5();

    private static native void delete(long j);

    private static native boolean getExtended_0(long j);

    private static native double getHessianThreshold_0(long j);

    private static native int getNOctaveLayers_0(long j);

    private static native int getNOctaves_0(long j);

    private static native boolean getUpright_0(long j);

    private static native void setExtended_0(long j, boolean z);

    private static native void setHessianThreshold_0(long j, double d);

    private static native void setNOctaveLayers_0(long j, int i);

    private static native void setNOctaves_0(long j, int i);

    private static native void setUpright_0(long j, boolean z);

    protected SURF(long j) {
        super(j);
    }

    public static SURF __fromPtr__(long j) {
        return new SURF(j);
    }

    public static SURF create(double d, int i, int i2, boolean z, boolean z2) {
        return __fromPtr__(create_0(d, i, i2, z, z2));
    }

    public static SURF create(double d, int i, int i2, boolean z) {
        return __fromPtr__(create_1(d, i, i2, z));
    }

    public static SURF create(double d, int i, int i2) {
        return __fromPtr__(create_2(d, i, i2));
    }

    public static SURF create(double d, int i) {
        return __fromPtr__(create_3(d, i));
    }

    public static SURF create(double d) {
        return __fromPtr__(create_4(d));
    }

    public static SURF create() {
        return __fromPtr__(create_5());
    }

    public boolean getExtended() {
        return getExtended_0(this.nativeObj);
    }

    public boolean getUpright() {
        return getUpright_0(this.nativeObj);
    }

    public double getHessianThreshold() {
        return getHessianThreshold_0(this.nativeObj);
    }

    public int getNOctaveLayers() {
        return getNOctaveLayers_0(this.nativeObj);
    }

    public int getNOctaves() {
        return getNOctaves_0(this.nativeObj);
    }

    public void setExtended(boolean z) {
        setExtended_0(this.nativeObj, z);
    }

    public void setHessianThreshold(double d) {
        setHessianThreshold_0(this.nativeObj, d);
    }

    public void setNOctaveLayers(int i) {
        setNOctaveLayers_0(this.nativeObj, i);
    }

    public void setNOctaves(int i) {
        setNOctaves_0(this.nativeObj, i);
    }

    public void setUpright(boolean z) {
        setUpright_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
