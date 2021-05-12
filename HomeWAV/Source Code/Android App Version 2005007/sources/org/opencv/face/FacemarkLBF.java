package org.opencv.face;

public class FacemarkLBF extends FacemarkTrain {
    private static native void delete(long j);

    protected FacemarkLBF(long j) {
        super(j);
    }

    public static FacemarkLBF __fromPtr__(long j) {
        return new FacemarkLBF(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
