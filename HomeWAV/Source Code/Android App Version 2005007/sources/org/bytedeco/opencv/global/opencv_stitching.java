package org.bytedeco.opencv.global;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.SizeVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.opencv_stitching.Graph;
import org.bytedeco.opencv.opencv_stitching.ImageFeatures;
import org.bytedeco.opencv.opencv_stitching.MatchesInfo;
import org.bytedeco.opencv.opencv_stitching.Stitcher;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_stitching extends org.bytedeco.opencv.presets.opencv_stitching {
    public static final int WAVE_CORRECT_HORIZ = 0;
    public static final int WAVE_CORRECT_VERT = 1;

    @Namespace("cv::detail")
    @Cast({"bool"})
    public static native boolean calibrateRotatingCamera(@ByRef @Const MatVector matVector, @ByRef Mat mat);

    @Namespace("cv::detail")
    public static native void computeImageFeatures(@opencv_core.Ptr Feature2D feature2D, @ByVal GpuMatVector gpuMatVector, @StdVector ImageFeatures imageFeatures);

    @Namespace("cv::detail")
    public static native void computeImageFeatures(@opencv_core.Ptr Feature2D feature2D, @ByVal GpuMatVector gpuMatVector, @StdVector ImageFeatures imageFeatures, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2);

    @Namespace("cv::detail")
    public static native void computeImageFeatures(@opencv_core.Ptr Feature2D feature2D, @ByVal MatVector matVector, @StdVector ImageFeatures imageFeatures);

    @Namespace("cv::detail")
    public static native void computeImageFeatures(@opencv_core.Ptr Feature2D feature2D, @ByVal MatVector matVector, @StdVector ImageFeatures imageFeatures, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector2);

    @Namespace("cv::detail")
    public static native void computeImageFeatures(@opencv_core.Ptr Feature2D feature2D, @ByVal UMatVector uMatVector, @StdVector ImageFeatures imageFeatures);

    @Namespace("cv::detail")
    public static native void computeImageFeatures(@opencv_core.Ptr Feature2D feature2D, @ByVal UMatVector uMatVector, @StdVector ImageFeatures imageFeatures, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector2);

    @Namespace("cv::detail")
    @Name({"computeImageFeatures"})
    public static native void computeImageFeatures2(@opencv_core.Ptr Feature2D feature2D, @ByVal GpuMat gpuMat, @ByRef ImageFeatures imageFeatures);

    @Namespace("cv::detail")
    @Name({"computeImageFeatures"})
    public static native void computeImageFeatures2(@opencv_core.Ptr Feature2D feature2D, @ByVal GpuMat gpuMat, @ByRef ImageFeatures imageFeatures, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::detail")
    @Name({"computeImageFeatures"})
    public static native void computeImageFeatures2(@opencv_core.Ptr Feature2D feature2D, @ByVal Mat mat, @ByRef ImageFeatures imageFeatures);

    @Namespace("cv::detail")
    @Name({"computeImageFeatures"})
    public static native void computeImageFeatures2(@opencv_core.Ptr Feature2D feature2D, @ByVal Mat mat, @ByRef ImageFeatures imageFeatures, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::detail")
    @Name({"computeImageFeatures"})
    public static native void computeImageFeatures2(@opencv_core.Ptr Feature2D feature2D, @ByVal UMat uMat, @ByRef ImageFeatures imageFeatures);

    @Namespace("cv::detail")
    @Name({"computeImageFeatures"})
    public static native void computeImageFeatures2(@opencv_core.Ptr Feature2D feature2D, @ByVal UMat uMat, @ByRef ImageFeatures imageFeatures, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::detail")
    public static native void createLaplacePyr(@ByVal GpuMat gpuMat, int i, @ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    public static native void createLaplacePyr(@ByVal Mat mat, int i, @ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    public static native void createLaplacePyr(@ByVal UMat uMat, int i, @ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    public static native void createLaplacePyrGpu(@ByVal GpuMat gpuMat, int i, @ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    public static native void createLaplacePyrGpu(@ByVal Mat mat, int i, @ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    public static native void createLaplacePyrGpu(@ByVal UMat uMat, int i, @ByRef UMatVector uMatVector);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Stitcher createStitcher();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Stitcher createStitcher(@Cast({"bool"}) boolean z);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Stitcher createStitcherScans();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Stitcher createStitcherScans(@Cast({"bool"}) boolean z);

    @Namespace("cv::detail")
    public static native void createWeightMap(@ByVal GpuMat gpuMat, float f, @ByVal GpuMat gpuMat2);

    @Namespace("cv::detail")
    public static native void createWeightMap(@ByVal Mat mat, float f, @ByVal Mat mat2);

    @Namespace("cv::detail")
    public static native void createWeightMap(@ByVal UMat uMat, float f, @ByVal UMat uMat2);

    @Namespace("cv::detail")
    public static native void estimateFocal(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, @StdVector DoubleBuffer doubleBuffer);

    @Namespace("cv::detail")
    public static native void estimateFocal(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, @StdVector DoublePointer doublePointer);

    @Namespace("cv::detail")
    public static native void estimateFocal(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, @StdVector double[] dArr);

    @Namespace("cv::detail")
    public static native void findMaxSpanningTree(int i, @StdVector MatchesInfo matchesInfo, @ByRef Graph graph, @StdVector IntBuffer intBuffer);

    @Namespace("cv::detail")
    public static native void findMaxSpanningTree(int i, @StdVector MatchesInfo matchesInfo, @ByRef Graph graph, @StdVector IntPointer intPointer);

    @Namespace("cv::detail")
    public static native void findMaxSpanningTree(int i, @StdVector MatchesInfo matchesInfo, @ByRef Graph graph, @StdVector int[] iArr);

    @Namespace("cv::detail")
    public static native void focalsFromHomography(@ByRef @Const Mat mat, @ByRef DoubleBuffer doubleBuffer, @ByRef DoubleBuffer doubleBuffer2, @ByRef @Cast({"bool*"}) BoolPointer boolPointer, @ByRef @Cast({"bool*"}) BoolPointer boolPointer2);

    @Namespace("cv::detail")
    public static native void focalsFromHomography(@ByRef @Const Mat mat, @ByRef DoubleBuffer doubleBuffer, @ByRef DoubleBuffer doubleBuffer2, @ByRef @Cast({"bool*"}) boolean[] zArr, @ByRef @Cast({"bool*"}) boolean[] zArr2);

    @Namespace("cv::detail")
    public static native void focalsFromHomography(@ByRef @Const Mat mat, @ByRef DoublePointer doublePointer, @ByRef DoublePointer doublePointer2, @ByRef @Cast({"bool*"}) BoolPointer boolPointer, @ByRef @Cast({"bool*"}) BoolPointer boolPointer2);

    @Namespace("cv::detail")
    public static native void focalsFromHomography(@ByRef @Const Mat mat, @ByRef DoublePointer doublePointer, @ByRef DoublePointer doublePointer2, @ByRef @Cast({"bool*"}) boolean[] zArr, @ByRef @Cast({"bool*"}) boolean[] zArr2);

    @Namespace("cv::detail")
    public static native void focalsFromHomography(@ByRef @Const Mat mat, @ByRef double[] dArr, @ByRef double[] dArr2, @ByRef @Cast({"bool*"}) BoolPointer boolPointer, @ByRef @Cast({"bool*"}) BoolPointer boolPointer2);

    @Namespace("cv::detail")
    public static native void focalsFromHomography(@ByRef @Const Mat mat, @ByRef double[] dArr, @ByRef double[] dArr2, @ByRef @Cast({"bool*"}) boolean[] zArr, @ByRef @Cast({"bool*"}) boolean[] zArr2);

    @Namespace("cv::detail")
    @StdVector
    public static native IntPointer leaveBiggestComponent(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, float f);

    @Namespace("cv::detail")
    @opencv_core.Str
    public static native BytePointer matchesGraphAsString(@ByRef StringVector stringVector, @StdVector MatchesInfo matchesInfo, float f);

    @Namespace("cv::detail")
    public static native void normalizeUsingWeightMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::detail")
    public static native void normalizeUsingWeightMap(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::detail")
    public static native void normalizeUsingWeightMap(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::detail")
    @Cast({"bool"})
    public static native boolean overlapRoi(@ByVal Point point, @ByVal Point point2, @ByVal Size size, @ByVal Size size2, @ByRef Rect rect);

    @Namespace("cv::detail")
    public static native void restoreImageFromLaplacePyr(@ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    public static native void restoreImageFromLaplacePyrGpu(@ByRef UMatVector uMatVector);

    @Namespace("cv::detail")
    @ByVal
    public static native Rect resultRoi(@ByRef @Const PointVector pointVector, @ByRef @Const SizeVector sizeVector);

    @Namespace("cv::detail")
    @ByVal
    public static native Rect resultRoi(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector);

    @Namespace("cv::detail")
    @ByVal
    public static native Rect resultRoiIntersection(@ByRef @Const PointVector pointVector, @ByRef @Const SizeVector sizeVector);

    @Namespace("cv::detail")
    @ByVal
    public static native Point resultTl(@ByRef @Const PointVector pointVector);

    @Namespace("cv::detail")
    public static native void selectRandomSubset(int i, int i2, @StdVector IntBuffer intBuffer);

    @Namespace("cv::detail")
    public static native void selectRandomSubset(int i, int i2, @StdVector IntPointer intPointer);

    @Namespace("cv::detail")
    public static native void selectRandomSubset(int i, int i2, @StdVector int[] iArr);

    @Namespace("cv::detail")
    @ByRef
    public static native IntPointer stitchingLogLevel();

    @Namespace("cv::detail")
    public static native void waveCorrect(@ByRef MatVector matVector, @Cast({"cv::detail::WaveCorrectKind"}) int i);

    static {
        Loader.load();
    }
}
