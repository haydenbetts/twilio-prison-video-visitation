package org.bytedeco.opencv.opencv_xfeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class SURF extends Feature2D {
    @opencv_core.Ptr
    public static native SURF create();

    @opencv_core.Ptr
    public static native SURF create(double d, int i, int i2, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Cast({"bool"})
    public native boolean getExtended();

    public native double getHessianThreshold();

    public native int getNOctaveLayers();

    public native int getNOctaves();

    @Cast({"bool"})
    public native boolean getUpright();

    public native void setExtended(@Cast({"bool"}) boolean z);

    public native void setHessianThreshold(double d);

    public native void setNOctaveLayers(int i);

    public native void setNOctaves(int i);

    public native void setUpright(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public SURF(Pointer pointer) {
        super(pointer);
    }
}
