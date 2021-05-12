package org.opencv.face;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.utils.Converters;

public class Face {
    private static native long createFacemarkAAM_0();

    private static native long createFacemarkKazemi_0();

    private static native long createFacemarkLBF_0();

    private static native void drawFacemarks_0(long j, long j2, double d, double d2, double d3, double d4);

    private static native void drawFacemarks_1(long j, long j2);

    private static native boolean getFacesHAAR_0(long j, long j2, String str);

    private static native boolean loadDatasetList_0(String str, String str2, List<String> list, List<String> list2);

    private static native boolean loadFacePoints_0(String str, long j, float f);

    private static native boolean loadFacePoints_1(String str, long j);

    private static native boolean loadTrainingData_0(String str, List<String> list, long j, char c, float f);

    private static native boolean loadTrainingData_1(String str, List<String> list, long j, char c);

    private static native boolean loadTrainingData_2(String str, List<String> list, long j);

    private static native boolean loadTrainingData_3(String str, String str2, List<String> list, long j, float f);

    private static native boolean loadTrainingData_4(String str, String str2, List<String> list, long j);

    private static native boolean loadTrainingData_5(List<String> list, long j, List<String> list2);

    public static Facemark createFacemarkAAM() {
        return Facemark.__fromPtr__(createFacemarkAAM_0());
    }

    public static Facemark createFacemarkKazemi() {
        return Facemark.__fromPtr__(createFacemarkKazemi_0());
    }

    public static Facemark createFacemarkLBF() {
        return Facemark.__fromPtr__(createFacemarkLBF_0());
    }

    public static boolean getFacesHAAR(Mat mat, Mat mat2, String str) {
        return getFacesHAAR_0(mat.nativeObj, mat2.nativeObj, str);
    }

    public static boolean loadDatasetList(String str, String str2, List<String> list, List<String> list2) {
        return loadDatasetList_0(str, str2, list, list2);
    }

    public static boolean loadFacePoints(String str, Mat mat, float f) {
        return loadFacePoints_0(str, mat.nativeObj, f);
    }

    public static boolean loadFacePoints(String str, Mat mat) {
        return loadFacePoints_1(str, mat.nativeObj);
    }

    public static boolean loadTrainingData(String str, List<String> list, Mat mat, char c, float f) {
        return loadTrainingData_0(str, list, mat.nativeObj, c, f);
    }

    public static boolean loadTrainingData(String str, List<String> list, Mat mat, char c) {
        return loadTrainingData_1(str, list, mat.nativeObj, c);
    }

    public static boolean loadTrainingData(String str, List<String> list, Mat mat) {
        return loadTrainingData_2(str, list, mat.nativeObj);
    }

    public static boolean loadTrainingData(String str, String str2, List<String> list, Mat mat, float f) {
        return loadTrainingData_3(str, str2, list, mat.nativeObj, f);
    }

    public static boolean loadTrainingData(String str, String str2, List<String> list, Mat mat) {
        return loadTrainingData_4(str, str2, list, mat.nativeObj);
    }

    public static boolean loadTrainingData(List<String> list, List<MatOfPoint2f> list2, List<String> list3) {
        return loadTrainingData_5(list, Converters.vector_vector_Point2f_to_Mat(list2, new ArrayList(list2 != null ? list2.size() : 0)).nativeObj, list3);
    }

    public static void drawFacemarks(Mat mat, Mat mat2, Scalar scalar) {
        drawFacemarks_0(mat.nativeObj, mat2.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
    }

    public static void drawFacemarks(Mat mat, Mat mat2) {
        drawFacemarks_1(mat.nativeObj, mat2.nativeObj);
    }
}
