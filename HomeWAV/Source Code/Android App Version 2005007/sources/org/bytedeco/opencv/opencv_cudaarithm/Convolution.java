package org.bytedeco.opencv.opencv_cudaarithm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudaarithm;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaarithm.class})
public class Convolution extends Algorithm {
    public native void convolve(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void convolve(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void convolve(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void convolve(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void convolve(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void convolve(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }

    public Convolution(Pointer pointer) {
        super(pointer);
    }
}
