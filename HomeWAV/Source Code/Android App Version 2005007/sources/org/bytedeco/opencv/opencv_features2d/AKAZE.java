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
public class AKAZE extends Feature2D {
    public static final int DESCRIPTOR_KAZE = 3;
    public static final int DESCRIPTOR_KAZE_UPRIGHT = 2;
    public static final int DESCRIPTOR_MLDB = 5;
    public static final int DESCRIPTOR_MLDB_UPRIGHT = 4;

    @opencv_core.Ptr
    public static native AKAZE create();

    @opencv_core.Ptr
    public static native AKAZE create(@Cast({"cv::AKAZE::DescriptorType"}) int i, int i2, int i3, float f, int i4, int i5, @Cast({"cv::KAZE::DiffusivityType"}) int i6);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    public native int getDescriptorChannels();

    public native int getDescriptorSize();

    @Cast({"cv::AKAZE::DescriptorType"})
    public native int getDescriptorType();

    @Cast({"cv::KAZE::DiffusivityType"})
    public native int getDiffusivity();

    public native int getNOctaveLayers();

    public native int getNOctaves();

    public native double getThreshold();

    public native void setDescriptorChannels(int i);

    public native void setDescriptorSize(int i);

    public native void setDescriptorType(@Cast({"cv::AKAZE::DescriptorType"}) int i);

    public native void setDiffusivity(@Cast({"cv::KAZE::DiffusivityType"}) int i);

    public native void setNOctaveLayers(int i);

    public native void setNOctaves(int i);

    public native void setThreshold(double d);

    static {
        Loader.load();
    }

    public AKAZE(Pointer pointer) {
        super(pointer);
    }
}
