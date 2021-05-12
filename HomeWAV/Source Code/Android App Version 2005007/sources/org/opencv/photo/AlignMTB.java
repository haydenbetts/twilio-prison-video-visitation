package org.opencv.photo;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.utils.Converters;

public class AlignMTB extends AlignExposures {
    private static native double[] calculateShift_0(long j, long j2, long j3);

    private static native void computeBitmaps_0(long j, long j2, long j3, long j4);

    private static native void delete(long j);

    private static native boolean getCut_0(long j);

    private static native int getExcludeRange_0(long j);

    private static native int getMaxBits_0(long j);

    private static native void process_0(long j, long j2, long j3, long j4, long j5);

    private static native void process_1(long j, long j2, long j3);

    private static native void setCut_0(long j, boolean z);

    private static native void setExcludeRange_0(long j, int i);

    private static native void setMaxBits_0(long j, int i);

    private static native void shiftMat_0(long j, long j2, long j3, double d, double d2);

    protected AlignMTB(long j) {
        super(j);
    }

    public static AlignMTB __fromPtr__(long j) {
        return new AlignMTB(j);
    }

    public Point calculateShift(Mat mat, Mat mat2) {
        return new Point(calculateShift_0(this.nativeObj, mat.nativeObj, mat2.nativeObj));
    }

    public boolean getCut() {
        return getCut_0(this.nativeObj);
    }

    public int getExcludeRange() {
        return getExcludeRange_0(this.nativeObj);
    }

    public int getMaxBits() {
        return getMaxBits_0(this.nativeObj);
    }

    public void computeBitmaps(Mat mat, Mat mat2, Mat mat3) {
        computeBitmaps_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public void process(List<Mat> list, List<Mat> list2, Mat mat, Mat mat2) {
        process_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void process(List<Mat> list, List<Mat> list2) {
        process_1(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj);
    }

    public void setCut(boolean z) {
        setCut_0(this.nativeObj, z);
    }

    public void setExcludeRange(int i) {
        setExcludeRange_0(this.nativeObj, i);
    }

    public void setMaxBits(int i) {
        setMaxBits_0(this.nativeObj, i);
    }

    public void shiftMat(Mat mat, Mat mat2, Point point) {
        shiftMat_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, point.x, point.y);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
