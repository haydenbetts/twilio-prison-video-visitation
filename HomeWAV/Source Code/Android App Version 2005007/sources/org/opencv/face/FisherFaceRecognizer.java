package org.opencv.face;

public class FisherFaceRecognizer extends BasicFaceRecognizer {
    private static native long create_0(int i, double d);

    private static native long create_1(int i);

    private static native long create_2();

    private static native void delete(long j);

    protected FisherFaceRecognizer(long j) {
        super(j);
    }

    public static FisherFaceRecognizer __fromPtr__(long j) {
        return new FisherFaceRecognizer(j);
    }

    public static FisherFaceRecognizer create(int i, double d) {
        return __fromPtr__(create_0(i, d));
    }

    public static FisherFaceRecognizer create(int i) {
        return __fromPtr__(create_1(i));
    }

    public static FisherFaceRecognizer create() {
        return __fromPtr__(create_2());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
