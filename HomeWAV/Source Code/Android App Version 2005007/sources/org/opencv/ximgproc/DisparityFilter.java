package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class DisparityFilter extends Algorithm {
    private static native void delete(long j);

    private static native void filter_0(long j, long j2, long j3, long j4, long j5, int i, int i2, int i3, int i4, long j6);

    private static native void filter_1(long j, long j2, long j3, long j4, long j5, int i, int i2, int i3, int i4);

    private static native void filter_2(long j, long j2, long j3, long j4, long j5);

    private static native void filter_3(long j, long j2, long j3, long j4);

    protected DisparityFilter(long j) {
        super(j);
    }

    public static DisparityFilter __fromPtr__(long j) {
        return new DisparityFilter(j);
    }

    public void filter(Mat mat, Mat mat2, Mat mat3, Mat mat4, Rect rect, Mat mat5) {
        Rect rect2 = rect;
        filter_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, rect2.x, rect2.y, rect2.width, rect2.height, mat5.nativeObj);
    }

    public void filter(Mat mat, Mat mat2, Mat mat3, Mat mat4, Rect rect) {
        Rect rect2 = rect;
        filter_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, rect2.x, rect2.y, rect2.width, rect2.height);
    }

    public void filter(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        filter_2(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public void filter(Mat mat, Mat mat2, Mat mat3) {
        filter_3(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
