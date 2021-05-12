package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_superres;

@Namespace("cv::superres")
@Properties(inherit = {opencv_superres.class})
@NoOffset
public class SuperResolution extends Algorithm {
    @Namespace
    @Name({"static_cast<cv::superres::FrameSource*>"})
    public static native FrameSource asFrameSource(SuperResolution superResolution);

    public native void collectGarbage();

    public native double getAlpha();

    public native int getBlurKernelSize();

    public native double getBlurSigma();

    public native int getIterations();

    public native int getKernelSize();

    public native double getLambda();

    @opencv_core.Ptr
    public native DenseOpticalFlowExt getOpticalFlow();

    public native int getScale();

    public native double getTau();

    public native int getTemporalAreaRadius();

    public native void nextFrame(@ByVal GpuMat gpuMat);

    public native void nextFrame(@ByVal Mat mat);

    public native void nextFrame(@ByVal UMat uMat);

    public native void reset();

    public native void setAlpha(double d);

    public native void setBlurKernelSize(int i);

    public native void setBlurSigma(double d);

    public native void setInput(@opencv_core.Ptr FrameSource frameSource);

    public native void setIterations(int i);

    public native void setKernelSize(int i);

    public native void setLambda(double d);

    public native void setOpticalFlow(@opencv_core.Ptr DenseOpticalFlowExt denseOpticalFlowExt);

    public native void setScale(int i);

    public native void setTau(double d);

    public native void setTemporalAreaRadius(int i);

    static {
        Loader.load();
    }

    public SuperResolution(Pointer pointer) {
        super(pointer);
    }

    public FrameSource asFrameSource() {
        return asFrameSource(this);
    }
}
