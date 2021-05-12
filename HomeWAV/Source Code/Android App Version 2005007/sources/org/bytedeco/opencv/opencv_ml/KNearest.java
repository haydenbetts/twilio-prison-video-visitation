package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class KNearest extends StatModel {
    public static final int BRUTE_FORCE = 1;
    public static final int KDTREE = 2;

    @opencv_core.Ptr
    public static native KNearest create();

    @opencv_core.Ptr
    public static native KNearest load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native KNearest load(@opencv_core.Str BytePointer bytePointer);

    public native float findNearest(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2);

    public native float findNearest(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    public native float findNearest(@ByVal Mat mat, int i, @ByVal Mat mat2);

    public native float findNearest(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    public native float findNearest(@ByVal UMat uMat, int i, @ByVal UMat uMat2);

    public native float findNearest(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    public native int getAlgorithmType();

    public native int getDefaultK();

    public native int getEmax();

    @Cast({"bool"})
    public native boolean getIsClassifier();

    public native void setAlgorithmType(int i);

    public native void setDefaultK(int i);

    public native void setEmax(int i);

    public native void setIsClassifier(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public KNearest(Pointer pointer) {
        super(pointer);
    }
}
