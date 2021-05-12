package org.opencv.ml;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class StatModel extends Algorithm {
    public static final int COMPRESSED_INPUT = 2;
    public static final int PREPROCESSED_INPUT = 4;
    public static final int RAW_OUTPUT = 1;
    public static final int UPDATE_MODEL = 1;

    private static native float calcError_0(long j, long j2, boolean z, long j3);

    private static native void delete(long j);

    private static native boolean empty_0(long j);

    private static native int getVarCount_0(long j);

    private static native boolean isClassifier_0(long j);

    private static native boolean isTrained_0(long j);

    private static native float predict_0(long j, long j2, long j3, int i);

    private static native float predict_1(long j, long j2, long j3);

    private static native float predict_2(long j, long j2);

    private static native boolean train_0(long j, long j2, int i, long j3);

    private static native boolean train_1(long j, long j2, int i);

    private static native boolean train_2(long j, long j2);

    protected StatModel(long j) {
        super(j);
    }

    public static StatModel __fromPtr__(long j) {
        return new StatModel(j);
    }

    public boolean empty() {
        return empty_0(this.nativeObj);
    }

    public boolean isClassifier() {
        return isClassifier_0(this.nativeObj);
    }

    public boolean isTrained() {
        return isTrained_0(this.nativeObj);
    }

    public boolean train(Mat mat, int i, Mat mat2) {
        return train_0(this.nativeObj, mat.nativeObj, i, mat2.nativeObj);
    }

    public boolean train(TrainData trainData, int i) {
        return train_1(this.nativeObj, trainData.getNativeObjAddr(), i);
    }

    public boolean train(TrainData trainData) {
        return train_2(this.nativeObj, trainData.getNativeObjAddr());
    }

    public float calcError(TrainData trainData, boolean z, Mat mat) {
        return calcError_0(this.nativeObj, trainData.getNativeObjAddr(), z, mat.nativeObj);
    }

    public float predict(Mat mat, Mat mat2, int i) {
        return predict_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, i);
    }

    public float predict(Mat mat, Mat mat2) {
        return predict_1(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public float predict(Mat mat) {
        return predict_2(this.nativeObj, mat.nativeObj);
    }

    public int getVarCount() {
        return getVarCount_0(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
