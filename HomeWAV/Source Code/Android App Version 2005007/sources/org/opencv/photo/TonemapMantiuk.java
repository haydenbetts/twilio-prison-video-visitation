package org.opencv.photo;

public class TonemapMantiuk extends Tonemap {
    private static native void delete(long j);

    private static native float getSaturation_0(long j);

    private static native float getScale_0(long j);

    private static native void setSaturation_0(long j, float f);

    private static native void setScale_0(long j, float f);

    protected TonemapMantiuk(long j) {
        super(j);
    }

    public static TonemapMantiuk __fromPtr__(long j) {
        return new TonemapMantiuk(j);
    }

    public float getSaturation() {
        return getSaturation_0(this.nativeObj);
    }

    public float getScale() {
        return getScale_0(this.nativeObj);
    }

    public void setSaturation(float f) {
        setSaturation_0(this.nativeObj, f);
    }

    public void setScale(float f) {
        setScale_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
