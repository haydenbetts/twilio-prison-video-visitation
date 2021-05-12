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
public class VGG extends Feature2D {
    public static final int VGG_120 = 100;
    public static final int VGG_48 = 103;
    public static final int VGG_64 = 102;
    public static final int VGG_80 = 101;

    @opencv_core.Ptr
    public static native VGG create();

    @opencv_core.Ptr
    public static native VGG create(int i, float f, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f2, @Cast({"bool"}) boolean z3);

    public native float getScaleFactor();

    public native float getSigma();

    @Cast({"bool"})
    public native boolean getUseNormalizeDescriptor();

    @Cast({"bool"})
    public native boolean getUseNormalizeImage();

    @Cast({"bool"})
    public native boolean getUseScaleOrientation();

    public native void setScaleFactor(float f);

    public native void setSigma(float f);

    public native void setUseNormalizeDescriptor(@Cast({"const bool"}) boolean z);

    public native void setUseNormalizeImage(@Cast({"const bool"}) boolean z);

    public native void setUseScaleOrientation(@Cast({"const bool"}) boolean z);

    static {
        Loader.load();
    }

    public VGG(Pointer pointer) {
        super(pointer);
    }
}
