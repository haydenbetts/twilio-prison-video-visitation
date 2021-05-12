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
public class FastFeatureDetector extends Feature2D {
    public static final int FAST_N = 10002;
    public static final int NONMAX_SUPPRESSION = 10001;
    public static final int THRESHOLD = 10000;
    public static final int TYPE_5_8 = 0;
    public static final int TYPE_7_12 = 1;
    public static final int TYPE_9_16 = 2;

    @opencv_core.Ptr
    public static native FastFeatureDetector create();

    @opencv_core.Ptr
    public static native FastFeatureDetector create(int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    @Cast({"bool"})
    public native boolean getNonmaxSuppression();

    public native int getThreshold();

    @Cast({"cv::FastFeatureDetector::DetectorType"})
    public native int getType();

    public native void setNonmaxSuppression(@Cast({"bool"}) boolean z);

    public native void setThreshold(int i);

    public native void setType(@Cast({"cv::FastFeatureDetector::DetectorType"}) int i);

    static {
        Loader.load();
    }

    public FastFeatureDetector(Pointer pointer) {
        super(pointer);
    }
}
