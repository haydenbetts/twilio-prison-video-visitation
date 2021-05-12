package org.bytedeco.opencv.opencv_xfeatures2d;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.KeyPointVectorVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class DAISY extends Feature2D {
    public static final int NRM_FULL = 102;
    public static final int NRM_NONE = 100;
    public static final int NRM_PARTIAL = 101;
    public static final int NRM_SIFT = 103;

    @opencv_core.Ptr
    public static native DAISY create();

    @opencv_core.Ptr
    public static native DAISY create(float f, int i, int i2, int i3, @Cast({"cv::xfeatures2d::DAISY::NormalizationType"}) int i4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @opencv_core.Ptr
    public static native DAISY create(float f, int i, int i2, int i3, @Cast({"cv::xfeatures2d::DAISY::NormalizationType"}) int i4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @opencv_core.Ptr
    public static native DAISY create(float f, int i, int i2, int i3, @Cast({"cv::xfeatures2d::DAISY::NormalizationType"}) int i4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    public native void GetDescriptor(double d, double d2, int i, FloatBuffer floatBuffer);

    public native void GetDescriptor(double d, double d2, int i, FloatPointer floatPointer);

    public native void GetDescriptor(double d, double d2, int i, float[] fArr);

    @Cast({"bool"})
    public native boolean GetDescriptor(double d, double d2, int i, FloatBuffer floatBuffer, DoubleBuffer doubleBuffer);

    @Cast({"bool"})
    public native boolean GetDescriptor(double d, double d2, int i, FloatPointer floatPointer, DoublePointer doublePointer);

    @Cast({"bool"})
    public native boolean GetDescriptor(double d, double d2, int i, float[] fArr, double[] dArr);

    public native void GetUnnormalizedDescriptor(double d, double d2, int i, FloatBuffer floatBuffer);

    public native void GetUnnormalizedDescriptor(double d, double d2, int i, FloatPointer floatPointer);

    public native void GetUnnormalizedDescriptor(double d, double d2, int i, float[] fArr);

    @Cast({"bool"})
    public native boolean GetUnnormalizedDescriptor(double d, double d2, int i, FloatBuffer floatBuffer, DoubleBuffer doubleBuffer);

    @Cast({"bool"})
    public native boolean GetUnnormalizedDescriptor(double d, double d2, int i, FloatPointer floatPointer, DoublePointer doublePointer);

    @Cast({"bool"})
    public native boolean GetUnnormalizedDescriptor(double d, double d2, int i, float[] fArr, double[] dArr);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal Rect rect, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMatVector gpuMatVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal GpuMatVector gpuMatVector2);

    public native void compute(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByVal Rect rect, @ByVal Mat mat2);

    public native void compute(@ByVal MatVector matVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal MatVector matVector2);

    public native void compute(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByVal Rect rect, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void compute(@ByVal UMatVector uMatVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal UMatVector uMatVector2);

    static {
        Loader.load();
    }

    public DAISY(Pointer pointer) {
        super(pointer);
    }
}
