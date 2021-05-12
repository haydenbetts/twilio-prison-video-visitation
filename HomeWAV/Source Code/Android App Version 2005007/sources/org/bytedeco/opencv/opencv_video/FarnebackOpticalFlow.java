package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class FarnebackOpticalFlow extends DenseOpticalFlow {
    @opencv_core.Ptr
    public static native FarnebackOpticalFlow create();

    @opencv_core.Ptr
    public static native FarnebackOpticalFlow create(int i, double d, @Cast({"bool"}) boolean z, int i2, int i3, int i4, double d2, int i5);

    @Cast({"bool"})
    public native boolean getFastPyramids();

    public native int getFlags();

    public native int getNumIters();

    public native int getNumLevels();

    public native int getPolyN();

    public native double getPolySigma();

    public native double getPyrScale();

    public native int getWinSize();

    public native void setFastPyramids(@Cast({"bool"}) boolean z);

    public native void setFlags(int i);

    public native void setNumIters(int i);

    public native void setNumLevels(int i);

    public native void setPolyN(int i);

    public native void setPolySigma(double d);

    public native void setPyrScale(double d);

    public native void setWinSize(int i);

    static {
        Loader.load();
    }

    public FarnebackOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
