package org.bytedeco.opencv.opencv_cudaimgproc;

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
import org.bytedeco.opencv.presets.opencv_cudaimgproc;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaimgproc.class})
public class HoughLinesDetector extends Algorithm {
    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void downloadResults(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void downloadResults(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void downloadResults(@ByVal Mat mat, @ByVal Mat mat2);

    public native void downloadResults(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void downloadResults(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void downloadResults(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Cast({"bool"})
    public native boolean getDoSort();

    public native int getMaxLines();

    public native float getRho();

    public native float getTheta();

    public native int getThreshold();

    public native void setDoSort(@Cast({"bool"}) boolean z);

    public native void setMaxLines(int i);

    public native void setRho(float f);

    public native void setTheta(float f);

    public native void setThreshold(int i);

    static {
        Loader.load();
    }

    public HoughLinesDetector(Pointer pointer) {
        super(pointer);
    }
}
