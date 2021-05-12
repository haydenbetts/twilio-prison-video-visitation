package org.opencv.structured_light;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.utils.Converters;

public class SinusoidalPattern extends StructuredLightPattern {
    private static native void computeDataModulationTerm_0(long j, long j2, long j3, long j4);

    private static native void computePhaseMap_0(long j, long j2, long j3, long j4, long j5);

    private static native void computePhaseMap_1(long j, long j2, long j3, long j4);

    private static native void computePhaseMap_2(long j, long j2, long j3);

    private static native long create_0();

    private static native void delete(long j);

    private static native void findProCamMatches_0(long j, long j2, long j3, long j4);

    private static native void unwrapPhaseMap_0(long j, long j2, long j3, double d, double d2, long j4);

    private static native void unwrapPhaseMap_1(long j, long j2, long j3, double d, double d2);

    protected SinusoidalPattern(long j) {
        super(j);
    }

    public static SinusoidalPattern __fromPtr__(long j) {
        return new SinusoidalPattern(j);
    }

    public static SinusoidalPattern create() {
        return __fromPtr__(create_0());
    }

    public void computeDataModulationTerm(List<Mat> list, Mat mat, Mat mat2) {
        computeDataModulationTerm_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void computePhaseMap(List<Mat> list, Mat mat, Mat mat2, Mat mat3) {
        computePhaseMap_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public void computePhaseMap(List<Mat> list, Mat mat, Mat mat2) {
        computePhaseMap_1(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void computePhaseMap(List<Mat> list, Mat mat) {
        computePhaseMap_2(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj);
    }

    public void findProCamMatches(Mat mat, Mat mat2, List<Mat> list) {
        Mat mat3 = new Mat();
        findProCamMatches_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list);
        mat3.release();
    }

    public void unwrapPhaseMap(Mat mat, Mat mat2, Size size, Mat mat3) {
        Size size2 = size;
        unwrapPhaseMap_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, size2.width, size2.height, mat3.nativeObj);
    }

    public void unwrapPhaseMap(Mat mat, Mat mat2, Size size) {
        unwrapPhaseMap_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, size.width, size.height);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
