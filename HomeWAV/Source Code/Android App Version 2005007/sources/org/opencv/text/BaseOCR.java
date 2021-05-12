package org.opencv.text;

public class BaseOCR {
    protected final long nativeObj;

    private static native void delete(long j);

    protected BaseOCR(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static BaseOCR __fromPtr__(long j) {
        return new BaseOCR(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
