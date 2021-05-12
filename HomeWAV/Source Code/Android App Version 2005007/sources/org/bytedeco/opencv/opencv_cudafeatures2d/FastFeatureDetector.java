package org.bytedeco.opencv.opencv_cudafeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudafeatures2d;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudafeatures2d.class})
@NoOffset
public class FastFeatureDetector extends Feature2DAsync {
    public static final int FEATURE_SIZE = FEATURE_SIZE();
    public static final int LOCATION_ROW = LOCATION_ROW();
    public static final int RESPONSE_ROW = RESPONSE_ROW();
    public static final int ROWS_COUNT = ROWS_COUNT();

    @MemberGetter
    public static native int FEATURE_SIZE();

    @MemberGetter
    public static native int LOCATION_ROW();

    @MemberGetter
    public static native int RESPONSE_ROW();

    @MemberGetter
    public static native int ROWS_COUNT();

    @opencv_core.Ptr
    public static native FastFeatureDetector create();

    @opencv_core.Ptr
    public static native FastFeatureDetector create(int i, @Cast({"bool"}) boolean z, int i2, int i3);

    public native int getMaxNumPoints();

    public native void setMaxNumPoints(int i);

    public native void setThreshold(int i);

    static {
        Loader.load();
    }

    public FastFeatureDetector(Pointer pointer) {
        super(pointer);
    }
}
