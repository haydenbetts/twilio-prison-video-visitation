package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_cudastereo.DisparityBilateralFilter;
import org.bytedeco.opencv.opencv_cudastereo.StereoBM;
import org.bytedeco.opencv.opencv_cudastereo.StereoBeliefPropagation;
import org.bytedeco.opencv.opencv_cudastereo.StereoConstantSpaceBP;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_cudastereo extends org.bytedeco.opencv.presets.opencv_cudastereo {
    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native DisparityBilateralFilter createDisparityBilateralFilter();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native DisparityBilateralFilter createDisparityBilateralFilter(int i, int i2, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native StereoBM createStereoBM();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native StereoBM createStereoBM(int i, int i2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native StereoBeliefPropagation createStereoBeliefPropagation();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native StereoBeliefPropagation createStereoBeliefPropagation(int i, int i2, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native StereoConstantSpaceBP createStereoConstantSpaceBP();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native StereoConstantSpaceBP createStereoConstantSpaceBP(int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::cuda")
    public static native void drawColorDisp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::cuda")
    public static native void drawColorDisp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void drawColorDisp(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::cuda")
    public static native void drawColorDisp(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void drawColorDisp(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::cuda")
    public static native void drawColorDisp(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void reprojectImageTo3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void reprojectImageTo3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void reprojectImageTo3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void reprojectImageTo3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void reprojectImageTo3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void reprojectImageTo3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }
}
