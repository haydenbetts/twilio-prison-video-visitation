package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_superres.BroxOpticalFlow;
import org.bytedeco.opencv.opencv_superres.FrameSource;
import org.bytedeco.opencv.opencv_superres.PyrLKOpticalFlow;
import org.bytedeco.opencv.opencv_superres.SuperResDualTVL1OpticalFlow;
import org.bytedeco.opencv.opencv_superres.SuperResFarnebackOpticalFlow;
import org.bytedeco.opencv.opencv_superres.SuperResolution;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_superres extends org.bytedeco.opencv.presets.opencv_superres {
    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Camera();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Camera(int i);

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Empty();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Video(@opencv_core.Str String str);

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Video(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Video_CUDA(@opencv_core.Str String str);

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native FrameSource createFrameSource_Video_CUDA(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native BroxOpticalFlow createOptFlow_Brox_CUDA();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native SuperResDualTVL1OpticalFlow createOptFlow_DualTVL1();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native SuperResDualTVL1OpticalFlow createOptFlow_DualTVL1_CUDA();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native SuperResFarnebackOpticalFlow createOptFlow_Farneback();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native SuperResFarnebackOpticalFlow createOptFlow_Farneback_CUDA();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native PyrLKOpticalFlow createOptFlow_PyrLK_CUDA();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native SuperResolution createSuperResolution_BTVL1();

    @Namespace("cv::superres")
    @opencv_core.Ptr
    public static native SuperResolution createSuperResolution_BTVL1_CUDA();

    static {
        Loader.load();
    }
}
