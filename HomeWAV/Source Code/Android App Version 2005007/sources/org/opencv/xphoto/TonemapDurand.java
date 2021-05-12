package org.opencv.xphoto;

import org.opencv.photo.Tonemap;

public class TonemapDurand extends Tonemap {
    private static native void delete(long j);

    private static native float getContrast_0(long j);

    private static native float getSaturation_0(long j);

    private static native float getSigmaColor_0(long j);

    private static native float getSigmaSpace_0(long j);

    private static native void setContrast_0(long j, float f);

    private static native void setSaturation_0(long j, float f);

    private static native void setSigmaColor_0(long j, float f);

    private static native void setSigmaSpace_0(long j, float f);

    protected TonemapDurand(long j) {
        super(j);
    }

    public static TonemapDurand __fromPtr__(long j) {
        return new TonemapDurand(j);
    }

    public float getContrast() {
        return getContrast_0(this.nativeObj);
    }

    public float getSaturation() {
        return getSaturation_0(this.nativeObj);
    }

    public float getSigmaColor() {
        return getSigmaColor_0(this.nativeObj);
    }

    public float getSigmaSpace() {
        return getSigmaSpace_0(this.nativeObj);
    }

    public void setContrast(float f) {
        setContrast_0(this.nativeObj, f);
    }

    public void setSaturation(float f) {
        setSaturation_0(this.nativeObj, f);
    }

    public void setSigmaColor(float f) {
        setSigmaColor_0(this.nativeObj, f);
    }

    public void setSigmaSpace(float f) {
        setSigmaSpace_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
