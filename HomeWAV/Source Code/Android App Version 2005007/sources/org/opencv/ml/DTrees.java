package org.opencv.ml;

import org.opencv.core.Mat;

public class DTrees extends StatModel {
    public static final int PREDICT_AUTO = 0;
    public static final int PREDICT_MASK = 768;
    public static final int PREDICT_MAX_VOTE = 512;
    public static final int PREDICT_SUM = 256;

    private static native long create_0();

    private static native void delete(long j);

    private static native int getCVFolds_0(long j);

    private static native int getMaxCategories_0(long j);

    private static native int getMaxDepth_0(long j);

    private static native int getMinSampleCount_0(long j);

    private static native long getPriors_0(long j);

    private static native float getRegressionAccuracy_0(long j);

    private static native boolean getTruncatePrunedTree_0(long j);

    private static native boolean getUse1SERule_0(long j);

    private static native boolean getUseSurrogates_0(long j);

    private static native long load_0(String str, String str2);

    private static native long load_1(String str);

    private static native void setCVFolds_0(long j, int i);

    private static native void setMaxCategories_0(long j, int i);

    private static native void setMaxDepth_0(long j, int i);

    private static native void setMinSampleCount_0(long j, int i);

    private static native void setPriors_0(long j, long j2);

    private static native void setRegressionAccuracy_0(long j, float f);

    private static native void setTruncatePrunedTree_0(long j, boolean z);

    private static native void setUse1SERule_0(long j, boolean z);

    private static native void setUseSurrogates_0(long j, boolean z);

    protected DTrees(long j) {
        super(j);
    }

    public static DTrees __fromPtr__(long j) {
        return new DTrees(j);
    }

    public Mat getPriors() {
        return new Mat(getPriors_0(this.nativeObj));
    }

    public static DTrees create() {
        return __fromPtr__(create_0());
    }

    public static DTrees load(String str, String str2) {
        return __fromPtr__(load_0(str, str2));
    }

    public static DTrees load(String str) {
        return __fromPtr__(load_1(str));
    }

    public boolean getTruncatePrunedTree() {
        return getTruncatePrunedTree_0(this.nativeObj);
    }

    public boolean getUse1SERule() {
        return getUse1SERule_0(this.nativeObj);
    }

    public boolean getUseSurrogates() {
        return getUseSurrogates_0(this.nativeObj);
    }

    public float getRegressionAccuracy() {
        return getRegressionAccuracy_0(this.nativeObj);
    }

    public int getCVFolds() {
        return getCVFolds_0(this.nativeObj);
    }

    public int getMaxCategories() {
        return getMaxCategories_0(this.nativeObj);
    }

    public int getMaxDepth() {
        return getMaxDepth_0(this.nativeObj);
    }

    public int getMinSampleCount() {
        return getMinSampleCount_0(this.nativeObj);
    }

    public void setCVFolds(int i) {
        setCVFolds_0(this.nativeObj, i);
    }

    public void setMaxCategories(int i) {
        setMaxCategories_0(this.nativeObj, i);
    }

    public void setMaxDepth(int i) {
        setMaxDepth_0(this.nativeObj, i);
    }

    public void setMinSampleCount(int i) {
        setMinSampleCount_0(this.nativeObj, i);
    }

    public void setPriors(Mat mat) {
        setPriors_0(this.nativeObj, mat.nativeObj);
    }

    public void setRegressionAccuracy(float f) {
        setRegressionAccuracy_0(this.nativeObj, f);
    }

    public void setTruncatePrunedTree(boolean z) {
        setTruncatePrunedTree_0(this.nativeObj, z);
    }

    public void setUse1SERule(boolean z) {
        setUse1SERule_0(this.nativeObj, z);
    }

    public void setUseSurrogates(boolean z) {
        setUseSurrogates_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
