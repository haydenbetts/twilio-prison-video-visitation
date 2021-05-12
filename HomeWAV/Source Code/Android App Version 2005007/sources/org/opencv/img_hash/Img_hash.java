package org.opencv.img_hash;

import org.opencv.core.Mat;

public class Img_hash {
    public static final int BLOCK_MEAN_HASH_MODE_0 = 0;
    public static final int BLOCK_MEAN_HASH_MODE_1 = 1;

    private static native void averageHash_0(long j, long j2);

    private static native void blockMeanHash_0(long j, long j2, int i);

    private static native void blockMeanHash_1(long j, long j2);

    private static native void colorMomentHash_0(long j, long j2);

    private static native void marrHildrethHash_0(long j, long j2, float f, float f2);

    private static native void marrHildrethHash_1(long j, long j2, float f);

    private static native void marrHildrethHash_2(long j, long j2);

    private static native void pHash_0(long j, long j2);

    private static native void radialVarianceHash_0(long j, long j2, double d, int i);

    private static native void radialVarianceHash_1(long j, long j2, double d);

    private static native void radialVarianceHash_2(long j, long j2);

    public static void averageHash(Mat mat, Mat mat2) {
        averageHash_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void blockMeanHash(Mat mat, Mat mat2, int i) {
        blockMeanHash_0(mat.nativeObj, mat2.nativeObj, i);
    }

    public static void blockMeanHash(Mat mat, Mat mat2) {
        blockMeanHash_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void colorMomentHash(Mat mat, Mat mat2) {
        colorMomentHash_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void marrHildrethHash(Mat mat, Mat mat2, float f, float f2) {
        marrHildrethHash_0(mat.nativeObj, mat2.nativeObj, f, f2);
    }

    public static void marrHildrethHash(Mat mat, Mat mat2, float f) {
        marrHildrethHash_1(mat.nativeObj, mat2.nativeObj, f);
    }

    public static void marrHildrethHash(Mat mat, Mat mat2) {
        marrHildrethHash_2(mat.nativeObj, mat2.nativeObj);
    }

    public static void pHash(Mat mat, Mat mat2) {
        pHash_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void radialVarianceHash(Mat mat, Mat mat2, double d, int i) {
        radialVarianceHash_0(mat.nativeObj, mat2.nativeObj, d, i);
    }

    public static void radialVarianceHash(Mat mat, Mat mat2, double d) {
        radialVarianceHash_1(mat.nativeObj, mat2.nativeObj, d);
    }

    public static void radialVarianceHash(Mat mat, Mat mat2) {
        radialVarianceHash_2(mat.nativeObj, mat2.nativeObj);
    }
}
