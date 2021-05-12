package org.opencv.face;

public class StandardCollector extends PredictCollector {
    private static native long create_0(double d);

    private static native long create_1();

    private static native void delete(long j);

    private static native double getMinDist_0(long j);

    private static native int getMinLabel_0(long j);

    protected StandardCollector(long j) {
        super(j);
    }

    public static StandardCollector __fromPtr__(long j) {
        return new StandardCollector(j);
    }

    public static StandardCollector create(double d) {
        return __fromPtr__(create_0(d));
    }

    public static StandardCollector create() {
        return __fromPtr__(create_1());
    }

    public double getMinDist() {
        return getMinDist_0(this.nativeObj);
    }

    public int getMinLabel() {
        return getMinLabel_0(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
