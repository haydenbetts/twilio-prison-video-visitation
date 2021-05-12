package org.bytedeco.opencv.global;

import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_calib3d.CirclesGridFinderParameters;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2d;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.Point3d;
import org.bytedeco.opencv.opencv_core.Point3fVectorVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_calib3d extends org.bytedeco.opencv.presets.opencv_calib3d {
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
    public static final int CV_CALIB_CB_ADAPTIVE_THRESH = 1;
    public static final int CV_CALIB_CB_FAST_CHECK = 8;
    public static final int CV_CALIB_CB_FILTER_QUADS = 4;
    public static final int CV_CALIB_CB_NORMALIZE_IMAGE = 2;
    public static final int CV_CALIB_FIX_ASPECT_RATIO = 2;
    public static final int CV_CALIB_FIX_FOCAL_LENGTH = 16;
    public static final int CV_CALIB_FIX_INTRINSIC = 256;
    public static final int CV_CALIB_FIX_K1 = 32;
    public static final int CV_CALIB_FIX_K2 = 64;
    public static final int CV_CALIB_FIX_K3 = 128;
    public static final int CV_CALIB_FIX_K4 = 2048;
    public static final int CV_CALIB_FIX_K5 = 4096;
    public static final int CV_CALIB_FIX_K6 = 8192;
    public static final int CV_CALIB_FIX_PRINCIPAL_POINT = 4;
    public static final int CV_CALIB_FIX_S1_S2_S3_S4 = 65536;
    public static final int CV_CALIB_FIX_TANGENT_DIST = 2097152;
    public static final int CV_CALIB_FIX_TAUX_TAUY = 524288;
    public static final int CV_CALIB_NINTRINSIC = 18;
    public static final int CV_CALIB_RATIONAL_MODEL = 16384;
    public static final int CV_CALIB_SAME_FOCAL_LENGTH = 512;
    public static final int CV_CALIB_THIN_PRISM_MODEL = 32768;
    public static final int CV_CALIB_TILTED_MODEL = 262144;
    public static final int CV_CALIB_USE_INTRINSIC_GUESS = 1;
    public static final int CV_CALIB_ZERO_DISPARITY = 1024;
    public static final int CV_CALIB_ZERO_TANGENT_DIST = 8;
    public static final int CV_DLS = 3;
    public static final int CV_EPNP = 1;
    public static final int CV_FM_7POINT = 1;
    public static final int CV_FM_8POINT = 2;
    public static final int CV_FM_LMEDS = 4;
    public static final int CV_FM_LMEDS_ONLY = 4;
    public static final int CV_FM_RANSAC = 8;
    public static final int CV_FM_RANSAC_ONLY = 8;
    public static final int CV_ITERATIVE = 0;
    public static final int CV_LMEDS = 4;
    public static final int CV_P3P = 2;
    public static final int CV_RANSAC = 8;
    public static final int CV_STEREO_BM_NORMALIZED_RESPONSE = 0;
    public static final int CV_STEREO_BM_XSOBEL = 1;
    public static final int FISHEYE_CALIB_CHECK_COND = 4;
    public static final int FISHEYE_CALIB_FIX_INTRINSIC = 256;
    public static final int FISHEYE_CALIB_FIX_K1 = 16;
    public static final int FISHEYE_CALIB_FIX_K2 = 32;
    public static final int FISHEYE_CALIB_FIX_K3 = 64;
    public static final int FISHEYE_CALIB_FIX_K4 = 128;
    public static final int FISHEYE_CALIB_FIX_PRINCIPAL_POINT = 512;
    public static final int FISHEYE_CALIB_FIX_SKEW = 8;
    public static final int FISHEYE_CALIB_RECOMPUTE_EXTRINSIC = 2;
    public static final int FISHEYE_CALIB_USE_INTRINSIC_GUESS = 1;
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

    @Namespace("cv")
    @Cast({"cv::Vec3d*"})
    @ByVal
    public static native Point3d RQDecomp3x3(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @Cast({"cv::Vec3d*"})
    @ByVal
    public static native Point3d RQDecomp3x3(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat6);

    @Namespace("cv")
    @Cast({"cv::Vec3d*"})
    @ByVal
    public static native Point3d RQDecomp3x3(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    @Cast({"cv::Vec3d*"})
    @ByVal
    public static native Point3d RQDecomp3x3(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat6);

    @Namespace("cv")
    @Cast({"cv::Vec3d*"})
    @ByVal
    public static native Point3d RQDecomp3x3(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @Cast({"cv::Vec3d*"})
    @ByVal
    public static native Point3d RQDecomp3x3(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat6);

    @Namespace("cv")
    public static native void Rodrigues(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void Rodrigues(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native void Rodrigues(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void Rodrigues(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native void Rodrigues(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void Rodrigues(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByRef @Const Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByRef @Const Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4);

    @Namespace("cv::fisheye")
    public static native double calibrate(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByRef @Const Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 100, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native double calibrateCamera(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2);

    @Namespace("cv")
    public static native double calibrateCamera(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    @Name({"calibrateCamera"})
    public static native double calibrateCameraExtended(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    @Name({"calibrateCamera"})
    public static native double calibrateCameraExtended(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native double calibrateCameraRO(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, int i, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native double calibrateCameraRO(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, int i, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat3, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    @Name({"calibrateCameraRO"})
    public static native double calibrateCameraROExtended(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, int i, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7);

    @Namespace("cv")
    @Name({"calibrateCameraRO"})
    public static native double calibrateCameraROExtended(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, int i, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal Mat mat, @ByVal Mat mat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal Mat mat, @ByVal Mat mat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal Mat mat, @ByVal Mat mat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void calibrateHandEye(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"cv::HandEyeCalibrationMethod"}) int i);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal GpuMat gpuMat, @ByVal Size size, double d, double d2, @ByRef DoubleBuffer doubleBuffer, @ByRef DoubleBuffer doubleBuffer2, @ByRef DoubleBuffer doubleBuffer3, @ByRef Point2d point2d, @ByRef DoubleBuffer doubleBuffer4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal GpuMat gpuMat, @ByVal Size size, double d, double d2, @ByRef DoublePointer doublePointer, @ByRef DoublePointer doublePointer2, @ByRef DoublePointer doublePointer3, @ByRef Point2d point2d, @ByRef DoublePointer doublePointer4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal GpuMat gpuMat, @ByVal Size size, double d, double d2, @ByRef double[] dArr, @ByRef double[] dArr2, @ByRef double[] dArr3, @ByRef Point2d point2d, @ByRef double[] dArr4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal Mat mat, @ByVal Size size, double d, double d2, @ByRef DoubleBuffer doubleBuffer, @ByRef DoubleBuffer doubleBuffer2, @ByRef DoubleBuffer doubleBuffer3, @ByRef Point2d point2d, @ByRef DoubleBuffer doubleBuffer4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal Mat mat, @ByVal Size size, double d, double d2, @ByRef DoublePointer doublePointer, @ByRef DoublePointer doublePointer2, @ByRef DoublePointer doublePointer3, @ByRef Point2d point2d, @ByRef DoublePointer doublePointer4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal Mat mat, @ByVal Size size, double d, double d2, @ByRef double[] dArr, @ByRef double[] dArr2, @ByRef double[] dArr3, @ByRef Point2d point2d, @ByRef double[] dArr4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal UMat uMat, @ByVal Size size, double d, double d2, @ByRef DoubleBuffer doubleBuffer, @ByRef DoubleBuffer doubleBuffer2, @ByRef DoubleBuffer doubleBuffer3, @ByRef Point2d point2d, @ByRef DoubleBuffer doubleBuffer4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal UMat uMat, @ByVal Size size, double d, double d2, @ByRef DoublePointer doublePointer, @ByRef DoublePointer doublePointer2, @ByRef DoublePointer doublePointer3, @ByRef Point2d point2d, @ByRef DoublePointer doublePointer4);

    @Namespace("cv")
    public static native void calibrationMatrixValues(@ByVal UMat uMat, @ByVal Size size, double d, double d2, @ByRef double[] dArr, @ByRef double[] dArr2, @ByRef double[] dArr3, @ByRef Point2d point2d, @ByRef double[] dArr4);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkChessboard(@ByVal GpuMat gpuMat, @ByVal Size size);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkChessboard(@ByVal Mat mat, @ByVal Size size);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkChessboard(@ByVal UMat uMat, @ByVal Size size);

    @Namespace("cv")
    public static native void composeRT(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    public static native void composeRT(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat8, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat9, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat10, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat11, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat12, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat13, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat14);

    @Namespace("cv")
    public static native void composeRT(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    public static native void composeRT(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat8, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat9, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat10, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat11, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat12, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat13, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat14);

    @Namespace("cv")
    public static native void composeRT(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    public static native void composeRT(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat8, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat9, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat10, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat11, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat12, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat13, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat14);

    @Namespace("cv")
    public static native void computeCorrespondEpilines(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void computeCorrespondEpilines(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void computeCorrespondEpilines(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void convertPointsFromHomogeneous(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void convertPointsFromHomogeneous(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void convertPointsFromHomogeneous(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void convertPointsHomogeneous(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void convertPointsHomogeneous(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void convertPointsHomogeneous(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void convertPointsToHomogeneous(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void convertPointsToHomogeneous(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void convertPointsToHomogeneous(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void correctMatches(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv")
    public static native void correctMatches(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    public static native void correctMatches(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv")
    public static native void decomposeEssentialMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void decomposeEssentialMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void decomposeEssentialMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3);

    @Namespace("cv")
    public static native int decomposeHomographyMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3);

    @Namespace("cv")
    public static native void decomposeProjectionMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void decomposeProjectionMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat8);

    @Namespace("cv")
    public static native void decomposeProjectionMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void decomposeProjectionMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat8);

    @Namespace("cv")
    public static native void decomposeProjectionMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void decomposeProjectionMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat8);

    @Namespace("cv::fisheye")
    public static native void distortPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::fisheye")
    public static native void distortPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d);

    @Namespace("cv::fisheye")
    public static native void distortPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::fisheye")
    public static native void distortPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, double d);

    @Namespace("cv::fisheye")
    public static native void distortPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::fisheye")
    public static native void distortPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, double d);

    @Namespace("cv")
    public static native void drawChessboardCorners(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void drawChessboardCorners(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void drawChessboardCorners(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void drawFrameAxes(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, float f);

    @Namespace("cv")
    public static native void drawFrameAxes(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, float f, int i);

    @Namespace("cv")
    public static native void drawFrameAxes(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, float f);

    @Namespace("cv")
    public static native void drawFrameAxes(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, float f, int i);

    @Namespace("cv")
    public static native void drawFrameAxes(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, float f);

    @Namespace("cv")
    public static native void drawFrameAxes(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, float f, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffine2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffine2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, int i, double d, @Cast({"size_t"}) long j, double d2, @Cast({"size_t"}) long j2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffine2D(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffine2D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, int i, double d, @Cast({"size_t"}) long j, double d2, @Cast({"size_t"}) long j2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffine2D(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffine2D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, int i, double d, @Cast({"size_t"}) long j, double d2, @Cast({"size_t"}) long j2);

    @Namespace("cv")
    public static native int estimateAffine3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native int estimateAffine3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d, double d2);

    @Namespace("cv")
    public static native int estimateAffine3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native int estimateAffine3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, double d, double d2);

    @Namespace("cv")
    public static native int estimateAffine3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native int estimateAffine3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, double d, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffinePartial2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffinePartial2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, int i, double d, @Cast({"size_t"}) long j, double d2, @Cast({"size_t"}) long j2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffinePartial2D(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffinePartial2D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, int i, double d, @Cast({"size_t"}) long j, double d2, @Cast({"size_t"}) long j2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffinePartial2D(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat estimateAffinePartial2D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, int i, double d, @Cast({"size_t"}) long j, double d2, @Cast({"size_t"}) long j2);

    @Namespace("cv")
    @ByVal
    public static native Scalar estimateChessboardSharpness(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Scalar estimateChessboardSharpness(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, float f, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Scalar estimateChessboardSharpness(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Scalar estimateChessboardSharpness(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, float f, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Scalar estimateChessboardSharpness(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Scalar estimateChessboardSharpness(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, float f, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv::fisheye")
    public static native void estimateNewCameraMatrixForUndistortRectify(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::fisheye")
    public static native void estimateNewCameraMatrixForUndistortRectify(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d, @ByRef(nullValue = "cv::Size()") @Const Size size2, double d2);

    @Namespace("cv::fisheye")
    public static native void estimateNewCameraMatrixForUndistortRectify(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Size size, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::fisheye")
    public static native void estimateNewCameraMatrixForUndistortRectify(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Size size, @ByVal Mat mat3, @ByVal Mat mat4, double d, @ByRef(nullValue = "cv::Size()") @Const Size size2, double d2);

    @Namespace("cv::fisheye")
    public static native void estimateNewCameraMatrixForUndistortRectify(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Size size, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::fisheye")
    public static native void estimateNewCameraMatrixForUndistortRectify(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, double d, @ByRef(nullValue = "cv::Size()") @Const Size size2, double d2);

    @Namespace("cv")
    public static native int estimateTranslation3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native int estimateTranslation3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d, double d2);

    @Namespace("cv")
    public static native int estimateTranslation3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native int estimateTranslation3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, double d, double d2);

    @Namespace("cv")
    public static native int estimateTranslation3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native int estimateTranslation3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, double d, double d2);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void filterHomographyDecompByVisibleRefpoints(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void filterSpeckles(@ByVal GpuMat gpuMat, double d, int i, double d2);

    @Namespace("cv")
    public static native void filterSpeckles(@ByVal GpuMat gpuMat, double d, int i, double d2, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void filterSpeckles(@ByVal Mat mat, double d, int i, double d2);

    @Namespace("cv")
    public static native void filterSpeckles(@ByVal Mat mat, double d, int i, double d2, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void filterSpeckles(@ByVal UMat uMat, double d, int i, double d2);

    @Namespace("cv")
    public static native void filterSpeckles(@ByVal UMat uMat, double d, int i, double d2, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean find4QuadCornerSubpix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean find4QuadCornerSubpix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean find4QuadCornerSubpix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCorners(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCorners(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCorners(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCorners(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCorners(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCorners(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCornersSB(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCornersSB(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCornersSB(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCornersSB(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCornersSB(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findChessboardCornersSB(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"findChessboardCornersSB"})
    public static native boolean findChessboardCornersSBWithMeta(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"findChessboardCornersSB"})
    public static native boolean findChessboardCornersSBWithMeta(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, int i, @ByVal Mat mat3);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"findChessboardCornersSB"})
    public static native boolean findChessboardCornersSBWithMeta(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, int i, @ByVal UMat uMat3);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, int i, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal GpuMat gpuMat2, int i, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D, @ByRef @Const CirclesGridFinderParameters circlesGridFinderParameters);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, int i, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal Mat mat, @ByVal Size size, @ByVal Mat mat2, int i, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D, @ByRef @Const CirclesGridFinderParameters circlesGridFinderParameters);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, int i, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean findCirclesGrid(@ByVal UMat uMat, @ByVal Size size, @ByVal UMat uMat2, int i, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D, @ByRef @Const CirclesGridFinderParameters circlesGridFinderParameters);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByVal(nullValue = "cv::Point2d(0, 0)") Point2d point2d, int i, double d2, double d3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d, double d2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByVal(nullValue = "cv::Point2d(0, 0)") Point2d point2d, int i, double d2, double d3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d, double d2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByVal(nullValue = "cv::Point2d(0, 0)") Point2d point2d, int i, double d2, double d3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findEssentialMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d, double d2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, int i2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, int i2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, int i2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, int i2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, int i2);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, int i2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findFundamentalMat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3, int i2, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3, int i2, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3, int i2, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @ByVal
    public static native Mat findHomography(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat getDefaultNewCameraMatrix(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native Mat getDefaultNewCameraMatrix(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::Size()") Size size, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Mat getDefaultNewCameraMatrix(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native Mat getDefaultNewCameraMatrix(@ByVal Mat mat, @ByVal(nullValue = "cv::Size()") Size size, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Mat getDefaultNewCameraMatrix(@ByVal UMat uMat);

    @Namespace("cv")
    @ByVal
    public static native Mat getDefaultNewCameraMatrix(@ByVal UMat uMat, @ByVal(nullValue = "cv::Size()") Size size, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Mat getOptimalNewCameraMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat getOptimalNewCameraMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d, @ByVal(nullValue = "cv::Size()") Size size2, Rect rect, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Mat getOptimalNewCameraMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat getOptimalNewCameraMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d, @ByVal(nullValue = "cv::Size()") Size size2, Rect rect, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Mat getOptimalNewCameraMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat getOptimalNewCameraMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d, @ByVal(nullValue = "cv::Size()") Size size2, Rect rect, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Rect getValidDisparityROI(@ByVal Rect rect, @ByVal Rect rect2, int i, int i2, int i3);

    @Namespace("cv")
    @ByVal
    public static native Mat initCameraMatrix2D(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size);

    @Namespace("cv")
    @ByVal
    public static native Mat initCameraMatrix2D(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Size size, double d);

    @Namespace("cv")
    public static native void initUndistortRectifyMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal Size size, int i, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    public static native void initUndistortRectifyMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, int i, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    public static native void initUndistortRectifyMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal Size size, int i, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, int i, int i2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, int i, int i2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i3);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, int i, int i2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @Cast({"cv::UndistortTypes"}) int i3, double d);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, int i, int i2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, int i, int i2, @ByVal Mat mat3, @ByVal Mat mat4, int i3);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, int i, int i2, @ByVal Mat mat3, @ByVal Mat mat4, @Cast({"cv::UndistortTypes"}) int i3, double d);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, int i, int i2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, int i, int i2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i3);

    @Namespace("cv")
    public static native float initWideAngleProjMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, int i, int i2, @ByVal UMat uMat3, @ByVal UMat uMat4, @Cast({"cv::UndistortTypes"}) int i3, double d);

    @Namespace("cv")
    public static native void matMulDeriv(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void matMulDeriv(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void matMulDeriv(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void projectPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7);

    @Namespace("cv")
    public static native void projectPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7, double d);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Mat mat, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Mat mat, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat6);

    @Namespace("cv")
    public static native void projectPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7);

    @Namespace("cv")
    public static native void projectPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7, double d);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Mat mat, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Mat mat, @ByVal UMat uMat3, @ByVal UMat uMat4, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv")
    public static native void projectPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv::fisheye")
    public static native void projectPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7);

    @Namespace("cv")
    public static native void projectPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7, double d);

    @Namespace("cv")
    public static native int recoverPose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv")
    public static native int recoverPose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, double d, @ByVal(nullValue = "cv::Point2d(0, 0)") Point2d point2d, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") GpuMat gpuMat6);

    @Namespace("cv")
    public static native int recoverPose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    public static native int recoverPose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, double d);

    @Namespace("cv")
    public static native int recoverPose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, double d, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat8);

    @Namespace("cv")
    public static native int recoverPose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") GpuMat gpuMat7);

    @Namespace("cv")
    public static native int recoverPose(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    public static native int recoverPose(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, double d, @ByVal(nullValue = "cv::Point2d(0, 0)") Point2d point2d, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") Mat mat6);

    @Namespace("cv")
    public static native int recoverPose(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    public static native int recoverPose(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, double d);

    @Namespace("cv")
    public static native int recoverPose(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, double d, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat8);

    @Namespace("cv")
    public static native int recoverPose(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") Mat mat7);

    @Namespace("cv")
    public static native int recoverPose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv")
    public static native int recoverPose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, double d, @ByVal(nullValue = "cv::Point2d(0, 0)") Point2d point2d, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") UMat uMat6);

    @Namespace("cv")
    public static native int recoverPose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    public static native int recoverPose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, double d);

    @Namespace("cv")
    public static native int recoverPose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, double d, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat8);

    @Namespace("cv")
    public static native int recoverPose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") UMat uMat7);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal Size size, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11, @ByVal GpuMat gpuMat12, @ByVal GpuMat gpuMat13, @ByVal GpuMat gpuMat14, @ByVal GpuMat gpuMat15, @ByVal GpuMat gpuMat16, @ByVal GpuMat gpuMat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Size size, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11, @ByVal GpuMat gpuMat12, @ByVal GpuMat gpuMat13, @ByVal GpuMat gpuMat14, @ByVal GpuMat gpuMat15, @ByVal GpuMat gpuMat16, @ByVal GpuMat gpuMat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal Size size, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11, @ByVal GpuMat gpuMat12, @ByVal GpuMat gpuMat13, @ByVal GpuMat gpuMat14, @ByVal GpuMat gpuMat15, @ByVal GpuMat gpuMat16, @ByVal GpuMat gpuMat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal Size size, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11, @ByVal Mat mat12, @ByVal Mat mat13, @ByVal Mat mat14, @ByVal Mat mat15, @ByVal Mat mat16, @ByVal Mat mat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Size size, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11, @ByVal Mat mat12, @ByVal Mat mat13, @ByVal Mat mat14, @ByVal Mat mat15, @ByVal Mat mat16, @ByVal Mat mat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal Size size, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11, @ByVal Mat mat12, @ByVal Mat mat13, @ByVal Mat mat14, @ByVal Mat mat15, @ByVal Mat mat16, @ByVal Mat mat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal Size size, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11, @ByVal UMat uMat12, @ByVal UMat uMat13, @ByVal UMat uMat14, @ByVal UMat uMat15, @ByVal UMat uMat16, @ByVal UMat uMat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Size size, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11, @ByVal UMat uMat12, @ByVal UMat uMat13, @ByVal UMat uMat14, @ByVal UMat uMat15, @ByVal UMat uMat16, @ByVal UMat uMat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native float rectify3Collinear(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal Size size, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11, @ByVal UMat uMat12, @ByVal UMat uMat13, @ByVal UMat uMat14, @ByVal UMat uMat15, @ByVal UMat uMat16, @ByVal UMat uMat17, double d, @ByVal Size size2, Rect rect, Rect rect2, int i);

    @Namespace("cv")
    public static native void reprojectImageTo3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void reprojectImageTo3D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z, int i);

    @Namespace("cv")
    public static native void reprojectImageTo3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void reprojectImageTo3D(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"bool"}) boolean z, int i);

    @Namespace("cv")
    public static native void reprojectImageTo3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void reprojectImageTo3D(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @Cast({"bool"}) boolean z, int i);

    @Namespace("cv")
    public static native double sampsonDistance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native double sampsonDistance(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native double sampsonDistance(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native int solveP3P(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal MatVector matVector, @ByVal MatVector matVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal MatVector matVector, @ByVal MatVector matVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal MatVector matVector, @ByVal MatVector matVector2, int i);

    @Namespace("cv")
    public static native int solveP3P(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnP(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnP(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @Cast({"bool"}) boolean z, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnP(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnP(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @Cast({"bool"}) boolean z, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnP(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnP(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @Cast({"bool"}) boolean z, int i);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal MatVector matVector, @ByVal MatVector matVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal MatVector matVector, @ByVal MatVector matVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal MatVector matVector, @ByVal MatVector matVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal MatVector matVector, @ByVal MatVector matVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal MatVector matVector, @ByVal MatVector matVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal MatVector matVector, @ByVal MatVector matVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2);

    @Namespace("cv")
    public static native int solvePnPGeneric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @Cast({"bool"}) boolean z, @Cast({"cv::SolvePnPMethod"}) int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnPRansac(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnPRansac(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @Cast({"bool"}) boolean z, int i, float f, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat7, int i2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnPRansac(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnPRansac(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @Cast({"bool"}) boolean z, int i, float f, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat7, int i2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnPRansac(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solvePnPRansac(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @Cast({"bool"}) boolean z, int i, float f, double d, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat7, int i2);

    @Namespace("cv")
    public static native void solvePnPRefineLM(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    public static native void solvePnPRefineLM(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::EPS + cv::TermCriteria::COUNT, 20, FLT_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void solvePnPRefineLM(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    public static native void solvePnPRefineLM(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::EPS + cv::TermCriteria::COUNT, 20, FLT_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void solvePnPRefineLM(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    public static native void solvePnPRefineLM(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::EPS + cv::TermCriteria::COUNT, 20, FLT_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void solvePnPRefineVVS(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv")
    public static native void solvePnPRefineVVS(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::EPS + cv::TermCriteria::COUNT, 20, FLT_EPSILON)") TermCriteria termCriteria, double d);

    @Namespace("cv")
    public static native void solvePnPRefineVVS(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv")
    public static native void solvePnPRefineVVS(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::EPS + cv::TermCriteria::COUNT, 20, FLT_EPSILON)") TermCriteria termCriteria, double d);

    @Namespace("cv")
    public static native void solvePnPRefineVVS(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv")
    public static native void solvePnPRefineVVS(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::EPS + cv::TermCriteria::COUNT, 20, FLT_EPSILON)") TermCriteria termCriteria, double d);

    @Namespace("cv")
    public static native double stereoCalibrate(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Point2fVectorVector point2fVectorVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8);

    @Namespace("cv")
    public static native double stereoCalibrate(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Point2fVectorVector point2fVectorVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 30, 1e-6)") TermCriteria termCriteria);

    @Namespace("cv")
    @Name({"stereoCalibrate"})
    public static native double stereoCalibrateExtended(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Point2fVectorVector point2fVectorVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9);

    @Namespace("cv")
    @Name({"stereoCalibrate"})
    public static native double stereoCalibrateExtended(@ByVal Point3fVectorVector point3fVectorVector, @ByVal Point2fVectorVector point2fVectorVector, @ByVal Point2fVectorVector point2fVectorVector2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 30, 1e-6)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void stereoRectify(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal Size size, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11);

    @Namespace("cv::fisheye")
    public static native void stereoRectify(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByRef @Const Size size, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11, int i);

    @Namespace("cv")
    public static native void stereoRectify(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal Size size, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11, int i, double d, @ByVal(nullValue = "cv::Size()") Size size2, Rect rect, Rect rect2);

    @Namespace("cv::fisheye")
    public static native void stereoRectify(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByRef @Const Size size, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, @ByVal GpuMat gpuMat8, @ByVal GpuMat gpuMat9, @ByVal GpuMat gpuMat10, @ByVal GpuMat gpuMat11, int i, @ByRef(nullValue = "cv::Size()") @Const Size size2, double d, double d2);

    @Namespace("cv")
    public static native void stereoRectify(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11);

    @Namespace("cv::fisheye")
    public static native void stereoRectify(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByRef @Const Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11, int i);

    @Namespace("cv")
    public static native void stereoRectify(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11, int i, double d, @ByVal(nullValue = "cv::Size()") Size size2, Rect rect, Rect rect2);

    @Namespace("cv::fisheye")
    public static native void stereoRectify(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByRef @Const Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, @ByVal Mat mat8, @ByVal Mat mat9, @ByVal Mat mat10, @ByVal Mat mat11, int i, @ByRef(nullValue = "cv::Size()") @Const Size size2, double d, double d2);

    @Namespace("cv")
    public static native void stereoRectify(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal Size size, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11);

    @Namespace("cv::fisheye")
    public static native void stereoRectify(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByRef @Const Size size, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11, int i);

    @Namespace("cv")
    public static native void stereoRectify(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal Size size, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11, int i, double d, @ByVal(nullValue = "cv::Size()") Size size2, Rect rect, Rect rect2);

    @Namespace("cv::fisheye")
    public static native void stereoRectify(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByRef @Const Size size, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, @ByVal UMat uMat8, @ByVal UMat uMat9, @ByVal UMat uMat10, @ByVal UMat uMat11, int i, @ByRef(nullValue = "cv::Size()") @Const Size size2, double d, double d2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean stereoRectifyUncalibrated(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Size size, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean stereoRectifyUncalibrated(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Size size, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, double d);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean stereoRectifyUncalibrated(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Size size, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean stereoRectifyUncalibrated(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Size size, @ByVal Mat mat4, @ByVal Mat mat5, double d);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean stereoRectifyUncalibrated(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Size size, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean stereoRectifyUncalibrated(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Size size, @ByVal UMat uMat4, @ByVal UMat uMat5, double d);

    @Namespace("cv")
    public static native void triangulatePoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv")
    public static native void triangulatePoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    public static native void triangulatePoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv")
    public static native void undistort(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void undistort(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv")
    public static native void undistort(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void undistort(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5);

    @Namespace("cv")
    public static native void undistort(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void undistort(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::fisheye")
    public static native void undistortImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::fisheye")
    public static native void undistortImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByRef(nullValue = "cv::Size()") @Const Size size);

    @Namespace("cv::fisheye")
    public static native void undistortImage(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::fisheye")
    public static native void undistortImage(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByRef(nullValue = "cv::Size()") @Const Size size);

    @Namespace("cv::fisheye")
    public static native void undistortImage(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::fisheye")
    public static native void undistortImage(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByRef(nullValue = "cv::Size()") @Const Size size);

    @Namespace("cv")
    public static native void undistortPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void undistortPoints(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6);

    @Namespace("cv")
    public static native void undistortPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void undistortPoints(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6);

    @Namespace("cv")
    public static native void undistortPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void undistortPoints(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6);

    @Namespace("cv")
    @Name({"undistortPoints"})
    public static native void undistortPointsIter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    @Name({"undistortPoints"})
    public static native void undistortPointsIter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    @Name({"undistortPoints"})
    public static native void undistortPointsIter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    public static native void validateDisparity(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void validateDisparity(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void validateDisparity(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void validateDisparity(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void validateDisparity(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void validateDisparity(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    static {
        Loader.load();
    }
}
