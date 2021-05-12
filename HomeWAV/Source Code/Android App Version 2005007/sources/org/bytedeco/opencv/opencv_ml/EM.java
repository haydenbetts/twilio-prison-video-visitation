package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2d;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class EM extends StatModel {
    public static final int COV_MAT_DEFAULT = 1;
    public static final int COV_MAT_DIAGONAL = 1;
    public static final int COV_MAT_GENERIC = 2;
    public static final int COV_MAT_SPHERICAL = 0;
    public static final int DEFAULT_MAX_ITERS = 100;
    public static final int DEFAULT_NCLUSTERS = 5;
    public static final int START_AUTO_STEP = 0;
    public static final int START_E_STEP = 1;
    public static final int START_M_STEP = 2;

    @opencv_core.Ptr
    public static native EM create();

    @opencv_core.Ptr
    public static native EM load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native EM load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native EM load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native EM load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native int getClustersNumber();

    public native int getCovarianceMatrixType();

    public native void getCovs(@ByRef MatVector matVector);

    @ByVal
    public native Mat getMeans();

    @ByVal
    public native TermCriteria getTermCriteria();

    @ByVal
    public native Mat getWeights();

    public native float predict(@ByVal GpuMat gpuMat);

    public native float predict(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, int i);

    public native float predict(@ByVal Mat mat);

    public native float predict(@ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, int i);

    public native float predict(@ByVal UMat uMat);

    public native float predict(@ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, int i);

    @Cast({"cv::Vec2d*"})
    @ByVal
    public native Point2d predict2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"cv::Vec2d*"})
    @ByVal
    public native Point2d predict2(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"cv::Vec2d*"})
    @ByVal
    public native Point2d predict2(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setClustersNumber(int i);

    public native void setCovarianceMatrixType(int i);

    public native void setTermCriteria(@ByRef @Const TermCriteria termCriteria);

    @Cast({"bool"})
    public native boolean trainE(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean trainE(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7);

    @Cast({"bool"})
    public native boolean trainE(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean trainE(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7);

    @Cast({"bool"})
    public native boolean trainE(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Cast({"bool"})
    public native boolean trainE(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7);

    @Cast({"bool"})
    public native boolean trainEM(@ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean trainEM(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    @Cast({"bool"})
    public native boolean trainEM(@ByVal Mat mat);

    @Cast({"bool"})
    public native boolean trainEM(@ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    @Cast({"bool"})
    public native boolean trainEM(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean trainEM(@ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    @Cast({"bool"})
    public native boolean trainM(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean trainM(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    @Cast({"bool"})
    public native boolean trainM(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean trainM(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5);

    @Cast({"bool"})
    public native boolean trainM(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Cast({"bool"})
    public native boolean trainM(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    static {
        Loader.load();
    }

    public EM(Pointer pointer) {
        super(pointer);
    }
}
