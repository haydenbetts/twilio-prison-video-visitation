package org.opencv.text;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.utils.Converters;

public class Text {
    public static final int ERFILTER_NM_IHSGrad = 1;
    public static final int ERFILTER_NM_RGBLGrad = 0;
    public static final int ERGROUPING_ORIENTATION_ANY = 1;
    public static final int ERGROUPING_ORIENTATION_HORIZ = 0;
    public static final int OCR_CNN_CLASSIFIER = 1;
    public static final int OCR_DECODER_VITERBI = 0;
    public static final int OCR_KNN_CLASSIFIER = 0;
    public static final int OCR_LEVEL_TEXTLINE = 1;
    public static final int OCR_LEVEL_WORD = 0;
    public static final int OEM_CUBE_ONLY = 1;
    public static final int OEM_DEFAULT = 3;
    public static final int OEM_TESSERACT_CUBE_COMBINED = 2;
    public static final int OEM_TESSERACT_ONLY = 0;
    public static final int PSM_AUTO = 3;
    public static final int PSM_AUTO_ONLY = 2;
    public static final int PSM_AUTO_OSD = 1;
    public static final int PSM_CIRCLE_WORD = 9;
    public static final int PSM_OSD_ONLY = 0;
    public static final int PSM_SINGLE_BLOCK = 6;
    public static final int PSM_SINGLE_BLOCK_VERT_TEXT = 5;
    public static final int PSM_SINGLE_CHAR = 10;
    public static final int PSM_SINGLE_COLUMN = 4;
    public static final int PSM_SINGLE_LINE = 7;
    public static final int PSM_SINGLE_WORD = 8;

    private static native void computeNMChannels_0(long j, long j2, int i);

    private static native void computeNMChannels_1(long j, long j2);

    private static native long createERFilterNM1_0(String str, int i, float f, float f2, float f3, boolean z, float f4);

    private static native long createERFilterNM1_1(String str, int i, float f, float f2, float f3, boolean z);

    private static native long createERFilterNM1_2(String str, int i, float f, float f2, float f3);

    private static native long createERFilterNM1_3(String str, int i, float f, float f2);

    private static native long createERFilterNM1_4(String str, int i, float f);

    private static native long createERFilterNM1_5(String str, int i);

    private static native long createERFilterNM1_6(String str);

    private static native long createERFilterNM2_0(String str, float f);

    private static native long createERFilterNM2_1(String str);

    private static native long createOCRHMMTransitionsTable_0(String str, List<String> list);

    private static native void detectRegions_0(long j, long j2, long j3, long j4, int i, String str, float f);

    private static native void detectRegions_1(long j, long j2, long j3, long j4, int i, String str);

    private static native void detectRegions_2(long j, long j2, long j3, long j4, int i);

    private static native void detectRegions_3(long j, long j2, long j3, long j4);

    private static native void detectRegions_4(long j, long j2, long j3, long j4);

    private static native void detectTextSWT_0(long j, long j2, boolean z, long j3, long j4);

    private static native void detectTextSWT_1(long j, long j2, boolean z, long j3);

    private static native void detectTextSWT_2(long j, long j2, boolean z);

    private static native void erGrouping_0(long j, long j2, long j3, long j4, int i, String str, float f);

    private static native void erGrouping_1(long j, long j2, long j3, long j4, int i, String str);

    private static native void erGrouping_2(long j, long j2, long j3, long j4, int i);

    private static native void erGrouping_3(long j, long j2, long j3, long j4);

    public static Mat createOCRHMMTransitionsTable(String str, List<String> list) {
        return new Mat(createOCRHMMTransitionsTable_0(str, list));
    }

    public static ERFilter createERFilterNM1(String str, int i, float f, float f2, float f3, boolean z, float f4) {
        return ERFilter.__fromPtr__(createERFilterNM1_0(str, i, f, f2, f3, z, f4));
    }

    public static ERFilter createERFilterNM1(String str, int i, float f, float f2, float f3, boolean z) {
        return ERFilter.__fromPtr__(createERFilterNM1_1(str, i, f, f2, f3, z));
    }

    public static ERFilter createERFilterNM1(String str, int i, float f, float f2, float f3) {
        return ERFilter.__fromPtr__(createERFilterNM1_2(str, i, f, f2, f3));
    }

    public static ERFilter createERFilterNM1(String str, int i, float f, float f2) {
        return ERFilter.__fromPtr__(createERFilterNM1_3(str, i, f, f2));
    }

    public static ERFilter createERFilterNM1(String str, int i, float f) {
        return ERFilter.__fromPtr__(createERFilterNM1_4(str, i, f));
    }

    public static ERFilter createERFilterNM1(String str, int i) {
        return ERFilter.__fromPtr__(createERFilterNM1_5(str, i));
    }

    public static ERFilter createERFilterNM1(String str) {
        return ERFilter.__fromPtr__(createERFilterNM1_6(str));
    }

    public static ERFilter createERFilterNM2(String str, float f) {
        return ERFilter.__fromPtr__(createERFilterNM2_0(str, f));
    }

    public static ERFilter createERFilterNM2(String str) {
        return ERFilter.__fromPtr__(createERFilterNM2_1(str));
    }

    public static void computeNMChannels(Mat mat, List<Mat> list, int i) {
        Mat mat2 = new Mat();
        computeNMChannels_0(mat.nativeObj, mat2.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
    }

    public static void computeNMChannels(Mat mat, List<Mat> list) {
        Mat mat2 = new Mat();
        computeNMChannels_1(mat.nativeObj, mat2.nativeObj);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
    }

    public static void detectRegions(Mat mat, ERFilter eRFilter, ERFilter eRFilter2, MatOfRect matOfRect, int i, String str, float f) {
        detectRegions_0(mat.nativeObj, eRFilter.getNativeObjAddr(), eRFilter2.getNativeObjAddr(), matOfRect.nativeObj, i, str, f);
    }

    public static void detectRegions(Mat mat, ERFilter eRFilter, ERFilter eRFilter2, MatOfRect matOfRect, int i, String str) {
        detectRegions_1(mat.nativeObj, eRFilter.getNativeObjAddr(), eRFilter2.getNativeObjAddr(), matOfRect.nativeObj, i, str);
    }

    public static void detectRegions(Mat mat, ERFilter eRFilter, ERFilter eRFilter2, MatOfRect matOfRect, int i) {
        detectRegions_2(mat.nativeObj, eRFilter.getNativeObjAddr(), eRFilter2.getNativeObjAddr(), matOfRect.nativeObj, i);
    }

    public static void detectRegions(Mat mat, ERFilter eRFilter, ERFilter eRFilter2, MatOfRect matOfRect) {
        detectRegions_3(mat.nativeObj, eRFilter.getNativeObjAddr(), eRFilter2.getNativeObjAddr(), matOfRect.nativeObj);
    }

    public static void detectRegions(Mat mat, ERFilter eRFilter, ERFilter eRFilter2, List<MatOfPoint> list) {
        Mat mat2 = new Mat();
        detectRegions_4(mat.nativeObj, eRFilter.getNativeObjAddr(), eRFilter2.getNativeObjAddr(), mat2.nativeObj);
        Converters.Mat_to_vector_vector_Point(mat2, list);
        mat2.release();
    }

    public static void detectTextSWT(Mat mat, MatOfRect matOfRect, boolean z, Mat mat2, Mat mat3) {
        detectTextSWT_0(mat.nativeObj, matOfRect.nativeObj, z, mat2.nativeObj, mat3.nativeObj);
    }

    public static void detectTextSWT(Mat mat, MatOfRect matOfRect, boolean z, Mat mat2) {
        detectTextSWT_1(mat.nativeObj, matOfRect.nativeObj, z, mat2.nativeObj);
    }

    public static void detectTextSWT(Mat mat, MatOfRect matOfRect, boolean z) {
        detectTextSWT_2(mat.nativeObj, matOfRect.nativeObj, z);
    }

    public static void erGrouping(Mat mat, Mat mat2, List<MatOfPoint> list, MatOfRect matOfRect, int i, String str, float f) {
        erGrouping_0(mat.nativeObj, mat2.nativeObj, Converters.vector_vector_Point_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, matOfRect.nativeObj, i, str, f);
    }

    public static void erGrouping(Mat mat, Mat mat2, List<MatOfPoint> list, MatOfRect matOfRect, int i, String str) {
        erGrouping_1(mat.nativeObj, mat2.nativeObj, Converters.vector_vector_Point_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, matOfRect.nativeObj, i, str);
    }

    public static void erGrouping(Mat mat, Mat mat2, List<MatOfPoint> list, MatOfRect matOfRect, int i) {
        erGrouping_2(mat.nativeObj, mat2.nativeObj, Converters.vector_vector_Point_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, matOfRect.nativeObj, i);
    }

    public static void erGrouping(Mat mat, Mat mat2, List<MatOfPoint> list, MatOfRect matOfRect) {
        erGrouping_3(mat.nativeObj, mat2.nativeObj, Converters.vector_vector_Point_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, matOfRect.nativeObj);
    }
}
