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
public class AgastFeatureDetector extends Feature2D {
    public static final int AGAST_5_8 = 0;
    public static final int AGAST_7_12d = 1;
    public static final int AGAST_7_12s = 2;
    public static final int NONMAX_SUPPRESSION = 10001;
    public static final int OAST_9_16 = 3;
    public static final int THRESHOLD = 10000;

    @opencv_core.Ptr
    public static native AgastFeatureDetector create();

    @opencv_core.Ptr
    public static native AgastFeatureDetector create(int i, @Cast({"bool"}) boolean z, @Cast({"cv::AgastFeatureDetector::DetectorType"}) int i2);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    @Cast({"bool"})
    public native boolean getNonmaxSuppression();

    public native int getThreshold();

    @Cast({"cv::AgastFeatureDetector::DetectorType"})
    public native int getType();

    public native void setNonmaxSuppression(@Cast({"bool"}) boolean z);

    public native void setThreshold(int i);

    public native void setType(@Cast({"cv::AgastFeatureDetector::DetectorType"}) int i);

    static {
        Loader.load();
    }

    public AgastFeatureDetector(Pointer pointer) {
        super(pointer);
    }
}
