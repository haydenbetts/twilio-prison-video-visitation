package org.opencv.ximgproc;

import org.opencv.core.Mat;

public class RICInterpolator extends SparseMatchInterpolator {
    private static native void delete(long j);

    private static native float getAlpha_0(long j);

    private static native float getFGSLambda_0(long j);

    private static native float getFGSSigma_0(long j);

    private static native int getK_0(long j);

    private static native float getMaxFlow_0(long j);

    private static native int getModelIter_0(long j);

    private static native boolean getRefineModels_0(long j);

    private static native int getSuperpixelMode_0(long j);

    private static native int getSuperpixelNNCnt_0(long j);

    private static native float getSuperpixelRuler_0(long j);

    private static native int getSuperpixelSize_0(long j);

    private static native boolean getUseGlobalSmootherFilter_0(long j);

    private static native boolean getUseVariationalRefinement_0(long j);

    private static native void setAlpha_0(long j, float f);

    private static native void setAlpha_1(long j);

    private static native void setCostMap_0(long j, long j2);

    private static native void setFGSLambda_0(long j, float f);

    private static native void setFGSLambda_1(long j);

    private static native void setFGSSigma_0(long j, float f);

    private static native void setFGSSigma_1(long j);

    private static native void setK_0(long j, int i);

    private static native void setK_1(long j);

    private static native void setMaxFlow_0(long j, float f);

    private static native void setMaxFlow_1(long j);

    private static native void setModelIter_0(long j, int i);

    private static native void setModelIter_1(long j);

    private static native void setRefineModels_0(long j, boolean z);

    private static native void setRefineModels_1(long j);

    private static native void setSuperpixelMode_0(long j, int i);

    private static native void setSuperpixelMode_1(long j);

    private static native void setSuperpixelNNCnt_0(long j, int i);

    private static native void setSuperpixelNNCnt_1(long j);

    private static native void setSuperpixelRuler_0(long j, float f);

    private static native void setSuperpixelRuler_1(long j);

    private static native void setSuperpixelSize_0(long j, int i);

    private static native void setSuperpixelSize_1(long j);

    private static native void setUseGlobalSmootherFilter_0(long j, boolean z);

    private static native void setUseGlobalSmootherFilter_1(long j);

    private static native void setUseVariationalRefinement_0(long j, boolean z);

    private static native void setUseVariationalRefinement_1(long j);

    protected RICInterpolator(long j) {
        super(j);
    }

    public static RICInterpolator __fromPtr__(long j) {
        return new RICInterpolator(j);
    }

    public boolean getRefineModels() {
        return getRefineModels_0(this.nativeObj);
    }

    public boolean getUseGlobalSmootherFilter() {
        return getUseGlobalSmootherFilter_0(this.nativeObj);
    }

    public boolean getUseVariationalRefinement() {
        return getUseVariationalRefinement_0(this.nativeObj);
    }

    public float getAlpha() {
        return getAlpha_0(this.nativeObj);
    }

    public float getFGSLambda() {
        return getFGSLambda_0(this.nativeObj);
    }

    public float getFGSSigma() {
        return getFGSSigma_0(this.nativeObj);
    }

    public float getMaxFlow() {
        return getMaxFlow_0(this.nativeObj);
    }

    public float getSuperpixelRuler() {
        return getSuperpixelRuler_0(this.nativeObj);
    }

    public int getK() {
        return getK_0(this.nativeObj);
    }

    public int getModelIter() {
        return getModelIter_0(this.nativeObj);
    }

    public int getSuperpixelMode() {
        return getSuperpixelMode_0(this.nativeObj);
    }

    public int getSuperpixelNNCnt() {
        return getSuperpixelNNCnt_0(this.nativeObj);
    }

    public int getSuperpixelSize() {
        return getSuperpixelSize_0(this.nativeObj);
    }

    public void setAlpha(float f) {
        setAlpha_0(this.nativeObj, f);
    }

    public void setAlpha() {
        setAlpha_1(this.nativeObj);
    }

    public void setCostMap(Mat mat) {
        setCostMap_0(this.nativeObj, mat.nativeObj);
    }

    public void setFGSLambda(float f) {
        setFGSLambda_0(this.nativeObj, f);
    }

    public void setFGSLambda() {
        setFGSLambda_1(this.nativeObj);
    }

    public void setFGSSigma(float f) {
        setFGSSigma_0(this.nativeObj, f);
    }

    public void setFGSSigma() {
        setFGSSigma_1(this.nativeObj);
    }

    public void setK(int i) {
        setK_0(this.nativeObj, i);
    }

    public void setK() {
        setK_1(this.nativeObj);
    }

    public void setMaxFlow(float f) {
        setMaxFlow_0(this.nativeObj, f);
    }

    public void setMaxFlow() {
        setMaxFlow_1(this.nativeObj);
    }

    public void setModelIter(int i) {
        setModelIter_0(this.nativeObj, i);
    }

    public void setModelIter() {
        setModelIter_1(this.nativeObj);
    }

    public void setRefineModels(boolean z) {
        setRefineModels_0(this.nativeObj, z);
    }

    public void setRefineModels() {
        setRefineModels_1(this.nativeObj);
    }

    public void setSuperpixelMode(int i) {
        setSuperpixelMode_0(this.nativeObj, i);
    }

    public void setSuperpixelMode() {
        setSuperpixelMode_1(this.nativeObj);
    }

    public void setSuperpixelNNCnt(int i) {
        setSuperpixelNNCnt_0(this.nativeObj, i);
    }

    public void setSuperpixelNNCnt() {
        setSuperpixelNNCnt_1(this.nativeObj);
    }

    public void setSuperpixelRuler(float f) {
        setSuperpixelRuler_0(this.nativeObj, f);
    }

    public void setSuperpixelRuler() {
        setSuperpixelRuler_1(this.nativeObj);
    }

    public void setSuperpixelSize(int i) {
        setSuperpixelSize_0(this.nativeObj, i);
    }

    public void setSuperpixelSize() {
        setSuperpixelSize_1(this.nativeObj);
    }

    public void setUseGlobalSmootherFilter(boolean z) {
        setUseGlobalSmootherFilter_0(this.nativeObj, z);
    }

    public void setUseGlobalSmootherFilter() {
        setUseGlobalSmootherFilter_1(this.nativeObj);
    }

    public void setUseVariationalRefinement(boolean z) {
        setUseVariationalRefinement_0(this.nativeObj, z);
    }

    public void setUseVariationalRefinement() {
        setUseVariationalRefinement_1(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
