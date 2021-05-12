package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RotatedRect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractorKNN;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractorMOG2;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_video extends org.bytedeco.opencv.presets.opencv_video {
    public static final int MOTION_AFFINE = 2;
    public static final int MOTION_EUCLIDEAN = 1;
    public static final int MOTION_HOMOGRAPHY = 3;
    public static final int MOTION_TRANSLATION = 0;
    public static final int OPTFLOW_FARNEBACK_GAUSSIAN = 256;
    public static final int OPTFLOW_LK_GET_MIN_EIGENVALS = 8;
    public static final int OPTFLOW_USE_INITIAL_FLOW = 4;

    @Namespace("cv")
    @ByVal
    public static native RotatedRect CamShift(@ByVal GpuMat gpuMat, @ByRef Rect rect, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect CamShift(@ByVal Mat mat, @ByRef Rect rect, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect CamShift(@ByVal UMat uMat, @ByRef Rect rect, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal Size size, int i);

    @Namespace("cv")
    public static native int buildOpticalFlowPyramid(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal Size size, int i, @Cast({"bool"}) boolean z, int i2, int i3, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcOpticalFlowFarneback(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i, int i2, int i3, int i4, double d2, int i5);

    @Namespace("cv")
    public static native void calcOpticalFlowFarneback(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i, int i2, int i3, int i4, double d2, int i5);

    @Namespace("cv")
    public static native void calcOpticalFlowFarneback(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i, int i2, int i3, int i4, double d2, int i5);

    @Namespace("cv")
    public static native void calcOpticalFlowPyrLK(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    public static native void calcOpticalFlowPyrLK(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal(nullValue = "cv::Size(21,21)") Size size, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 30, 0.01)") TermCriteria termCriteria, int i2, double d);

    @Namespace("cv")
    public static native void calcOpticalFlowPyrLK(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    public static native void calcOpticalFlowPyrLK(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal(nullValue = "cv::Size(21,21)") Size size, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 30, 0.01)") TermCriteria termCriteria, int i2, double d);

    @Namespace("cv")
    public static native void calcOpticalFlowPyrLK(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    public static native void calcOpticalFlowPyrLK(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal(nullValue = "cv::Size(21,21)") Size size, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 30, 0.01)") TermCriteria termCriteria, int i2, double d);

    @Namespace("cv")
    public static native double computeECC(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native double computeECC(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native double computeECC(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native double computeECC(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native double computeECC(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native double computeECC(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native BackgroundSubtractorKNN createBackgroundSubtractorKNN();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native BackgroundSubtractorKNN createBackgroundSubtractorKNN(int i, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2(int i, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @Deprecated
    @ByVal
    public static native Mat estimateRigidTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @Deprecated
    @ByVal
    public static native Mat estimateRigidTransform(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @Deprecated
    @ByVal
    public static native Mat estimateRigidTransform(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 50, 0.001)") TermCriteria termCriteria, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByVal TermCriteria termCriteria, @ByVal GpuMat gpuMat4, int i2);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 50, 0.001)") TermCriteria termCriteria, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByVal TermCriteria termCriteria, @ByVal Mat mat4, int i2);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 50, 0.001)") TermCriteria termCriteria, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native double findTransformECC(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByVal TermCriteria termCriteria, @ByVal UMat uMat4, int i2);

    @Namespace("cv")
    public static native int meanShift(@ByVal GpuMat gpuMat, @ByRef Rect rect, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    public static native int meanShift(@ByVal Mat mat, @ByRef Rect rect, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    public static native int meanShift(@ByVal UMat uMat, @ByRef Rect rect, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    @ByVal
    public static native Mat readOpticalFlow(@opencv_core.Str String str);

    @Namespace("cv")
    @ByVal
    public static native Mat readOpticalFlow(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean writeOpticalFlow(@opencv_core.Str String str, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean writeOpticalFlow(@opencv_core.Str String str, @ByVal Mat mat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean writeOpticalFlow(@opencv_core.Str String str, @ByVal UMat uMat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean writeOpticalFlow(@opencv_core.Str BytePointer bytePointer, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean writeOpticalFlow(@opencv_core.Str BytePointer bytePointer, @ByVal Mat mat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean writeOpticalFlow(@opencv_core.Str BytePointer bytePointer, @ByVal UMat uMat);

    static {
        Loader.load();
    }
}
