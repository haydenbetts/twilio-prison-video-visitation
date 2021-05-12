package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_calib3d.StereoMatcher;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar4i;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_ximgproc.AdaptiveManifoldFilter;
import org.bytedeco.opencv.opencv_ximgproc.DTFilter;
import org.bytedeco.opencv.opencv_ximgproc.DisparityWLSFilter;
import org.bytedeco.opencv.opencv_ximgproc.EdgeAwareInterpolator;
import org.bytedeco.opencv.opencv_ximgproc.FastBilateralSolverFilter;
import org.bytedeco.opencv.opencv_ximgproc.FastGlobalSmootherFilter;
import org.bytedeco.opencv.opencv_ximgproc.GraphSegmentation;
import org.bytedeco.opencv.opencv_ximgproc.GuidedFilter;
import org.bytedeco.opencv.opencv_ximgproc.RFFeatureGetter;
import org.bytedeco.opencv.opencv_ximgproc.RICInterpolator;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentation;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentationStrategy;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentationStrategyColor;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentationStrategyFill;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentationStrategyMultiple;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentationStrategySize;
import org.bytedeco.opencv.opencv_ximgproc.SelectiveSearchSegmentationStrategyTexture;
import org.bytedeco.opencv.opencv_ximgproc.StructuredEdgeDetection;
import org.bytedeco.opencv.opencv_ximgproc.SuperpixelLSC;
import org.bytedeco.opencv.opencv_ximgproc.SuperpixelSEEDS;
import org.bytedeco.opencv.opencv_ximgproc.SuperpixelSLIC;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_ximgproc extends org.bytedeco.opencv.presets.opencv_ximgproc {
    public static final int AM_FILTER = 4;
    public static final int ARO_0_45 = 0;
    public static final int ARO_315_0 = 3;
    public static final int ARO_315_135 = 6;
    public static final int ARO_315_45 = 4;
    public static final int ARO_45_135 = 5;
    public static final int ARO_45_90 = 1;
    public static final int ARO_90_135 = 2;
    public static final int ARO_CTR_HOR = 7;
    public static final int ARO_CTR_VER = 8;
    public static final int BINARIZATION_NIBLACK = 0;
    public static final int BINARIZATION_NICK = 3;
    public static final int BINARIZATION_SAUVOLA = 1;
    public static final int BINARIZATION_WOLF = 2;
    public static final int DTF_IC = 1;
    public static final int DTF_NC = 0;
    public static final int DTF_RF = 2;
    public static final int FHT_ADD = 2;
    public static final int FHT_AVE = 3;
    public static final int FHT_MAX = 1;
    public static final int FHT_MIN = 0;
    public static final int GUIDED_FILTER = 3;
    public static final int HDO_DESKEW = 1;
    public static final int HDO_RAW = 0;
    public static final int MSLIC = 102;
    public static final int RO_IGNORE_BORDERS = 1;
    public static final int RO_STRICT = 0;
    public static final int SLIC = 100;
    public static final int SLICO = 101;
    public static final int THINNING_GUOHALL = 1;
    public static final int THINNING_ZHANGSUEN = 0;

    @Namespace("cv::ximgproc")
    public static native void FastHoughTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::ximgproc")
    public static native void FastHoughTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, int i4);

    @Namespace("cv::ximgproc")
    public static native void FastHoughTransform(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::ximgproc")
    public static native void FastHoughTransform(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, int i4);

    @Namespace("cv::ximgproc")
    public static native void FastHoughTransform(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::ximgproc")
    public static native void FastHoughTransform(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, int i4);

    @Namespace("cv::ximgproc")
    @Cast({"cv::Vec4i*"})
    @ByVal
    public static native Scalar4i HoughPoint2Line(@ByRef @Const Point point, @ByVal GpuMat gpuMat);

    @Namespace("cv::ximgproc")
    @Cast({"cv::Vec4i*"})
    @ByVal
    public static native Scalar4i HoughPoint2Line(@ByRef @Const Point point, @ByVal GpuMat gpuMat, int i, int i2, int i3);

    @Namespace("cv::ximgproc")
    @Cast({"cv::Vec4i*"})
    @ByVal
    public static native Scalar4i HoughPoint2Line(@ByRef @Const Point point, @ByVal Mat mat);

    @Namespace("cv::ximgproc")
    @Cast({"cv::Vec4i*"})
    @ByVal
    public static native Scalar4i HoughPoint2Line(@ByRef @Const Point point, @ByVal Mat mat, int i, int i2, int i3);

    @Namespace("cv::ximgproc")
    @Cast({"cv::Vec4i*"})
    @ByVal
    public static native Scalar4i HoughPoint2Line(@ByRef @Const Point point, @ByVal UMat uMat);

    @Namespace("cv::ximgproc")
    @Cast({"cv::Vec4i*"})
    @ByVal
    public static native Scalar4i HoughPoint2Line(@ByRef @Const Point point, @ByVal UMat uMat, int i, int i2, int i3);

    @Namespace("cv::ximgproc")
    public static native void amFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void amFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv::ximgproc")
    public static native void amFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void amFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv::ximgproc")
    public static native void amFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void amFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv::ximgproc")
    public static native void anisotropicDiffusion(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2, int i);

    @Namespace("cv::ximgproc")
    public static native void anisotropicDiffusion(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2, int i);

    @Namespace("cv::ximgproc")
    public static native void anisotropicDiffusion(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2, int i);

    @Namespace("cv::ximgproc")
    public static native void bilateralTextureFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::ximgproc")
    public static native void bilateralTextureFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void bilateralTextureFilter(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::ximgproc")
    public static native void bilateralTextureFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void bilateralTextureFilter(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ximgproc")
    public static native void bilateralTextureFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native double computeBadPixelPercent(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Rect rect);

    @Namespace("cv::ximgproc")
    public static native double computeBadPixelPercent(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Rect rect, int i);

    @Namespace("cv::ximgproc")
    public static native double computeBadPixelPercent(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Rect rect);

    @Namespace("cv::ximgproc")
    public static native double computeBadPixelPercent(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Rect rect, int i);

    @Namespace("cv::ximgproc")
    public static native double computeBadPixelPercent(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Rect rect);

    @Namespace("cv::ximgproc")
    public static native double computeBadPixelPercent(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Rect rect, int i);

    @Namespace("cv::ximgproc")
    public static native double computeMSE(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Rect rect);

    @Namespace("cv::ximgproc")
    public static native double computeMSE(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Rect rect);

    @Namespace("cv::ximgproc")
    public static native double computeMSE(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Rect rect);

    @Namespace("cv::ximgproc")
    public static native void covarianceEstimation(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv::ximgproc")
    public static native void covarianceEstimation(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv::ximgproc")
    public static native void covarianceEstimation(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native AdaptiveManifoldFilter createAMFilter(double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native AdaptiveManifoldFilter createAMFilter(double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DTFilter createDTFilter(@ByVal GpuMat gpuMat, double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DTFilter createDTFilter(@ByVal GpuMat gpuMat, double d, double d2, int i, int i2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DTFilter createDTFilter(@ByVal Mat mat, double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DTFilter createDTFilter(@ByVal Mat mat, double d, double d2, int i, int i2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DTFilter createDTFilter(@ByVal UMat uMat, double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DTFilter createDTFilter(@ByVal UMat uMat, double d, double d2, int i, int i2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DisparityWLSFilter createDisparityWLSFilter(@opencv_core.Ptr StereoMatcher stereoMatcher);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native DisparityWLSFilter createDisparityWLSFilterGeneric(@Cast({"bool"}) boolean z);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native EdgeAwareInterpolator createEdgeAwareInterpolator();

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastBilateralSolverFilter createFastBilateralSolverFilter(@ByVal GpuMat gpuMat, double d, double d2, double d3);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastBilateralSolverFilter createFastBilateralSolverFilter(@ByVal GpuMat gpuMat, double d, double d2, double d3, double d4, int i, double d5);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastBilateralSolverFilter createFastBilateralSolverFilter(@ByVal Mat mat, double d, double d2, double d3);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastBilateralSolverFilter createFastBilateralSolverFilter(@ByVal Mat mat, double d, double d2, double d3, double d4, int i, double d5);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastBilateralSolverFilter createFastBilateralSolverFilter(@ByVal UMat uMat, double d, double d2, double d3);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastBilateralSolverFilter createFastBilateralSolverFilter(@ByVal UMat uMat, double d, double d2, double d3, double d4, int i, double d5);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastGlobalSmootherFilter createFastGlobalSmootherFilter(@ByVal GpuMat gpuMat, double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastGlobalSmootherFilter createFastGlobalSmootherFilter(@ByVal GpuMat gpuMat, double d, double d2, double d3, int i);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastGlobalSmootherFilter createFastGlobalSmootherFilter(@ByVal Mat mat, double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastGlobalSmootherFilter createFastGlobalSmootherFilter(@ByVal Mat mat, double d, double d2, double d3, int i);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastGlobalSmootherFilter createFastGlobalSmootherFilter(@ByVal UMat uMat, double d, double d2);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native FastGlobalSmootherFilter createFastGlobalSmootherFilter(@ByVal UMat uMat, double d, double d2, double d3, int i);

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native GraphSegmentation createGraphSegmentation();

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native GraphSegmentation createGraphSegmentation(double d, float f, int i);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native GuidedFilter createGuidedFilter(@ByVal GpuMat gpuMat, int i, double d);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native GuidedFilter createGuidedFilter(@ByVal Mat mat, int i, double d);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native GuidedFilter createGuidedFilter(@ByVal UMat uMat, int i, double d);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native RFFeatureGetter createRFFeatureGetter();

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native RICInterpolator createRICInterpolator();

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native StereoMatcher createRightMatcher(@opencv_core.Ptr StereoMatcher stereoMatcher);

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentation createSelectiveSearchSegmentation();

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyColor createSelectiveSearchSegmentationStrategyColor();

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyFill createSelectiveSearchSegmentationStrategyFill();

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple();

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(@opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy);

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(@opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, @opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy2);

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(@opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, @opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy2, @opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy3);

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(@opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, @opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy2, @opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy3, @opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy4);

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategySize createSelectiveSearchSegmentationStrategySize();

    @Namespace("cv::ximgproc::segmentation")
    @opencv_core.Ptr
    public static native SelectiveSearchSegmentationStrategyTexture createSelectiveSearchSegmentationStrategyTexture();

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native StructuredEdgeDetection createStructuredEdgeDetection(@opencv_core.Str String str);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native StructuredEdgeDetection createStructuredEdgeDetection(@opencv_core.Str String str, @Const @opencv_core.Ptr RFFeatureGetter rFFeatureGetter);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native StructuredEdgeDetection createStructuredEdgeDetection(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native StructuredEdgeDetection createStructuredEdgeDetection(@opencv_core.Str BytePointer bytePointer, @Const @opencv_core.Ptr RFFeatureGetter rFFeatureGetter);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelLSC createSuperpixelLSC(@ByVal GpuMat gpuMat);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelLSC createSuperpixelLSC(@ByVal GpuMat gpuMat, int i, float f);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelLSC createSuperpixelLSC(@ByVal Mat mat);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelLSC createSuperpixelLSC(@ByVal Mat mat, int i, float f);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelLSC createSuperpixelLSC(@ByVal UMat uMat);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelLSC createSuperpixelLSC(@ByVal UMat uMat, int i, float f);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSEEDS createSuperpixelSEEDS(int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSEEDS createSuperpixelSEEDS(int i, int i2, int i3, int i4, int i5, int i6, int i7, @Cast({"bool"}) boolean z);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSLIC createSuperpixelSLIC(@ByVal GpuMat gpuMat);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSLIC createSuperpixelSLIC(@ByVal GpuMat gpuMat, int i, int i2, float f);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSLIC createSuperpixelSLIC(@ByVal Mat mat);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSLIC createSuperpixelSLIC(@ByVal Mat mat, int i, int i2, float f);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSLIC createSuperpixelSLIC(@ByVal UMat uMat);

    @Namespace("cv::ximgproc")
    @opencv_core.Ptr
    public static native SuperpixelSLIC createSuperpixelSLIC(@ByVal UMat uMat, int i, int i2, float f);

    @Namespace("cv::ximgproc")
    public static native void dtFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void dtFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2, int i, int i2);

    @Namespace("cv::ximgproc")
    public static native void dtFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void dtFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2, int i, int i2);

    @Namespace("cv::ximgproc")
    public static native void dtFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void dtFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2, int i, int i2);

    @Namespace("cv::ximgproc")
    public static native void fastBilateralSolverFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::ximgproc")
    public static native void fastBilateralSolverFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d, double d2, double d3, double d4, int i, double d5);

    @Namespace("cv::ximgproc")
    public static native void fastBilateralSolverFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::ximgproc")
    public static native void fastBilateralSolverFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, double d, double d2, double d3, double d4, int i, double d5);

    @Namespace("cv::ximgproc")
    public static native void fastBilateralSolverFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::ximgproc")
    public static native void fastBilateralSolverFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, double d, double d2, double d3, double d4, int i, double d5);

    @Namespace("cv::ximgproc")
    public static native void fastGlobalSmootherFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void fastGlobalSmootherFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2, double d3, int i);

    @Namespace("cv::ximgproc")
    public static native void fastGlobalSmootherFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void fastGlobalSmootherFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2, double d3, int i);

    @Namespace("cv::ximgproc")
    public static native void fastGlobalSmootherFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void fastGlobalSmootherFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2, double d3, int i);

    @Namespace("cv::ximgproc")
    public static native void getDisparityVis(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::ximgproc")
    public static native void getDisparityVis(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d);

    @Namespace("cv::ximgproc")
    public static native void getDisparityVis(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::ximgproc")
    public static native void getDisparityVis(@ByVal Mat mat, @ByVal Mat mat2, double d);

    @Namespace("cv::ximgproc")
    public static native void getDisparityVis(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ximgproc")
    public static native void getDisparityVis(@ByVal UMat uMat, @ByVal UMat uMat2, double d);

    @Namespace("cv::ximgproc")
    public static native void guidedFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d);

    @Namespace("cv::ximgproc")
    public static native void guidedFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d, int i2);

    @Namespace("cv::ximgproc")
    public static native void guidedFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d);

    @Namespace("cv::ximgproc")
    public static native void guidedFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d, int i2);

    @Namespace("cv::ximgproc")
    public static native void guidedFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d);

    @Namespace("cv::ximgproc")
    public static native void guidedFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d, int i2);

    @Namespace("cv::ximgproc")
    public static native void jointBilateralFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void jointBilateralFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d, double d2, int i2);

    @Namespace("cv::ximgproc")
    public static native void jointBilateralFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void jointBilateralFilter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d, double d2, int i2);

    @Namespace("cv::ximgproc")
    public static native void jointBilateralFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void jointBilateralFilter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d, double d2, int i2);

    @Namespace("cv::ximgproc")
    public static native void l0Smooth(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::ximgproc")
    public static native void l0Smooth(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void l0Smooth(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::ximgproc")
    public static native void l0Smooth(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void l0Smooth(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ximgproc")
    public static native void l0Smooth(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2);

    @Namespace("cv::ximgproc")
    public static native void niBlackThreshold(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, int i, int i2, double d2);

    @Namespace("cv::ximgproc")
    public static native void niBlackThreshold(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, int i, int i2, double d2, int i3, double d3);

    @Namespace("cv::ximgproc")
    public static native void niBlackThreshold(@ByVal Mat mat, @ByVal Mat mat2, double d, int i, int i2, double d2);

    @Namespace("cv::ximgproc")
    public static native void niBlackThreshold(@ByVal Mat mat, @ByVal Mat mat2, double d, int i, int i2, double d2, int i3, double d3);

    @Namespace("cv::ximgproc")
    public static native void niBlackThreshold(@ByVal UMat uMat, @ByVal UMat uMat2, double d, int i, int i2, double d2);

    @Namespace("cv::ximgproc")
    public static native void niBlackThreshold(@ByVal UMat uMat, @ByVal UMat uMat2, double d, int i, int i2, double d2, int i3, double d3);

    @Namespace("cv::ximgproc")
    public static native int readGT(@opencv_core.Str String str, @ByVal GpuMat gpuMat);

    @Namespace("cv::ximgproc")
    public static native int readGT(@opencv_core.Str String str, @ByVal Mat mat);

    @Namespace("cv::ximgproc")
    public static native int readGT(@opencv_core.Str String str, @ByVal UMat uMat);

    @Namespace("cv::ximgproc")
    public static native int readGT(@opencv_core.Str BytePointer bytePointer, @ByVal GpuMat gpuMat);

    @Namespace("cv::ximgproc")
    public static native int readGT(@opencv_core.Str BytePointer bytePointer, @ByVal Mat mat);

    @Namespace("cv::ximgproc")
    public static native int readGT(@opencv_core.Str BytePointer bytePointer, @ByVal UMat uMat);

    @Namespace("cv::ximgproc")
    public static native void rollingGuidanceFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::ximgproc")
    public static native void rollingGuidanceFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, int i2, int i3);

    @Namespace("cv::ximgproc")
    public static native void rollingGuidanceFilter(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::ximgproc")
    public static native void rollingGuidanceFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, int i2, int i3);

    @Namespace("cv::ximgproc")
    public static native void rollingGuidanceFilter(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ximgproc")
    public static native void rollingGuidanceFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, int i2, int i3);

    @Namespace("cv::ximgproc")
    public static native void thinning(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::ximgproc")
    public static native void thinning(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::ximgproc")
    public static native void thinning(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::ximgproc")
    public static native void thinning(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::ximgproc")
    public static native void thinning(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ximgproc")
    public static native void thinning(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    static {
        Loader.load();
    }
}
