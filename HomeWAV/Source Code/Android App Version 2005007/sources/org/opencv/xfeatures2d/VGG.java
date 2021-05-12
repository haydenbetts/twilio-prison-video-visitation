package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class VGG extends Feature2D {
    private static native long create_0(int i, float f, boolean z, boolean z2, float f2, boolean z3);

    private static native long create_1(int i, float f, boolean z, boolean z2, float f2);

    private static native long create_2(int i, float f, boolean z, boolean z2);

    private static native long create_3(int i, float f, boolean z);

    private static native long create_4(int i, float f);

    private static native long create_5(int i);

    private static native long create_6();

    private static native void delete(long j);

    private static native float getScaleFactor_0(long j);

    private static native float getSigma_0(long j);

    private static native boolean getUseNormalizeDescriptor_0(long j);

    private static native boolean getUseNormalizeImage_0(long j);

    private static native boolean getUseScaleOrientation_0(long j);

    private static native void setScaleFactor_0(long j, float f);

    private static native void setSigma_0(long j, float f);

    private static native void setUseNormalizeDescriptor_0(long j, boolean z);

    private static native void setUseNormalizeImage_0(long j, boolean z);

    private static native void setUseScaleOrientation_0(long j, boolean z);

    protected VGG(long j) {
        super(j);
    }

    public static VGG __fromPtr__(long j) {
        return new VGG(j);
    }

    public static VGG create(int i, float f, boolean z, boolean z2, float f2, boolean z3) {
        return __fromPtr__(create_0(i, f, z, z2, f2, z3));
    }

    public static VGG create(int i, float f, boolean z, boolean z2, float f2) {
        return __fromPtr__(create_1(i, f, z, z2, f2));
    }

    public static VGG create(int i, float f, boolean z, boolean z2) {
        return __fromPtr__(create_2(i, f, z, z2));
    }

    public static VGG create(int i, float f, boolean z) {
        return __fromPtr__(create_3(i, f, z));
    }

    public static VGG create(int i, float f) {
        return __fromPtr__(create_4(i, f));
    }

    public static VGG create(int i) {
        return __fromPtr__(create_5(i));
    }

    public static VGG create() {
        return __fromPtr__(create_6());
    }

    public boolean getUseNormalizeDescriptor() {
        return getUseNormalizeDescriptor_0(this.nativeObj);
    }

    public boolean getUseNormalizeImage() {
        return getUseNormalizeImage_0(this.nativeObj);
    }

    public boolean getUseScaleOrientation() {
        return getUseScaleOrientation_0(this.nativeObj);
    }

    public float getScaleFactor() {
        return getScaleFactor_0(this.nativeObj);
    }

    public float getSigma() {
        return getSigma_0(this.nativeObj);
    }

    public void setScaleFactor(float f) {
        setScaleFactor_0(this.nativeObj, f);
    }

    public void setSigma(float f) {
        setSigma_0(this.nativeObj, f);
    }

    public void setUseNormalizeDescriptor(boolean z) {
        setUseNormalizeDescriptor_0(this.nativeObj, z);
    }

    public void setUseNormalizeImage(boolean z) {
        setUseNormalizeImage_0(this.nativeObj, z);
    }

    public void setUseScaleOrientation(boolean z) {
        setUseScaleOrientation_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
