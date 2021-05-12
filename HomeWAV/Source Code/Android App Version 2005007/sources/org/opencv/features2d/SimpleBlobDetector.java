package org.opencv.features2d;

public class SimpleBlobDetector extends Feature2D {
    private static native long create_0();

    private static native void delete(long j);

    private static native String getDefaultName_0(long j);

    protected SimpleBlobDetector(long j) {
        super(j);
    }

    public static SimpleBlobDetector __fromPtr__(long j) {
        return new SimpleBlobDetector(j);
    }

    public static SimpleBlobDetector create() {
        return __fromPtr__(create_0());
    }

    public String getDefaultName() {
        return getDefaultName_0(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
