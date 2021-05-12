package org.opencv.photo;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class MergeExposures extends Algorithm {
    private static native void delete(long j);

    private static native void process_0(long j, long j2, long j3, long j4, long j5);

    protected MergeExposures(long j) {
        super(j);
    }

    public static MergeExposures __fromPtr__(long j) {
        return new MergeExposures(j);
    }

    public void process(List<Mat> list, Mat mat, Mat mat2, Mat mat3) {
        process_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
