package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class FastLineDetector extends Algorithm {
    private static native void delete(long j);

    private static native void detect_0(long j, long j2, long j3);

    private static native void drawSegments_0(long j, long j2, long j3, boolean z);

    private static native void drawSegments_1(long j, long j2, long j3);

    protected FastLineDetector(long j) {
        super(j);
    }

    public static FastLineDetector __fromPtr__(long j) {
        return new FastLineDetector(j);
    }

    public void detect(Mat mat, Mat mat2) {
        detect_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void drawSegments(Mat mat, Mat mat2, boolean z) {
        drawSegments_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, z);
    }

    public void drawSegments(Mat mat, Mat mat2) {
        drawSegments_1(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
