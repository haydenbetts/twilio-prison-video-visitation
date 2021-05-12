package org.opencv.face;

public class FacemarkKazemi extends Facemark {
    private static native void delete(long j);

    protected FacemarkKazemi(long j) {
        super(j);
    }

    public static FacemarkKazemi __fromPtr__(long j) {
        return new FacemarkKazemi(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
