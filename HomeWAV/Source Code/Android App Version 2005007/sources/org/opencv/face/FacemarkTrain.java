package org.opencv.face;

public class FacemarkTrain extends Facemark {
    private static native void delete(long j);

    protected FacemarkTrain(long j) {
        super(j);
    }

    public static FacemarkTrain __fromPtr__(long j) {
        return new FacemarkTrain(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
