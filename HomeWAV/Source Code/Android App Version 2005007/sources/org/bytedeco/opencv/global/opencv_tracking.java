package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.Rect2d;
import org.bytedeco.opencv.opencv_tracking.AugmentedUnscentedKalmanFilterParams;
import org.bytedeco.opencv.opencv_tracking.UnscentedKalmanFilter;
import org.bytedeco.opencv.opencv_tracking.UnscentedKalmanFilterParams;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_tracking extends org.bytedeco.opencv.presets.opencv_tracking {
    public static final String CC_FEATURES = "features";
    public static final String CC_FEATURE_PARAMS = "featureParams";
    public static final String CC_FEATURE_SIZE = "featSize";
    public static final String CC_ISINTEGRAL = "isIntegral";
    public static final String CC_MAX_CAT_COUNT = "maxCatCount";
    public static final String CC_NUM_FEATURES = "numFeat";
    public static final String CC_RECT = "rect";
    public static final String CC_RECTS = "rects";
    public static final String CC_TILTED = "tilted";
    public static final int CV_HAAR_FEATURE_MAX = 3;
    public static final String FEATURES = "features";
    public static final String HFP_NAME = "haarFeatureParams";
    public static final String HOGF_NAME = "HOGFeatureParams";
    public static final String LBPF_NAME = "lbpFeatureParams";
    public static final int N_BINS = 9;
    public static final int N_CELLS = 4;

    @Namespace("cv::tracking")
    @opencv_core.Ptr
    public static native UnscentedKalmanFilter createAugmentedUnscentedKalmanFilter(@ByRef @Const AugmentedUnscentedKalmanFilterParams augmentedUnscentedKalmanFilterParams);

    @Namespace("cv::tracking")
    @opencv_core.Ptr
    public static native UnscentedKalmanFilter createUnscentedKalmanFilter(@ByRef @Const UnscentedKalmanFilterParams unscentedKalmanFilterParams);

    @Namespace("cv::tld")
    @ByVal
    public static native Rect2d tld_InitDataset(int i);

    @Namespace("cv::tld")
    @ByVal
    public static native Rect2d tld_InitDataset(int i, String str, int i2);

    @Namespace("cv::tld")
    @ByVal
    public static native Rect2d tld_InitDataset(int i, @Cast({"const char*"}) BytePointer bytePointer, int i2);

    @Namespace("cv::tld")
    @opencv_core.Str
    public static native BytePointer tld_getNextDatasetFrame();

    static {
        Loader.load();
    }
}
