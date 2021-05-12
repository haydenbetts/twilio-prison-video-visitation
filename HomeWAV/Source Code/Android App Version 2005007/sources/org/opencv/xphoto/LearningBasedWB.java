package org.opencv.xphoto;

import org.opencv.core.Mat;

public class LearningBasedWB extends WhiteBalancer {
    private static native void delete(long j);

    private static native void extractSimpleFeatures_0(long j, long j2, long j3);

    private static native int getHistBinNum_0(long j);

    private static native int getRangeMaxVal_0(long j);

    private static native float getSaturationThreshold_0(long j);

    private static native void setHistBinNum_0(long j, int i);

    private static native void setRangeMaxVal_0(long j, int i);

    private static native void setSaturationThreshold_0(long j, float f);

    protected LearningBasedWB(long j) {
        super(j);
    }

    public static LearningBasedWB __fromPtr__(long j) {
        return new LearningBasedWB(j);
    }

    public float getSaturationThreshold() {
        return getSaturationThreshold_0(this.nativeObj);
    }

    public int getHistBinNum() {
        return getHistBinNum_0(this.nativeObj);
    }

    public int getRangeMaxVal() {
        return getRangeMaxVal_0(this.nativeObj);
    }

    public void extractSimpleFeatures(Mat mat, Mat mat2) {
        extractSimpleFeatures_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void setHistBinNum(int i) {
        setHistBinNum_0(this.nativeObj, i);
    }

    public void setRangeMaxVal(int i) {
        setRangeMaxVal_0(this.nativeObj, i);
    }

    public void setSaturationThreshold(float f) {
        setSaturationThreshold_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
