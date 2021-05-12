package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Namespace("cv")
@Properties(inherit = {opencv_imgproc.class})
public class LineSegmentDetector extends Algorithm {
    public native int compareSegments(@ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native int compareSegments(@ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") GpuMat gpuMat3);

    public native int compareSegments(@ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2);

    public native int compareSegments(@ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") Mat mat3);

    public native int compareSegments(@ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native int compareSegments(@ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") UMat uMat3);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    public native void drawSegments(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void drawSegments(@ByVal Mat mat, @ByVal Mat mat2);

    public native void drawSegments(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public LineSegmentDetector(Pointer pointer) {
        super(pointer);
    }
}
