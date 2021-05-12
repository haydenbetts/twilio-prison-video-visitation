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
public class BoostDesc extends Feature2D {
    public static final int BGM = 100;
    public static final int BGM_BILINEAR = 102;
    public static final int BGM_HARD = 101;
    public static final int BINBOOST_128 = 301;
    public static final int BINBOOST_256 = 302;
    public static final int BINBOOST_64 = 300;
    public static final int LBGM = 200;

    @opencv_core.Ptr
    public static native BoostDesc create();

    @opencv_core.Ptr
    public static native BoostDesc create(int i, @Cast({"bool"}) boolean z, float f);

    public native float getScaleFactor();

    @Cast({"bool"})
    public native boolean getUseScaleOrientation();

    public native void setScaleFactor(float f);

    public native void setUseScaleOrientation(@Cast({"const bool"}) boolean z);

    static {
        Loader.load();
    }

    public BoostDesc(Pointer pointer) {
        super(pointer);
    }
}
