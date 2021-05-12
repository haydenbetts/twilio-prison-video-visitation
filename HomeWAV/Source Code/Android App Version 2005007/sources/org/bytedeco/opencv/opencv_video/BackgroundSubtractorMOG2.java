package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class BackgroundSubtractorMOG2 extends BackgroundSubtractor {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, double d);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, double d);

    public native double getBackgroundRatio();

    public native double getComplexityReductionThreshold();

    @Cast({"bool"})
    public native boolean getDetectShadows();

    public native int getHistory();

    public native int getNMixtures();

    public native double getShadowThreshold();

    public native int getShadowValue();

    public native double getVarInit();

    public native double getVarMax();

    public native double getVarMin();

    public native double getVarThreshold();

    public native double getVarThresholdGen();

    public native void setBackgroundRatio(double d);

    public native void setComplexityReductionThreshold(double d);

    public native void setDetectShadows(@Cast({"bool"}) boolean z);

    public native void setHistory(int i);

    public native void setNMixtures(int i);

    public native void setShadowThreshold(double d);

    public native void setShadowValue(int i);

    public native void setVarInit(double d);

    public native void setVarMax(double d);

    public native void setVarMin(double d);

    public native void setVarThreshold(double d);

    public native void setVarThresholdGen(double d);

    static {
        Loader.load();
    }

    public BackgroundSubtractorMOG2(Pointer pointer) {
        super(pointer);
    }
}
