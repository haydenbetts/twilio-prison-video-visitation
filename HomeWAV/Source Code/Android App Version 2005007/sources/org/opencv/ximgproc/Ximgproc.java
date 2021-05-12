package org.opencv.ximgproc;

import org.opencv.calib3d.StereoMatcher;
import org.opencv.core.Mat;

public class Ximgproc {
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
    public static final int SLIC = 100;
    public static final int SLICO = 101;
    public static final int THINNING_GUOHALL = 1;
    public static final int THINNING_ZHANGSUEN = 0;
    public static final int WMF_COS = 8;
    public static final int WMF_EXP = 1;
    public static final int WMF_IV1 = 2;
    public static final int WMF_IV2 = 4;
    public static final int WMF_JAC = 16;
    public static final int WMF_OFF = 32;

    private static native void FastHoughTransform_0(long j, long j2, int i, int i2, int i3, int i4);

    private static native void FastHoughTransform_1(long j, long j2, int i, int i2, int i3);

    private static native void FastHoughTransform_2(long j, long j2, int i, int i2);

    private static native void FastHoughTransform_3(long j, long j2, int i);

    private static native void GradientDericheX_0(long j, long j2, double d, double d2);

    private static native void GradientDericheY_0(long j, long j2, double d, double d2);

    private static native void PeiLinNormalization_0(long j, long j2);

    private static native void amFilter_0(long j, long j2, long j3, double d, double d2, boolean z);

    private static native void amFilter_1(long j, long j2, long j3, double d, double d2);

    private static native void anisotropicDiffusion_0(long j, long j2, float f, float f2, int i);

    private static native void bilateralTextureFilter_0(long j, long j2, int i, int i2, double d, double d2);

    private static native void bilateralTextureFilter_1(long j, long j2, int i, int i2, double d);

    private static native void bilateralTextureFilter_2(long j, long j2, int i, int i2);

    private static native void bilateralTextureFilter_3(long j, long j2, int i);

    private static native void bilateralTextureFilter_4(long j, long j2);

    private static native void colorMatchTemplate_0(long j, long j2, long j3);

    private static native void contourSampling_0(long j, long j2, int i);

    private static native void covarianceEstimation_0(long j, long j2, int i, int i2);

    private static native long createAMFilter_0(double d, double d2, boolean z);

    private static native long createAMFilter_1(double d, double d2);

    private static native long createContourFitting_0(int i, int i2);

    private static native long createContourFitting_1(int i);

    private static native long createContourFitting_2();

    private static native long createDTFilter_0(long j, double d, double d2, int i, int i2);

    private static native long createDTFilter_1(long j, double d, double d2, int i);

    private static native long createDTFilter_2(long j, double d, double d2);

    private static native long createDisparityWLSFilterGeneric_0(boolean z);

    private static native long createDisparityWLSFilter_0(long j);

    private static native long createEdgeAwareInterpolator_0();

    private static native long createEdgeBoxes_0(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10, float f11);

    private static native long createEdgeBoxes_1(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10);

    private static native long createEdgeBoxes_10(float f, float f2);

    private static native long createEdgeBoxes_11(float f);

    private static native long createEdgeBoxes_12();

    private static native long createEdgeBoxes_2(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9);

    private static native long createEdgeBoxes_3(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8);

    private static native long createEdgeBoxes_4(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7);

    private static native long createEdgeBoxes_5(float f, float f2, float f3, float f4, int i, float f5, float f6);

    private static native long createEdgeBoxes_6(float f, float f2, float f3, float f4, int i, float f5);

    private static native long createEdgeBoxes_7(float f, float f2, float f3, float f4, int i);

    private static native long createEdgeBoxes_8(float f, float f2, float f3, float f4);

    private static native long createEdgeBoxes_9(float f, float f2, float f3);

    private static native long createFastBilateralSolverFilter_0(long j, double d, double d2, double d3, double d4, int i, double d5);

    private static native long createFastBilateralSolverFilter_1(long j, double d, double d2, double d3, double d4, int i);

    private static native long createFastBilateralSolverFilter_2(long j, double d, double d2, double d3, double d4);

    private static native long createFastBilateralSolverFilter_3(long j, double d, double d2, double d3);

    private static native long createFastGlobalSmootherFilter_0(long j, double d, double d2, double d3, int i);

    private static native long createFastGlobalSmootherFilter_1(long j, double d, double d2, double d3);

    private static native long createFastGlobalSmootherFilter_2(long j, double d, double d2);

    private static native long createFastLineDetector_0(int i, float f, double d, double d2, int i2, boolean z);

    private static native long createFastLineDetector_1(int i, float f, double d, double d2, int i2);

    private static native long createFastLineDetector_2(int i, float f, double d, double d2);

    private static native long createFastLineDetector_3(int i, float f, double d);

    private static native long createFastLineDetector_4(int i, float f);

    private static native long createFastLineDetector_5(int i);

    private static native long createFastLineDetector_6();

    private static native long createGraphSegmentation_0(double d, float f, int i);

    private static native long createGraphSegmentation_1(double d, float f);

    private static native long createGraphSegmentation_2(double d);

    private static native long createGraphSegmentation_3();

    private static native long createGuidedFilter_0(long j, int i, double d);

    private static native void createQuaternionImage_0(long j, long j2);

    private static native long createRFFeatureGetter_0();

    private static native long createRICInterpolator_0();

    private static native long createRightMatcher_0(long j);

    private static native long createSelectiveSearchSegmentationStrategyColor_0();

    private static native long createSelectiveSearchSegmentationStrategyFill_0();

    private static native long createSelectiveSearchSegmentationStrategyMultiple_0(long j, long j2, long j3, long j4);

    private static native long createSelectiveSearchSegmentationStrategyMultiple_1(long j, long j2, long j3);

    private static native long createSelectiveSearchSegmentationStrategyMultiple_2(long j, long j2);

    private static native long createSelectiveSearchSegmentationStrategyMultiple_3(long j);

    private static native long createSelectiveSearchSegmentationStrategyMultiple_4();

    private static native long createSelectiveSearchSegmentationStrategySize_0();

    private static native long createSelectiveSearchSegmentationStrategyTexture_0();

    private static native long createSelectiveSearchSegmentation_0();

    private static native long createStructuredEdgeDetection_0(String str, long j);

    private static native long createStructuredEdgeDetection_1(String str);

    private static native long createSuperpixelLSC_0(long j, int i, float f);

    private static native long createSuperpixelLSC_1(long j, int i);

    private static native long createSuperpixelLSC_2(long j);

    private static native long createSuperpixelSEEDS_0(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z);

    private static native long createSuperpixelSEEDS_1(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native long createSuperpixelSEEDS_2(int i, int i2, int i3, int i4, int i5, int i6);

    private static native long createSuperpixelSEEDS_3(int i, int i2, int i3, int i4, int i5);

    private static native long createSuperpixelSLIC_0(long j, int i, int i2, float f);

    private static native long createSuperpixelSLIC_1(long j, int i, int i2);

    private static native long createSuperpixelSLIC_2(long j, int i);

    private static native long createSuperpixelSLIC_3(long j);

    private static native void dtFilter_0(long j, long j2, long j3, double d, double d2, int i, int i2);

    private static native void dtFilter_1(long j, long j2, long j3, double d, double d2, int i);

    private static native void dtFilter_2(long j, long j2, long j3, double d, double d2);

    private static native void edgePreservingFilter_0(long j, long j2, int i, double d);

    private static native void fastBilateralSolverFilter_0(long j, long j2, long j3, long j4, double d, double d2, double d3, double d4, int i, double d5);

    private static native void fastBilateralSolverFilter_1(long j, long j2, long j3, long j4, double d, double d2, double d3, double d4, int i);

    private static native void fastBilateralSolverFilter_2(long j, long j2, long j3, long j4, double d, double d2, double d3, double d4);

    private static native void fastBilateralSolverFilter_3(long j, long j2, long j3, long j4, double d, double d2, double d3);

    private static native void fastBilateralSolverFilter_4(long j, long j2, long j3, long j4, double d, double d2);

    private static native void fastBilateralSolverFilter_5(long j, long j2, long j3, long j4, double d);

    private static native void fastBilateralSolverFilter_6(long j, long j2, long j3, long j4);

    private static native void fastGlobalSmootherFilter_0(long j, long j2, long j3, double d, double d2, double d3, int i);

    private static native void fastGlobalSmootherFilter_1(long j, long j2, long j3, double d, double d2, double d3);

    private static native void fastGlobalSmootherFilter_2(long j, long j2, long j3, double d, double d2);

    private static native void fourierDescriptor_0(long j, long j2, int i, int i2);

    private static native void fourierDescriptor_1(long j, long j2, int i);

    private static native void fourierDescriptor_2(long j, long j2);

    private static native void guidedFilter_0(long j, long j2, long j3, int i, double d, int i2);

    private static native void guidedFilter_1(long j, long j2, long j3, int i, double d);

    private static native void jointBilateralFilter_0(long j, long j2, long j3, int i, double d, double d2, int i2);

    private static native void jointBilateralFilter_1(long j, long j2, long j3, int i, double d, double d2);

    private static native void l0Smooth_0(long j, long j2, double d, double d2);

    private static native void l0Smooth_1(long j, long j2, double d);

    private static native void l0Smooth_2(long j, long j2);

    private static native void niBlackThreshold_0(long j, long j2, double d, int i, int i2, double d2, int i3, double d3);

    private static native void niBlackThreshold_1(long j, long j2, double d, int i, int i2, double d2, int i3);

    private static native void niBlackThreshold_2(long j, long j2, double d, int i, int i2, double d2);

    private static native void qconj_0(long j, long j2);

    private static native void qdft_0(long j, long j2, int i, boolean z);

    private static native void qmultiply_0(long j, long j2, long j3);

    private static native void qunitary_0(long j, long j2);

    private static native void rollingGuidanceFilter_0(long j, long j2, int i, double d, double d2, int i2, int i3);

    private static native void rollingGuidanceFilter_1(long j, long j2, int i, double d, double d2, int i2);

    private static native void rollingGuidanceFilter_2(long j, long j2, int i, double d, double d2);

    private static native void rollingGuidanceFilter_3(long j, long j2, int i, double d);

    private static native void rollingGuidanceFilter_4(long j, long j2, int i);

    private static native void rollingGuidanceFilter_5(long j, long j2);

    private static native void thinning_0(long j, long j2, int i);

    private static native void thinning_1(long j, long j2);

    private static native void transformFD_0(long j, long j2, long j3, boolean z);

    private static native void transformFD_1(long j, long j2, long j3);

    private static native void weightedMedianFilter_0(long j, long j2, long j3, int i, double d, int i2, long j4);

    private static native void weightedMedianFilter_1(long j, long j2, long j3, int i, double d, int i2);

    private static native void weightedMedianFilter_2(long j, long j2, long j3, int i, double d);

    private static native void weightedMedianFilter_3(long j, long j2, long j3, int i);

    public static AdaptiveManifoldFilter createAMFilter(double d, double d2, boolean z) {
        return AdaptiveManifoldFilter.__fromPtr__(createAMFilter_0(d, d2, z));
    }

    public static AdaptiveManifoldFilter createAMFilter(double d, double d2) {
        return AdaptiveManifoldFilter.__fromPtr__(createAMFilter_1(d, d2));
    }

    public static ContourFitting createContourFitting(int i, int i2) {
        return ContourFitting.__fromPtr__(createContourFitting_0(i, i2));
    }

    public static ContourFitting createContourFitting(int i) {
        return ContourFitting.__fromPtr__(createContourFitting_1(i));
    }

    public static ContourFitting createContourFitting() {
        return ContourFitting.__fromPtr__(createContourFitting_2());
    }

    public static DTFilter createDTFilter(Mat mat, double d, double d2, int i, int i2) {
        return DTFilter.__fromPtr__(createDTFilter_0(mat.nativeObj, d, d2, i, i2));
    }

    public static DTFilter createDTFilter(Mat mat, double d, double d2, int i) {
        return DTFilter.__fromPtr__(createDTFilter_1(mat.nativeObj, d, d2, i));
    }

    public static DTFilter createDTFilter(Mat mat, double d, double d2) {
        return DTFilter.__fromPtr__(createDTFilter_2(mat.nativeObj, d, d2));
    }

    public static DisparityWLSFilter createDisparityWLSFilter(StereoMatcher stereoMatcher) {
        return DisparityWLSFilter.__fromPtr__(createDisparityWLSFilter_0(stereoMatcher.getNativeObjAddr()));
    }

    public static DisparityWLSFilter createDisparityWLSFilterGeneric(boolean z) {
        return DisparityWLSFilter.__fromPtr__(createDisparityWLSFilterGeneric_0(z));
    }

    public static EdgeAwareInterpolator createEdgeAwareInterpolator() {
        return EdgeAwareInterpolator.__fromPtr__(createEdgeAwareInterpolator_0());
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_0(f, f2, f3, f4, i, f5, f6, f7, f8, f9, f10, f11));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_1(f, f2, f3, f4, i, f5, f6, f7, f8, f9, f10));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_2(f, f2, f3, f4, i, f5, f6, f7, f8, f9));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_3(f, f2, f3, f4, i, f5, f6, f7, f8));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_4(f, f2, f3, f4, i, f5, f6, f7));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5, float f6) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_5(f, f2, f3, f4, i, f5, f6));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i, float f5) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_6(f, f2, f3, f4, i, f5));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4, int i) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_7(f, f2, f3, f4, i));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3, float f4) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_8(f, f2, f3, f4));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2, float f3) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_9(f, f2, f3));
    }

    public static EdgeBoxes createEdgeBoxes(float f, float f2) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_10(f, f2));
    }

    public static EdgeBoxes createEdgeBoxes(float f) {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_11(f));
    }

    public static EdgeBoxes createEdgeBoxes() {
        return EdgeBoxes.__fromPtr__(createEdgeBoxes_12());
    }

    public static FastBilateralSolverFilter createFastBilateralSolverFilter(Mat mat, double d, double d2, double d3, double d4, int i, double d5) {
        return FastBilateralSolverFilter.__fromPtr__(createFastBilateralSolverFilter_0(mat.nativeObj, d, d2, d3, d4, i, d5));
    }

    public static FastBilateralSolverFilter createFastBilateralSolverFilter(Mat mat, double d, double d2, double d3, double d4, int i) {
        return FastBilateralSolverFilter.__fromPtr__(createFastBilateralSolverFilter_1(mat.nativeObj, d, d2, d3, d4, i));
    }

    public static FastBilateralSolverFilter createFastBilateralSolverFilter(Mat mat, double d, double d2, double d3, double d4) {
        return FastBilateralSolverFilter.__fromPtr__(createFastBilateralSolverFilter_2(mat.nativeObj, d, d2, d3, d4));
    }

    public static FastBilateralSolverFilter createFastBilateralSolverFilter(Mat mat, double d, double d2, double d3) {
        return FastBilateralSolverFilter.__fromPtr__(createFastBilateralSolverFilter_3(mat.nativeObj, d, d2, d3));
    }

    public static FastGlobalSmootherFilter createFastGlobalSmootherFilter(Mat mat, double d, double d2, double d3, int i) {
        return FastGlobalSmootherFilter.__fromPtr__(createFastGlobalSmootherFilter_0(mat.nativeObj, d, d2, d3, i));
    }

    public static FastGlobalSmootherFilter createFastGlobalSmootherFilter(Mat mat, double d, double d2, double d3) {
        return FastGlobalSmootherFilter.__fromPtr__(createFastGlobalSmootherFilter_1(mat.nativeObj, d, d2, d3));
    }

    public static FastGlobalSmootherFilter createFastGlobalSmootherFilter(Mat mat, double d, double d2) {
        return FastGlobalSmootherFilter.__fromPtr__(createFastGlobalSmootherFilter_2(mat.nativeObj, d, d2));
    }

    public static FastLineDetector createFastLineDetector(int i, float f, double d, double d2, int i2, boolean z) {
        return FastLineDetector.__fromPtr__(createFastLineDetector_0(i, f, d, d2, i2, z));
    }

    public static FastLineDetector createFastLineDetector(int i, float f, double d, double d2, int i2) {
        return FastLineDetector.__fromPtr__(createFastLineDetector_1(i, f, d, d2, i2));
    }

    public static FastLineDetector createFastLineDetector(int i, float f, double d, double d2) {
        return FastLineDetector.__fromPtr__(createFastLineDetector_2(i, f, d, d2));
    }

    public static FastLineDetector createFastLineDetector(int i, float f, double d) {
        return FastLineDetector.__fromPtr__(createFastLineDetector_3(i, f, d));
    }

    public static FastLineDetector createFastLineDetector(int i, float f) {
        return FastLineDetector.__fromPtr__(createFastLineDetector_4(i, f));
    }

    public static FastLineDetector createFastLineDetector(int i) {
        return FastLineDetector.__fromPtr__(createFastLineDetector_5(i));
    }

    public static FastLineDetector createFastLineDetector() {
        return FastLineDetector.__fromPtr__(createFastLineDetector_6());
    }

    public static GraphSegmentation createGraphSegmentation(double d, float f, int i) {
        return GraphSegmentation.__fromPtr__(createGraphSegmentation_0(d, f, i));
    }

    public static GraphSegmentation createGraphSegmentation(double d, float f) {
        return GraphSegmentation.__fromPtr__(createGraphSegmentation_1(d, f));
    }

    public static GraphSegmentation createGraphSegmentation(double d) {
        return GraphSegmentation.__fromPtr__(createGraphSegmentation_2(d));
    }

    public static GraphSegmentation createGraphSegmentation() {
        return GraphSegmentation.__fromPtr__(createGraphSegmentation_3());
    }

    public static GuidedFilter createGuidedFilter(Mat mat, int i, double d) {
        return GuidedFilter.__fromPtr__(createGuidedFilter_0(mat.nativeObj, i, d));
    }

    public static RFFeatureGetter createRFFeatureGetter() {
        return RFFeatureGetter.__fromPtr__(createRFFeatureGetter_0());
    }

    public static RICInterpolator createRICInterpolator() {
        return RICInterpolator.__fromPtr__(createRICInterpolator_0());
    }

    public static SelectiveSearchSegmentation createSelectiveSearchSegmentation() {
        return SelectiveSearchSegmentation.__fromPtr__(createSelectiveSearchSegmentation_0());
    }

    public static SelectiveSearchSegmentationStrategyColor createSelectiveSearchSegmentationStrategyColor() {
        return SelectiveSearchSegmentationStrategyColor.__fromPtr__(createSelectiveSearchSegmentationStrategyColor_0());
    }

    public static SelectiveSearchSegmentationStrategyFill createSelectiveSearchSegmentationStrategyFill() {
        return SelectiveSearchSegmentationStrategyFill.__fromPtr__(createSelectiveSearchSegmentationStrategyFill_0());
    }

    public static SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy2, SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy3, SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy4) {
        return SelectiveSearchSegmentationStrategyMultiple.__fromPtr__(createSelectiveSearchSegmentationStrategyMultiple_0(selectiveSearchSegmentationStrategy.getNativeObjAddr(), selectiveSearchSegmentationStrategy2.getNativeObjAddr(), selectiveSearchSegmentationStrategy3.getNativeObjAddr(), selectiveSearchSegmentationStrategy4.getNativeObjAddr()));
    }

    public static SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy2, SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy3) {
        return SelectiveSearchSegmentationStrategyMultiple.__fromPtr__(createSelectiveSearchSegmentationStrategyMultiple_1(selectiveSearchSegmentationStrategy.getNativeObjAddr(), selectiveSearchSegmentationStrategy2.getNativeObjAddr(), selectiveSearchSegmentationStrategy3.getNativeObjAddr()));
    }

    public static SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy2) {
        return SelectiveSearchSegmentationStrategyMultiple.__fromPtr__(createSelectiveSearchSegmentationStrategyMultiple_2(selectiveSearchSegmentationStrategy.getNativeObjAddr(), selectiveSearchSegmentationStrategy2.getNativeObjAddr()));
    }

    public static SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple(SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy) {
        return SelectiveSearchSegmentationStrategyMultiple.__fromPtr__(createSelectiveSearchSegmentationStrategyMultiple_3(selectiveSearchSegmentationStrategy.getNativeObjAddr()));
    }

    public static SelectiveSearchSegmentationStrategyMultiple createSelectiveSearchSegmentationStrategyMultiple() {
        return SelectiveSearchSegmentationStrategyMultiple.__fromPtr__(createSelectiveSearchSegmentationStrategyMultiple_4());
    }

    public static SelectiveSearchSegmentationStrategySize createSelectiveSearchSegmentationStrategySize() {
        return SelectiveSearchSegmentationStrategySize.__fromPtr__(createSelectiveSearchSegmentationStrategySize_0());
    }

    public static SelectiveSearchSegmentationStrategyTexture createSelectiveSearchSegmentationStrategyTexture() {
        return SelectiveSearchSegmentationStrategyTexture.__fromPtr__(createSelectiveSearchSegmentationStrategyTexture_0());
    }

    public static StereoMatcher createRightMatcher(StereoMatcher stereoMatcher) {
        return StereoMatcher.__fromPtr__(createRightMatcher_0(stereoMatcher.getNativeObjAddr()));
    }

    public static StructuredEdgeDetection createStructuredEdgeDetection(String str, RFFeatureGetter rFFeatureGetter) {
        return StructuredEdgeDetection.__fromPtr__(createStructuredEdgeDetection_0(str, rFFeatureGetter.getNativeObjAddr()));
    }

    public static StructuredEdgeDetection createStructuredEdgeDetection(String str) {
        return StructuredEdgeDetection.__fromPtr__(createStructuredEdgeDetection_1(str));
    }

    public static SuperpixelLSC createSuperpixelLSC(Mat mat, int i, float f) {
        return SuperpixelLSC.__fromPtr__(createSuperpixelLSC_0(mat.nativeObj, i, f));
    }

    public static SuperpixelLSC createSuperpixelLSC(Mat mat, int i) {
        return SuperpixelLSC.__fromPtr__(createSuperpixelLSC_1(mat.nativeObj, i));
    }

    public static SuperpixelLSC createSuperpixelLSC(Mat mat) {
        return SuperpixelLSC.__fromPtr__(createSuperpixelLSC_2(mat.nativeObj));
    }

    public static SuperpixelSEEDS createSuperpixelSEEDS(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        return SuperpixelSEEDS.__fromPtr__(createSuperpixelSEEDS_0(i, i2, i3, i4, i5, i6, i7, z));
    }

    public static SuperpixelSEEDS createSuperpixelSEEDS(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return SuperpixelSEEDS.__fromPtr__(createSuperpixelSEEDS_1(i, i2, i3, i4, i5, i6, i7));
    }

    public static SuperpixelSEEDS createSuperpixelSEEDS(int i, int i2, int i3, int i4, int i5, int i6) {
        return SuperpixelSEEDS.__fromPtr__(createSuperpixelSEEDS_2(i, i2, i3, i4, i5, i6));
    }

    public static SuperpixelSEEDS createSuperpixelSEEDS(int i, int i2, int i3, int i4, int i5) {
        return SuperpixelSEEDS.__fromPtr__(createSuperpixelSEEDS_3(i, i2, i3, i4, i5));
    }

    public static SuperpixelSLIC createSuperpixelSLIC(Mat mat, int i, int i2, float f) {
        return SuperpixelSLIC.__fromPtr__(createSuperpixelSLIC_0(mat.nativeObj, i, i2, f));
    }

    public static SuperpixelSLIC createSuperpixelSLIC(Mat mat, int i, int i2) {
        return SuperpixelSLIC.__fromPtr__(createSuperpixelSLIC_1(mat.nativeObj, i, i2));
    }

    public static SuperpixelSLIC createSuperpixelSLIC(Mat mat, int i) {
        return SuperpixelSLIC.__fromPtr__(createSuperpixelSLIC_2(mat.nativeObj, i));
    }

    public static SuperpixelSLIC createSuperpixelSLIC(Mat mat) {
        return SuperpixelSLIC.__fromPtr__(createSuperpixelSLIC_3(mat.nativeObj));
    }

    public static void FastHoughTransform(Mat mat, Mat mat2, int i, int i2, int i3, int i4) {
        FastHoughTransform_0(mat.nativeObj, mat2.nativeObj, i, i2, i3, i4);
    }

    public static void FastHoughTransform(Mat mat, Mat mat2, int i, int i2, int i3) {
        FastHoughTransform_1(mat.nativeObj, mat2.nativeObj, i, i2, i3);
    }

    public static void FastHoughTransform(Mat mat, Mat mat2, int i, int i2) {
        FastHoughTransform_2(mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public static void FastHoughTransform(Mat mat, Mat mat2, int i) {
        FastHoughTransform_3(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void GradientDericheX(Mat mat, Mat mat2, double d, double d2) {
        GradientDericheX_0(mat.nativeObj, mat2.nativeObj, d, d2);
    }

    public static void GradientDericheY(Mat mat, Mat mat2, double d, double d2) {
        GradientDericheY_0(mat.nativeObj, mat2.nativeObj, d, d2);
    }

    public static void PeiLinNormalization(Mat mat, Mat mat2) {
        PeiLinNormalization_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void amFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2, boolean z) {
        amFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2, z);
    }

    public static void amFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2) {
        amFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2);
    }

    public static void anisotropicDiffusion(Mat mat, Mat mat2, float f, float f2, int i) {
        anisotropicDiffusion_0(mat.nativeObj, mat2.nativeObj, f, f2, i);
    }

    public static void bilateralTextureFilter(Mat mat, Mat mat2, int i, int i2, double d, double d2) {
        bilateralTextureFilter_0(mat.nativeObj, mat2.nativeObj, i, i2, d, d2);
    }

    public static void bilateralTextureFilter(Mat mat, Mat mat2, int i, int i2, double d) {
        bilateralTextureFilter_1(mat.nativeObj, mat2.nativeObj, i, i2, d);
    }

    public static void bilateralTextureFilter(Mat mat, Mat mat2, int i, int i2) {
        bilateralTextureFilter_2(mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public static void bilateralTextureFilter(Mat mat, Mat mat2, int i) {
        bilateralTextureFilter_3(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void bilateralTextureFilter(Mat mat, Mat mat2) {
        bilateralTextureFilter_4(mat.nativeObj, mat2.nativeObj);
    }

    public static void colorMatchTemplate(Mat mat, Mat mat2, Mat mat3) {
        colorMatchTemplate_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void contourSampling(Mat mat, Mat mat2, int i) {
        contourSampling_0(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void covarianceEstimation(Mat mat, Mat mat2, int i, int i2) {
        covarianceEstimation_0(mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public static void createQuaternionImage(Mat mat, Mat mat2) {
        createQuaternionImage_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void dtFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2, int i, int i2) {
        dtFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2, i, i2);
    }

    public static void dtFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2, int i) {
        dtFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2, i);
    }

    public static void dtFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2) {
        dtFilter_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2);
    }

    public static void edgePreservingFilter(Mat mat, Mat mat2, int i, double d) {
        edgePreservingFilter_0(mat.nativeObj, mat2.nativeObj, i, d);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2, double d3, double d4, int i, double d5) {
        fastBilateralSolverFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2, d3, d4, i, d5);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2, double d3, double d4, int i) {
        fastBilateralSolverFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2, d3, d4, i);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2, double d3, double d4) {
        fastBilateralSolverFilter_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2, d3, d4);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2, double d3) {
        fastBilateralSolverFilter_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2, d3);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2) {
        fastBilateralSolverFilter_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d) {
        fastBilateralSolverFilter_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d);
    }

    public static void fastBilateralSolverFilter(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        fastBilateralSolverFilter_6(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void fastGlobalSmootherFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2, double d3, int i) {
        fastGlobalSmootherFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2, d3, i);
    }

    public static void fastGlobalSmootherFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2, double d3) {
        fastGlobalSmootherFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2, d3);
    }

    public static void fastGlobalSmootherFilter(Mat mat, Mat mat2, Mat mat3, double d, double d2) {
        fastGlobalSmootherFilter_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, d2);
    }

    public static void fourierDescriptor(Mat mat, Mat mat2, int i, int i2) {
        fourierDescriptor_0(mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public static void fourierDescriptor(Mat mat, Mat mat2, int i) {
        fourierDescriptor_1(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void fourierDescriptor(Mat mat, Mat mat2) {
        fourierDescriptor_2(mat.nativeObj, mat2.nativeObj);
    }

    public static void guidedFilter(Mat mat, Mat mat2, Mat mat3, int i, double d, int i2) {
        guidedFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, i2);
    }

    public static void guidedFilter(Mat mat, Mat mat2, Mat mat3, int i, double d) {
        guidedFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d);
    }

    public static void jointBilateralFilter(Mat mat, Mat mat2, Mat mat3, int i, double d, double d2, int i2) {
        jointBilateralFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, d2, i2);
    }

    public static void jointBilateralFilter(Mat mat, Mat mat2, Mat mat3, int i, double d, double d2) {
        jointBilateralFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, d2);
    }

    public static void l0Smooth(Mat mat, Mat mat2, double d, double d2) {
        l0Smooth_0(mat.nativeObj, mat2.nativeObj, d, d2);
    }

    public static void l0Smooth(Mat mat, Mat mat2, double d) {
        l0Smooth_1(mat.nativeObj, mat2.nativeObj, d);
    }

    public static void l0Smooth(Mat mat, Mat mat2) {
        l0Smooth_2(mat.nativeObj, mat2.nativeObj);
    }

    public static void niBlackThreshold(Mat mat, Mat mat2, double d, int i, int i2, double d2, int i3, double d3) {
        niBlackThreshold_0(mat.nativeObj, mat2.nativeObj, d, i, i2, d2, i3, d3);
    }

    public static void niBlackThreshold(Mat mat, Mat mat2, double d, int i, int i2, double d2, int i3) {
        niBlackThreshold_1(mat.nativeObj, mat2.nativeObj, d, i, i2, d2, i3);
    }

    public static void niBlackThreshold(Mat mat, Mat mat2, double d, int i, int i2, double d2) {
        niBlackThreshold_2(mat.nativeObj, mat2.nativeObj, d, i, i2, d2);
    }

    public static void qconj(Mat mat, Mat mat2) {
        qconj_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void qdft(Mat mat, Mat mat2, int i, boolean z) {
        qdft_0(mat.nativeObj, mat2.nativeObj, i, z);
    }

    public static void qmultiply(Mat mat, Mat mat2, Mat mat3) {
        qmultiply_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void qunitary(Mat mat, Mat mat2) {
        qunitary_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void rollingGuidanceFilter(Mat mat, Mat mat2, int i, double d, double d2, int i2, int i3) {
        rollingGuidanceFilter_0(mat.nativeObj, mat2.nativeObj, i, d, d2, i2, i3);
    }

    public static void rollingGuidanceFilter(Mat mat, Mat mat2, int i, double d, double d2, int i2) {
        rollingGuidanceFilter_1(mat.nativeObj, mat2.nativeObj, i, d, d2, i2);
    }

    public static void rollingGuidanceFilter(Mat mat, Mat mat2, int i, double d, double d2) {
        rollingGuidanceFilter_2(mat.nativeObj, mat2.nativeObj, i, d, d2);
    }

    public static void rollingGuidanceFilter(Mat mat, Mat mat2, int i, double d) {
        rollingGuidanceFilter_3(mat.nativeObj, mat2.nativeObj, i, d);
    }

    public static void rollingGuidanceFilter(Mat mat, Mat mat2, int i) {
        rollingGuidanceFilter_4(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void rollingGuidanceFilter(Mat mat, Mat mat2) {
        rollingGuidanceFilter_5(mat.nativeObj, mat2.nativeObj);
    }

    public static void thinning(Mat mat, Mat mat2, int i) {
        thinning_0(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void thinning(Mat mat, Mat mat2) {
        thinning_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void transformFD(Mat mat, Mat mat2, Mat mat3, boolean z) {
        transformFD_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, z);
    }

    public static void transformFD(Mat mat, Mat mat2, Mat mat3) {
        transformFD_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void weightedMedianFilter(Mat mat, Mat mat2, Mat mat3, int i, double d, int i2, Mat mat4) {
        weightedMedianFilter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, i2, mat4.nativeObj);
    }

    public static void weightedMedianFilter(Mat mat, Mat mat2, Mat mat3, int i, double d, int i2) {
        weightedMedianFilter_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, i2);
    }

    public static void weightedMedianFilter(Mat mat, Mat mat2, Mat mat3, int i, double d) {
        weightedMedianFilter_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d);
    }

    public static void weightedMedianFilter(Mat mat, Mat mat2, Mat mat3, int i) {
        weightedMedianFilter_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i);
    }
}
