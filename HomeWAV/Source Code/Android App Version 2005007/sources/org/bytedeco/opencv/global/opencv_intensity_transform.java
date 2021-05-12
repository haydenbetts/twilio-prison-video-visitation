package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;

public class opencv_intensity_transform extends org.bytedeco.opencv.presets.opencv_intensity_transform {
    @Namespace("cv::intensity_transform")
    public static native void BIMEF(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::intensity_transform")
    public static native void BIMEF(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2, float f3);

    @Namespace("cv::intensity_transform")
    public static native void BIMEF(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::intensity_transform")
    public static native void BIMEF(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2, float f3);

    @Namespace("cv::intensity_transform")
    public static native void BIMEF(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::intensity_transform")
    public static native void BIMEF(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2, float f3);

    @Namespace("cv::intensity_transform")
    @Name({"BIMEF"})
    public static native void BIMEF2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2, float f3, float f4);

    @Namespace("cv::intensity_transform")
    @Name({"BIMEF"})
    public static native void BIMEF2(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2, float f3, float f4);

    @Namespace("cv::intensity_transform")
    @Name({"BIMEF"})
    public static native void BIMEF2(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2, float f3, float f4);

    @Namespace("cv::intensity_transform")
    public static native void autoscaling(@Const @ByVal Mat mat, @ByRef Mat mat2);

    @Namespace("cv::intensity_transform")
    public static native void contrastStretching(@Const @ByVal Mat mat, @ByRef Mat mat2, int i, int i2, int i3, int i4);

    @Namespace("cv::intensity_transform")
    public static native void gammaCorrection(@Const @ByVal Mat mat, @ByRef Mat mat2, float f);

    @Namespace("cv::intensity_transform")
    public static native void logTransform(@Const @ByVal Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }
}
