package org.opencv.text;

public class Callback {
    protected final long nativeObj;

    private static native void delete(long j);

    protected Callback(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static Callback __fromPtr__(long j) {
        return new Callback(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
