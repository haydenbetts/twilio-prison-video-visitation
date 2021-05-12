package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class StructuredEdgeDetection extends Algorithm {
    private static native void computeOrientation_0(long j, long j2, long j3);

    private static native void delete(long j);

    private static native void detectEdges_0(long j, long j2, long j3);

    private static native void edgesNms_0(long j, long j2, long j3, long j4, int i, int i2, float f, boolean z);

    private static native void edgesNms_1(long j, long j2, long j3, long j4, int i, int i2, float f);

    private static native void edgesNms_2(long j, long j2, long j3, long j4, int i, int i2);

    private static native void edgesNms_3(long j, long j2, long j3, long j4, int i);

    private static native void edgesNms_4(long j, long j2, long j3, long j4);

    protected StructuredEdgeDetection(long j) {
        super(j);
    }

    public static StructuredEdgeDetection __fromPtr__(long j) {
        return new StructuredEdgeDetection(j);
    }

    public void computeOrientation(Mat mat, Mat mat2) {
        computeOrientation_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void detectEdges(Mat mat, Mat mat2) {
        detectEdges_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void edgesNms(Mat mat, Mat mat2, Mat mat3, int i, int i2, float f, boolean z) {
        edgesNms_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, i2, f, z);
    }

    public void edgesNms(Mat mat, Mat mat2, Mat mat3, int i, int i2, float f) {
        edgesNms_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, i2, f);
    }

    public void edgesNms(Mat mat, Mat mat2, Mat mat3, int i, int i2) {
        edgesNms_2(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, i2);
    }

    public void edgesNms(Mat mat, Mat mat2, Mat mat3, int i) {
        edgesNms_3(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i);
    }

    public void edgesNms(Mat mat, Mat mat2, Mat mat3) {
        edgesNms_4(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
