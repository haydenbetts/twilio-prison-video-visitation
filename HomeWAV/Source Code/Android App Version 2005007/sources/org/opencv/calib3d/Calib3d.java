package org.opencv.calib3d;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

public class Calib3d {
    public static final int CALIB_CB_ACCURACY = 32;
    public static final int CALIB_CB_ADAPTIVE_THRESH = 1;
    public static final int CALIB_CB_ASYMMETRIC_GRID = 2;
    public static final int CALIB_CB_CLUSTERING = 4;
    public static final int CALIB_CB_EXHAUSTIVE = 16;
    public static final int CALIB_CB_FAST_CHECK = 8;
    public static final int CALIB_CB_FILTER_QUADS = 4;
    public static final int CALIB_CB_LARGER = 64;
    public static final int CALIB_CB_MARKER = 128;
    public static final int CALIB_CB_NORMALIZE_IMAGE = 2;
    public static final int CALIB_CB_SYMMETRIC_GRID = 1;
    public static final int CALIB_FIX_ASPECT_RATIO = 2;
    public static final int CALIB_FIX_FOCAL_LENGTH = 16;
    public static final int CALIB_FIX_INTRINSIC = 256;
    public static final int CALIB_FIX_K1 = 32;
    public static final int CALIB_FIX_K2 = 64;
    public static final int CALIB_FIX_K3 = 128;
    public static final int CALIB_FIX_K4 = 2048;
    public static final int CALIB_FIX_K5 = 4096;
    public static final int CALIB_FIX_K6 = 8192;
    public static final int CALIB_FIX_PRINCIPAL_POINT = 4;
    public static final int CALIB_FIX_S1_S2_S3_S4 = 65536;
    public static final int CALIB_FIX_TANGENT_DIST = 2097152;
    public static final int CALIB_FIX_TAUX_TAUY = 524288;
    public static final int CALIB_HAND_EYE_ANDREFF = 3;
    public static final int CALIB_HAND_EYE_DANIILIDIS = 4;
    public static final int CALIB_HAND_EYE_HORAUD = 2;
    public static final int CALIB_HAND_EYE_PARK = 1;
    public static final int CALIB_HAND_EYE_TSAI = 0;
    public static final int CALIB_NINTRINSIC = 18;
    public static final int CALIB_RATIONAL_MODEL = 16384;
    public static final int CALIB_SAME_FOCAL_LENGTH = 512;
    public static final int CALIB_THIN_PRISM_MODEL = 32768;
    public static final int CALIB_TILTED_MODEL = 262144;
    public static final int CALIB_USE_EXTRINSIC_GUESS = 4194304;
    public static final int CALIB_USE_INTRINSIC_GUESS = 1;
    public static final int CALIB_USE_LU = 131072;
    public static final int CALIB_USE_QR = 1048576;
    public static final int CALIB_ZERO_DISPARITY = 1024;
    public static final int CALIB_ZERO_TANGENT_DIST = 8;
    public static final int CV_DLS = 3;
    public static final int CV_EPNP = 1;
    public static final int CV_ITERATIVE = 0;
    public static final int CV_P3P = 2;
    public static final int CirclesGridFinderParameters_ASYMMETRIC_GRID = 1;
    public static final int CirclesGridFinderParameters_SYMMETRIC_GRID = 0;
    public static final int CvLevMarq_CALC_J = 2;
    public static final int CvLevMarq_CHECK_ERR = 3;
    public static final int CvLevMarq_DONE = 0;
    public static final int CvLevMarq_STARTED = 1;
    public static final int FM_7POINT = 1;
    public static final int FM_8POINT = 2;
    public static final int FM_LMEDS = 4;
    public static final int FM_RANSAC = 8;
    public static final int LMEDS = 4;
    public static final int PROJ_SPHERICAL_EQRECT = 1;
    public static final int PROJ_SPHERICAL_ORTHO = 0;
    public static final int RANSAC = 8;
    public static final int RHO = 16;
    public static final int SOLVEPNP_AP3P = 5;
    public static final int SOLVEPNP_DLS = 3;
    public static final int SOLVEPNP_EPNP = 1;
    public static final int SOLVEPNP_IPPE = 6;
    public static final int SOLVEPNP_IPPE_SQUARE = 7;
    public static final int SOLVEPNP_ITERATIVE = 0;
    public static final int SOLVEPNP_MAX_COUNT = 8;
    public static final int SOLVEPNP_P3P = 2;
    public static final int SOLVEPNP_UPNP = 4;
    public static final int fisheye_CALIB_CHECK_COND = 4;
    public static final int fisheye_CALIB_FIX_INTRINSIC = 256;
    public static final int fisheye_CALIB_FIX_K1 = 16;
    public static final int fisheye_CALIB_FIX_K2 = 32;
    public static final int fisheye_CALIB_FIX_K3 = 64;
    public static final int fisheye_CALIB_FIX_K4 = 128;
    public static final int fisheye_CALIB_FIX_PRINCIPAL_POINT = 512;
    public static final int fisheye_CALIB_FIX_SKEW = 8;
    public static final int fisheye_CALIB_RECOMPUTE_EXTRINSIC = 2;
    public static final int fisheye_CALIB_USE_INTRINSIC_GUESS = 1;

    private static native double[] RQDecomp3x3_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native double[] RQDecomp3x3_1(long j, long j2, long j3, long j4, long j5);

    private static native double[] RQDecomp3x3_2(long j, long j2, long j3, long j4);

    private static native double[] RQDecomp3x3_3(long j, long j2, long j3);

    private static native void Rodrigues_0(long j, long j2, long j3);

    private static native void Rodrigues_1(long j, long j2);

    private static native double calibrateCameraExtended_0(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int i, int i2, int i3, double d3);

    private static native double calibrateCameraExtended_1(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int i);

    private static native double calibrateCameraExtended_2(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    private static native double calibrateCameraROExtended_0(long j, long j2, double d, double d2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i2, int i3, int i4, double d3);

    private static native double calibrateCameraROExtended_1(long j, long j2, double d, double d2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i2);

    private static native double calibrateCameraROExtended_2(long j, long j2, double d, double d2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    private static native double calibrateCameraRO_0(long j, long j2, double d, double d2, int i, long j3, long j4, long j5, long j6, long j7, int i2, int i3, int i4, double d3);

    private static native double calibrateCameraRO_1(long j, long j2, double d, double d2, int i, long j3, long j4, long j5, long j6, long j7, int i2);

    private static native double calibrateCameraRO_2(long j, long j2, double d, double d2, int i, long j3, long j4, long j5, long j6, long j7);

    private static native double calibrateCamera_0(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i, int i2, int i3, double d3);

    private static native double calibrateCamera_1(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i);

    private static native double calibrateCamera_2(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6);

    private static native void calibrateHandEye_0(long j, long j2, long j3, long j4, long j5, long j6, int i);

    private static native void calibrateHandEye_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void calibrationMatrixValues_0(long j, double d, double d2, double d3, double d4, double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5);

    private static native boolean checkChessboard_0(long j, double d, double d2);

    private static native void composeRT_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14);

    private static native void composeRT_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13);

    private static native void composeRT_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12);

    private static native void composeRT_3(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    private static native void composeRT_4(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10);

    private static native void composeRT_5(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    private static native void composeRT_6(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    private static native void composeRT_7(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native void composeRT_8(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void computeCorrespondEpilines_0(long j, int i, long j2, long j3);

    private static native void convertPointsFromHomogeneous_0(long j, long j2);

    private static native void convertPointsToHomogeneous_0(long j, long j2);

    private static native void correctMatches_0(long j, long j2, long j3, long j4, long j5);

    private static native void decomposeEssentialMat_0(long j, long j2, long j3, long j4);

    private static native int decomposeHomographyMat_0(long j, long j2, long j3, long j4, long j5);

    private static native void decomposeProjectionMatrix_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    private static native void decomposeProjectionMatrix_1(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native void decomposeProjectionMatrix_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void decomposeProjectionMatrix_3(long j, long j2, long j3, long j4, long j5);

    private static native void decomposeProjectionMatrix_4(long j, long j2, long j3, long j4);

    private static native void drawChessboardCorners_0(long j, double d, double d2, long j2, boolean z);

    private static native void drawFrameAxes_0(long j, long j2, long j3, long j4, long j5, float f, int i);

    private static native void drawFrameAxes_1(long j, long j2, long j3, long j4, long j5, float f);

    private static native long estimateAffine2D_0(long j, long j2, long j3, int i, double d, long j4, double d2, long j5);

    private static native long estimateAffine2D_1(long j, long j2, long j3, int i, double d, long j4, double d2);

    private static native long estimateAffine2D_2(long j, long j2, long j3, int i, double d, long j4);

    private static native long estimateAffine2D_3(long j, long j2, long j3, int i, double d);

    private static native long estimateAffine2D_4(long j, long j2, long j3, int i);

    private static native long estimateAffine2D_5(long j, long j2, long j3);

    private static native long estimateAffine2D_6(long j, long j2);

    private static native int estimateAffine3D_0(long j, long j2, long j3, long j4, double d, double d2);

    private static native int estimateAffine3D_1(long j, long j2, long j3, long j4, double d);

    private static native int estimateAffine3D_2(long j, long j2, long j3, long j4);

    private static native long estimateAffinePartial2D_0(long j, long j2, long j3, int i, double d, long j4, double d2, long j5);

    private static native long estimateAffinePartial2D_1(long j, long j2, long j3, int i, double d, long j4, double d2);

    private static native long estimateAffinePartial2D_2(long j, long j2, long j3, int i, double d, long j4);

    private static native long estimateAffinePartial2D_3(long j, long j2, long j3, int i, double d);

    private static native long estimateAffinePartial2D_4(long j, long j2, long j3, int i);

    private static native long estimateAffinePartial2D_5(long j, long j2, long j3);

    private static native long estimateAffinePartial2D_6(long j, long j2);

    private static native double[] estimateChessboardSharpness_0(long j, double d, double d2, long j2, float f, boolean z, long j3);

    private static native double[] estimateChessboardSharpness_1(long j, double d, double d2, long j2, float f, boolean z);

    private static native double[] estimateChessboardSharpness_2(long j, double d, double d2, long j2, float f);

    private static native double[] estimateChessboardSharpness_3(long j, double d, double d2, long j2);

    private static native int estimateTranslation3D_0(long j, long j2, long j3, long j4, double d, double d2);

    private static native int estimateTranslation3D_1(long j, long j2, long j3, long j4, double d);

    private static native int estimateTranslation3D_2(long j, long j2, long j3, long j4);

    private static native void filterHomographyDecompByVisibleRefpoints_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void filterHomographyDecompByVisibleRefpoints_1(long j, long j2, long j3, long j4, long j5);

    private static native void filterSpeckles_0(long j, double d, int i, double d2, long j2);

    private static native void filterSpeckles_1(long j, double d, int i, double d2);

    private static native boolean find4QuadCornerSubpix_0(long j, long j2, double d, double d2);

    private static native boolean findChessboardCornersSBWithMeta_0(long j, double d, double d2, long j2, int i, long j3);

    private static native boolean findChessboardCornersSB_0(long j, double d, double d2, long j2, int i);

    private static native boolean findChessboardCornersSB_1(long j, double d, double d2, long j2);

    private static native boolean findChessboardCorners_0(long j, double d, double d2, long j2, int i);

    private static native boolean findChessboardCorners_1(long j, double d, double d2, long j2);

    private static native boolean findCirclesGrid_0(long j, double d, double d2, long j2, int i);

    private static native boolean findCirclesGrid_2(long j, double d, double d2, long j2);

    private static native long findEssentialMat_0(long j, long j2, long j3, int i, double d, double d2, long j4);

    private static native long findEssentialMat_1(long j, long j2, long j3, int i, double d, double d2);

    private static native long findEssentialMat_10(long j, long j2, double d);

    private static native long findEssentialMat_11(long j, long j2);

    private static native long findEssentialMat_2(long j, long j2, long j3, int i, double d);

    private static native long findEssentialMat_3(long j, long j2, long j3, int i);

    private static native long findEssentialMat_4(long j, long j2, long j3);

    private static native long findEssentialMat_5(long j, long j2, double d, double d2, double d3, int i, double d4, double d5, long j3);

    private static native long findEssentialMat_6(long j, long j2, double d, double d2, double d3, int i, double d4, double d5);

    private static native long findEssentialMat_7(long j, long j2, double d, double d2, double d3, int i, double d4);

    private static native long findEssentialMat_8(long j, long j2, double d, double d2, double d3, int i);

    private static native long findEssentialMat_9(long j, long j2, double d, double d2, double d3);

    private static native long findFundamentalMat_0(long j, long j2, int i, double d, double d2, int i2, long j3);

    private static native long findFundamentalMat_1(long j, long j2, int i, double d, double d2, int i2);

    private static native long findFundamentalMat_2(long j, long j2, int i, double d, double d2, long j3);

    private static native long findFundamentalMat_3(long j, long j2, int i, double d, double d2);

    private static native long findFundamentalMat_4(long j, long j2, int i, double d);

    private static native long findFundamentalMat_5(long j, long j2, int i);

    private static native long findFundamentalMat_6(long j, long j2);

    private static native long findHomography_0(long j, long j2, int i, double d, long j3, int i2, double d2);

    private static native long findHomography_1(long j, long j2, int i, double d, long j3, int i2);

    private static native long findHomography_2(long j, long j2, int i, double d, long j3);

    private static native long findHomography_3(long j, long j2, int i, double d);

    private static native long findHomography_4(long j, long j2, int i);

    private static native long findHomography_5(long j, long j2);

    private static native double fisheye_calibrate_0(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i, int i2, int i3, double d3);

    private static native double fisheye_calibrate_1(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i);

    private static native double fisheye_calibrate_2(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6);

    private static native void fisheye_distortPoints_0(long j, long j2, long j3, long j4, double d);

    private static native void fisheye_distortPoints_1(long j, long j2, long j3, long j4);

    private static native void fisheye_estimateNewCameraMatrixForUndistortRectify_0(long j, long j2, double d, double d2, long j3, long j4, double d3, double d4, double d5, double d6);

    private static native void fisheye_estimateNewCameraMatrixForUndistortRectify_1(long j, long j2, double d, double d2, long j3, long j4, double d3, double d4, double d5);

    private static native void fisheye_estimateNewCameraMatrixForUndistortRectify_2(long j, long j2, double d, double d2, long j3, long j4, double d3);

    private static native void fisheye_estimateNewCameraMatrixForUndistortRectify_3(long j, long j2, double d, double d2, long j3, long j4);

    private static native void fisheye_initUndistortRectifyMap_0(long j, long j2, long j3, long j4, double d, double d2, int i, long j5, long j6);

    private static native void fisheye_projectPoints_0(long j, long j2, long j3, long j4, long j5, long j6, double d, long j7);

    private static native void fisheye_projectPoints_1(long j, long j2, long j3, long j4, long j5, long j6, double d);

    private static native void fisheye_projectPoints_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native double fisheye_stereoCalibrate_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, int i, int i2, int i3, double d3);

    private static native double fisheye_stereoCalibrate_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, int i);

    private static native double fisheye_stereoCalibrate_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9);

    private static native void fisheye_stereoRectify_0(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5, double d6);

    private static native void fisheye_stereoRectify_1(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5);

    private static native void fisheye_stereoRectify_2(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4);

    private static native void fisheye_stereoRectify_3(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i);

    private static native void fisheye_undistortImage_0(long j, long j2, long j3, long j4, long j5, double d, double d2);

    private static native void fisheye_undistortImage_1(long j, long j2, long j3, long j4, long j5);

    private static native void fisheye_undistortImage_2(long j, long j2, long j3, long j4);

    private static native void fisheye_undistortPoints_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void fisheye_undistortPoints_1(long j, long j2, long j3, long j4, long j5);

    private static native void fisheye_undistortPoints_2(long j, long j2, long j3, long j4);

    private static native long getDefaultNewCameraMatrix_0(long j, double d, double d2, boolean z);

    private static native long getDefaultNewCameraMatrix_1(long j, double d, double d2);

    private static native long getDefaultNewCameraMatrix_2(long j);

    private static native long getOptimalNewCameraMatrix_0(long j, long j2, double d, double d2, double d3, double d4, double d5, double[] dArr, boolean z);

    private static native long getOptimalNewCameraMatrix_1(long j, long j2, double d, double d2, double d3, double d4, double d5, double[] dArr);

    private static native long getOptimalNewCameraMatrix_2(long j, long j2, double d, double d2, double d3, double d4, double d5);

    private static native long getOptimalNewCameraMatrix_3(long j, long j2, double d, double d2, double d3);

    private static native double[] getValidDisparityROI_0(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11);

    private static native long initCameraMatrix2D_0(long j, long j2, double d, double d2, double d3);

    private static native long initCameraMatrix2D_1(long j, long j2, double d, double d2);

    private static native void initUndistortRectifyMap_0(long j, long j2, long j3, long j4, double d, double d2, int i, long j5, long j6);

    private static native void matMulDeriv_0(long j, long j2, long j3, long j4);

    private static native void projectPoints_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d);

    private static native void projectPoints_1(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native void projectPoints_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native int recoverPose_0(long j, long j2, long j3, long j4, long j5, double d, double d2, double d3, long j6);

    private static native int recoverPose_1(long j, long j2, long j3, long j4, long j5, double d, double d2, double d3);

    private static native int recoverPose_2(long j, long j2, long j3, long j4, long j5, double d);

    private static native int recoverPose_3(long j, long j2, long j3, long j4, long j5);

    private static native int recoverPose_4(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native int recoverPose_5(long j, long j2, long j3, long j4, long j5, long j6);

    private static native int recoverPose_6(long j, long j2, long j3, long j4, long j5, long j6, double d, long j7, long j8);

    private static native int recoverPose_7(long j, long j2, long j3, long j4, long j5, long j6, double d, long j7);

    private static native int recoverPose_8(long j, long j2, long j3, long j4, long j5, long j6, double d);

    private static native float rectify3Collinear_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, double d, double d2, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, double d3, double d4, double d5, double[] dArr, double[] dArr2, int i);

    private static native void reprojectImageTo3D_0(long j, long j2, long j3, boolean z, int i);

    private static native void reprojectImageTo3D_1(long j, long j2, long j3, boolean z);

    private static native void reprojectImageTo3D_2(long j, long j2, long j3);

    private static native double sampsonDistance_0(long j, long j2, long j3);

    private static native int solveP3P_0(long j, long j2, long j3, long j4, long j5, long j6, int i);

    private static native int solvePnPGeneric_0(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, long j7, long j8, long j9);

    private static native int solvePnPGeneric_1(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, long j7, long j8);

    private static native int solvePnPGeneric_2(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, long j7);

    private static native int solvePnPGeneric_3(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i);

    private static native int solvePnPGeneric_4(long j, long j2, long j3, long j4, long j5, long j6, boolean z);

    private static native int solvePnPGeneric_5(long j, long j2, long j3, long j4, long j5, long j6);

    private static native boolean solvePnPRansac_0(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, float f, double d, long j7, int i2);

    private static native boolean solvePnPRansac_1(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, float f, double d, long j7);

    private static native boolean solvePnPRansac_2(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, float f, double d);

    private static native boolean solvePnPRansac_3(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, float f);

    private static native boolean solvePnPRansac_4(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i);

    private static native boolean solvePnPRansac_5(long j, long j2, long j3, long j4, long j5, long j6, boolean z);

    private static native boolean solvePnPRansac_6(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void solvePnPRefineLM_0(long j, long j2, long j3, long j4, long j5, long j6, int i, int i2, double d);

    private static native void solvePnPRefineLM_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void solvePnPRefineVVS_0(long j, long j2, long j3, long j4, long j5, long j6, int i, int i2, double d, double d2);

    private static native void solvePnPRefineVVS_1(long j, long j2, long j3, long j4, long j5, long j6, int i, int i2, double d);

    private static native void solvePnPRefineVVS_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native boolean solvePnP_0(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i);

    private static native boolean solvePnP_1(long j, long j2, long j3, long j4, long j5, long j6, boolean z);

    private static native boolean solvePnP_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native double stereoCalibrateExtended_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, long j12, int i, int i2, int i3, double d3);

    private static native double stereoCalibrateExtended_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, long j12, int i);

    private static native double stereoCalibrateExtended_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, long j12);

    private static native double stereoCalibrate_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, int i, int i2, int i3, double d3);

    private static native double stereoCalibrate_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, int i);

    private static native double stereoCalibrate_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11);

    private static native boolean stereoRectifyUncalibrated_0(long j, long j2, long j3, double d, double d2, long j4, long j5, double d3);

    private static native boolean stereoRectifyUncalibrated_1(long j, long j2, long j3, double d, double d2, long j4, long j5);

    private static native void stereoRectify_0(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5, double[] dArr, double[] dArr2);

    private static native void stereoRectify_1(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5, double[] dArr);

    private static native void stereoRectify_2(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5);

    private static native void stereoRectify_3(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3);

    private static native void stereoRectify_4(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i);

    private static native void stereoRectify_5(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    private static native void triangulatePoints_0(long j, long j2, long j3, long j4, long j5);

    private static native void undistortPointsIter_0(long j, long j2, long j3, long j4, long j5, long j6, int i, int i2, double d);

    private static native void undistortPoints_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void undistortPoints_1(long j, long j2, long j3, long j4, long j5);

    private static native void undistortPoints_2(long j, long j2, long j3, long j4);

    private static native void undistort_0(long j, long j2, long j3, long j4, long j5);

    private static native void undistort_1(long j, long j2, long j3, long j4);

    private static native void validateDisparity_0(long j, long j2, int i, int i2, int i3);

    private static native void validateDisparity_1(long j, long j2, int i, int i2);

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j, double d2, long j2) {
        return new Mat(estimateAffine2D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j, d2, j2));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j, double d2) {
        return new Mat(estimateAffine2D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j, d2));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j) {
        return new Mat(estimateAffine2D_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3, int i, double d) {
        return new Mat(estimateAffine2D_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3, int i) {
        return new Mat(estimateAffine2D_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3) {
        return new Mat(estimateAffine2D_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2) {
        return new Mat(estimateAffine2D_6(mat.nativeObj, mat2.nativeObj));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j, double d2, long j2) {
        return new Mat(estimateAffinePartial2D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j, d2, j2));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j, double d2) {
        return new Mat(estimateAffinePartial2D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j, d2));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j) {
        return new Mat(estimateAffinePartial2D_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3, int i, double d) {
        return new Mat(estimateAffinePartial2D_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3, int i) {
        return new Mat(estimateAffinePartial2D_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3) {
        return new Mat(estimateAffinePartial2D_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2) {
        return new Mat(estimateAffinePartial2D_6(mat.nativeObj, mat2.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3, int i, double d, double d2, Mat mat4) {
        return new Mat(findEssentialMat_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, d2, mat4.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3, int i, double d, double d2) {
        return new Mat(findEssentialMat_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, d2));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3, int i, double d) {
        return new Mat(findEssentialMat_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3, int i) {
        return new Mat(findEssentialMat_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3) {
        return new Mat(findEssentialMat_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point, int i, double d2, double d3, Mat mat3) {
        Object obj;
        Point point2 = point;
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        double d4 = point2.x;
        double d5 = point2.y;
        Object obj2 = obj;
        return new Mat(findEssentialMat_5(j, j2, d, d4, d5, i, d2, d3, mat3.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point, int i, double d2, double d3) {
        Point point2 = point;
        return new Mat(findEssentialMat_6(mat.nativeObj, mat2.nativeObj, d, point2.x, point2.y, i, d2, d3));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point, int i, double d2) {
        Point point2 = point;
        return new Mat(findEssentialMat_7(mat.nativeObj, mat2.nativeObj, d, point2.x, point2.y, i, d2));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point, int i) {
        Point point2 = point;
        return new Mat(findEssentialMat_8(mat.nativeObj, mat2.nativeObj, d, point2.x, point2.y, i));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point) {
        return new Mat(findEssentialMat_9(mat.nativeObj, mat2.nativeObj, d, point.x, point.y));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d) {
        return new Mat(findEssentialMat_10(mat.nativeObj, mat2.nativeObj, d));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2) {
        return new Mat(findEssentialMat_11(mat.nativeObj, mat2.nativeObj));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, double d2, int i2, Mat mat) {
        return new Mat(findFundamentalMat_0(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, d2, i2, mat.nativeObj));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, double d2, int i2) {
        return new Mat(findFundamentalMat_1(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, d2, i2));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, double d2, Mat mat) {
        return new Mat(findFundamentalMat_2(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, d2, mat.nativeObj));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, double d2) {
        return new Mat(findFundamentalMat_3(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, d2));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d) {
        return new Mat(findFundamentalMat_4(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i) {
        return new Mat(findFundamentalMat_5(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2) {
        return new Mat(findFundamentalMat_6(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, Mat mat, int i2, double d2) {
        return new Mat(findHomography_0(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, mat.nativeObj, i2, d2));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, Mat mat, int i2) {
        return new Mat(findHomography_1(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, mat.nativeObj, i2));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, Mat mat) {
        return new Mat(findHomography_2(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, mat.nativeObj));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d) {
        return new Mat(findHomography_3(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i) {
        return new Mat(findHomography_4(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2) {
        return new Mat(findHomography_5(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj));
    }

    public static Mat getDefaultNewCameraMatrix(Mat mat, Size size, boolean z) {
        return new Mat(getDefaultNewCameraMatrix_0(mat.nativeObj, size.width, size.height, z));
    }

    public static Mat getDefaultNewCameraMatrix(Mat mat, Size size) {
        return new Mat(getDefaultNewCameraMatrix_1(mat.nativeObj, size.width, size.height));
    }

    public static Mat getDefaultNewCameraMatrix(Mat mat) {
        return new Mat(getDefaultNewCameraMatrix_2(mat.nativeObj));
    }

    public static Mat getOptimalNewCameraMatrix(Mat mat, Mat mat2, Size size, double d, Size size2, Rect rect, boolean z) {
        Size size3 = size;
        Size size4 = size2;
        Rect rect2 = rect;
        double[] dArr = new double[4];
        Rect rect3 = rect;
        Mat mat3 = new Mat(getOptimalNewCameraMatrix_0(mat.nativeObj, mat2.nativeObj, size3.width, size3.height, d, size4.width, size4.height, dArr, z));
        if (rect3 != null) {
            rect3.x = (int) dArr[0];
            rect3.y = (int) dArr[1];
            rect3.width = (int) dArr[2];
            rect3.height = (int) dArr[3];
        }
        return mat3;
    }

    public static Mat getOptimalNewCameraMatrix(Mat mat, Mat mat2, Size size, double d, Size size2, Rect rect) {
        Size size3 = size;
        Size size4 = size2;
        Rect rect2 = rect;
        double[] dArr = new double[4];
        Rect rect3 = rect;
        Mat mat3 = new Mat(getOptimalNewCameraMatrix_1(mat.nativeObj, mat2.nativeObj, size3.width, size3.height, d, size4.width, size4.height, dArr));
        if (rect3 != null) {
            rect3.x = (int) dArr[0];
            rect3.y = (int) dArr[1];
            rect3.width = (int) dArr[2];
            rect3.height = (int) dArr[3];
        }
        return mat3;
    }

    public static Mat getOptimalNewCameraMatrix(Mat mat, Mat mat2, Size size, double d, Size size2) {
        Size size3 = size;
        Size size4 = size2;
        return new Mat(getOptimalNewCameraMatrix_2(mat.nativeObj, mat2.nativeObj, size3.width, size3.height, d, size4.width, size4.height));
    }

    public static Mat getOptimalNewCameraMatrix(Mat mat, Mat mat2, Size size, double d) {
        return new Mat(getOptimalNewCameraMatrix_3(mat.nativeObj, mat2.nativeObj, size.width, size.height, d));
    }

    public static Mat initCameraMatrix2D(List<MatOfPoint3f> list, List<MatOfPoint2f> list2, Size size, double d) {
        int i = 0;
        Mat vector_vector_Point3f_to_Mat = Converters.vector_vector_Point3f_to_Mat(list, new ArrayList(list != null ? list.size() : 0));
        if (list2 != null) {
            i = list2.size();
        }
        return new Mat(initCameraMatrix2D_0(vector_vector_Point3f_to_Mat.nativeObj, Converters.vector_vector_Point2f_to_Mat(list2, new ArrayList(i)).nativeObj, size.width, size.height, d));
    }

    public static Mat initCameraMatrix2D(List<MatOfPoint3f> list, List<MatOfPoint2f> list2, Size size) {
        int i = 0;
        Mat vector_vector_Point3f_to_Mat = Converters.vector_vector_Point3f_to_Mat(list, new ArrayList(list != null ? list.size() : 0));
        if (list2 != null) {
            i = list2.size();
        }
        return new Mat(initCameraMatrix2D_1(vector_vector_Point3f_to_Mat.nativeObj, Converters.vector_vector_Point2f_to_Mat(list2, new ArrayList(i)).nativeObj, size.width, size.height));
    }

    public static Rect getValidDisparityROI(Rect rect, Rect rect2, int i, int i2, int i3) {
        Rect rect3 = rect;
        Rect rect4 = rect2;
        return new Rect(getValidDisparityROI_0(rect3.x, rect3.y, rect3.width, rect3.height, rect4.x, rect4.y, rect4.width, rect4.height, i, i2, i3));
    }

    public static Scalar estimateChessboardSharpness(Mat mat, Size size, Mat mat2, float f, boolean z, Mat mat3) {
        Size size2 = size;
        return new Scalar(estimateChessboardSharpness_0(mat.nativeObj, size2.width, size2.height, mat2.nativeObj, f, z, mat3.nativeObj));
    }

    public static Scalar estimateChessboardSharpness(Mat mat, Size size, Mat mat2, float f, boolean z) {
        return new Scalar(estimateChessboardSharpness_1(mat.nativeObj, size.width, size.height, mat2.nativeObj, f, z));
    }

    public static Scalar estimateChessboardSharpness(Mat mat, Size size, Mat mat2, float f) {
        return new Scalar(estimateChessboardSharpness_2(mat.nativeObj, size.width, size.height, mat2.nativeObj, f));
    }

    public static Scalar estimateChessboardSharpness(Mat mat, Size size, Mat mat2) {
        return new Scalar(estimateChessboardSharpness_3(mat.nativeObj, size.width, size.height, mat2.nativeObj));
    }

    public static double[] RQDecomp3x3(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return RQDecomp3x3_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static double[] RQDecomp3x3(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return RQDecomp3x3_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static double[] RQDecomp3x3(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return RQDecomp3x3_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static double[] RQDecomp3x3(Mat mat, Mat mat2, Mat mat3) {
        return RQDecomp3x3_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static boolean checkChessboard(Mat mat, Size size) {
        return checkChessboard_0(mat.nativeObj, size.width, size.height);
    }

    public static boolean find4QuadCornerSubpix(Mat mat, Mat mat2, Size size) {
        return find4QuadCornerSubpix_0(mat.nativeObj, mat2.nativeObj, size.width, size.height);
    }

    public static boolean findChessboardCorners(Mat mat, Size size, MatOfPoint2f matOfPoint2f, int i) {
        return findChessboardCorners_0(mat.nativeObj, size.width, size.height, matOfPoint2f.nativeObj, i);
    }

    public static boolean findChessboardCorners(Mat mat, Size size, MatOfPoint2f matOfPoint2f) {
        return findChessboardCorners_1(mat.nativeObj, size.width, size.height, matOfPoint2f.nativeObj);
    }

    public static boolean findChessboardCornersSBWithMeta(Mat mat, Size size, Mat mat2, int i, Mat mat3) {
        return findChessboardCornersSBWithMeta_0(mat.nativeObj, size.width, size.height, mat2.nativeObj, i, mat3.nativeObj);
    }

    public static boolean findChessboardCornersSB(Mat mat, Size size, Mat mat2, int i) {
        return findChessboardCornersSB_0(mat.nativeObj, size.width, size.height, mat2.nativeObj, i);
    }

    public static boolean findChessboardCornersSB(Mat mat, Size size, Mat mat2) {
        return findChessboardCornersSB_1(mat.nativeObj, size.width, size.height, mat2.nativeObj);
    }

    public static boolean findCirclesGrid(Mat mat, Size size, Mat mat2, int i) {
        return findCirclesGrid_0(mat.nativeObj, size.width, size.height, mat2.nativeObj, i);
    }

    public static boolean findCirclesGrid(Mat mat, Size size, Mat mat2) {
        return findCirclesGrid_2(mat.nativeObj, size.width, size.height, mat2.nativeObj);
    }

    public static boolean solvePnP(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i) {
        return solvePnP_0(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i);
    }

    public static boolean solvePnP(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z) {
        return solvePnP_1(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z);
    }

    public static boolean solvePnP(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3) {
        return solvePnP_2(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i, float f, double d, Mat mat4, int i2) {
        long j = matOfPoint3f.nativeObj;
        return solvePnPRansac_0(j, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i, f, d, mat4.nativeObj, i2);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i, float f, double d, Mat mat4) {
        long j = matOfPoint3f.nativeObj;
        return solvePnPRansac_1(j, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i, f, d, mat4.nativeObj);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i, float f, double d) {
        return solvePnPRansac_2(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i, f, d);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i, float f) {
        return solvePnPRansac_3(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i, f);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i) {
        return solvePnPRansac_4(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z) {
        return solvePnPRansac_5(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3) {
        return solvePnPRansac_6(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static boolean stereoRectifyUncalibrated(Mat mat, Mat mat2, Mat mat3, Size size, Mat mat4, Mat mat5, double d) {
        Size size2 = size;
        return stereoRectifyUncalibrated_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, size2.width, size2.height, mat4.nativeObj, mat5.nativeObj, d);
    }

    public static boolean stereoRectifyUncalibrated(Mat mat, Mat mat2, Mat mat3, Size size, Mat mat4, Mat mat5) {
        Size size2 = size;
        return stereoRectifyUncalibrated_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, size2.width, size2.height, mat4.nativeObj, mat5.nativeObj);
    }

    public static double calibrateCameraExtended(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat8 = mat6;
        Mat mat9 = mat7;
        long j2 = j;
        double calibrateCameraExtended_0 = calibrateCameraExtended_0(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat9.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Mat mat10 = mat8;
        Converters.Mat_to_vector_Mat(mat10, list3);
        mat10.release();
        Converters.Mat_to_vector_Mat(mat9, list4);
        mat9.release();
        return calibrateCameraExtended_0;
    }

    public static double calibrateCameraExtended(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = vector_Mat_to_Mat2.nativeObj;
        double d = size2.width;
        double d2 = size2.height;
        long j3 = mat.nativeObj;
        long j4 = mat2.nativeObj;
        Mat mat8 = mat7;
        long j5 = mat6.nativeObj;
        Mat mat9 = mat8;
        long j6 = j;
        long j7 = mat9.nativeObj;
        Mat mat10 = mat9;
        Mat mat11 = mat6;
        long j8 = j6;
        double calibrateCameraExtended_1 = calibrateCameraExtended_1(j8, j2, d, d2, j3, j4, j5, j7, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat11, list3);
        mat11.release();
        Mat mat12 = mat10;
        Converters.Mat_to_vector_Mat(mat12, list4);
        mat12.release();
        return calibrateCameraExtended_1;
    }

    public static double calibrateCameraExtended(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        double calibrateCameraExtended_2 = calibrateCameraExtended_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat7.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat7, list4);
        mat7.release();
        return calibrateCameraExtended_2;
    }

    public static double calibrateCamera(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat5 = mat3;
        Mat mat6 = mat4;
        long j2 = j;
        double calibrateCamera_0 = calibrateCamera_0(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat6.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Mat mat7 = mat5;
        Converters.Mat_to_vector_Mat(mat7, list3);
        mat7.release();
        Converters.Mat_to_vector_Mat(mat6, list4);
        mat6.release();
        return calibrateCamera_0;
    }

    public static double calibrateCamera(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = vector_Mat_to_Mat2.nativeObj;
        double d = size2.width;
        double d2 = size2.height;
        long j3 = mat.nativeObj;
        long j4 = mat2.nativeObj;
        Mat mat5 = mat4;
        long j5 = mat3.nativeObj;
        Mat mat6 = mat5;
        long j6 = j;
        long j7 = mat6.nativeObj;
        Mat mat7 = mat6;
        Mat mat8 = mat3;
        long j8 = j7;
        double calibrateCamera_1 = calibrateCamera_1(j6, j2, d, d2, j3, j4, j5, j8, i);
        Converters.Mat_to_vector_Mat(mat8, list3);
        mat8.release();
        Mat mat9 = mat7;
        Converters.Mat_to_vector_Mat(mat9, list4);
        mat9.release();
        return calibrateCamera_1;
    }

    public static double calibrateCamera(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrateCamera_2 = calibrateCamera_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Mat mat5 = mat3;
        Converters.Mat_to_vector_Mat(mat5, list3);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrateCamera_2;
    }

    public static double calibrateCameraROExtended(List<Mat> list, List<Mat> list2, Size size, int i, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, int i2, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat10 = mat9;
        Mat mat11 = mat10;
        Mat mat12 = mat8;
        long j2 = j;
        double calibrateCameraROExtended_0 = calibrateCameraROExtended_0(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, i, mat.nativeObj, mat2.nativeObj, mat8.nativeObj, mat11.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, i2, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Mat mat13 = mat12;
        Converters.Mat_to_vector_Mat(mat13, list3);
        mat13.release();
        Converters.Mat_to_vector_Mat(mat11, list4);
        mat11.release();
        return calibrateCameraROExtended_0;
    }

    public static double calibrateCameraROExtended(List<Mat> list, List<Mat> list2, Size size, int i, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, int i2) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat10 = mat8;
        Mat mat11 = mat9;
        Mat mat12 = mat10;
        long j2 = j;
        double calibrateCameraROExtended_1 = calibrateCameraROExtended_1(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, i, mat.nativeObj, mat2.nativeObj, mat8.nativeObj, mat11.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, i2);
        Converters.Mat_to_vector_Mat(mat12, list3);
        mat12.release();
        Mat mat13 = mat11;
        Converters.Mat_to_vector_Mat(mat13, list4);
        mat13.release();
        return calibrateCameraROExtended_1;
    }

    public static double calibrateCameraROExtended(List<Mat> list, List<Mat> list2, Size size, int i, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat10 = mat8;
        Mat mat11 = mat9;
        Mat mat12 = mat10;
        long j2 = j;
        double calibrateCameraROExtended_2 = calibrateCameraROExtended_2(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, i, mat.nativeObj, mat2.nativeObj, mat8.nativeObj, mat11.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
        Converters.Mat_to_vector_Mat(mat12, list3);
        mat12.release();
        Mat mat13 = mat11;
        Converters.Mat_to_vector_Mat(mat13, list4);
        mat13.release();
        return calibrateCameraROExtended_2;
    }

    public static double calibrateCameraRO(List<Mat> list, List<Mat> list2, Size size, int i, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, int i2, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat4 = new Mat();
        Mat mat5 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat6 = mat5;
        Mat mat7 = mat6;
        Mat mat8 = mat4;
        long j2 = j;
        double calibrateCameraRO_0 = calibrateCameraRO_0(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, i, mat.nativeObj, mat2.nativeObj, mat4.nativeObj, mat7.nativeObj, mat3.nativeObj, i2, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Mat mat9 = mat8;
        Converters.Mat_to_vector_Mat(mat9, list3);
        mat9.release();
        Converters.Mat_to_vector_Mat(mat7, list4);
        mat7.release();
        return calibrateCameraRO_0;
    }

    public static double calibrateCameraRO(List<Mat> list, List<Mat> list2, Size size, int i, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, int i2) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat4 = new Mat();
        Mat mat5 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat6 = mat4;
        Mat mat7 = mat5;
        Mat mat8 = mat6;
        long j2 = j;
        double calibrateCameraRO_1 = calibrateCameraRO_1(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, i, mat.nativeObj, mat2.nativeObj, mat4.nativeObj, mat7.nativeObj, mat3.nativeObj, i2);
        Converters.Mat_to_vector_Mat(mat8, list3);
        mat8.release();
        Mat mat9 = mat7;
        Converters.Mat_to_vector_Mat(mat9, list4);
        mat9.release();
        return calibrateCameraRO_1;
    }

    public static double calibrateCameraRO(List<Mat> list, List<Mat> list2, Size size, int i, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat4 = new Mat();
        Mat mat5 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat6 = mat4;
        Mat mat7 = mat5;
        Mat mat8 = mat6;
        long j2 = j;
        double calibrateCameraRO_2 = calibrateCameraRO_2(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, i, mat.nativeObj, mat2.nativeObj, mat4.nativeObj, mat7.nativeObj, mat3.nativeObj);
        Converters.Mat_to_vector_Mat(mat8, list3);
        mat8.release();
        Mat mat9 = mat7;
        Converters.Mat_to_vector_Mat(mat9, list4);
        mat9.release();
        return calibrateCameraRO_2;
    }

    public static double sampsonDistance(Mat mat, Mat mat2, Mat mat3) {
        return sampsonDistance_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static double stereoCalibrateExtended(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat vector_Mat_to_Mat3 = Converters.vector_Mat_to_Mat(list3);
        long j = vector_Mat_to_Mat.nativeObj;
        return stereoCalibrateExtended_0(j, vector_Mat_to_Mat2.nativeObj, vector_Mat_to_Mat3.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static double stereoCalibrateExtended(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat vector_Mat_to_Mat3 = Converters.vector_Mat_to_Mat(list3);
        long j = vector_Mat_to_Mat.nativeObj;
        return stereoCalibrateExtended_1(j, vector_Mat_to_Mat2.nativeObj, vector_Mat_to_Mat3.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, i);
    }

    public static double stereoCalibrateExtended(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9) {
        Size size2 = size;
        return stereoCalibrateExtended_2(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat vector_Mat_to_Mat3 = Converters.vector_Mat_to_Mat(list3);
        long j = vector_Mat_to_Mat.nativeObj;
        return stereoCalibrate_0(j, vector_Mat_to_Mat2.nativeObj, vector_Mat_to_Mat3.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat vector_Mat_to_Mat3 = Converters.vector_Mat_to_Mat(list3);
        long j = vector_Mat_to_Mat.nativeObj;
        return stereoCalibrate_1(j, vector_Mat_to_Mat2.nativeObj, vector_Mat_to_Mat3.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, i);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8) {
        Size size2 = size;
        return stereoCalibrate_2(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj);
    }

    public static double fisheye_calibrate(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat5 = mat3;
        Mat mat6 = mat4;
        long j2 = j;
        double fisheye_calibrate_0 = fisheye_calibrate_0(j2, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat6.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Mat mat7 = mat5;
        Converters.Mat_to_vector_Mat(mat7, list3);
        mat7.release();
        Converters.Mat_to_vector_Mat(mat6, list4);
        mat6.release();
        return fisheye_calibrate_0;
    }

    public static double fisheye_calibrate(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = vector_Mat_to_Mat2.nativeObj;
        double d = size2.width;
        double d2 = size2.height;
        long j3 = mat.nativeObj;
        long j4 = mat2.nativeObj;
        Mat mat5 = mat4;
        long j5 = mat3.nativeObj;
        Mat mat6 = mat5;
        long j6 = j;
        long j7 = mat6.nativeObj;
        Mat mat7 = mat6;
        Mat mat8 = mat3;
        long j8 = j7;
        double fisheye_calibrate_1 = fisheye_calibrate_1(j6, j2, d, d2, j3, j4, j5, j8, i);
        Converters.Mat_to_vector_Mat(mat8, list3);
        mat8.release();
        Mat mat9 = mat7;
        Converters.Mat_to_vector_Mat(mat9, list4);
        mat9.release();
        return fisheye_calibrate_1;
    }

    public static double fisheye_calibrate(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double fisheye_calibrate_2 = fisheye_calibrate_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Mat mat5 = mat3;
        Converters.Mat_to_vector_Mat(mat5, list3);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return fisheye_calibrate_2;
    }

    public static double fisheye_stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat vector_Mat_to_Mat3 = Converters.vector_Mat_to_Mat(list3);
        long j = vector_Mat_to_Mat.nativeObj;
        return fisheye_stereoCalibrate_0(j, vector_Mat_to_Mat2.nativeObj, vector_Mat_to_Mat3.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static double fisheye_stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat vector_Mat_to_Mat3 = Converters.vector_Mat_to_Mat(list3);
        long j = vector_Mat_to_Mat.nativeObj;
        return fisheye_stereoCalibrate_1(j, vector_Mat_to_Mat2.nativeObj, vector_Mat_to_Mat3.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, i);
    }

    public static double fisheye_stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6) {
        Size size2 = size;
        return fisheye_stereoCalibrate_2(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj);
    }

    public static float rectify3Collinear(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, List<Mat> list, List<Mat> list2, Size size, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, Mat mat12, Mat mat13, Mat mat14, Mat mat15, Mat mat16, Mat mat17, double d, Size size2, Rect rect, Rect rect2, int i) {
        Size size3 = size;
        Size size4 = size2;
        Rect rect3 = rect;
        Rect rect4 = rect2;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        long j3 = mat3.nativeObj;
        long j4 = mat4.nativeObj;
        double[] dArr3 = dArr;
        long j5 = mat5.nativeObj;
        double[] dArr4 = dArr3;
        double[] dArr5 = dArr2;
        Mat mat18 = vector_Mat_to_Mat;
        long j6 = mat6.nativeObj;
        long j7 = mat18.nativeObj;
        long j8 = vector_Mat_to_Mat2.nativeObj;
        double d2 = size3.width;
        double d3 = size3.height;
        long j9 = mat7.nativeObj;
        long j10 = mat8.nativeObj;
        long j11 = mat9.nativeObj;
        long j12 = mat10.nativeObj;
        long j13 = mat11.nativeObj;
        long j14 = mat12.nativeObj;
        long j15 = mat13.nativeObj;
        long j16 = mat14.nativeObj;
        long j17 = mat15.nativeObj;
        long j18 = mat16.nativeObj;
        long j19 = mat17.nativeObj;
        double d4 = size4.width;
        double d5 = size4.height;
        long j20 = j;
        float rectify3Collinear_0 = rectify3Collinear_0(j20, j2, j3, j4, j5, j6, j7, j8, d2, d3, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, d, d4, d5, dArr, dArr2, i);
        Rect rect5 = rect;
        if (rect5 != null) {
            rect5.x = (int) dArr4[0];
            rect5.y = (int) dArr4[1];
            rect5.width = (int) dArr4[2];
            rect5.height = (int) dArr4[3];
        }
        Rect rect6 = rect2;
        if (rect6 != null) {
            rect6.x = (int) dArr5[0];
            rect6.y = (int) dArr5[1];
            rect6.width = (int) dArr5[2];
            rect6.height = (int) dArr5[3];
        }
        return rectify3Collinear_0;
    }

    public static int decomposeHomographyMat(Mat mat, Mat mat2, List<Mat> list, List<Mat> list2, List<Mat> list3) {
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        Mat mat5 = new Mat();
        int decomposeHomographyMat_0 = decomposeHomographyMat_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        List<Mat> list4 = list;
        Converters.Mat_to_vector_Mat(mat3, list);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list2);
        mat4.release();
        Converters.Mat_to_vector_Mat(mat5, list3);
        mat5.release();
        return decomposeHomographyMat_0;
    }

    public static int estimateAffine3D(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2) {
        return estimateAffine3D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2);
    }

    public static int estimateAffine3D(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d) {
        return estimateAffine3D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d);
    }

    public static int estimateAffine3D(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return estimateAffine3D_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static int estimateTranslation3D(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2) {
        return estimateTranslation3D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2);
    }

    public static int estimateTranslation3D(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d) {
        return estimateTranslation3D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d);
    }

    public static int estimateTranslation3D(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return estimateTranslation3D_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, double d, Point point, Mat mat6) {
        Point point2 = point;
        long j = mat.nativeObj;
        return recoverPose_0(j, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, d, point2.x, point2.y, mat6.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, double d, Point point) {
        Point point2 = point;
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        long j3 = mat3.nativeObj;
        long j4 = mat4.nativeObj;
        return recoverPose_1(j, j2, j3, j4, mat5.nativeObj, d, point2.x, point2.y);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, double d) {
        return recoverPose_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, d);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return recoverPose_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7) {
        return recoverPose_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return recoverPose_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d, Mat mat7, Mat mat8) {
        double d2 = d;
        long j = mat.nativeObj;
        return recoverPose_6(j, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d2, mat7.nativeObj, mat8.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d, Mat mat7) {
        return recoverPose_7(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d, mat7.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d) {
        return recoverPose_8(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d);
    }

    public static int solveP3P(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, int i) {
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        int solveP3P_0 = solveP3P_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat5, list);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat6, list2);
        mat6.release();
        return solveP3P_0;
    }

    public static int solvePnPGeneric(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, boolean z, int i, Mat mat5, Mat mat6, Mat mat7) {
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        long j3 = mat3.nativeObj;
        long j4 = mat4.nativeObj;
        long j5 = mat8.nativeObj;
        long j6 = mat9.nativeObj;
        Mat mat10 = mat8;
        Mat mat11 = mat10;
        long j7 = j;
        int solvePnPGeneric_0 = solvePnPGeneric_0(j7, j2, j3, j4, j5, j6, z, i, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
        Mat mat12 = mat11;
        Converters.Mat_to_vector_Mat(mat12, list);
        mat12.release();
        Mat mat13 = mat9;
        Converters.Mat_to_vector_Mat(mat13, list2);
        mat13.release();
        return solvePnPGeneric_0;
    }

    public static int solvePnPGeneric(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, boolean z, int i, Mat mat5, Mat mat6) {
        Mat mat7 = new Mat();
        Mat mat8 = new Mat();
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        long j3 = mat3.nativeObj;
        long j4 = mat4.nativeObj;
        long j5 = mat7.nativeObj;
        long j6 = mat8.nativeObj;
        Mat mat9 = mat7;
        Mat mat10 = mat9;
        long j7 = j;
        int solvePnPGeneric_1 = solvePnPGeneric_1(j7, j2, j3, j4, j5, j6, z, i, mat5.nativeObj, mat6.nativeObj);
        Mat mat11 = mat10;
        Converters.Mat_to_vector_Mat(mat11, list);
        mat11.release();
        Mat mat12 = mat8;
        Converters.Mat_to_vector_Mat(mat12, list2);
        mat12.release();
        return solvePnPGeneric_1;
    }

    public static int solvePnPGeneric(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, boolean z, int i, Mat mat5) {
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        int solvePnPGeneric_2 = solvePnPGeneric_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat6.nativeObj, mat7.nativeObj, z, i, mat5.nativeObj);
        Converters.Mat_to_vector_Mat(mat6, list);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat7, list2);
        mat7.release();
        return solvePnPGeneric_2;
    }

    public static int solvePnPGeneric(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, boolean z, int i) {
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        int solvePnPGeneric_3 = solvePnPGeneric_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, z, i);
        Converters.Mat_to_vector_Mat(mat5, list);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat6, list2);
        mat6.release();
        return solvePnPGeneric_3;
    }

    public static int solvePnPGeneric(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, boolean z) {
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        int solvePnPGeneric_4 = solvePnPGeneric_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, z);
        Converters.Mat_to_vector_Mat(mat5, list);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat6, list2);
        mat6.release();
        return solvePnPGeneric_4;
    }

    public static int solvePnPGeneric(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2) {
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        int solvePnPGeneric_5 = solvePnPGeneric_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
        Converters.Mat_to_vector_Mat(mat5, list);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat6, list2);
        mat6.release();
        return solvePnPGeneric_5;
    }

    public static void Rodrigues(Mat mat, Mat mat2, Mat mat3) {
        Rodrigues_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void Rodrigues(Mat mat, Mat mat2) {
        Rodrigues_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void calibrateHandEye(List<Mat> list, List<Mat> list2, List<Mat> list3, List<Mat> list4, Mat mat, Mat mat2, int i) {
        calibrateHandEye_0(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, Converters.vector_Mat_to_Mat(list4).nativeObj, mat.nativeObj, mat2.nativeObj, i);
    }

    public static void calibrateHandEye(List<Mat> list, List<Mat> list2, List<Mat> list3, List<Mat> list4, Mat mat, Mat mat2) {
        calibrateHandEye_1(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, Converters.vector_Mat_to_Mat(list4).nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public static void calibrationMatrixValues(Mat mat, Size size, double d, double d2, double[] dArr, double[] dArr2, double[] dArr3, Point point, double[] dArr4) {
        Size size2 = size;
        Point point2 = point;
        double[] dArr5 = new double[1];
        double[] dArr6 = new double[1];
        double[] dArr7 = new double[2];
        double[] dArr8 = new double[1];
        double[] dArr9 = new double[1];
        double[] dArr10 = dArr6;
        double[] dArr11 = dArr5;
        calibrationMatrixValues_0(mat.nativeObj, size2.width, size2.height, d, d2, dArr5, dArr6, dArr9, dArr7, dArr8);
        if (dArr != null) {
            dArr[0] = dArr11[0];
        }
        if (dArr2 != null) {
            dArr2[0] = dArr10[0];
        }
        if (dArr3 != null) {
            dArr3[0] = dArr9[0];
        }
        if (point2 != null) {
            point2.x = dArr7[0];
            point2.y = dArr7[1];
        }
        if (dArr4 != null) {
            dArr4[0] = dArr8[0];
        }
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, Mat mat12, Mat mat13, Mat mat14) {
        long j = mat.nativeObj;
        long j2 = j;
        composeRT_0(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, mat12.nativeObj, mat13.nativeObj, mat14.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, Mat mat12, Mat mat13) {
        long j = mat.nativeObj;
        long j2 = j;
        composeRT_1(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, mat12.nativeObj, mat13.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, Mat mat12) {
        long j = mat.nativeObj;
        long j2 = j;
        composeRT_2(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, mat12.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11) {
        long j = mat.nativeObj;
        long j2 = j;
        composeRT_3(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10) {
        long j = mat.nativeObj;
        long j2 = j;
        composeRT_4(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9) {
        long j = mat.nativeObj;
        long j2 = j;
        composeRT_5(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8) {
        composeRT_6(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7) {
        composeRT_7(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        composeRT_8(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void computeCorrespondEpilines(Mat mat, int i, Mat mat2, Mat mat3) {
        computeCorrespondEpilines_0(mat.nativeObj, i, mat2.nativeObj, mat3.nativeObj);
    }

    public static void convertPointsFromHomogeneous(Mat mat, Mat mat2) {
        convertPointsFromHomogeneous_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void convertPointsToHomogeneous(Mat mat, Mat mat2) {
        convertPointsToHomogeneous_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void correctMatches(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        correctMatches_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void decomposeEssentialMat(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        decomposeEssentialMat_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8) {
        decomposeProjectionMatrix_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7) {
        decomposeProjectionMatrix_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        decomposeProjectionMatrix_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        decomposeProjectionMatrix_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        decomposeProjectionMatrix_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void drawChessboardCorners(Mat mat, Size size, MatOfPoint2f matOfPoint2f, boolean z) {
        drawChessboardCorners_0(mat.nativeObj, size.width, size.height, matOfPoint2f.nativeObj, z);
    }

    public static void drawFrameAxes(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, float f, int i) {
        drawFrameAxes_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, f, i);
    }

    public static void drawFrameAxes(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, float f) {
        drawFrameAxes_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, f);
    }

    public static void filterHomographyDecompByVisibleRefpoints(List<Mat> list, List<Mat> list2, Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        filterHomographyDecompByVisibleRefpoints_0(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void filterHomographyDecompByVisibleRefpoints(List<Mat> list, List<Mat> list2, Mat mat, Mat mat2, Mat mat3) {
        filterHomographyDecompByVisibleRefpoints_1(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void filterSpeckles(Mat mat, double d, int i, double d2, Mat mat2) {
        filterSpeckles_0(mat.nativeObj, d, i, d2, mat2.nativeObj);
    }

    public static void filterSpeckles(Mat mat, double d, int i, double d2) {
        filterSpeckles_1(mat.nativeObj, d, i, d2);
    }

    public static void initUndistortRectifyMap(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, int i, Mat mat5, Mat mat6) {
        Size size2 = size;
        long j = mat.nativeObj;
        long j2 = j;
        initUndistortRectifyMap_0(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, i, mat5.nativeObj, mat6.nativeObj);
    }

    public static void matMulDeriv(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        matMulDeriv_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, Mat mat, Mat mat2, Mat mat3, MatOfDouble matOfDouble, MatOfPoint2f matOfPoint2f, Mat mat4, double d) {
        projectPoints_0(matOfPoint3f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, matOfDouble.nativeObj, matOfPoint2f.nativeObj, mat4.nativeObj, d);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, Mat mat, Mat mat2, Mat mat3, MatOfDouble matOfDouble, MatOfPoint2f matOfPoint2f, Mat mat4) {
        projectPoints_1(matOfPoint3f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, matOfDouble.nativeObj, matOfPoint2f.nativeObj, mat4.nativeObj);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, Mat mat, Mat mat2, Mat mat3, MatOfDouble matOfDouble, MatOfPoint2f matOfPoint2f) {
        projectPoints_2(matOfPoint3f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, matOfDouble.nativeObj, matOfPoint2f.nativeObj);
    }

    public static void reprojectImageTo3D(Mat mat, Mat mat2, Mat mat3, boolean z, int i) {
        reprojectImageTo3D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i);
    }

    public static void reprojectImageTo3D(Mat mat, Mat mat2, Mat mat3, boolean z) {
        reprojectImageTo3D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, z);
    }

    public static void reprojectImageTo3D(Mat mat, Mat mat2, Mat mat3) {
        reprojectImageTo3D_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void solvePnPRefineLM(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, TermCriteria termCriteria) {
        TermCriteria termCriteria2 = termCriteria;
        solvePnPRefineLM_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static void solvePnPRefineLM(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        solvePnPRefineLM_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void solvePnPRefineVVS(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, TermCriteria termCriteria, double d) {
        TermCriteria termCriteria2 = termCriteria;
        long j = mat.nativeObj;
        solvePnPRefineVVS_0(j, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon, d);
    }

    public static void solvePnPRefineVVS(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, TermCriteria termCriteria) {
        TermCriteria termCriteria2 = termCriteria;
        solvePnPRefineVVS_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static void solvePnPRefineVVS(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        solvePnPRefineVVS_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, double d, Size size2, Rect rect, Rect rect2) {
        Size size3 = size;
        Size size4 = size2;
        Rect rect3 = rect;
        Rect rect4 = rect2;
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        long j3 = mat3.nativeObj;
        long j4 = mat4.nativeObj;
        double d2 = size3.width;
        double[] dArr3 = dArr;
        double d3 = size3.height;
        double[] dArr4 = dArr3;
        double[] dArr5 = dArr2;
        stereoRectify_0(j, j2, j3, j4, d2, d3, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, d, size4.width, size4.height, dArr, dArr2);
        Rect rect5 = rect;
        if (rect5 != null) {
            rect5.x = (int) dArr4[0];
            rect5.y = (int) dArr4[1];
            rect5.width = (int) dArr4[2];
            rect5.height = (int) dArr4[3];
        }
        Rect rect6 = rect2;
        if (rect6 != null) {
            rect6.x = (int) dArr5[0];
            rect6.y = (int) dArr5[1];
            rect6.width = (int) dArr5[2];
            rect6.height = (int) dArr5[3];
        }
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, double d, Size size2, Rect rect) {
        Size size3 = size;
        Size size4 = size2;
        Rect rect2 = rect;
        double[] dArr = new double[4];
        double[] dArr2 = dArr;
        long j = mat.nativeObj;
        long j2 = mat2.nativeObj;
        long j3 = mat3.nativeObj;
        long j4 = mat4.nativeObj;
        double d2 = size3.width;
        double d3 = size3.height;
        double[] dArr3 = dArr;
        long j5 = j;
        stereoRectify_1(j5, j2, j3, j4, d2, d3, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, d, size4.width, size4.height, dArr2);
        Rect rect3 = rect;
        if (rect3 != null) {
            rect3.x = (int) dArr3[0];
            rect3.y = (int) dArr3[1];
            rect3.width = (int) dArr3[2];
            rect3.height = (int) dArr3[3];
        }
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, double d, Size size2) {
        Size size3 = size;
        Size size4 = size2;
        long j = mat.nativeObj;
        long j2 = j;
        stereoRectify_2(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size3.width, size3.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, d, size4.width, size4.height);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, double d) {
        Size size2 = size;
        long j = mat.nativeObj;
        long j2 = j;
        stereoRectify_3(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, d);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i) {
        Size size2 = size;
        long j = mat.nativeObj;
        long j2 = j;
        stereoRectify_4(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11) {
        Size size2 = size;
        stereoRectify_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj);
    }

    public static void triangulatePoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        triangulatePoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void undistort(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        undistort_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void undistort(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        undistort_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void undistortPointsIter(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, TermCriteria termCriteria) {
        TermCriteria termCriteria2 = termCriteria;
        undistortPointsIter_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static void undistortPoints(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        undistortPoints_0(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void undistortPoints(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, Mat mat, Mat mat2, Mat mat3) {
        undistortPoints_1(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void undistortPoints(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, Mat mat, Mat mat2) {
        undistortPoints_2(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public static void validateDisparity(Mat mat, Mat mat2, int i, int i2, int i3) {
        validateDisparity_0(mat.nativeObj, mat2.nativeObj, i, i2, i3);
    }

    public static void validateDisparity(Mat mat, Mat mat2, int i, int i2) {
        validateDisparity_1(mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public static void fisheye_distortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d) {
        fisheye_distortPoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d);
    }

    public static void fisheye_distortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        fisheye_distortPoints_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void fisheye_estimateNewCameraMatrixForUndistortRectify(Mat mat, Mat mat2, Size size, Mat mat3, Mat mat4, double d, Size size2, double d2) {
        Size size3 = size;
        Size size4 = size2;
        long j = mat.nativeObj;
        long j2 = j;
        fisheye_estimateNewCameraMatrixForUndistortRectify_0(j2, mat2.nativeObj, size3.width, size3.height, mat3.nativeObj, mat4.nativeObj, d, size4.width, size4.height, d2);
    }

    public static void fisheye_estimateNewCameraMatrixForUndistortRectify(Mat mat, Mat mat2, Size size, Mat mat3, Mat mat4, double d, Size size2) {
        Size size3 = size;
        Size size4 = size2;
        double d2 = d;
        fisheye_estimateNewCameraMatrixForUndistortRectify_1(mat.nativeObj, mat2.nativeObj, size3.width, size3.height, mat3.nativeObj, mat4.nativeObj, d2, size4.width, size4.height);
    }

    public static void fisheye_estimateNewCameraMatrixForUndistortRectify(Mat mat, Mat mat2, Size size, Mat mat3, Mat mat4, double d) {
        Size size2 = size;
        fisheye_estimateNewCameraMatrixForUndistortRectify_2(mat.nativeObj, mat2.nativeObj, size2.width, size2.height, mat3.nativeObj, mat4.nativeObj, d);
    }

    public static void fisheye_estimateNewCameraMatrixForUndistortRectify(Mat mat, Mat mat2, Size size, Mat mat3, Mat mat4) {
        Size size2 = size;
        fisheye_estimateNewCameraMatrixForUndistortRectify_3(mat.nativeObj, mat2.nativeObj, size2.width, size2.height, mat3.nativeObj, mat4.nativeObj);
    }

    public static void fisheye_initUndistortRectifyMap(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, int i, Mat mat5, Mat mat6) {
        Size size2 = size;
        long j = mat.nativeObj;
        long j2 = j;
        fisheye_initUndistortRectifyMap_0(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, i, mat5.nativeObj, mat6.nativeObj);
    }

    public static void fisheye_projectPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d, Mat mat7) {
        fisheye_projectPoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d, mat7.nativeObj);
    }

    public static void fisheye_projectPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d) {
        fisheye_projectPoints_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d);
    }

    public static void fisheye_projectPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        fisheye_projectPoints_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void fisheye_stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, Size size2, double d, double d2) {
        Size size3 = size;
        Size size4 = size2;
        long j = mat.nativeObj;
        long j2 = j;
        fisheye_stereoRectify_0(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size3.width, size3.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, size4.width, size4.height, d, d2);
    }

    public static void fisheye_stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, Size size2, double d) {
        Size size3 = size;
        Size size4 = size2;
        double d2 = d;
        long j = mat.nativeObj;
        long j2 = j;
        fisheye_stereoRectify_1(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size3.width, size3.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, size4.width, size4.height, d2);
    }

    public static void fisheye_stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, Size size2) {
        Size size3 = size;
        Size size4 = size2;
        long j = mat.nativeObj;
        long j2 = j;
        fisheye_stereoRectify_2(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size3.width, size3.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, size4.width, size4.height);
    }

    public static void fisheye_stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i) {
        Size size2 = size;
        long j = mat.nativeObj;
        long j2 = j;
        fisheye_stereoRectify_3(j2, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size2.width, size2.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i);
    }

    public static void fisheye_undistortImage(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Size size) {
        Size size2 = size;
        fisheye_undistortImage_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, size2.width, size2.height);
    }

    public static void fisheye_undistortImage(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        fisheye_undistortImage_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void fisheye_undistortImage(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        fisheye_undistortImage_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void fisheye_undistortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        fisheye_undistortPoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void fisheye_undistortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        fisheye_undistortPoints_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void fisheye_undistortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        fisheye_undistortPoints_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }
}
