package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_aruco.Board;
import org.bytedeco.opencv.opencv_aruco.CharucoBoard;
import org.bytedeco.opencv.opencv_aruco.DetectorParameters;
import org.bytedeco.opencv.opencv_aruco.Dictionary;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Scalar4i;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_aruco extends org.bytedeco.opencv.presets.opencv_aruco {
    public static final int CORNER_REFINE_APRILTAG = 3;
    public static final int CORNER_REFINE_CONTOUR = 2;
    public static final int CORNER_REFINE_NONE = 0;
    public static final int CORNER_REFINE_SUBPIX = 1;
    public static final int DICT_4X4_100 = 1;
    public static final int DICT_4X4_1000 = 3;
    public static final int DICT_4X4_250 = 2;
    public static final int DICT_4X4_50 = 0;
    public static final int DICT_5X5_100 = 5;
    public static final int DICT_5X5_1000 = 7;
    public static final int DICT_5X5_250 = 6;
    public static final int DICT_5X5_50 = 4;
    public static final int DICT_6X6_100 = 9;
    public static final int DICT_6X6_1000 = 11;
    public static final int DICT_6X6_250 = 10;
    public static final int DICT_6X6_50 = 8;
    public static final int DICT_7X7_100 = 13;
    public static final int DICT_7X7_1000 = 15;
    public static final int DICT_7X7_250 = 14;
    public static final int DICT_7X7_50 = 12;
    public static final int DICT_APRILTAG_16h5 = 17;
    public static final int DICT_APRILTAG_25h9 = 18;
    public static final int DICT_APRILTAG_36h10 = 19;
    public static final int DICT_APRILTAG_36h11 = 20;
    public static final int DICT_ARUCO_ORIGINAL = 16;

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native double calibrateCameraAruco(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector3, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal MatVector matVector2, @ByVal MatVector matVector3, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraAruco"})
    public static native double calibrateCameraArucoExtended(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native double calibrateCameraCharuco(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector3, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector4, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector3, @ByVal GpuMatVector gpuMatVector4, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal MatVector matVector, @ByVal MatVector matVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal MatVector matVector3, @ByVal MatVector matVector4, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::aruco")
    @Name({"calibrateCameraCharuco"})
    public static native double calibrateCameraCharucoExtended(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMatVector uMatVector3, @ByVal UMatVector uMatVector4, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT + cv::TermCriteria::EPS, 30, DBL_EPSILON)") TermCriteria termCriteria);

    @Namespace("cv::aruco")
    @opencv_core.Ptr
    @Name({"generateCustomDictionary"})
    public static native Dictionary custom_dictionary(int i, int i2);

    @Namespace("cv::aruco")
    @opencv_core.Ptr
    @Name({"generateCustomDictionary"})
    public static native Dictionary custom_dictionary(int i, int i2, int i3);

    @Namespace("cv::aruco")
    @opencv_core.Ptr
    @Name({"generateCustomDictionary"})
    public static native Dictionary custom_dictionary_from(int i, int i2, @opencv_core.Ptr Dictionary dictionary);

    @Namespace("cv::aruco")
    @opencv_core.Ptr
    @Name({"generateCustomDictionary"})
    public static native Dictionary custom_dictionary_from(int i, int i2, @opencv_core.Ptr Dictionary dictionary, int i3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, float f, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, float f, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, float f, @ByVal MatVector matVector2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, float f, @ByVal MatVector matVector2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, float f, @ByVal UMatVector uMatVector2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, float f, @ByVal UMatVector uMatVector2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, float f, @ByVal GpuMatVector gpuMatVector2, @ByVal Mat mat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, float f, @ByVal GpuMatVector gpuMatVector2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal Mat mat2, float f, @ByVal MatVector matVector2, @ByVal Mat mat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal Mat mat2, float f, @ByVal MatVector matVector2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal Mat mat2, float f, @ByVal UMatVector uMatVector2, @ByVal Mat mat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal Mat mat2, float f, @ByVal UMatVector uMatVector2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, float f, @ByVal GpuMatVector gpuMatVector2, @ByVal UMat uMat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, float f, @ByVal GpuMatVector gpuMatVector2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal UMat uMat2, float f, @ByVal MatVector matVector2, @ByVal UMat uMat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal UMat uMat2, float f, @ByVal MatVector matVector2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, float f, @ByVal UMatVector uMatVector2, @ByVal UMat uMat3);

    @Namespace("cv::aruco")
    public static native void detectCharucoDiamond(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, float f, @ByVal UMatVector uMatVector2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Dictionary dictionary, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Dictionary dictionary, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Dictionary dictionary, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Dictionary dictionary, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal Mat mat, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal Mat mat, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal Mat mat, @opencv_core.Ptr Dictionary dictionary, @ByVal MatVector matVector, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal Mat mat, @opencv_core.Ptr Dictionary dictionary, @ByVal MatVector matVector, @ByVal Mat mat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal Mat mat, @opencv_core.Ptr Dictionary dictionary, @ByVal UMatVector uMatVector, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal Mat mat, @opencv_core.Ptr Dictionary dictionary, @ByVal UMatVector uMatVector, @ByVal Mat mat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal UMat uMat, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal UMat uMat, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal UMat uMat, @opencv_core.Ptr Dictionary dictionary, @ByVal MatVector matVector, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal UMat uMat, @opencv_core.Ptr Dictionary dictionary, @ByVal MatVector matVector, @ByVal UMat uMat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal UMat uMat, @opencv_core.Ptr Dictionary dictionary, @ByVal UMatVector uMatVector, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native void detectMarkers(@ByVal UMat uMat, @opencv_core.Ptr Dictionary dictionary, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, @opencv_core.Ptr DetectorParameters detectorParameters, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv::aruco")
    public static native void drawAxis(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, float f);

    @Namespace("cv::aruco")
    public static native void drawAxis(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, float f);

    @Namespace("cv::aruco")
    public static native void drawAxis(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, float f);

    @Namespace("cv::aruco")
    public static native void drawCharucoDiamond(@opencv_core.Ptr Dictionary dictionary, @Cast({"cv::Vec4i*"}) @ByVal Scalar4i scalar4i, int i, int i2, @ByVal GpuMat gpuMat);

    @Namespace("cv::aruco")
    public static native void drawCharucoDiamond(@opencv_core.Ptr Dictionary dictionary, @Cast({"cv::Vec4i*"}) @ByVal Scalar4i scalar4i, int i, int i2, @ByVal GpuMat gpuMat, int i3, int i4);

    @Namespace("cv::aruco")
    public static native void drawCharucoDiamond(@opencv_core.Ptr Dictionary dictionary, @Cast({"cv::Vec4i*"}) @ByVal Scalar4i scalar4i, int i, int i2, @ByVal Mat mat);

    @Namespace("cv::aruco")
    public static native void drawCharucoDiamond(@opencv_core.Ptr Dictionary dictionary, @Cast({"cv::Vec4i*"}) @ByVal Scalar4i scalar4i, int i, int i2, @ByVal Mat mat, int i3, int i4);

    @Namespace("cv::aruco")
    public static native void drawCharucoDiamond(@opencv_core.Ptr Dictionary dictionary, @Cast({"cv::Vec4i*"}) @ByVal Scalar4i scalar4i, int i, int i2, @ByVal UMat uMat);

    @Namespace("cv::aruco")
    public static native void drawCharucoDiamond(@opencv_core.Ptr Dictionary dictionary, @Cast({"cv::Vec4i*"}) @ByVal Scalar4i scalar4i, int i, int i2, @ByVal UMat uMat, int i3, int i4);

    @Namespace("cv::aruco")
    public static native void drawDetectedCornersCharuco(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::aruco")
    public static native void drawDetectedCornersCharuco(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::Scalar(255, 0, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedCornersCharuco(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::aruco")
    public static native void drawDetectedCornersCharuco(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::Scalar(255, 0, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedCornersCharuco(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::aruco")
    public static native void drawDetectedCornersCharuco(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::Scalar(255, 0, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal GpuMat gpuMat, @ByVal MatVector matVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal Mat mat, @ByVal MatVector matVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal Mat mat, @ByVal UMatVector uMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal UMat uMat, @ByVal MatVector matVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal UMat uMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedDiamonds(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::Scalar(0, 0, 255)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal GpuMat gpuMat, @ByVal MatVector matVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal Mat mat, @ByVal MatVector matVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal Mat mat, @ByVal UMatVector uMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal UMat uMat, @ByVal MatVector matVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal UMat uMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::aruco")
    public static native void drawDetectedMarkers(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::Scalar(0, 255, 0)") Scalar scalar);

    @Namespace("cv::aruco")
    public static native void drawMarker(@opencv_core.Ptr Dictionary dictionary, int i, int i2, @ByVal GpuMat gpuMat);

    @Namespace("cv::aruco")
    public static native void drawMarker(@opencv_core.Ptr Dictionary dictionary, int i, int i2, @ByVal GpuMat gpuMat, int i3);

    @Namespace("cv::aruco")
    public static native void drawMarker(@opencv_core.Ptr Dictionary dictionary, int i, int i2, @ByVal Mat mat);

    @Namespace("cv::aruco")
    public static native void drawMarker(@opencv_core.Ptr Dictionary dictionary, int i, int i2, @ByVal Mat mat, int i3);

    @Namespace("cv::aruco")
    public static native void drawMarker(@opencv_core.Ptr Dictionary dictionary, int i, int i2, @ByVal UMat uMat);

    @Namespace("cv::aruco")
    public static native void drawMarker(@opencv_core.Ptr Dictionary dictionary, int i, int i2, @ByVal UMat uMat, int i3);

    @Namespace("cv::aruco")
    public static native void drawPlanarBoard(@opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat);

    @Namespace("cv::aruco")
    public static native void drawPlanarBoard(@opencv_core.Ptr Board board, @ByVal Size size, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv::aruco")
    public static native void drawPlanarBoard(@opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat);

    @Namespace("cv::aruco")
    public static native void drawPlanarBoard(@opencv_core.Ptr Board board, @ByVal Size size, @ByVal Mat mat, int i, int i2);

    @Namespace("cv::aruco")
    public static native void drawPlanarBoard(@opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat);

    @Namespace("cv::aruco")
    public static native void drawPlanarBoard(@opencv_core.Ptr Board board, @ByVal Size size, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal MatVector matVector, @ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal MatVector matVector, @ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal MatVector matVector, @ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal MatVector matVector, @ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal UMatVector uMatVector, @ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal UMatVector uMatVector, @ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::aruco")
    public static native int estimatePoseBoard(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean estimatePoseCharucoBoard(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean estimatePoseCharucoBoard(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean estimatePoseCharucoBoard(@ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean estimatePoseCharucoBoard(@ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean estimatePoseCharucoBoard(@ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean estimatePoseCharucoBoard(@ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, @Cast({"bool"}) boolean z);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal GpuMatVector gpuMatVector, float f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal GpuMatVector gpuMatVector, float f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal GpuMatVector gpuMatVector, float f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal GpuMatVector gpuMatVector, float f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal GpuMatVector gpuMatVector, float f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal GpuMatVector gpuMatVector, float f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal MatVector matVector, float f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal MatVector matVector, float f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal MatVector matVector, float f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal MatVector matVector, float f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal MatVector matVector, float f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal MatVector matVector, float f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal UMatVector uMatVector, float f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal UMatVector uMatVector, float f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal UMatVector uMatVector, float f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal UMatVector uMatVector, float f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal UMatVector uMatVector, float f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native void estimatePoseSingleMarkers(@ByVal UMatVector uMatVector, float f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::aruco")
    public static native void getBoardObjectAndImagePoints(@opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::aruco")
    @opencv_core.Ptr
    public static native Dictionary getPredefinedDictionary(@Cast({"cv::aruco::PREDEFINED_DICTIONARY_NAME"}) int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, int i);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::aruco")
    public static native int interpolateCornersCharuco(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, int i);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal GpuMat gpuMat, @opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal Mat mat2, @ByVal MatVector matVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal Mat mat2, @ByVal MatVector matVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal Mat mat2, @ByVal UMatVector uMatVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal Mat mat, @opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal Mat mat2, @ByVal UMatVector uMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal UMat uMat2, @ByVal MatVector matVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal MatVector matVector, @ByVal UMat uMat2, @ByVal MatVector matVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, @ByVal UMatVector uMatVector2);

    @Namespace("cv::aruco")
    public static native void refineDetectedMarkers(@ByVal UMat uMat, @opencv_core.Ptr Board board, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, @ByVal UMatVector uMatVector2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, float f, float f2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5, @opencv_core.Ptr DetectorParameters detectorParameters);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean testCharucoCornersCollinear(@opencv_core.Ptr CharucoBoard charucoBoard, @ByVal GpuMat gpuMat);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean testCharucoCornersCollinear(@opencv_core.Ptr CharucoBoard charucoBoard, @ByVal Mat mat);

    @Namespace("cv::aruco")
    @Cast({"bool"})
    public static native boolean testCharucoCornersCollinear(@opencv_core.Ptr CharucoBoard charucoBoard, @ByVal UMat uMat);

    static {
        Loader.load();
    }
}
