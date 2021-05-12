package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class BoostDesc extends Feature2D {
    private static native long create_0(int i, boolean z, float f);

    private static native long create_1(int i, boolean z);

    private static native long create_2(int i);

    private static native long create_3();

    private static native void delete(long j);

    private static native float getScaleFactor_0(long j);

    private static native boolean getUseScaleOrientation_0(long j);

    private static native void setScaleFactor_0(long j, float f);

    private static native void setUseScaleOrientation_0(long j, boolean z);

    protected BoostDesc(long j) {
        super(j);
    }

    public static BoostDesc __fromPtr__(long j) {
        return new BoostDesc(j);
    }

    public static BoostDesc create(int i, boolean z, float f) {
        return __fromPtr__(create_0(i, z, f));
    }

    public static BoostDesc create(int i, boolean z) {
        return __fromPtr__(create_1(i, z));
    }

    public static BoostDesc create(int i) {
        return __fromPtr__(create_2(i));
    }

    public static BoostDesc create() {
        return __fromPtr__(create_3());
    }

    public boolean getUseScaleOrientation() {
        return getUseScaleOrientation_0(this.nativeObj);
    }

    public float getScaleFactor() {
        return getScaleFactor_0(this.nativeObj);
    }

    public void setScaleFactor(float f) {
        setScaleFactor_0(this.nativeObj, f);
    }

    public void setUseScaleOrientation(boolean z) {
        setUseScaleOrientation_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
