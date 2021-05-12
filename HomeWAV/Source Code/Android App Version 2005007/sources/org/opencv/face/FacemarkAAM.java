package org.opencv.face;

public class FacemarkAAM extends FacemarkTrain {
    private static native void delete(long j);

    protected FacemarkAAM(long j) {
        super(j);
    }

    public static FacemarkAAM __fromPtr__(long j) {
        return new FacemarkAAM(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
