package org.opencv.ml;

public class ParamGrid {
    protected final long nativeObj;

    private static native long create_0(double d, double d2, double d3);

    private static native long create_1(double d, double d2);

    private static native long create_2(double d);

    private static native long create_3();

    private static native void delete(long j);

    private static native double get_logStep_0(long j);

    private static native double get_maxVal_0(long j);

    private static native double get_minVal_0(long j);

    private static native void set_logStep_0(long j, double d);

    private static native void set_maxVal_0(long j, double d);

    private static native void set_minVal_0(long j, double d);

    protected ParamGrid(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static ParamGrid __fromPtr__(long j) {
        return new ParamGrid(j);
    }

    public static ParamGrid create(double d, double d2, double d3) {
        return __fromPtr__(create_0(d, d2, d3));
    }

    public static ParamGrid create(double d, double d2) {
        return __fromPtr__(create_1(d, d2));
    }

    public static ParamGrid create(double d) {
        return __fromPtr__(create_2(d));
    }

    public static ParamGrid create() {
        return __fromPtr__(create_3());
    }

    public double get_minVal() {
        return get_minVal_0(this.nativeObj);
    }

    public void set_minVal(double d) {
        set_minVal_0(this.nativeObj, d);
    }

    public double get_maxVal() {
        return get_maxVal_0(this.nativeObj);
    }

    public void set_maxVal(double d) {
        set_maxVal_0(this.nativeObj, d);
    }

    public double get_logStep() {
        return get_logStep_0(this.nativeObj);
    }

    public void set_logStep(double d) {
        set_logStep_0(this.nativeObj, d);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
