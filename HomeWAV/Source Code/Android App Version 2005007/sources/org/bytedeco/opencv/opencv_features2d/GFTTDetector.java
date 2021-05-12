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
public class GFTTDetector extends Feature2D {
    @opencv_core.Ptr
    public static native GFTTDetector create();

    @opencv_core.Ptr
    public static native GFTTDetector create(int i, double d, double d2, int i2, int i3);

    @opencv_core.Ptr
    public static native GFTTDetector create(int i, double d, double d2, int i2, int i3, @Cast({"bool"}) boolean z, double d3);

    @opencv_core.Ptr
    public static native GFTTDetector create(int i, double d, double d2, int i2, @Cast({"bool"}) boolean z, double d3);

    public native int getBlockSize();

    @opencv_core.Str
    public native BytePointer getDefaultName();

    @Cast({"bool"})
    public native boolean getHarrisDetector();

    public native double getK();

    public native int getMaxFeatures();

    public native double getMinDistance();

    public native double getQualityLevel();

    public native void setBlockSize(int i);

    public native void setHarrisDetector(@Cast({"bool"}) boolean z);

    public native void setK(double d);

    public native void setMaxFeatures(int i);

    public native void setMinDistance(double d);

    public native void setQualityLevel(double d);

    static {
        Loader.load();
    }

    public GFTTDetector(Pointer pointer) {
        super(pointer);
    }
}
