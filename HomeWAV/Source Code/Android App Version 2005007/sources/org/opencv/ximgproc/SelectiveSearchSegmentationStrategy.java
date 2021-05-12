package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class SelectiveSearchSegmentationStrategy extends Algorithm {
    private static native void delete(long j);

    private static native float get_0(long j, int i, int i2);

    private static native void merge_0(long j, int i, int i2);

    private static native void setImage_0(long j, long j2, long j3, long j4, int i);

    private static native void setImage_1(long j, long j2, long j3, long j4);

    protected SelectiveSearchSegmentationStrategy(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentationStrategy __fromPtr__(long j) {
        return new SelectiveSearchSegmentationStrategy(j);
    }

    public float get(int i, int i2) {
        return get_0(this.nativeObj, i, i2);
    }

    public void merge(int i, int i2) {
        merge_0(this.nativeObj, i, i2);
    }

    public void setImage(Mat mat, Mat mat2, Mat mat3, int i) {
        setImage_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i);
    }

    public void setImage(Mat mat, Mat mat2, Mat mat3) {
        setImage_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
