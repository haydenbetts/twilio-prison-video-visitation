package org.opencv.face;

public class PredictCollector {
    protected final long nativeObj;

    private static native void delete(long j);

    protected PredictCollector(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static PredictCollector __fromPtr__(long j) {
        return new PredictCollector(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
