package org.opencv.dnn;

import org.opencv.core.Mat;

public class ClassificationModel extends Model {
    private static native long ClassificationModel_0(long j);

    private static native long ClassificationModel_1(String str, String str2);

    private static native long ClassificationModel_2(String str);

    private static native void classify_0(long j, long j2, double[] dArr, double[] dArr2);

    private static native void delete(long j);

    protected ClassificationModel(long j) {
        super(j);
    }

    public static ClassificationModel __fromPtr__(long j) {
        return new ClassificationModel(j);
    }

    public ClassificationModel(Net net2) {
        super(ClassificationModel_0(net2.nativeObj));
    }

    public ClassificationModel(String str, String str2) {
        super(ClassificationModel_1(str, str2));
    }

    public ClassificationModel(String str) {
        super(ClassificationModel_2(str));
    }

    public void classify(Mat mat, int[] iArr, float[] fArr) {
        double[] dArr = new double[1];
        double[] dArr2 = new double[1];
        classify_0(this.nativeObj, mat.nativeObj, dArr, dArr2);
        if (iArr != null) {
            iArr[0] = (int) dArr[0];
        }
        if (fArr != null) {
            fArr[0] = (float) dArr2[0];
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
