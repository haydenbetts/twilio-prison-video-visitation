package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class AdaptiveManifoldFilter extends Algorithm {
    @opencv_core.Ptr
    public static native AdaptiveManifoldFilter create();

    public native void collectGarbage();

    public native void filter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void filter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void filter(@ByVal Mat mat, @ByVal Mat mat2);

    public native void filter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void filter(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void filter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Cast({"bool"})
    public native boolean getAdjustOutliers();

    public native int getPCAIterations();

    public native double getSigmaR();

    public native double getSigmaS();

    public native int getTreeHeight();

    @Cast({"bool"})
    public native boolean getUseRNG();

    public native void setAdjustOutliers(@Cast({"bool"}) boolean z);

    public native void setPCAIterations(int i);

    public native void setSigmaR(double d);

    public native void setSigmaS(double d);

    public native void setTreeHeight(int i);

    public native void setUseRNG(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public AdaptiveManifoldFilter(Pointer pointer) {
        super(pointer);
    }
}
