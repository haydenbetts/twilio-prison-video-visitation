package org.bytedeco.opencv.opencv_cudaoptflow;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudaoptflow;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaoptflow.class})
public class OpticalFlowDual_TVL1 extends DenseOpticalFlow {
    @opencv_core.Ptr
    public static native OpticalFlowDual_TVL1 create();

    @opencv_core.Ptr
    public static native OpticalFlowDual_TVL1 create(double d, double d2, double d3, int i, int i2, double d4, int i3, double d5, double d6, @Cast({"bool"}) boolean z);

    public native double getEpsilon();

    public native double getGamma();

    public native double getLambda();

    public native int getNumIterations();

    public native int getNumScales();

    public native int getNumWarps();

    public native double getScaleStep();

    public native double getTau();

    public native double getTheta();

    @Cast({"bool"})
    public native boolean getUseInitialFlow();

    public native void setEpsilon(double d);

    public native void setGamma(double d);

    public native void setLambda(double d);

    public native void setNumIterations(int i);

    public native void setNumScales(int i);

    public native void setNumWarps(int i);

    public native void setScaleStep(double d);

    public native void setTau(double d);

    public native void setTheta(double d);

    public native void setUseInitialFlow(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public OpticalFlowDual_TVL1(Pointer pointer) {
        super(pointer);
    }
}
