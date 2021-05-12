package org.bytedeco.opencv.opencv_cudaoptflow;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudaoptflow;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaoptflow.class})
public class DenseOpticalFlow extends Algorithm {
    public native void calc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void calc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void calc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void calc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void calc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void calc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }

    public DenseOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
