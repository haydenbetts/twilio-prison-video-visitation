package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_optflow.DualTVL1OpticalFlow;
import org.bytedeco.opencv.opencv_video.DenseOpticalFlow;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_optflow extends org.bytedeco.opencv.presets.opencv_optflow {
    @Namespace("cv::motempl")
    public static native double calcGlobalOrientation(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2);

    @Namespace("cv::motempl")
    public static native double calcGlobalOrientation(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2);

    @Namespace("cv::motempl")
    public static native double calcGlobalOrientation(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2);

    @Namespace("cv::motempl")
    public static native void calcMotionGradient(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2);

    @Namespace("cv::motempl")
    public static native void calcMotionGradient(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2, int i);

    @Namespace("cv::motempl")
    public static native void calcMotionGradient(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2);

    @Namespace("cv::motempl")
    public static native void calcMotionGradient(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2, int i);

    @Namespace("cv::motempl")
    public static native void calcMotionGradient(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2);

    @Namespace("cv::motempl")
    public static native void calcMotionGradient(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2, int i);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSF(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, int i3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSF(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, int i3, double d, double d2, int i4, double d3, double d4, double d5, int i5, double d6, double d7, double d8);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSF(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, int i3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSF(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, int i3, double d, double d2, int i4, double d3, double d4, double d5, int i5, double d6, double d7, double d8);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSF(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, int i3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSF(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, int i3, double d, double d2, int i4, double d3, double d4, double d5, int i5, double d6, double d7, double d8);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSparseToDense(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSparseToDense(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, float f, @Cast({"bool"}) boolean z, float f2, float f3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSparseToDense(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSparseToDense(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, float f, @Cast({"bool"}) boolean z, float f2, float f3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSparseToDense(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::optflow")
    public static native void calcOpticalFlowSparseToDense(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, float f, @Cast({"bool"}) boolean z, float f2, float f3);

    @Namespace("cv::optflow")
    @opencv_core.Ptr
    public static native DenseOpticalFlow createOptFlow_DeepFlow();

    @Namespace("cv::optflow")
    @opencv_core.Ptr
    public static native DualTVL1OpticalFlow createOptFlow_DualTVL1();

    @Namespace("cv::optflow")
    @opencv_core.Ptr
    public static native DenseOpticalFlow createOptFlow_Farneback();

    @Namespace("cv::optflow")
    @opencv_core.Ptr
    public static native DenseOpticalFlow createOptFlow_SimpleFlow();

    @Namespace("cv::optflow")
    @opencv_core.Ptr
    public static native DenseOpticalFlow createOptFlow_SparseToDense();

    @Namespace("cv::motempl")
    public static native void segmentMotion(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef RectVector rectVector, double d, double d2);

    @Namespace("cv::motempl")
    public static native void segmentMotion(@ByVal Mat mat, @ByVal Mat mat2, @ByRef RectVector rectVector, double d, double d2);

    @Namespace("cv::motempl")
    public static native void segmentMotion(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef RectVector rectVector, double d, double d2);

    @Namespace("cv::motempl")
    public static native void updateMotionHistory(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2);

    @Namespace("cv::motempl")
    public static native void updateMotionHistory(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2);

    @Namespace("cv::motempl")
    public static native void updateMotionHistory(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2);

    static {
        Loader.load();
    }
}
