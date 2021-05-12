package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class ORB extends Feature2D {
    public static final int FAST_SCORE = 1;
    public static final int HARRIS_SCORE = 0;
    public static final int kBytes = kBytes();

    @opencv_core.Ptr
    public static native ORB create();

    @opencv_core.Ptr
    public static native ORB create(int i, float f, int i2, int i3, int i4, int i5, @Cast({"cv::ORB::ScoreType"}) int i6, int i7, int i8);

    @MemberGetter
    public static native int kBytes();

    @opencv_core.Str
    public native BytePointer getDefaultName();

    public native int getEdgeThreshold();

    public native int getFastThreshold();

    public native int getFirstLevel();

    public native int getMaxFeatures();

    public native int getNLevels();

    public native int getPatchSize();

    public native double getScaleFactor();

    @Cast({"cv::ORB::ScoreType"})
    public native int getScoreType();

    public native int getWTA_K();

    public native void setEdgeThreshold(int i);

    public native void setFastThreshold(int i);

    public native void setFirstLevel(int i);

    public native void setMaxFeatures(int i);

    public native void setNLevels(int i);

    public native void setPatchSize(int i);

    public native void setScaleFactor(double d);

    public native void setScoreType(@Cast({"cv::ORB::ScoreType"}) int i);

    public native void setWTA_K(int i);

    static {
        Loader.load();
    }

    public ORB(Pointer pointer) {
        super(pointer);
    }
}
