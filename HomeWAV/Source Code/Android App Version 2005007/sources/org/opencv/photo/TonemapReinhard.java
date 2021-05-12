package org.opencv.photo;

public class TonemapReinhard extends Tonemap {
    private static native void delete(long j);

    private static native float getColorAdaptation_0(long j);

    private static native float getIntensity_0(long j);

    private static native float getLightAdaptation_0(long j);

    private static native void setColorAdaptation_0(long j, float f);

    private static native void setIntensity_0(long j, float f);

    private static native void setLightAdaptation_0(long j, float f);

    protected TonemapReinhard(long j) {
        super(j);
    }

    public static TonemapReinhard __fromPtr__(long j) {
        return new TonemapReinhard(j);
    }

    public float getColorAdaptation() {
        return getColorAdaptation_0(this.nativeObj);
    }

    public float getIntensity() {
        return getIntensity_0(this.nativeObj);
    }

    public float getLightAdaptation() {
        return getLightAdaptation_0(this.nativeObj);
    }

    public void setColorAdaptation(float f) {
        setColorAdaptation_0(this.nativeObj, f);
    }

    public void setIntensity(float f) {
        setIntensity_0(this.nativeObj, f);
    }

    public void setLightAdaptation(float f) {
        setLightAdaptation_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
