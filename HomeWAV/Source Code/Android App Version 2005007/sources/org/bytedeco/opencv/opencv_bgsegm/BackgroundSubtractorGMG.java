package org.bytedeco.opencv.opencv_bgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractor;
import org.bytedeco.opencv.presets.opencv_bgsegm;

@Namespace("cv::bgsegm")
@Properties(inherit = {opencv_bgsegm.class})
public class BackgroundSubtractorGMG extends BackgroundSubtractor {
    public native double getBackgroundPrior();

    public native double getDecisionThreshold();

    public native double getDefaultLearningRate();

    public native int getMaxFeatures();

    public native double getMaxVal();

    public native double getMinVal();

    public native int getNumFrames();

    public native int getQuantizationLevels();

    public native int getSmoothingRadius();

    @Cast({"bool"})
    public native boolean getUpdateBackgroundModel();

    public native void setBackgroundPrior(double d);

    public native void setDecisionThreshold(double d);

    public native void setDefaultLearningRate(double d);

    public native void setMaxFeatures(int i);

    public native void setMaxVal(double d);

    public native void setMinVal(double d);

    public native void setNumFrames(int i);

    public native void setQuantizationLevels(int i);

    public native void setSmoothingRadius(int i);

    public native void setUpdateBackgroundModel(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public BackgroundSubtractorGMG(Pointer pointer) {
        super(pointer);
    }
}
