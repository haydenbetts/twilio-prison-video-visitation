package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_cudabgsegm.BackgroundSubtractorMOG;
import org.bytedeco.opencv.opencv_cudabgsegm.BackgroundSubtractorMOG2;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_cudabgsegm extends org.bytedeco.opencv.presets.opencv_cudabgsegm {
    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG createBackgroundSubtractorMOG();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG createBackgroundSubtractorMOG(int i, int i2, double d, double d2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2(int i, double d, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }
}
