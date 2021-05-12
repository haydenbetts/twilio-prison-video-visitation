package org.opencv.video;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

public class Video {
    private static final int CV_LKFLOW_GET_MIN_EIGENVALS = 8;
    private static final int CV_LKFLOW_INITIAL_GUESSES = 4;
    public static final int MOTION_AFFINE = 2;
    public static final int MOTION_EUCLIDEAN = 1;
    public static final int MOTION_HOMOGRAPHY = 3;
    public static final int MOTION_TRANSLATION = 0;
    public static final int OPTFLOW_FARNEBACK_GAUSSIAN = 256;
    public static final int OPTFLOW_LK_GET_MIN_EIGENVALS = 8;
    public static final int OPTFLOW_USE_INITIAL_FLOW = 4;

    private static native double[] CamShift_0(long j, int i, int i2, int i3, int i4, double[] dArr, int i5, int i6, double d);

    private static native int buildOpticalFlowPyramid_0(long j, long j2, double d, double d2, int i, boolean z, int i2, int i3, boolean z2);

    private static native int buildOpticalFlowPyramid_1(long j, long j2, double d, double d2, int i, boolean z, int i2, int i3);

    private static native int buildOpticalFlowPyramid_2(long j, long j2, double d, double d2, int i, boolean z, int i2);

    private static native int buildOpticalFlowPyramid_3(long j, long j2, double d, double d2, int i, boolean z);

    private static native int buildOpticalFlowPyramid_4(long j, long j2, double d, double d2, int i);

    private static native void calcOpticalFlowFarneback_0(long j, long j2, long j3, double d, int i, int i2, int i3, int i4, double d2, int i5);

    private static native void calcOpticalFlowPyrLK_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, int i, int i2, int i3, double d3, int i4, double d4);

    private static native void calcOpticalFlowPyrLK_1(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, int i, int i2, int i3, double d3, int i4);

    private static native void calcOpticalFlowPyrLK_2(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, int i, int i2, int i3, double d3);

    private static native void calcOpticalFlowPyrLK_3(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, int i);

    private static native void calcOpticalFlowPyrLK_4(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2);

    private static native void calcOpticalFlowPyrLK_5(long j, long j2, long j3, long j4, long j5, long j6);

    private static native double computeECC_0(long j, long j2, long j3);

    private static native double computeECC_1(long j, long j2);

    private static native long createBackgroundSubtractorKNN_0(int i, double d, boolean z);

    private static native long createBackgroundSubtractorKNN_1(int i, double d);

    private static native long createBackgroundSubtractorKNN_2(int i);

    private static native long createBackgroundSubtractorKNN_3();

    private static native long createBackgroundSubtractorMOG2_0(int i, double d, boolean z);

    private static native long createBackgroundSubtractorMOG2_1(int i, double d);

    private static native long createBackgroundSubtractorMOG2_2(int i);

    private static native long createBackgroundSubtractorMOG2_3();

    private static native double findTransformECC_0(long j, long j2, long j3, int i, int i2, int i3, double d, long j4, int i4);

    private static native int meanShift_0(long j, int i, int i2, int i3, int i4, double[] dArr, int i5, int i6, double d);

    private static native long readOpticalFlow_0(String str);

    private static native boolean writeOpticalFlow_0(String str, long j);

    public static Mat readOpticalFlow(String str) {
        return new Mat(readOpticalFlow_0(str));
    }

    public static BackgroundSubtractorKNN createBackgroundSubtractorKNN(int i, double d, boolean z) {
        return BackgroundSubtractorKNN.__fromPtr__(createBackgroundSubtractorKNN_0(i, d, z));
    }

    public static BackgroundSubtractorKNN createBackgroundSubtractorKNN(int i, double d) {
        return BackgroundSubtractorKNN.__fromPtr__(createBackgroundSubtractorKNN_1(i, d));
    }

    public static BackgroundSubtractorKNN createBackgroundSubtractorKNN(int i) {
        return BackgroundSubtractorKNN.__fromPtr__(createBackgroundSubtractorKNN_2(i));
    }

    public static BackgroundSubtractorKNN createBackgroundSubtractorKNN() {
        return BackgroundSubtractorKNN.__fromPtr__(createBackgroundSubtractorKNN_3());
    }

    public static BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2(int i, double d, boolean z) {
        return BackgroundSubtractorMOG2.__fromPtr__(createBackgroundSubtractorMOG2_0(i, d, z));
    }

    public static BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2(int i, double d) {
        return BackgroundSubtractorMOG2.__fromPtr__(createBackgroundSubtractorMOG2_1(i, d));
    }

    public static BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2(int i) {
        return BackgroundSubtractorMOG2.__fromPtr__(createBackgroundSubtractorMOG2_2(i));
    }

    public static BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2() {
        return BackgroundSubtractorMOG2.__fromPtr__(createBackgroundSubtractorMOG2_3());
    }

    public static RotatedRect CamShift(Mat mat, Rect rect, TermCriteria termCriteria) {
        double[] dArr = new double[4];
        RotatedRect rotatedRect = new RotatedRect(CamShift_0(mat.nativeObj, rect.x, rect.y, rect.width, rect.height, dArr, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon));
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        return rotatedRect;
    }

    public static boolean writeOpticalFlow(String str, Mat mat) {
        return writeOpticalFlow_0(str, mat.nativeObj);
    }

    public static double computeECC(Mat mat, Mat mat2, Mat mat3) {
        return computeECC_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static double computeECC(Mat mat, Mat mat2) {
        return computeECC_1(mat.nativeObj, mat2.nativeObj);
    }

    public static double findTransformECC(Mat mat, Mat mat2, Mat mat3, int i, TermCriteria termCriteria, Mat mat4, int i2) {
        TermCriteria termCriteria2 = termCriteria;
        return findTransformECC_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon, mat4.nativeObj, i2);
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i, boolean z, int i2, int i3, boolean z2) {
        Size size2 = size;
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_0 = buildOpticalFlowPyramid_0(mat.nativeObj, mat2.nativeObj, size2.width, size2.height, i, z, i2, i3, z2);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_0;
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i, boolean z, int i2, int i3) {
        Size size2 = size;
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_1 = buildOpticalFlowPyramid_1(mat.nativeObj, mat2.nativeObj, size2.width, size2.height, i, z, i2, i3);
        List<Mat> list2 = list;
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_1;
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i, boolean z, int i2) {
        Size size2 = size;
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_2 = buildOpticalFlowPyramid_2(mat.nativeObj, mat2.nativeObj, size2.width, size2.height, i, z, i2);
        List<Mat> list2 = list;
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_2;
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i, boolean z) {
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_3 = buildOpticalFlowPyramid_3(mat.nativeObj, mat2.nativeObj, size.width, size.height, i, z);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_3;
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i) {
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_4 = buildOpticalFlowPyramid_4(mat.nativeObj, mat2.nativeObj, size.width, size.height, i);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_4;
    }

    public static int meanShift(Mat mat, Rect rect, TermCriteria termCriteria) {
        double[] dArr = new double[4];
        int meanShift_0 = meanShift_0(mat.nativeObj, rect.x, rect.y, rect.width, rect.height, dArr, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        return meanShift_0;
    }

    public static void calcOpticalFlowFarneback(Mat mat, Mat mat2, Mat mat3, double d, int i, int i2, int i3, int i4, double d2, int i5) {
        calcOpticalFlowFarneback_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, i, i2, i3, i4, d2, i5);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size, int i, TermCriteria termCriteria, int i2, double d) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        long j = mat.nativeObj;
        long j2 = j;
        calcOpticalFlowPyrLK_0(j2, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size2.width, size2.height, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon, i2, d);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size, int i, TermCriteria termCriteria, int i2) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        long j = mat.nativeObj;
        long j2 = j;
        calcOpticalFlowPyrLK_1(j2, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size2.width, size2.height, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon, i2);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size, int i, TermCriteria termCriteria) {
        Size size2 = size;
        TermCriteria termCriteria2 = termCriteria;
        long j = mat.nativeObj;
        long j2 = j;
        calcOpticalFlowPyrLK_2(j2, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size2.width, size2.height, i, termCriteria2.type, termCriteria2.maxCount, termCriteria2.epsilon);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size, int i) {
        Size size2 = size;
        long j = mat.nativeObj;
        long j2 = j;
        calcOpticalFlowPyrLK_3(j2, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size2.width, size2.height, i);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size) {
        Size size2 = size;
        calcOpticalFlowPyrLK_4(mat.nativeObj, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size2.width, size2.height);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat) {
        calcOpticalFlowPyrLK_5(mat.nativeObj, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj);
    }
}
