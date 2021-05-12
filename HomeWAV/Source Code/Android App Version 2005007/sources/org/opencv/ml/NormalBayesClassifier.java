package org.opencv.ml;

import org.opencv.core.Mat;

public class NormalBayesClassifier extends StatModel {
    private static native long create_0();

    private static native void delete(long j);

    private static native long load_0(String str, String str2);

    private static native long load_1(String str);

    private static native float predictProb_0(long j, long j2, long j3, long j4, int i);

    private static native float predictProb_1(long j, long j2, long j3, long j4);

    protected NormalBayesClassifier(long j) {
        super(j);
    }

    public static NormalBayesClassifier __fromPtr__(long j) {
        return new NormalBayesClassifier(j);
    }

    public static NormalBayesClassifier create() {
        return __fromPtr__(create_0());
    }

    public static NormalBayesClassifier load(String str, String str2) {
        return __fromPtr__(load_0(str, str2));
    }

    public static NormalBayesClassifier load(String str) {
        return __fromPtr__(load_1(str));
    }

    public float predictProb(Mat mat, Mat mat2, Mat mat3, int i) {
        return predictProb_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i);
    }

    public float predictProb(Mat mat, Mat mat2, Mat mat3) {
        return predictProb_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
