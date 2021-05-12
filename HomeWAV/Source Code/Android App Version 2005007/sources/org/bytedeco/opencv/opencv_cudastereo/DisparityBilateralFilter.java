package org.bytedeco.opencv.opencv_cudastereo;

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
import org.bytedeco.opencv.presets.opencv_cudastereo;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudastereo.class})
public class DisparityBilateralFilter extends Algorithm {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native double getEdgeThreshold();

    public native double getMaxDiscThreshold();

    public native int getNumDisparities();

    public native int getNumIters();

    public native int getRadius();

    public native double getSigmaRange();

    public native void setEdgeThreshold(double d);

    public native void setMaxDiscThreshold(double d);

    public native void setNumDisparities(int i);

    public native void setNumIters(int i);

    public native void setRadius(int i);

    public native void setSigmaRange(double d);

    static {
        Loader.load();
    }

    public DisparityBilateralFilter(Pointer pointer) {
        super(pointer);
    }
}
