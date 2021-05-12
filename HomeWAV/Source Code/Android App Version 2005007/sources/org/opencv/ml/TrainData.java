package org.opencv.ml;

import java.util.List;
import org.opencv.core.Mat;

public class TrainData {
    protected final long nativeObj;

    private static native long create_0(long j, int i, long j2, long j3, long j4, long j5, long j6);

    private static native long create_1(long j, int i, long j2, long j3, long j4, long j5);

    private static native long create_2(long j, int i, long j2, long j3, long j4);

    private static native long create_3(long j, int i, long j2, long j3);

    private static native long create_4(long j, int i, long j2);

    private static native void delete(long j);

    private static native int getCatCount_0(long j, int i);

    private static native long getCatMap_0(long j);

    private static native long getCatOfs_0(long j);

    private static native long getClassLabels_0(long j);

    private static native long getDefaultSubstValues_0(long j);

    private static native int getLayout_0(long j);

    private static native long getMissing_0(long j);

    private static native int getNAllVars_0(long j);

    private static native int getNSamples_0(long j);

    private static native int getNTestSamples_0(long j);

    private static native int getNTrainSamples_0(long j);

    private static native int getNVars_0(long j);

    private static native void getNames_0(long j, List<String> list);

    private static native long getNormCatResponses_0(long j);

    private static native int getResponseType_0(long j);

    private static native long getResponses_0(long j);

    private static native long getSampleWeights_0(long j);

    private static native void getSample_0(long j, long j2, int i, float f);

    private static native long getSamples_0(long j);

    private static native long getSubMatrix_0(long j, long j2, int i);

    private static native long getSubVector_0(long j, long j2);

    private static native long getTestNormCatResponses_0(long j);

    private static native long getTestResponses_0(long j);

    private static native long getTestSampleIdx_0(long j);

    private static native long getTestSampleWeights_0(long j);

    private static native long getTestSamples_0(long j);

    private static native long getTrainNormCatResponses_0(long j);

    private static native long getTrainResponses_0(long j);

    private static native long getTrainSampleIdx_0(long j);

    private static native long getTrainSampleWeights_0(long j);

    private static native long getTrainSamples_0(long j, int i, boolean z, boolean z2);

    private static native long getTrainSamples_1(long j, int i, boolean z);

    private static native long getTrainSamples_2(long j, int i);

    private static native long getTrainSamples_3(long j);

    private static native void getValues_0(long j, int i, long j2, float f);

    private static native long getVarIdx_0(long j);

    private static native long getVarSymbolFlags_0(long j);

    private static native long getVarType_0(long j);

    private static native void setTrainTestSplitRatio_0(long j, double d, boolean z);

    private static native void setTrainTestSplitRatio_1(long j, double d);

    private static native void setTrainTestSplit_0(long j, int i, boolean z);

    private static native void setTrainTestSplit_1(long j, int i);

    private static native void shuffleTrainTest_0(long j);

    protected TrainData(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static TrainData __fromPtr__(long j) {
        return new TrainData(j);
    }

    public Mat getCatMap() {
        return new Mat(getCatMap_0(this.nativeObj));
    }

    public Mat getCatOfs() {
        return new Mat(getCatOfs_0(this.nativeObj));
    }

    public Mat getClassLabels() {
        return new Mat(getClassLabels_0(this.nativeObj));
    }

    public Mat getDefaultSubstValues() {
        return new Mat(getDefaultSubstValues_0(this.nativeObj));
    }

    public Mat getMissing() {
        return new Mat(getMissing_0(this.nativeObj));
    }

    public Mat getNormCatResponses() {
        return new Mat(getNormCatResponses_0(this.nativeObj));
    }

    public Mat getResponses() {
        return new Mat(getResponses_0(this.nativeObj));
    }

    public Mat getSampleWeights() {
        return new Mat(getSampleWeights_0(this.nativeObj));
    }

    public Mat getSamples() {
        return new Mat(getSamples_0(this.nativeObj));
    }

    public static Mat getSubMatrix(Mat mat, Mat mat2, int i) {
        return new Mat(getSubMatrix_0(mat.nativeObj, mat2.nativeObj, i));
    }

    public static Mat getSubVector(Mat mat, Mat mat2) {
        return new Mat(getSubVector_0(mat.nativeObj, mat2.nativeObj));
    }

    public Mat getTestNormCatResponses() {
        return new Mat(getTestNormCatResponses_0(this.nativeObj));
    }

    public Mat getTestResponses() {
        return new Mat(getTestResponses_0(this.nativeObj));
    }

    public Mat getTestSampleIdx() {
        return new Mat(getTestSampleIdx_0(this.nativeObj));
    }

    public Mat getTestSampleWeights() {
        return new Mat(getTestSampleWeights_0(this.nativeObj));
    }

    public Mat getTestSamples() {
        return new Mat(getTestSamples_0(this.nativeObj));
    }

    public Mat getTrainNormCatResponses() {
        return new Mat(getTrainNormCatResponses_0(this.nativeObj));
    }

    public Mat getTrainResponses() {
        return new Mat(getTrainResponses_0(this.nativeObj));
    }

    public Mat getTrainSampleIdx() {
        return new Mat(getTrainSampleIdx_0(this.nativeObj));
    }

    public Mat getTrainSampleWeights() {
        return new Mat(getTrainSampleWeights_0(this.nativeObj));
    }

    public Mat getTrainSamples(int i, boolean z, boolean z2) {
        return new Mat(getTrainSamples_0(this.nativeObj, i, z, z2));
    }

    public Mat getTrainSamples(int i, boolean z) {
        return new Mat(getTrainSamples_1(this.nativeObj, i, z));
    }

    public Mat getTrainSamples(int i) {
        return new Mat(getTrainSamples_2(this.nativeObj, i));
    }

    public Mat getTrainSamples() {
        return new Mat(getTrainSamples_3(this.nativeObj));
    }

    public Mat getVarIdx() {
        return new Mat(getVarIdx_0(this.nativeObj));
    }

    public Mat getVarSymbolFlags() {
        return new Mat(getVarSymbolFlags_0(this.nativeObj));
    }

    public Mat getVarType() {
        return new Mat(getVarType_0(this.nativeObj));
    }

    public static TrainData create(Mat mat, int i, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return __fromPtr__(create_0(mat.nativeObj, i, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj));
    }

    public static TrainData create(Mat mat, int i, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return __fromPtr__(create_1(mat.nativeObj, i, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj));
    }

    public static TrainData create(Mat mat, int i, Mat mat2, Mat mat3, Mat mat4) {
        return __fromPtr__(create_2(mat.nativeObj, i, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj));
    }

    public static TrainData create(Mat mat, int i, Mat mat2, Mat mat3) {
        return __fromPtr__(create_3(mat.nativeObj, i, mat2.nativeObj, mat3.nativeObj));
    }

    public static TrainData create(Mat mat, int i, Mat mat2) {
        return __fromPtr__(create_4(mat.nativeObj, i, mat2.nativeObj));
    }

    public int getCatCount(int i) {
        return getCatCount_0(this.nativeObj, i);
    }

    public int getLayout() {
        return getLayout_0(this.nativeObj);
    }

    public int getNAllVars() {
        return getNAllVars_0(this.nativeObj);
    }

    public int getNSamples() {
        return getNSamples_0(this.nativeObj);
    }

    public int getNTestSamples() {
        return getNTestSamples_0(this.nativeObj);
    }

    public int getNTrainSamples() {
        return getNTrainSamples_0(this.nativeObj);
    }

    public int getNVars() {
        return getNVars_0(this.nativeObj);
    }

    public int getResponseType() {
        return getResponseType_0(this.nativeObj);
    }

    public void getNames(List<String> list) {
        getNames_0(this.nativeObj, list);
    }

    public void getSample(Mat mat, int i, float f) {
        getSample_0(this.nativeObj, mat.nativeObj, i, f);
    }

    public void getValues(int i, Mat mat, float f) {
        getValues_0(this.nativeObj, i, mat.nativeObj, f);
    }

    public void setTrainTestSplit(int i, boolean z) {
        setTrainTestSplit_0(this.nativeObj, i, z);
    }

    public void setTrainTestSplit(int i) {
        setTrainTestSplit_1(this.nativeObj, i);
    }

    public void setTrainTestSplitRatio(double d, boolean z) {
        setTrainTestSplitRatio_0(this.nativeObj, d, z);
    }

    public void setTrainTestSplitRatio(double d) {
        setTrainTestSplitRatio_1(this.nativeObj, d);
    }

    public void shuffleTrainTest() {
        shuffleTrainTest_0(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
