package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_bgsegm.BackgroundSubtractorCNT;
import org.bytedeco.opencv.opencv_bgsegm.BackgroundSubtractorGMG;
import org.bytedeco.opencv.opencv_bgsegm.BackgroundSubtractorGSOC;
import org.bytedeco.opencv.opencv_bgsegm.BackgroundSubtractorLSBP;
import org.bytedeco.opencv.opencv_bgsegm.BackgroundSubtractorMOG;
import org.bytedeco.opencv.opencv_bgsegm.SyntheticSequenceGenerator;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_bgsegm extends org.bytedeco.opencv.presets.opencv_bgsegm {
    public static final int LSBP_CAMERA_MOTION_COMPENSATION_LK = 1;
    public static final int LSBP_CAMERA_MOTION_COMPENSATION_NONE = 0;

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorCNT createBackgroundSubtractorCNT();

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorCNT createBackgroundSubtractorCNT(int i, @Cast({"bool"}) boolean z, int i2, @Cast({"bool"}) boolean z2);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorGMG createBackgroundSubtractorGMG();

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorGMG createBackgroundSubtractorGMG(int i, double d);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorGSOC createBackgroundSubtractorGSOC();

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, float f7, float f8);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorLSBP createBackgroundSubtractorLSBP();

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i4, int i5);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG createBackgroundSubtractorMOG();

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG createBackgroundSubtractorMOG(int i, int i2, double d, double d2);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native SyntheticSequenceGenerator createSyntheticSequenceGenerator(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native SyntheticSequenceGenerator createSyntheticSequenceGenerator(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, double d3, double d4);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native SyntheticSequenceGenerator createSyntheticSequenceGenerator(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native SyntheticSequenceGenerator createSyntheticSequenceGenerator(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, double d3, double d4);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native SyntheticSequenceGenerator createSyntheticSequenceGenerator(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::bgsegm")
    @opencv_core.Ptr
    public static native SyntheticSequenceGenerator createSyntheticSequenceGenerator(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, double d3, double d4);

    static {
        Loader.load();
    }
}
