package org.opencv.features2d;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.utils.Converters;

public class Features2d {
    public static final int DrawMatchesFlags_DEFAULT = 0;
    public static final int DrawMatchesFlags_DRAW_OVER_OUTIMG = 1;
    public static final int DrawMatchesFlags_DRAW_RICH_KEYPOINTS = 4;
    public static final int DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS = 2;

    private static native void drawKeypoints_0(long j, long j2, long j3, double d, double d2, double d3, double d4, int i);

    private static native void drawKeypoints_1(long j, long j2, long j3, double d, double d2, double d3, double d4);

    private static native void drawKeypoints_2(long j, long j2, long j3);

    private static native void drawMatchesKnn_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7, int i);

    private static native void drawMatchesKnn_1(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7);

    private static native void drawMatchesKnn_2(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8);

    private static native void drawMatchesKnn_3(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4);

    private static native void drawMatchesKnn_4(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void drawMatches_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7, int i);

    private static native void drawMatches_1(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7);

    private static native void drawMatches_2(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8);

    private static native void drawMatches_3(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4);

    private static native void drawMatches_4(long j, long j2, long j3, long j4, long j5, long j6);

    public static void drawKeypoints(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, Scalar scalar, int i) {
        Scalar scalar2 = scalar;
        drawKeypoints_0(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3], i);
    }

    public static void drawKeypoints(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, Scalar scalar) {
        Scalar scalar2 = scalar;
        drawKeypoints_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3]);
    }

    public static void drawKeypoints(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2) {
        drawKeypoints_2(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3, Scalar scalar, Scalar scalar2, MatOfByte matOfByte, int i) {
        Scalar scalar3 = scalar;
        Scalar scalar4 = scalar2;
        long j = mat.nativeObj;
        long j2 = j;
        drawMatches_0(j2, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj, scalar3.val[0], scalar3.val[1], scalar3.val[2], scalar3.val[3], scalar4.val[0], scalar4.val[1], scalar4.val[2], scalar4.val[3], matOfByte.nativeObj, i);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3, Scalar scalar, Scalar scalar2, MatOfByte matOfByte) {
        Scalar scalar3 = scalar;
        Scalar scalar4 = scalar2;
        drawMatches_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj, scalar3.val[0], scalar3.val[1], scalar3.val[2], scalar3.val[3], scalar4.val[0], scalar4.val[1], scalar4.val[2], scalar4.val[3], matOfByte.nativeObj);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3, Scalar scalar, Scalar scalar2) {
        Scalar scalar3 = scalar;
        Scalar scalar4 = scalar2;
        drawMatches_2(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj, scalar3.val[0], scalar3.val[1], scalar3.val[2], scalar3.val[3], scalar4.val[0], scalar4.val[1], scalar4.val[2], scalar4.val[3]);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3, Scalar scalar) {
        Scalar scalar2 = scalar;
        drawMatches_3(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3]);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3) {
        drawMatches_4(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3, Scalar scalar, Scalar scalar2, List<MatOfByte> list2, int i) {
        List<MatOfDMatch> list3 = list;
        Scalar scalar3 = scalar;
        Scalar scalar4 = scalar2;
        List<MatOfByte> list4 = list2;
        drawMatchesKnn_0(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list3, new ArrayList(list3 != null ? list.size() : 0)).nativeObj, mat3.nativeObj, scalar3.val[0], scalar3.val[1], scalar3.val[2], scalar3.val[3], scalar4.val[0], scalar4.val[1], scalar4.val[2], scalar4.val[3], Converters.vector_vector_char_to_Mat(list4, new ArrayList(list4 != null ? list2.size() : 0)).nativeObj, i);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3, Scalar scalar, Scalar scalar2, List<MatOfByte> list2) {
        List<MatOfDMatch> list3 = list;
        Scalar scalar3 = scalar;
        Scalar scalar4 = scalar2;
        List<MatOfByte> list4 = list2;
        drawMatchesKnn_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list3, new ArrayList(list3 != null ? list.size() : 0)).nativeObj, mat3.nativeObj, scalar3.val[0], scalar3.val[1], scalar3.val[2], scalar3.val[3], scalar4.val[0], scalar4.val[1], scalar4.val[2], scalar4.val[3], Converters.vector_vector_char_to_Mat(list4, new ArrayList(list4 != null ? list2.size() : 0)).nativeObj);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3, Scalar scalar, Scalar scalar2) {
        List<MatOfDMatch> list2 = list;
        Scalar scalar3 = scalar;
        Scalar scalar4 = scalar2;
        drawMatchesKnn_2(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list2, new ArrayList(list2 != null ? list.size() : 0)).nativeObj, mat3.nativeObj, scalar3.val[0], scalar3.val[1], scalar3.val[2], scalar3.val[3], scalar4.val[0], scalar4.val[1], scalar4.val[2], scalar4.val[3]);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3, Scalar scalar) {
        List<MatOfDMatch> list2 = list;
        Scalar scalar2 = scalar;
        drawMatchesKnn_3(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list2, new ArrayList(list2 != null ? list.size() : 0)).nativeObj, mat3.nativeObj, scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3]);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3) {
        List<MatOfDMatch> list2 = list;
        drawMatchesKnn_4(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list2, new ArrayList(list2 != null ? list.size() : 0)).nativeObj, mat3.nativeObj);
    }
}
