package org.opencv.face;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.utils.Converters;

public class FaceRecognizer extends Algorithm {
    private static native void delete(long j);

    private static native String getLabelInfo_0(long j, int i);

    private static native long getLabelsByString_0(long j, String str);

    private static native void predict_0(long j, long j2, double[] dArr, double[] dArr2);

    private static native void predict_collect_0(long j, long j2, long j3);

    private static native int predict_label_0(long j, long j2);

    private static native void read_0(long j, String str);

    private static native void setLabelInfo_0(long j, int i, String str);

    private static native void train_0(long j, long j2, long j3);

    private static native void update_0(long j, long j2, long j3);

    private static native void write_0(long j, String str);

    protected FaceRecognizer(long j) {
        super(j);
    }

    public static FaceRecognizer __fromPtr__(long j) {
        return new FaceRecognizer(j);
    }

    public String getLabelInfo(int i) {
        return getLabelInfo_0(this.nativeObj, i);
    }

    public int predict_label(Mat mat) {
        return predict_label_0(this.nativeObj, mat.nativeObj);
    }

    public MatOfInt getLabelsByString(String str) {
        return MatOfInt.fromNativeAddr(getLabelsByString_0(this.nativeObj, str));
    }

    public void predict_collect(Mat mat, PredictCollector predictCollector) {
        predict_collect_0(this.nativeObj, mat.nativeObj, predictCollector.getNativeObjAddr());
    }

    public void predict(Mat mat, int[] iArr, double[] dArr) {
        double[] dArr2 = new double[1];
        double[] dArr3 = new double[1];
        predict_0(this.nativeObj, mat.nativeObj, dArr2, dArr3);
        if (iArr != null) {
            iArr[0] = (int) dArr2[0];
        }
        if (dArr != null) {
            dArr[0] = dArr3[0];
        }
    }

    public void read(String str) {
        read_0(this.nativeObj, str);
    }

    public void setLabelInfo(int i, String str) {
        setLabelInfo_0(this.nativeObj, i, str);
    }

    public void train(List<Mat> list, Mat mat) {
        train_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj);
    }

    public void update(List<Mat> list, Mat mat) {
        update_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj);
    }

    public void write(String str) {
        write_0(this.nativeObj, str);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
