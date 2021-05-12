package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;

public class opencv_cudawarping extends org.bytedeco.opencv.presets.opencv_cudawarping {
    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void buildWarpAffineMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal Mat mat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void buildWarpPerspectiveMaps(@ByVal UMat uMat, @Cast({"bool"}) boolean z, @ByVal Size size, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pyrDown(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void pyrDown(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pyrDown(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void pyrDown(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pyrDown(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void pyrDown(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pyrUp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void pyrUp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pyrUp(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void pyrUp(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pyrUp(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void pyrUp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void remap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv::cuda")
    public static native void remap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void remap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    @Namespace("cv::cuda")
    public static native void remap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void remap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    @Namespace("cv::cuda")
    public static native void remap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void resize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void resize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d, double d2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void resize(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void resize(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d, double d2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void resize(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void resize(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d, double d2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rotate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d);

    @Namespace("cv::cuda")
    public static native void rotate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d, double d2, double d3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rotate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d);

    @Namespace("cv::cuda")
    public static native void rotate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d, double d2, double d3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rotate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d);

    @Namespace("cv::cuda")
    public static native void rotate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d, double d2, double d3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void warpAffine(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Mat mat, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void warpAffine(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Mat mat, @ByVal Size size, int i, int i2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void warpPerspective(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Mat mat, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void warpPerspective(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Mat mat, @ByVal Size size, int i, int i2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }
}
