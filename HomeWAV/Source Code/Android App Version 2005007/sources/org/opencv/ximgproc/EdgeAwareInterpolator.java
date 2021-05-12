package org.opencv.ximgproc;

import org.opencv.core.Mat;

public class EdgeAwareInterpolator extends SparseMatchInterpolator {
    private static native void delete(long j);

    private static native float getFGSLambda_0(long j);

    private static native float getFGSSigma_0(long j);

    private static native int getK_0(long j);

    private static native float getLambda_0(long j);

    private static native float getSigma_0(long j);

    private static native boolean getUsePostProcessing_0(long j);

    private static native void setCostMap_0(long j, long j2);

    private static native void setFGSLambda_0(long j, float f);

    private static native void setFGSSigma_0(long j, float f);

    private static native void setK_0(long j, int i);

    private static native void setLambda_0(long j, float f);

    private static native void setSigma_0(long j, float f);

    private static native void setUsePostProcessing_0(long j, boolean z);

    protected EdgeAwareInterpolator(long j) {
        super(j);
    }

    public static EdgeAwareInterpolator __fromPtr__(long j) {
        return new EdgeAwareInterpolator(j);
    }

    public boolean getUsePostProcessing() {
        return getUsePostProcessing_0(this.nativeObj);
    }

    public float getFGSLambda() {
        return getFGSLambda_0(this.nativeObj);
    }

    public float getFGSSigma() {
        return getFGSSigma_0(this.nativeObj);
    }

    public float getLambda() {
        return getLambda_0(this.nativeObj);
    }

    public float getSigma() {
        return getSigma_0(this.nativeObj);
    }

    public int getK() {
        return getK_0(this.nativeObj);
    }

    public void setCostMap(Mat mat) {
        setCostMap_0(this.nativeObj, mat.nativeObj);
    }

    public void setFGSLambda(float f) {
        setFGSLambda_0(this.nativeObj, f);
    }

    public void setFGSSigma(float f) {
        setFGSSigma_0(this.nativeObj, f);
    }

    public void setK(int i) {
        setK_0(this.nativeObj, i);
    }

    public void setLambda(float f) {
        setLambda_0(this.nativeObj, f);
    }

    public void setSigma(float f) {
        setSigma_0(this.nativeObj, f);
    }

    public void setUsePostProcessing(boolean z) {
        setUsePostProcessing_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
