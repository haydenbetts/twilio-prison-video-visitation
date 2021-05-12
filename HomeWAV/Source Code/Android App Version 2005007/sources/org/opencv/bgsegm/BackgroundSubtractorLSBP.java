package org.opencv.bgsegm;

import org.opencv.core.Mat;
import org.opencv.video.BackgroundSubtractor;

public class BackgroundSubtractorLSBP extends BackgroundSubtractor {
    private static native void apply_0(long j, long j2, long j3, double d);

    private static native void apply_1(long j, long j2, long j3);

    private static native void delete(long j);

    private static native void getBackgroundImage_0(long j, long j2);

    protected BackgroundSubtractorLSBP(long j) {
        super(j);
    }

    public static BackgroundSubtractorLSBP __fromPtr__(long j) {
        return new BackgroundSubtractorLSBP(j);
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

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
