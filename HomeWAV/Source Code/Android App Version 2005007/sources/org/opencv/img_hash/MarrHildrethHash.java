package org.opencv.img_hash;

public class MarrHildrethHash extends ImgHashBase {
    private static native long create_0(float f, float f2);

    private static native long create_1(float f);

    private static native long create_2();

    private static native void delete(long j);

    private static native float getAlpha_0(long j);

    private static native float getScale_0(long j);

    private static native void setKernelParam_0(long j, float f, float f2);

    protected MarrHildrethHash(long j) {
        super(j);
    }

    public static MarrHildrethHash __fromPtr__(long j) {
        return new MarrHildrethHash(j);
    }

    public static MarrHildrethHash create(float f, float f2) {
        return __fromPtr__(create_0(f, f2));
    }

    public static MarrHildrethHash create(float f) {
        return __fromPtr__(create_1(f));
    }

    public static MarrHildrethHash create() {
        return __fromPtr__(create_2());
    }

    public float getAlpha() {
        return getAlpha_0(this.nativeObj);
    }

    public float getScale() {
        return getScale_0(this.nativeObj);
    }

    public void setKernelParam(float f, float f2) {
        setKernelParam_0(this.nativeObj, f, f2);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
