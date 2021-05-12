package org.opencv.text;

public class ClassifierCallback {
    protected final long nativeObj;

    private static native void delete(long j);

    protected ClassifierCallback(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static ClassifierCallback __fromPtr__(long j) {
        return new ClassifierCallback(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
