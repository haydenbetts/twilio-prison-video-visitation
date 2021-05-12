package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class KAZE extends Feature2D {
    public static final int DIFF_CHARBONNIER = 3;
    public static final int DIFF_PM_G1 = 0;
    public static final int DIFF_PM_G2 = 1;
    public static final int DIFF_WEICKERT = 2;

    @opencv_core.Ptr
    public static native KAZE create();

    @opencv_core.Ptr
    public static native KAZE create(@Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f, int i, int i2, @Cast({"cv::KAZE::DiffusivityType"}) int i3);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    @Cast({"cv::KAZE::DiffusivityType"})
    public native int getDiffusivity();

    @Cast({"bool"})
    public native boolean getExtended();

    public native int getNOctaveLayers();

    public native int getNOctaves();

    public native double getThreshold();

    @Cast({"bool"})
    public native boolean getUpright();

    public native void setDiffusivity(@Cast({"cv::KAZE::DiffusivityType"}) int i);

    public native void setExtended(@Cast({"bool"}) boolean z);

    public native void setNOctaveLayers(int i);

    public native void setNOctaves(int i);

    public native void setThreshold(double d);

    public native void setUpright(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public KAZE(Pointer pointer) {
        super(pointer);
    }
}
