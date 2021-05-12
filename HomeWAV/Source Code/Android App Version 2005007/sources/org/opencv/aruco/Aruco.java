package org.opencv.aruco;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

public class Aruco {
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

    private static native double calibrateCameraArucoExtended_0(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, int i2, int i3, double d3);

    private static native double calibrateCameraArucoExtended_1(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i);

    private static native double calibrateCameraArucoExtended_2(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    private static native double calibrateCameraAruco_0(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, int i, int i2, int i3, double d3);

    private static native double calibrateCameraAruco_1(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, int i);

    private static native double calibrateCameraAruco_2(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8);

    private static native double calibrateCameraAruco_3(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7);

    private static native double calibrateCameraAruco_4(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6);

    private static native double calibrateCameraCharucoExtended_0(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6, long j7, long j8, long j9, long j10, int i, int i2, int i3, double d3);

    private static native double calibrateCameraCharucoExtended_1(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6, long j7, long j8, long j9, long j10, int i);

    private static native double calibrateCameraCharucoExtended_2(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6, long j7, long j8, long j9, long j10);

    private static native double calibrateCameraCharuco_0(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6, long j7, int i, int i2, int i3, double d3);

    private static native double calibrateCameraCharuco_1(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6, long j7, int i);

    private static native double calibrateCameraCharuco_2(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6, long j7);

    private static native double calibrateCameraCharuco_3(long j, long j2, long j3, double d, double d2, long j4, long j5, long j6);

    private static native double calibrateCameraCharuco_4(long j, long j2, long j3, double d, double d2, long j4, long j5);

    private static native long custom_dictionary_0(int i, int i2, int i3);

    private static native long custom_dictionary_1(int i, int i2);

    private static native long custom_dictionary_from_0(int i, int i2, long j, int i3);

    private static native long custom_dictionary_from_1(int i, int i2, long j);

    private static native void detectCharucoDiamond_0(long j, long j2, long j3, float f, long j4, long j5, long j6, long j7);

    private static native void detectCharucoDiamond_1(long j, long j2, long j3, float f, long j4, long j5, long j6);

    private static native void detectCharucoDiamond_2(long j, long j2, long j3, float f, long j4, long j5);

    private static native void detectMarkers_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    private static native void detectMarkers_1(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native void detectMarkers_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void detectMarkers_3(long j, long j2, long j3, long j4, long j5);

    private static native void detectMarkers_4(long j, long j2, long j3, long j4);

    private static native void drawAxis_0(long j, long j2, long j3, long j4, long j5, float f);

    private static native void drawDetectedCornersCharuco_0(long j, long j2, long j3, double d, double d2, double d3, double d4);

    private static native void drawDetectedCornersCharuco_1(long j, long j2, long j3);

    private static native void drawDetectedCornersCharuco_2(long j, long j2);

    private static native void drawDetectedDiamonds_0(long j, long j2, long j3, double d, double d2, double d3, double d4);

    private static native void drawDetectedDiamonds_1(long j, long j2, long j3);

    private static native void drawDetectedDiamonds_2(long j, long j2);

    private static native void drawDetectedMarkers_0(long j, long j2, long j3, double d, double d2, double d3, double d4);

    private static native void drawDetectedMarkers_1(long j, long j2, long j3);

    private static native void drawDetectedMarkers_2(long j, long j2);

    private static native void drawMarker_0(long j, int i, int i2, long j2, int i3);

    private static native void drawMarker_1(long j, int i, int i2, long j2);

    private static native void drawPlanarBoard_0(long j, double d, double d2, long j2, int i, int i2);

    private static native void drawPlanarBoard_1(long j, double d, double d2, long j2, int i);

    private static native void drawPlanarBoard_2(long j, double d, double d2, long j2);

    private static native int estimatePoseBoard_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z);

    private static native int estimatePoseBoard_1(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native boolean estimatePoseCharucoBoard_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z);

    private static native boolean estimatePoseCharucoBoard_1(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native void estimatePoseSingleMarkers_0(long j, float f, long j2, long j3, long j4, long j5, long j6);

    private static native void estimatePoseSingleMarkers_1(long j, float f, long j2, long j3, long j4, long j5);

    private static native void getBoardObjectAndImagePoints_0(long j, long j2, long j3, long j4, long j5);

    private static native long getPredefinedDictionary_0(int i);

    private static native int interpolateCornersCharuco_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int i);

    private static native int interpolateCornersCharuco_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    private static native int interpolateCornersCharuco_2(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native int interpolateCornersCharuco_3(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void refineDetectedMarkers_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, float f, float f2, boolean z, long j8, long j9);

    private static native void refineDetectedMarkers_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, float f, float f2, boolean z, long j8);

    private static native void refineDetectedMarkers_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, float f, float f2, boolean z);

    private static native void refineDetectedMarkers_3(long j, long j2, long j3, long j4, long j5, long j6, long j7, float f, float f2);

    private static native void refineDetectedMarkers_4(long j, long j2, long j3, long j4, long j5, long j6, long j7, float f);

    private static native void refineDetectedMarkers_5(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native void refineDetectedMarkers_6(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void refineDetectedMarkers_7(long j, long j2, long j3, long j4, long j5);

    private static native boolean testCharucoCornersCollinear_0(long j, long j2);

    public static Dictionary custom_dictionary_from(int i, int i2, Dictionary dictionary, int i3) {
        return Dictionary.__fromPtr__(custom_dictionary_from_0(i, i2, dictionary.getNativeObjAddr(), i3));
    }

    public static Dictionary custom_dictionary_from(int i, int i2, Dictionary dictionary) {
        return Dictionary.__fromPtr__(custom_dictionary_from_1(i, i2, dictionary.getNativeObjAddr()));
    }

    public static Dictionary custom_dictionary(int i, int i2, int i3) {
        return Dictionary.__fromPtr__(custom_dictionary_0(i, i2, i3));
    }

    public static Dictionary custom_dictionary(int i, int i2) {
        return Dictionary.__fromPtr__(custom_dictionary_1(i, i2));
    }

    public static Dictionary getPredefinedDictionary(int i) {
        return Dictionary.__fromPtr__(getPredefinedDictionary_0(i));
    }

    public static boolean estimatePoseCharucoBoard(Mat mat, Mat mat2, CharucoBoard charucoBoard, Mat mat3, Mat mat4, Mat mat5, Mat mat6, boolean z) {
        return estimatePoseCharucoBoard_0(mat.nativeObj, mat2.nativeObj, charucoBoard.getNativeObjAddr(), mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, z);
    }

    public static boolean estimatePoseCharucoBoard(Mat mat, Mat mat2, CharucoBoard charucoBoard, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return estimatePoseCharucoBoard_1(mat.nativeObj, mat2.nativeObj, charucoBoard.getNativeObjAddr(), mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static boolean testCharucoCornersCollinear(CharucoBoard charucoBoard, Mat mat) {
        return testCharucoCornersCollinear_0(charucoBoard.getNativeObjAddr(), mat.nativeObj);
    }

    public static double calibrateCameraArucoExtended(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2, List<Mat> list3, Mat mat5, Mat mat6, Mat mat7, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = mat.nativeObj;
        long j3 = mat2.nativeObj;
        long nativeObjAddr = board.getNativeObjAddr();
        double d = size2.width;
        double d2 = size2.height;
        Mat mat10 = mat8;
        long j4 = mat3.nativeObj;
        Mat mat11 = mat10;
        long j5 = j;
        Mat mat12 = mat9;
        long j6 = j2;
        Mat mat13 = mat12;
        long j7 = j5;
        double calibrateCameraArucoExtended_0 = calibrateCameraArucoExtended_0(j7, j6, j3, nativeObjAddr, d, d2, j4, mat4.nativeObj, mat11.nativeObj, mat12.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Converters.Mat_to_vector_Mat(mat11, list2);
        mat11.release();
        Converters.Mat_to_vector_Mat(mat13, list3);
        mat13.release();
        return calibrateCameraArucoExtended_0;
    }

    public static double calibrateCameraArucoExtended(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2, List<Mat> list3, Mat mat5, Mat mat6, Mat mat7, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = mat.nativeObj;
        Mat mat10 = mat9;
        Mat mat11 = mat10;
        long j3 = j2;
        Mat mat12 = mat8;
        Mat mat13 = mat11;
        Mat mat14 = mat12;
        long j4 = j;
        long j5 = j3;
        double calibrateCameraArucoExtended_1 = calibrateCameraArucoExtended_1(j4, j5, mat2.nativeObj, board.getNativeObjAddr(), size2.width, size2.height, mat3.nativeObj, mat4.nativeObj, mat12.nativeObj, mat11.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat14, list2);
        mat14.release();
        Mat mat15 = mat13;
        Converters.Mat_to_vector_Mat(mat15, list3);
        mat15.release();
        return calibrateCameraArucoExtended_1;
    }

    public static double calibrateCameraArucoExtended(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2, List<Mat> list3, Mat mat5, Mat mat6, Mat mat7) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat8 = new Mat();
        Mat mat9 = new Mat();
        double calibrateCameraArucoExtended_2 = calibrateCameraArucoExtended_2(vector_Mat_to_Mat.nativeObj, mat.nativeObj, mat2.nativeObj, board.getNativeObjAddr(), size2.width, size2.height, mat3.nativeObj, mat4.nativeObj, mat8.nativeObj, mat9.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
        Converters.Mat_to_vector_Mat(mat8, list2);
        mat8.release();
        Converters.Mat_to_vector_Mat(mat9, list3);
        mat9.release();
        return calibrateCameraArucoExtended_2;
    }

    public static double calibrateCameraAruco(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2, List<Mat> list3, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = mat.nativeObj;
        long j3 = mat2.nativeObj;
        long nativeObjAddr = board.getNativeObjAddr();
        double d = size2.width;
        double d2 = size2.height;
        Mat mat7 = mat5;
        long j4 = mat3.nativeObj;
        Mat mat8 = mat7;
        long j5 = j;
        Mat mat9 = mat6;
        long j6 = j2;
        Mat mat10 = mat9;
        long j7 = j5;
        double calibrateCameraAruco_0 = calibrateCameraAruco_0(j7, j6, j3, nativeObjAddr, d, d2, j4, mat4.nativeObj, mat8.nativeObj, mat9.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Converters.Mat_to_vector_Mat(mat8, list2);
        mat8.release();
        Converters.Mat_to_vector_Mat(mat10, list3);
        mat10.release();
        return calibrateCameraAruco_0;
    }

    public static double calibrateCameraAruco(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2, List<Mat> list3, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = mat.nativeObj;
        long j3 = mat2.nativeObj;
        long nativeObjAddr = board.getNativeObjAddr();
        double d = size2.width;
        double d2 = size2.height;
        Mat mat7 = mat6;
        long j4 = mat3.nativeObj;
        Mat mat8 = mat7;
        long j5 = j2;
        Mat mat9 = mat5;
        long j6 = mat4.nativeObj;
        long j7 = mat9.nativeObj;
        long j8 = mat8.nativeObj;
        Mat mat10 = mat8;
        Mat mat11 = mat9;
        double calibrateCameraAruco_1 = calibrateCameraAruco_1(j, j5, j3, nativeObjAddr, d, d2, j4, j6, j7, j8, i);
        Converters.Mat_to_vector_Mat(mat11, list2);
        mat11.release();
        Mat mat12 = mat10;
        Converters.Mat_to_vector_Mat(mat12, list3);
        mat12.release();
        return calibrateCameraAruco_1;
    }

    public static double calibrateCameraAruco(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2, List<Mat> list3) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        double calibrateCameraAruco_2 = calibrateCameraAruco_2(vector_Mat_to_Mat.nativeObj, mat.nativeObj, mat2.nativeObj, board.getNativeObjAddr(), size2.width, size2.height, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
        Converters.Mat_to_vector_Mat(mat5, list2);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        return calibrateCameraAruco_2;
    }

    public static double calibrateCameraAruco(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4, List<Mat> list2) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat5 = new Mat();
        double calibrateCameraAruco_3 = calibrateCameraAruco_3(vector_Mat_to_Mat.nativeObj, mat.nativeObj, mat2.nativeObj, board.getNativeObjAddr(), size2.width, size2.height, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        Converters.Mat_to_vector_Mat(mat5, list2);
        mat5.release();
        return calibrateCameraAruco_3;
    }

    public static double calibrateCameraAruco(List<Mat> list, Mat mat, Mat mat2, Board board, Size size, Mat mat3, Mat mat4) {
        Size size2 = size;
        return calibrateCameraAruco_4(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, board.getNativeObjAddr(), size2.width, size2.height, mat3.nativeObj, mat4.nativeObj);
    }

    public static double calibrateCameraCharucoExtended(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = vector_Mat_to_Mat2.nativeObj;
        Mat mat8 = mat6;
        Mat mat9 = mat8;
        long j3 = j;
        Mat mat10 = mat7;
        Mat mat11 = mat10;
        long j4 = j2;
        long j5 = j3;
        double calibrateCameraCharucoExtended_0 = calibrateCameraCharucoExtended_0(j5, j4, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat9.nativeObj, mat10.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Converters.Mat_to_vector_Mat(mat9, list3);
        mat9.release();
        Converters.Mat_to_vector_Mat(mat11, list4);
        mat11.release();
        return calibrateCameraCharucoExtended_0;
    }

    public static double calibrateCameraCharucoExtended(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat8 = mat7;
        Mat mat9 = mat8;
        Mat mat10 = mat6;
        long j2 = j;
        double calibrateCameraCharucoExtended_1 = calibrateCameraCharucoExtended_1(j2, vector_Mat_to_Mat2.nativeObj, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat9.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, i);
        Mat mat11 = mat10;
        Converters.Mat_to_vector_Mat(mat11, list3);
        mat11.release();
        Converters.Mat_to_vector_Mat(mat9, list4);
        mat9.release();
        return calibrateCameraCharucoExtended_1;
    }

    public static double calibrateCameraCharucoExtended(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        double calibrateCameraCharucoExtended_2 = calibrateCameraCharucoExtended_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat7.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat7, list4);
        mat7.release();
        return calibrateCameraCharucoExtended_2;
    }

    public static double calibrateCameraCharuco(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        long j2 = vector_Mat_to_Mat2.nativeObj;
        long nativeObjAddr = charucoBoard.getNativeObjAddr();
        double d = size2.width;
        double d2 = size2.height;
        long j3 = mat.nativeObj;
        Mat mat5 = mat3;
        long j4 = mat2.nativeObj;
        Mat mat6 = mat5;
        long j5 = j;
        Mat mat7 = mat4;
        long j6 = mat6.nativeObj;
        long j7 = mat7.nativeObj;
        Mat mat8 = mat7;
        long j8 = j5;
        double calibrateCameraCharuco_0 = calibrateCameraCharuco_0(j8, j2, nativeObjAddr, d, d2, j3, j4, j6, j7, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat8, list4);
        mat8.release();
        return calibrateCameraCharuco_0;
    }

    public static double calibrateCameraCharuco(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        long j = vector_Mat_to_Mat.nativeObj;
        Mat mat5 = mat4;
        Mat mat6 = mat5;
        Mat mat7 = mat3;
        long j2 = j;
        double calibrateCameraCharuco_1 = calibrateCameraCharuco_1(j2, vector_Mat_to_Mat2.nativeObj, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat6.nativeObj, i);
        Mat mat8 = mat7;
        Converters.Mat_to_vector_Mat(mat8, list3);
        mat8.release();
        Converters.Mat_to_vector_Mat(mat6, list4);
        mat6.release();
        return calibrateCameraCharuco_1;
    }

    public static double calibrateCameraCharuco(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrateCameraCharuco_2 = calibrateCameraCharuco_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrateCameraCharuco_2;
    }

    public static double calibrateCameraCharuco(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2, List<Mat> list3) {
        Size size2 = size;
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        double calibrateCameraCharuco_3 = calibrateCameraCharuco_3(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        return calibrateCameraCharuco_3;
    }

    public static double calibrateCameraCharuco(List<Mat> list, List<Mat> list2, CharucoBoard charucoBoard, Size size, Mat mat, Mat mat2) {
        Size size2 = size;
        return calibrateCameraCharuco_4(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, charucoBoard.getNativeObjAddr(), size2.width, size2.height, mat.nativeObj, mat2.nativeObj);
    }

    public static int estimatePoseBoard(List<Mat> list, Mat mat, Board board, Mat mat2, Mat mat3, Mat mat4, Mat mat5, boolean z) {
        return estimatePoseBoard_0(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, board.getNativeObjAddr(), mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, z);
    }

    public static int estimatePoseBoard(List<Mat> list, Mat mat, Board board, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return estimatePoseBoard_1(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, board.getNativeObjAddr(), mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static int interpolateCornersCharuco(List<Mat> list, Mat mat, Mat mat2, CharucoBoard charucoBoard, Mat mat3, Mat mat4, Mat mat5, Mat mat6, int i) {
        return interpolateCornersCharuco_0(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, charucoBoard.getNativeObjAddr(), mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, i);
    }

    public static int interpolateCornersCharuco(List<Mat> list, Mat mat, Mat mat2, CharucoBoard charucoBoard, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return interpolateCornersCharuco_1(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, charucoBoard.getNativeObjAddr(), mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static int interpolateCornersCharuco(List<Mat> list, Mat mat, Mat mat2, CharucoBoard charucoBoard, Mat mat3, Mat mat4, Mat mat5) {
        return interpolateCornersCharuco_2(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, charucoBoard.getNativeObjAddr(), mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static int interpolateCornersCharuco(List<Mat> list, Mat mat, Mat mat2, CharucoBoard charucoBoard, Mat mat3, Mat mat4) {
        return interpolateCornersCharuco_3(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, charucoBoard.getNativeObjAddr(), mat3.nativeObj, mat4.nativeObj);
    }

    public static void detectCharucoDiamond(Mat mat, List<Mat> list, Mat mat2, float f, List<Mat> list2, Mat mat3, Mat mat4, Mat mat5) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat6 = new Mat();
        detectCharucoDiamond_0(mat.nativeObj, vector_Mat_to_Mat.nativeObj, mat2.nativeObj, f, mat6.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        Mat mat7 = mat6;
        Converters.Mat_to_vector_Mat(mat7, list2);
        mat7.release();
    }

    public static void detectCharucoDiamond(Mat mat, List<Mat> list, Mat mat2, float f, List<Mat> list2, Mat mat3, Mat mat4) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat5 = new Mat();
        detectCharucoDiamond_1(mat.nativeObj, vector_Mat_to_Mat.nativeObj, mat2.nativeObj, f, mat5.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Converters.Mat_to_vector_Mat(mat5, list2);
        mat5.release();
    }

    public static void detectCharucoDiamond(Mat mat, List<Mat> list, Mat mat2, float f, List<Mat> list2, Mat mat3) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat4 = new Mat();
        detectCharucoDiamond_2(mat.nativeObj, vector_Mat_to_Mat.nativeObj, mat2.nativeObj, f, mat4.nativeObj, mat3.nativeObj);
        Converters.Mat_to_vector_Mat(mat4, list2);
        mat4.release();
    }

    public static void detectMarkers(Mat mat, Dictionary dictionary, List<Mat> list, Mat mat2, DetectorParameters detectorParameters, List<Mat> list2, Mat mat3, Mat mat4) {
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        Mat mat7 = mat6;
        detectMarkers_0(mat.nativeObj, dictionary.getNativeObjAddr(), mat5.nativeObj, mat2.nativeObj, detectorParameters.getNativeObjAddr(), mat6.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Mat mat8 = mat5;
        Converters.Mat_to_vector_Mat(mat8, list);
        mat8.release();
        Mat mat9 = mat7;
        Converters.Mat_to_vector_Mat(mat9, list2);
        mat9.release();
    }

    public static void detectMarkers(Mat mat, Dictionary dictionary, List<Mat> list, Mat mat2, DetectorParameters detectorParameters, List<Mat> list2, Mat mat3) {
        Mat mat4 = new Mat();
        Mat mat5 = new Mat();
        detectMarkers_1(mat.nativeObj, dictionary.getNativeObjAddr(), mat4.nativeObj, mat2.nativeObj, detectorParameters.getNativeObjAddr(), mat5.nativeObj, mat3.nativeObj);
        Converters.Mat_to_vector_Mat(mat4, list);
        mat4.release();
        Converters.Mat_to_vector_Mat(mat5, list2);
        mat5.release();
    }

    public static void detectMarkers(Mat mat, Dictionary dictionary, List<Mat> list, Mat mat2, DetectorParameters detectorParameters, List<Mat> list2) {
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        detectMarkers_2(mat.nativeObj, dictionary.getNativeObjAddr(), mat3.nativeObj, mat2.nativeObj, detectorParameters.getNativeObjAddr(), mat4.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list2);
        mat4.release();
    }

    public static void detectMarkers(Mat mat, Dictionary dictionary, List<Mat> list, Mat mat2, DetectorParameters detectorParameters) {
        Mat mat3 = new Mat();
        detectMarkers_3(mat.nativeObj, dictionary.getNativeObjAddr(), mat3.nativeObj, mat2.nativeObj, detectorParameters.getNativeObjAddr());
        Converters.Mat_to_vector_Mat(mat3, list);
        mat3.release();
    }

    public static void detectMarkers(Mat mat, Dictionary dictionary, List<Mat> list, Mat mat2) {
        Mat mat3 = new Mat();
        detectMarkers_4(mat.nativeObj, dictionary.getNativeObjAddr(), mat3.nativeObj, mat2.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list);
        mat3.release();
    }

    @Deprecated
    public static void drawAxis(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, float f) {
        drawAxis_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, f);
    }

    public static void drawDetectedCornersCharuco(Mat mat, Mat mat2, Mat mat3, Scalar scalar) {
        Scalar scalar2 = scalar;
        drawDetectedCornersCharuco_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3]);
    }

    public static void drawDetectedCornersCharuco(Mat mat, Mat mat2, Mat mat3) {
        drawDetectedCornersCharuco_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void drawDetectedCornersCharuco(Mat mat, Mat mat2) {
        drawDetectedCornersCharuco_2(mat.nativeObj, mat2.nativeObj);
    }

    public static void drawDetectedDiamonds(Mat mat, List<Mat> list, Mat mat2, Scalar scalar) {
        Scalar scalar2 = scalar;
        drawDetectedDiamonds_0(mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat2.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3]);
    }

    public static void drawDetectedDiamonds(Mat mat, List<Mat> list, Mat mat2) {
        drawDetectedDiamonds_1(mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat2.nativeObj);
    }

    public static void drawDetectedDiamonds(Mat mat, List<Mat> list) {
        drawDetectedDiamonds_2(mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj);
    }

    public static void drawDetectedMarkers(Mat mat, List<Mat> list, Mat mat2, Scalar scalar) {
        Scalar scalar2 = scalar;
        drawDetectedMarkers_0(mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat2.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3]);
    }

    public static void drawDetectedMarkers(Mat mat, List<Mat> list, Mat mat2) {
        drawDetectedMarkers_1(mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat2.nativeObj);
    }

    public static void drawDetectedMarkers(Mat mat, List<Mat> list) {
        drawDetectedMarkers_2(mat.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj);
    }

    public static void drawMarker(Dictionary dictionary, int i, int i2, Mat mat, int i3) {
        drawMarker_0(dictionary.getNativeObjAddr(), i, i2, mat.nativeObj, i3);
    }

    public static void drawMarker(Dictionary dictionary, int i, int i2, Mat mat) {
        drawMarker_1(dictionary.getNativeObjAddr(), i, i2, mat.nativeObj);
    }

    public static void drawPlanarBoard(Board board, Size size, Mat mat, int i, int i2) {
        drawPlanarBoard_0(board.getNativeObjAddr(), size.width, size.height, mat.nativeObj, i, i2);
    }

    public static void drawPlanarBoard(Board board, Size size, Mat mat, int i) {
        drawPlanarBoard_1(board.getNativeObjAddr(), size.width, size.height, mat.nativeObj, i);
    }

    public static void drawPlanarBoard(Board board, Size size, Mat mat) {
        drawPlanarBoard_2(board.getNativeObjAddr(), size.width, size.height, mat.nativeObj);
    }

    public static void estimatePoseSingleMarkers(List<Mat> list, float f, Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        estimatePoseSingleMarkers_0(Converters.vector_Mat_to_Mat(list).nativeObj, f, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void estimatePoseSingleMarkers(List<Mat> list, float f, Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        estimatePoseSingleMarkers_1(Converters.vector_Mat_to_Mat(list).nativeObj, f, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void getBoardObjectAndImagePoints(Board board, List<Mat> list, Mat mat, Mat mat2, Mat mat3) {
        getBoardObjectAndImagePoints_0(board.getNativeObjAddr(), Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3, Mat mat4, float f, float f2, boolean z, Mat mat5, DetectorParameters detectorParameters) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        long j = mat.nativeObj;
        Mat mat6 = vector_Mat_to_Mat;
        Mat mat7 = mat6;
        long j2 = j;
        refineDetectedMarkers_0(j2, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, f, f2, z, mat5.nativeObj, detectorParameters.getNativeObjAddr());
        Mat mat8 = mat7;
        Converters.Mat_to_vector_Mat(mat8, list);
        mat8.release();
        Mat mat9 = vector_Mat_to_Mat2;
        Converters.Mat_to_vector_Mat(mat9, list2);
        mat9.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3, Mat mat4, float f, float f2, boolean z, Mat mat5) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        long j = mat.nativeObj;
        Mat mat6 = vector_Mat_to_Mat;
        Mat mat7 = mat6;
        long j2 = j;
        refineDetectedMarkers_1(j2, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, f, f2, z, mat5.nativeObj);
        Mat mat8 = mat7;
        Converters.Mat_to_vector_Mat(mat8, list);
        mat8.release();
        Mat mat9 = vector_Mat_to_Mat2;
        Converters.Mat_to_vector_Mat(mat9, list2);
        mat9.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3, Mat mat4, float f, float f2, boolean z) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat5 = vector_Mat_to_Mat;
        refineDetectedMarkers_2(mat.nativeObj, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, f, f2, z);
        Mat mat6 = mat5;
        Converters.Mat_to_vector_Mat(mat6, list);
        mat6.release();
        Mat mat7 = vector_Mat_to_Mat2;
        Converters.Mat_to_vector_Mat(mat7, list2);
        mat7.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3, Mat mat4, float f, float f2) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        refineDetectedMarkers_3(mat.nativeObj, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, f, f2);
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat, list);
        vector_Mat_to_Mat.release();
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat2, list2);
        vector_Mat_to_Mat2.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3, Mat mat4, float f) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        refineDetectedMarkers_4(mat.nativeObj, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, f);
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat, list);
        vector_Mat_to_Mat.release();
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat2, list2);
        vector_Mat_to_Mat2.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3, Mat mat4) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        refineDetectedMarkers_5(mat.nativeObj, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat, list);
        vector_Mat_to_Mat.release();
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat2, list2);
        vector_Mat_to_Mat2.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2, Mat mat3) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        refineDetectedMarkers_6(mat.nativeObj, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj, mat3.nativeObj);
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat, list);
        vector_Mat_to_Mat.release();
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat2, list2);
        vector_Mat_to_Mat2.release();
    }

    public static void refineDetectedMarkers(Mat mat, Board board, List<Mat> list, Mat mat2, List<Mat> list2) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        refineDetectedMarkers_7(mat.nativeObj, board.getNativeObjAddr(), vector_Mat_to_Mat.nativeObj, mat2.nativeObj, vector_Mat_to_Mat2.nativeObj);
        List<Mat> list3 = list;
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat, list);
        vector_Mat_to_Mat.release();
        Converters.Mat_to_vector_Mat(vector_Mat_to_Mat2, list2);
        vector_Mat_to_Mat2.release();
    }
}
