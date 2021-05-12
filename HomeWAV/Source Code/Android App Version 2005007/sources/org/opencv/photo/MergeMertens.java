package org.opencv.photo;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class MergeMertens extends MergeExposures {
    private static native void delete(long j);

    private static native float getContrastWeight_0(long j);

    private static native float getExposureWeight_0(long j);

    private static native float getSaturationWeight_0(long j);

    private static native void process_0(long j, long j2, long j3, long j4, long j5);

    private static native void process_1(long j, long j2, long j3);

    private static native void setContrastWeight_0(long j, float f);

    private static native void setExposureWeight_0(long j, float f);

    private static native void setSaturationWeight_0(long j, float f);

    protected MergeMertens(long j) {
        super(j);
    }

    public static MergeMertens __fromPtr__(long j) {
        return new MergeMertens(j);
    }

    public float getContrastWeight() {
        return getContrastWeight_0(this.nativeObj);
    }

    public float getExposureWeight() {
        return getExposureWeight_0(this.nativeObj);
    }

    public float getSaturationWeight() {
        return getSaturationWeight_0(this.nativeObj);
    }

    public void process(List<Mat> list, Mat mat, Mat mat2, Mat mat3) {
        process_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public void process(List<Mat> list, Mat mat) {
        process_1(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj);
    }

    public void setContrastWeight(float f) {
        setContrastWeight_0(this.nativeObj, f);
    }

    public void setExposureWeight(float f) {
        setExposureWeight_0(this.nativeObj, f);
    }

    public void setSaturationWeight(float f) {
        setSaturationWeight_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
