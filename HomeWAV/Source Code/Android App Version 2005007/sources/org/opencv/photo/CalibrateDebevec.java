package org.opencv.photo;

public class CalibrateDebevec extends CalibrateCRF {
    private static native void delete(long j);

    private static native float getLambda_0(long j);

    private static native boolean getRandom_0(long j);

    private static native int getSamples_0(long j);

    private static native void setLambda_0(long j, float f);

    private static native void setRandom_0(long j, boolean z);

    private static native void setSamples_0(long j, int i);

    protected CalibrateDebevec(long j) {
        super(j);
    }

    public static CalibrateDebevec __fromPtr__(long j) {
        return new CalibrateDebevec(j);
    }

    public boolean getRandom() {
        return getRandom_0(this.nativeObj);
    }

    public float getLambda() {
        return getLambda_0(this.nativeObj);
    }

    public int getSamples() {
        return getSamples_0(this.nativeObj);
    }

    public void setLambda(float f) {
        setLambda_0(this.nativeObj, f);
    }

    public void setRandom(boolean z) {
        setRandom_0(this.nativeObj, z);
    }

    public void setSamples(int i) {
        setSamples_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
