package org.opencv.photo;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class CalibrateCRF extends Algorithm {
    private static native void delete(long j);

    private static native void process_0(long j, long j2, long j3, long j4);

    protected CalibrateCRF(long j) {
        super(j);
    }

    public static CalibrateCRF __fromPtr__(long j) {
        return new CalibrateCRF(j);
    }

    public void process(List<Mat> list, Mat mat, Mat mat2) {
        process_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
