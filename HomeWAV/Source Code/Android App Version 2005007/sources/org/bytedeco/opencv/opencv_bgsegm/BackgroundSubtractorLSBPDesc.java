package org.bytedeco.opencv.opencv_bgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_bgsegm;

@Namespace("cv::bgsegm")
@Properties(inherit = {opencv_bgsegm.class})
public class BackgroundSubtractorLSBPDesc extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public static native void calcLocalSVDValues(@ByVal GpuMat gpuMat, @ByRef @Const Mat mat);

    public static native void calcLocalSVDValues(@ByVal Mat mat, @ByRef @Const Mat mat2);

    public static native void calcLocalSVDValues(@ByVal UMat uMat, @ByRef @Const Mat mat);

    public static native void compute(@ByVal GpuMat gpuMat, @ByRef @Const Mat mat, @Cast({"const cv::Point2i*"}) Point point);

    public static native void compute(@ByVal Mat mat, @ByRef @Const Mat mat2, @Cast({"const cv::Point2i*"}) Point point);

    public static native void compute(@ByVal UMat uMat, @ByRef @Const Mat mat, @Cast({"const cv::Point2i*"}) Point point);

    public static native void computeFromLocalSVDValues(@ByVal GpuMat gpuMat, @ByRef @Const Mat mat, @Cast({"const cv::Point2i*"}) Point point);

    public static native void computeFromLocalSVDValues(@ByVal Mat mat, @ByRef @Const Mat mat2, @Cast({"const cv::Point2i*"}) Point point);

    public static native void computeFromLocalSVDValues(@ByVal UMat uMat, @ByRef @Const Mat mat, @Cast({"const cv::Point2i*"}) Point point);

    static {
        Loader.load();
    }

    public BackgroundSubtractorLSBPDesc() {
        super((Pointer) null);
        allocate();
    }

    public BackgroundSubtractorLSBPDesc(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BackgroundSubtractorLSBPDesc(Pointer pointer) {
        super(pointer);
    }

    public BackgroundSubtractorLSBPDesc position(long j) {
        return (BackgroundSubtractorLSBPDesc) super.position(j);
    }

    public BackgroundSubtractorLSBPDesc getPointer(long j) {
        return new BackgroundSubtractorLSBPDesc((Pointer) this).position(this.position + j);
    }
}
