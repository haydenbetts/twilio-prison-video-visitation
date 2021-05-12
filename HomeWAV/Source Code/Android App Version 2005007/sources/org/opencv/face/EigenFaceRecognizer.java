package org.opencv.face;

public class EigenFaceRecognizer extends BasicFaceRecognizer {
    private static native long create_0(int i, double d);

    private static native long create_1(int i);

    private static native long create_2();

    private static native void delete(long j);

    protected EigenFaceRecognizer(long j) {
        super(j);
    }

    public static EigenFaceRecognizer __fromPtr__(long j) {
        return new EigenFaceRecognizer(j);
    }

    public static EigenFaceRecognizer create(int i, double d) {
        return __fromPtr__(create_0(i, d));
    }

    public static EigenFaceRecognizer create(int i) {
        return __fromPtr__(create_1(i));
    }

    public static EigenFaceRecognizer create() {
        return __fromPtr__(create_2());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
