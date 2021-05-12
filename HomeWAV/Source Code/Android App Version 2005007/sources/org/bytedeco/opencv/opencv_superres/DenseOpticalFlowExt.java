package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_superres;

@Namespace("cv::superres")
@Properties(inherit = {opencv_superres.class})
public class DenseOpticalFlowExt extends Algorithm {
    public native void calc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void calc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    public native void calc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void calc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    public native void calc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void calc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    public native void collectGarbage();

    static {
        Loader.load();
    }

    public DenseOpticalFlowExt(Pointer pointer) {
        super(pointer);
    }
}
