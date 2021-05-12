package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;

public class EdgeBoxes extends Algorithm {
    private static native void delete(long j);

    private static native float getAlpha_0(long j);

    private static native float getBeta_0(long j);

    private static native void getBoundingBoxes_0(long j, long j2, long j3, long j4, long j5);

    private static native void getBoundingBoxes_1(long j, long j2, long j3, long j4);

    private static native float getClusterMinMag_0(long j);

    private static native float getEdgeMergeThr_0(long j);

    private static native float getEdgeMinMag_0(long j);

    private static native float getEta_0(long j);

    private static native float getGamma_0(long j);

    private static native float getKappa_0(long j);

    private static native float getMaxAspectRatio_0(long j);

    private static native int getMaxBoxes_0(long j);

    private static native float getMinBoxArea_0(long j);

    private static native float getMinScore_0(long j);

    private static native void setAlpha_0(long j, float f);

    private static native void setBeta_0(long j, float f);

    private static native void setClusterMinMag_0(long j, float f);

    private static native void setEdgeMergeThr_0(long j, float f);

    private static native void setEdgeMinMag_0(long j, float f);

    private static native void setEta_0(long j, float f);

    private static native void setGamma_0(long j, float f);

    private static native void setKappa_0(long j, float f);

    private static native void setMaxAspectRatio_0(long j, float f);

    private static native void setMaxBoxes_0(long j, int i);

    private static native void setMinBoxArea_0(long j, float f);

    private static native void setMinScore_0(long j, float f);

    protected EdgeBoxes(long j) {
        super(j);
    }

    public static EdgeBoxes __fromPtr__(long j) {
        return new EdgeBoxes(j);
    }

    public float getAlpha() {
        return getAlpha_0(this.nativeObj);
    }

    public float getBeta() {
        return getBeta_0(this.nativeObj);
    }

    public float getClusterMinMag() {
        return getClusterMinMag_0(this.nativeObj);
    }

    public float getEdgeMergeThr() {
        return getEdgeMergeThr_0(this.nativeObj);
    }

    public float getEdgeMinMag() {
        return getEdgeMinMag_0(this.nativeObj);
    }

    public float getEta() {
        return getEta_0(this.nativeObj);
    }

    public float getGamma() {
        return getGamma_0(this.nativeObj);
    }

    public float getKappa() {
        return getKappa_0(this.nativeObj);
    }

    public float getMaxAspectRatio() {
        return getMaxAspectRatio_0(this.nativeObj);
    }

    public float getMinBoxArea() {
        return getMinBoxArea_0(this.nativeObj);
    }

    public float getMinScore() {
        return getMinScore_0(this.nativeObj);
    }

    public int getMaxBoxes() {
        return getMaxBoxes_0(this.nativeObj);
    }

    public void getBoundingBoxes(Mat mat, Mat mat2, MatOfRect matOfRect, Mat mat3) {
        getBoundingBoxes_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, matOfRect.nativeObj, mat3.nativeObj);
    }

    public void getBoundingBoxes(Mat mat, Mat mat2, MatOfRect matOfRect) {
        getBoundingBoxes_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, matOfRect.nativeObj);
    }

    public void setAlpha(float f) {
        setAlpha_0(this.nativeObj, f);
    }

    public void setBeta(float f) {
        setBeta_0(this.nativeObj, f);
    }

    public void setClusterMinMag(float f) {
        setClusterMinMag_0(this.nativeObj, f);
    }

    public void setEdgeMergeThr(float f) {
        setEdgeMergeThr_0(this.nativeObj, f);
    }

    public void setEdgeMinMag(float f) {
        setEdgeMinMag_0(this.nativeObj, f);
    }

    public void setEta(float f) {
        setEta_0(this.nativeObj, f);
    }

    public void setGamma(float f) {
        setGamma_0(this.nativeObj, f);
    }

    public void setKappa(float f) {
        setKappa_0(this.nativeObj, f);
    }

    public void setMaxAspectRatio(float f) {
        setMaxAspectRatio_0(this.nativeObj, f);
    }

    public void setMaxBoxes(int i) {
        setMaxBoxes_0(this.nativeObj, i);
    }

    public void setMinBoxArea(float f) {
        setMinBoxArea_0(this.nativeObj, f);
    }

    public void setMinScore(float f) {
        setMinScore_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
