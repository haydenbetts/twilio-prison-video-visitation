package org.opencv.bgsegm;

public class BackgroundSubtractorLSBPDesc {
    protected final long nativeObj;

    private static native void delete(long j);

    protected BackgroundSubtractorLSBPDesc(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static BackgroundSubtractorLSBPDesc __fromPtr__(long j) {
        return new BackgroundSubtractorLSBPDesc(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
