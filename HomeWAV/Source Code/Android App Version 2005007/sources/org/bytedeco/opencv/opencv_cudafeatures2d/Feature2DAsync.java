package org.bytedeco.opencv.opencv_cudafeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_cudafeatures2d;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudafeatures2d.class})
public class Feature2DAsync extends Feature2D {
    public native void computeAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void computeAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void computeAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void computeAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void computeAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void computeAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void convert(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector);

    public native void convert(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector);

    public native void convert(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector);

    public native void detectAndComputeAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    public native void detectAndComputeAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectAndComputeAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    public native void detectAndComputeAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectAndComputeAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    public native void detectAndComputeAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detectAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectAsync(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detectAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectAsync(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detectAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }

    public Feature2DAsync(Pointer pointer) {
        super(pointer);
    }
}
