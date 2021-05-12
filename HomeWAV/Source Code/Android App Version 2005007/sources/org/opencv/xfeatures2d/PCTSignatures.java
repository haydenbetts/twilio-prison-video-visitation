package org.opencv.xfeatures2d;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint2f;
import org.opencv.utils.Converters;

public class PCTSignatures extends Algorithm {
    public static final int GAUSSIAN = 1;
    public static final int HEURISTIC = 2;
    public static final int L0_25 = 0;
    public static final int L0_5 = 1;
    public static final int L1 = 2;
    public static final int L2 = 3;
    public static final int L2SQUARED = 4;
    public static final int L5 = 5;
    public static final int L_INFINITY = 6;
    public static final int MINUS = 0;
    public static final int NORMAL = 2;
    public static final int REGULAR = 1;
    public static final int UNIFORM = 0;

    private static native void computeSignature_0(long j, long j2, long j3);

    private static native void computeSignatures_0(long j, long j2, long j3);

    private static native long create_0(int i, int i2, int i3);

    private static native long create_1(int i, int i2);

    private static native long create_2(int i);

    private static native long create_3();

    private static native long create_4(long j, int i);

    private static native long create_5(long j, long j2);

    private static native void delete(long j);

    private static native void drawSignature_0(long j, long j2, long j3, float f, int i);

    private static native void drawSignature_1(long j, long j2, long j3, float f);

    private static native void drawSignature_2(long j, long j2, long j3);

    private static native void generateInitPoints_0(long j, int i, int i2);

    private static native int getClusterMinSize_0(long j);

    private static native int getDistanceFunction_0(long j);

    private static native float getDropThreshold_0(long j);

    private static native int getGrayscaleBits_0(long j);

    private static native int getInitSeedCount_0(long j);

    private static native long getInitSeedIndexes_0(long j);

    private static native int getIterationCount_0(long j);

    private static native float getJoiningDistance_0(long j);

    private static native int getMaxClustersCount_0(long j);

    private static native int getSampleCount_0(long j);

    private static native long getSamplingPoints_0(long j);

    private static native float getWeightA_0(long j);

    private static native float getWeightB_0(long j);

    private static native float getWeightContrast_0(long j);

    private static native float getWeightEntropy_0(long j);

    private static native float getWeightL_0(long j);

    private static native float getWeightX_0(long j);

    private static native float getWeightY_0(long j);

    private static native int getWindowRadius_0(long j);

    private static native void setClusterMinSize_0(long j, int i);

    private static native void setDistanceFunction_0(long j, int i);

    private static native void setDropThreshold_0(long j, float f);

    private static native void setGrayscaleBits_0(long j, int i);

    private static native void setInitSeedIndexes_0(long j, long j2);

    private static native void setIterationCount_0(long j, int i);

    private static native void setJoiningDistance_0(long j, float f);

    private static native void setMaxClustersCount_0(long j, int i);

    private static native void setSamplingPoints_0(long j, long j2);

    private static native void setTranslation_0(long j, int i, float f);

    private static native void setTranslations_0(long j, long j2);

    private static native void setWeightA_0(long j, float f);

    private static native void setWeightB_0(long j, float f);

    private static native void setWeightContrast_0(long j, float f);

    private static native void setWeightEntropy_0(long j, float f);

    private static native void setWeightL_0(long j, float f);

    private static native void setWeightX_0(long j, float f);

    private static native void setWeightY_0(long j, float f);

    private static native void setWeight_0(long j, int i, float f);

    private static native void setWeights_0(long j, long j2);

    private static native void setWindowRadius_0(long j, int i);

    protected PCTSignatures(long j) {
        super(j);
    }

    public static PCTSignatures __fromPtr__(long j) {
        return new PCTSignatures(j);
    }

    public static PCTSignatures create(int i, int i2, int i3) {
        return __fromPtr__(create_0(i, i2, i3));
    }

    public static PCTSignatures create(int i, int i2) {
        return __fromPtr__(create_1(i, i2));
    }

    public static PCTSignatures create(int i) {
        return __fromPtr__(create_2(i));
    }

    public static PCTSignatures create() {
        return __fromPtr__(create_3());
    }

    public static PCTSignatures create(MatOfPoint2f matOfPoint2f, int i) {
        return __fromPtr__(create_4(matOfPoint2f.nativeObj, i));
    }

    public static PCTSignatures create(MatOfPoint2f matOfPoint2f, MatOfInt matOfInt) {
        return __fromPtr__(create_5(matOfPoint2f.nativeObj, matOfInt.nativeObj));
    }

    public float getDropThreshold() {
        return getDropThreshold_0(this.nativeObj);
    }

    public float getJoiningDistance() {
        return getJoiningDistance_0(this.nativeObj);
    }

    public float getWeightA() {
        return getWeightA_0(this.nativeObj);
    }

    public float getWeightB() {
        return getWeightB_0(this.nativeObj);
    }

    public float getWeightContrast() {
        return getWeightContrast_0(this.nativeObj);
    }

    public float getWeightEntropy() {
        return getWeightEntropy_0(this.nativeObj);
    }

    public float getWeightL() {
        return getWeightL_0(this.nativeObj);
    }

    public float getWeightX() {
        return getWeightX_0(this.nativeObj);
    }

    public float getWeightY() {
        return getWeightY_0(this.nativeObj);
    }

    public int getClusterMinSize() {
        return getClusterMinSize_0(this.nativeObj);
    }

    public int getDistanceFunction() {
        return getDistanceFunction_0(this.nativeObj);
    }

    public int getGrayscaleBits() {
        return getGrayscaleBits_0(this.nativeObj);
    }

    public int getInitSeedCount() {
        return getInitSeedCount_0(this.nativeObj);
    }

    public int getIterationCount() {
        return getIterationCount_0(this.nativeObj);
    }

    public int getMaxClustersCount() {
        return getMaxClustersCount_0(this.nativeObj);
    }

    public int getSampleCount() {
        return getSampleCount_0(this.nativeObj);
    }

    public int getWindowRadius() {
        return getWindowRadius_0(this.nativeObj);
    }

    public MatOfPoint2f getSamplingPoints() {
        return MatOfPoint2f.fromNativeAddr(getSamplingPoints_0(this.nativeObj));
    }

    public MatOfInt getInitSeedIndexes() {
        return MatOfInt.fromNativeAddr(getInitSeedIndexes_0(this.nativeObj));
    }

    public void computeSignature(Mat mat, Mat mat2) {
        computeSignature_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void computeSignatures(List<Mat> list, List<Mat> list2) {
        computeSignatures_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj);
    }

    public static void drawSignature(Mat mat, Mat mat2, Mat mat3, float f, int i) {
        drawSignature_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i);
    }

    public static void drawSignature(Mat mat, Mat mat2, Mat mat3, float f) {
        drawSignature_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f);
    }

    public static void drawSignature(Mat mat, Mat mat2, Mat mat3) {
        drawSignature_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void generateInitPoints(MatOfPoint2f matOfPoint2f, int i, int i2) {
        generateInitPoints_0(matOfPoint2f.nativeObj, i, i2);
    }

    public void setClusterMinSize(int i) {
        setClusterMinSize_0(this.nativeObj, i);
    }

    public void setDistanceFunction(int i) {
        setDistanceFunction_0(this.nativeObj, i);
    }

    public void setDropThreshold(float f) {
        setDropThreshold_0(this.nativeObj, f);
    }

    public void setGrayscaleBits(int i) {
        setGrayscaleBits_0(this.nativeObj, i);
    }

    public void setInitSeedIndexes(MatOfInt matOfInt) {
        setInitSeedIndexes_0(this.nativeObj, matOfInt.nativeObj);
    }

    public void setIterationCount(int i) {
        setIterationCount_0(this.nativeObj, i);
    }

    public void setJoiningDistance(float f) {
        setJoiningDistance_0(this.nativeObj, f);
    }

    public void setMaxClustersCount(int i) {
        setMaxClustersCount_0(this.nativeObj, i);
    }

    public void setSamplingPoints(MatOfPoint2f matOfPoint2f) {
        setSamplingPoints_0(this.nativeObj, matOfPoint2f.nativeObj);
    }

    public void setTranslation(int i, float f) {
        setTranslation_0(this.nativeObj, i, f);
    }

    public void setTranslations(MatOfFloat matOfFloat) {
        setTranslations_0(this.nativeObj, matOfFloat.nativeObj);
    }

    public void setWeight(int i, float f) {
        setWeight_0(this.nativeObj, i, f);
    }

    public void setWeightA(float f) {
        setWeightA_0(this.nativeObj, f);
    }

    public void setWeightB(float f) {
        setWeightB_0(this.nativeObj, f);
    }

    public void setWeightContrast(float f) {
        setWeightContrast_0(this.nativeObj, f);
    }

    public void setWeightEntropy(float f) {
        setWeightEntropy_0(this.nativeObj, f);
    }

    public void setWeightL(float f) {
        setWeightL_0(this.nativeObj, f);
    }

    public void setWeightX(float f) {
        setWeightX_0(this.nativeObj, f);
    }

    public void setWeightY(float f) {
        setWeightY_0(this.nativeObj, f);
    }

    public void setWeights(MatOfFloat matOfFloat) {
        setWeights_0(this.nativeObj, matOfFloat.nativeObj);
    }

    public void setWindowRadius(int i) {
        setWindowRadius_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
