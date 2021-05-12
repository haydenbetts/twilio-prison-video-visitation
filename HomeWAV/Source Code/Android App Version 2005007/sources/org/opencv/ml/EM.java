package org.opencv.ml;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

public class EM extends StatModel {
    public static final int COV_MAT_DEFAULT = 1;
    public static final int COV_MAT_DIAGONAL = 1;
    public static final int COV_MAT_GENERIC = 2;
    public static final int COV_MAT_SPHERICAL = 0;
    public static final int DEFAULT_MAX_ITERS = 100;
    public static final int DEFAULT_NCLUSTERS = 5;
    public static final int START_AUTO_STEP = 0;
    public static final int START_E_STEP = 1;
    public static final int START_M_STEP = 2;

    private static native long create_0();

    private static native void delete(long j);

    private static native int getClustersNumber_0(long j);

    private static native int getCovarianceMatrixType_0(long j);

    private static native void getCovs_0(long j, long j2);

    private static native long getMeans_0(long j);

    private static native double[] getTermCriteria_0(long j);

    private static native long getWeights_0(long j);

    private static native long load_0(String str, String str2);

    private static native long load_1(String str);

    private static native double[] predict2_0(long j, long j2, long j3);

    private static native float predict_0(long j, long j2, long j3, int i);

    private static native float predict_1(long j, long j2, long j3);

    private static native float predict_2(long j, long j2);

    private static native void setClustersNumber_0(long j, int i);

    private static native void setCovarianceMatrixType_0(long j, int i);

    private static native void setTermCriteria_0(long j, int i, int i2, double d);

    private static native boolean trainEM_0(long j, long j2, long j3, long j4, long j5);

    private static native boolean trainEM_1(long j, long j2, long j3, long j4);

    private static native boolean trainEM_2(long j, long j2, long j3);

    private static native boolean trainEM_3(long j, long j2);

    private static native boolean trainE_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    private static native boolean trainE_1(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native boolean trainE_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native boolean trainE_3(long j, long j2, long j3, long j4, long j5);

    private static native boolean trainE_4(long j, long j2, long j3, long j4);

    private static native boolean trainE_5(long j, long j2, long j3);

    private static native boolean trainM_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native boolean trainM_1(long j, long j2, long j3, long j4, long j5);

    private static native boolean trainM_2(long j, long j2, long j3, long j4);

    private static native boolean trainM_3(long j, long j2, long j3);

    protected EM(long j) {
        super(j);
    }

    public static EM __fromPtr__(long j) {
        return new EM(j);
    }

    public Mat getMeans() {
        return new Mat(getMeans_0(this.nativeObj));
    }

    public Mat getWeights() {
        return new Mat(getWeights_0(this.nativeObj));
    }

    public static EM create() {
        return __fromPtr__(create_0());
    }

    public static EM load(String str, String str2) {
        return __fromPtr__(load_0(str, str2));
    }

    public static EM load(String str) {
        return __fromPtr__(load_1(str));
    }

    public TermCriteria getTermCriteria() {
        return new TermCriteria(getTermCriteria_0(this.nativeObj));
    }

    public double[] predict2(Mat mat, Mat mat2) {
        return predict2_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public boolean trainE(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7) {
        return trainE_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
    }

    public boolean trainE(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return trainE_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public boolean trainE(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return trainE_2(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public boolean trainE(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return trainE_3(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public boolean trainE(Mat mat, Mat mat2, Mat mat3) {
        return trainE_4(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public boolean trainE(Mat mat, Mat mat2) {
        return trainE_5(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public boolean trainEM(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return trainEM_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public boolean trainEM(Mat mat, Mat mat2, Mat mat3) {
        return trainEM_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public boolean trainEM(Mat mat, Mat mat2) {
        return trainEM_2(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public boolean trainEM(Mat mat) {
        return trainEM_3(this.nativeObj, mat.nativeObj);
    }

    public boolean trainM(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return trainM_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public boolean trainM(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return trainM_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public boolean trainM(Mat mat, Mat mat2, Mat mat3) {
        return trainM_2(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public boolean trainM(Mat mat, Mat mat2) {
        return trainM_3(this.nativeObj, mat.nativeObj, mat2.nativeObj);
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

    public int getClustersNumber() {
        return getClustersNumber_0(this.nativeObj);
    }

    public int getCovarianceMatrixType() {
        return getCovarianceMatrixType_0(this.nativeObj);
    }

    public void getCovs(List<Mat> list) {
        Mat mat = new Mat();
        getCovs_0(this.nativeObj, mat.nativeObj);
        Converters.Mat_to_vector_Mat(mat, list);
        mat.release();
    }

    public void setClustersNumber(int i) {
        setClustersNumber_0(this.nativeObj, i);
    }

    public void setCovarianceMatrixType(int i) {
        setCovarianceMatrixType_0(this.nativeObj, i);
    }

    public void setTermCriteria(TermCriteria termCriteria) {
        setTermCriteria_0(this.nativeObj, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
