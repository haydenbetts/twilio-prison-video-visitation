package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class BackgroundSubtractorKNN extends BackgroundSubtractor {
    @Cast({"bool"})
    public native boolean getDetectShadows();

    public native double getDist2Threshold();

    public native int getHistory();

    public native int getNSamples();

    public native double getShadowThreshold();

    public native int getShadowValue();

    public native int getkNNSamples();

    public native void setDetectShadows(@Cast({"bool"}) boolean z);

    public native void setDist2Threshold(double d);

    public native void setHistory(int i);

    public native void setNSamples(int i);

    public native void setShadowThreshold(double d);

    public native void setShadowValue(int i);

    public native void setkNNSamples(int i);

    static {
        Loader.load();
    }

    public BackgroundSubtractorKNN(Pointer pointer) {
        super(pointer);
    }
}
