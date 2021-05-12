package org.bytedeco.opencv.opencv_optflow;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_video.DenseOpticalFlow;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_optflow;

@Namespace("cv::optflow")
@Properties(inherit = {opencv_optflow.class})
public class DualTVL1OpticalFlow extends DenseOpticalFlow {
    @opencv_core.Ptr
    public static native DualTVL1OpticalFlow create();

    @opencv_core.Ptr
    public static native DualTVL1OpticalFlow create(double d, double d2, double d3, int i, int i2, double d4, int i3, int i4, double d5, double d6, int i5, @Cast({"bool"}) boolean z);

    public native double getEpsilon();

    public native double getGamma();

    public native int getInnerIterations();

    public native double getLambda();

    public native int getMedianFiltering();

    public native int getOuterIterations();

    public native double getScaleStep();

    public native int getScalesNumber();

    public native double getTau();

    public native double getTheta();

    @Cast({"bool"})
    public native boolean getUseInitialFlow();

    public native int getWarpingsNumber();

    public native void setEpsilon(double d);

    public native void setGamma(double d);

    public native void setInnerIterations(int i);

    public native void setLambda(double d);

    public native void setMedianFiltering(int i);

    public native void setOuterIterations(int i);

    public native void setScaleStep(double d);

    public native void setScalesNumber(int i);

    public native void setTau(double d);

    public native void setTheta(double d);

    public native void setUseInitialFlow(@Cast({"bool"}) boolean z);

    public native void setWarpingsNumber(int i);

    static {
        Loader.load();
    }

    public DualTVL1OpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
