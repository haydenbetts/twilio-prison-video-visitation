package org.opencv.bgsegm;

import org.opencv.core.Mat;
import org.opencv.video.BackgroundSubtractor;

public class BackgroundSubtractorCNT extends BackgroundSubtractor {
    private static native void apply_0(long j, long j2, long j3, double d);

    private static native void apply_1(long j, long j2, long j3);

    private static native void delete(long j);

    private static native void getBackgroundImage_0(long j, long j2);

    private static native boolean getIsParallel_0(long j);

    private static native int getMaxPixelStability_0(long j);

    private static native int getMinPixelStability_0(long j);

    private static native boolean getUseHistory_0(long j);

    private static native void setIsParallel_0(long j, boolean z);

    private static native void setMaxPixelStability_0(long j, int i);

    private static native void setMinPixelStability_0(long j, int i);

    private static native void setUseHistory_0(long j, boolean z);

    protected BackgroundSubtractorCNT(long j) {
        super(j);
    }

    public static BackgroundSubtractorCNT __fromPtr__(long j) {
        return new BackgroundSubtractorCNT(j);
    }

    public boolean getIsParallel() {
        return getIsParallel_0(this.nativeObj);
    }

    public boolean getUseHistory() {
        return getUseHistory_0(this.nativeObj);
    }

    public int getMaxPixelStability() {
        return getMaxPixelStability_0(this.nativeObj);
    }

    public int getMinPixelStability() {
        return getMinPixelStability_0(this.nativeObj);
    }

    public void apply(Mat mat, Mat mat2, double d) {
        apply_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, d);
    }

    public void apply(Mat mat, Mat mat2) {
        apply_1(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void getBackgroundImage(Mat mat) {
        getBackgroundImage_0(this.nativeObj, mat.nativeObj);
    }

    public void setIsParallel(boolean z) {
        setIsParallel_0(this.nativeObj, z);
    }

    public void setMaxPixelStability(int i) {
        setMaxPixelStability_0(this.nativeObj, i);
    }

    public void setMinPixelStability(int i) {
        setMinPixelStability_0(this.nativeObj, i);
    }

    public void setUseHistory(boolean z) {
        setUseHistory_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
