package org.opencv.phase_unwrapping;

public class Params {
    protected final long nativeObj;

    private static native long Params_0();

    private static native void delete(long j);

    private static native int get_height_0(long j);

    private static native float get_histThresh_0(long j);

    private static native int get_nbrOfLargeBins_0(long j);

    private static native int get_nbrOfSmallBins_0(long j);

    private static native int get_width_0(long j);

    private static native void set_height_0(long j, int i);

    private static native void set_histThresh_0(long j, float f);

    private static native void set_nbrOfLargeBins_0(long j, int i);

    private static native void set_nbrOfSmallBins_0(long j, int i);

    private static native void set_width_0(long j, int i);

    protected Params(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static Params __fromPtr__(long j) {
        return new Params(j);
    }

    public Params() {
        this.nativeObj = Params_0();
    }

    public int get_width() {
        return get_width_0(this.nativeObj);
    }

    public void set_width(int i) {
        set_width_0(this.nativeObj, i);
    }

    public int get_height() {
        return get_height_0(this.nativeObj);
    }

    public void set_height(int i) {
        set_height_0(this.nativeObj, i);
    }

    public float get_histThresh() {
        return get_histThresh_0(this.nativeObj);
    }

    public void set_histThresh(float f) {
        set_histThresh_0(this.nativeObj, f);
    }

    public int get_nbrOfSmallBins() {
        return get_nbrOfSmallBins_0(this.nativeObj);
    }

    public void set_nbrOfSmallBins(int i) {
        set_nbrOfSmallBins_0(this.nativeObj, i);
    }

    public int get_nbrOfLargeBins() {
        return get_nbrOfLargeBins_0(this.nativeObj);
    }

    public void set_nbrOfLargeBins(int i) {
        set_nbrOfLargeBins_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
