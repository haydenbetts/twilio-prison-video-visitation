package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_cudafilters.Filter;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_cudafilters extends org.bytedeco.opencv.presets.opencv_cudafilters {
    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createBoxFilter(int i, int i2, @ByVal Size size);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createBoxFilter(int i, int i2, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createBoxMaxFilter(int i, @ByVal Size size);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createBoxMaxFilter(int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i2, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createBoxMinFilter(int i, @ByVal Size size);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createBoxMinFilter(int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i2, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createColumnSumFilter(int i, int i2, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createColumnSumFilter(int i, int i2, int i3, int i4, int i5, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createDerivFilter(int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createDerivFilter(int i, int i2, int i3, int i4, int i5, @Cast({"bool"}) boolean z, double d, int i6, int i7);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createGaussianFilter(int i, int i2, @ByVal Size size, double d);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createGaussianFilter(int i, int i2, @ByVal Size size, double d, double d2, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLaplacianFilter(int i, int i2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLaplacianFilter(int i, int i2, int i3, double d, int i4, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLinearFilter(int i, int i2, @ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLinearFilter(int i, int i2, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLinearFilter(int i, int i2, @ByVal Mat mat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLinearFilter(int i, int i2, @ByVal Mat mat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLinearFilter(int i, int i2, @ByVal UMat uMat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createLinearFilter(int i, int i2, @ByVal UMat uMat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMedianFilter(int i, int i2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMedianFilter(int i, int i2, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMorphologyFilter(int i, int i2, @ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMorphologyFilter(int i, int i2, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMorphologyFilter(int i, int i2, @ByVal Mat mat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMorphologyFilter(int i, int i2, @ByVal Mat mat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMorphologyFilter(int i, int i2, @ByVal UMat uMat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createMorphologyFilter(int i, int i2, @ByVal UMat uMat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createRowSumFilter(int i, int i2, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createRowSumFilter(int i, int i2, int i3, int i4, int i5, @ByVal(nullValue = "cv::Scalar::all(0)") Scalar scalar);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createScharrFilter(int i, int i2, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createScharrFilter(int i, int i2, int i3, int i4, double d, int i5, int i6);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSeparableLinearFilter(int i, int i2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSeparableLinearFilter(int i, int i2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSeparableLinearFilter(int i, int i2, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSeparableLinearFilter(int i, int i2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSeparableLinearFilter(int i, int i2, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSeparableLinearFilter(int i, int i2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSobelFilter(int i, int i2, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Filter createSobelFilter(int i, int i2, int i3, int i4, int i5, double d, int i6, int i7);

    static {
        Loader.load();
    }
}
