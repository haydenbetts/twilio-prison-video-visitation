package org.opencv.bgsegm;

import org.opencv.core.Mat;

public class Bgsegm {
    public static final int LSBP_CAMERA_MOTION_COMPENSATION_LK = 1;
    public static final int LSBP_CAMERA_MOTION_COMPENSATION_NONE = 0;

    private static native long createBackgroundSubtractorCNT_0(int i, boolean z, int i2, boolean z2);

    private static native long createBackgroundSubtractorCNT_1(int i, boolean z, int i2);

    private static native long createBackgroundSubtractorCNT_2(int i, boolean z);

    private static native long createBackgroundSubtractorCNT_3(int i);

    private static native long createBackgroundSubtractorCNT_4();

    private static native long createBackgroundSubtractorGMG_0(int i, double d);

    private static native long createBackgroundSubtractorGMG_1(int i);

    private static native long createBackgroundSubtractorGMG_2();

    private static native long createBackgroundSubtractorGSOC_0(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, float f7, float f8);

    private static native long createBackgroundSubtractorGSOC_1(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, float f7);

    private static native long createBackgroundSubtractorGSOC_10(int i);

    private static native long createBackgroundSubtractorGSOC_11();

    private static native long createBackgroundSubtractorGSOC_2(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6);

    private static native long createBackgroundSubtractorGSOC_3(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5);

    private static native long createBackgroundSubtractorGSOC_4(int i, int i2, float f, float f2, int i3, float f3, float f4);

    private static native long createBackgroundSubtractorGSOC_5(int i, int i2, float f, float f2, int i3, float f3);

    private static native long createBackgroundSubtractorGSOC_6(int i, int i2, float f, float f2, int i3);

    private static native long createBackgroundSubtractorGSOC_7(int i, int i2, float f, float f2);

    private static native long createBackgroundSubtractorGSOC_8(int i, int i2, float f);

    private static native long createBackgroundSubtractorGSOC_9(int i, int i2);

    private static native long createBackgroundSubtractorLSBP_0(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i4, int i5);

    private static native long createBackgroundSubtractorLSBP_1(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i4);

    private static native long createBackgroundSubtractorLSBP_10(int i, int i2, int i3);

    private static native long createBackgroundSubtractorLSBP_11(int i, int i2);

    private static native long createBackgroundSubtractorLSBP_12(int i);

    private static native long createBackgroundSubtractorLSBP_13();

    private static native long createBackgroundSubtractorLSBP_2(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8);

    private static native long createBackgroundSubtractorLSBP_3(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    private static native long createBackgroundSubtractorLSBP_4(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6);

    private static native long createBackgroundSubtractorLSBP_5(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5);

    private static native long createBackgroundSubtractorLSBP_6(int i, int i2, int i3, float f, float f2, float f3, float f4);

    private static native long createBackgroundSubtractorLSBP_7(int i, int i2, int i3, float f, float f2, float f3);

    private static native long createBackgroundSubtractorLSBP_8(int i, int i2, int i3, float f, float f2);

    private static native long createBackgroundSubtractorLSBP_9(int i, int i2, int i3, float f);

    private static native long createBackgroundSubtractorMOG_0(int i, int i2, double d, double d2);

    private static native long createBackgroundSubtractorMOG_1(int i, int i2, double d);

    private static native long createBackgroundSubtractorMOG_2(int i, int i2);

    private static native long createBackgroundSubtractorMOG_3(int i);

    private static native long createBackgroundSubtractorMOG_4();

    private static native long createSyntheticSequenceGenerator_0(long j, long j2, double d, double d2, double d3, double d4);

    private static native long createSyntheticSequenceGenerator_1(long j, long j2, double d, double d2, double d3);

    private static native long createSyntheticSequenceGenerator_2(long j, long j2, double d, double d2);

    private static native long createSyntheticSequenceGenerator_3(long j, long j2, double d);

    private static native long createSyntheticSequenceGenerator_4(long j, long j2);

    public static BackgroundSubtractorCNT createBackgroundSubtractorCNT(int i, boolean z, int i2, boolean z2) {
        return BackgroundSubtractorCNT.__fromPtr__(createBackgroundSubtractorCNT_0(i, z, i2, z2));
    }

    public static BackgroundSubtractorCNT createBackgroundSubtractorCNT(int i, boolean z, int i2) {
        return BackgroundSubtractorCNT.__fromPtr__(createBackgroundSubtractorCNT_1(i, z, i2));
    }

    public static BackgroundSubtractorCNT createBackgroundSubtractorCNT(int i, boolean z) {
        return BackgroundSubtractorCNT.__fromPtr__(createBackgroundSubtractorCNT_2(i, z));
    }

    public static BackgroundSubtractorCNT createBackgroundSubtractorCNT(int i) {
        return BackgroundSubtractorCNT.__fromPtr__(createBackgroundSubtractorCNT_3(i));
    }

    public static BackgroundSubtractorCNT createBackgroundSubtractorCNT() {
        return BackgroundSubtractorCNT.__fromPtr__(createBackgroundSubtractorCNT_4());
    }

    public static BackgroundSubtractorGMG createBackgroundSubtractorGMG(int i, double d) {
        return BackgroundSubtractorGMG.__fromPtr__(createBackgroundSubtractorGMG_0(i, d));
    }

    public static BackgroundSubtractorGMG createBackgroundSubtractorGMG(int i) {
        return BackgroundSubtractorGMG.__fromPtr__(createBackgroundSubtractorGMG_1(i));
    }

    public static BackgroundSubtractorGMG createBackgroundSubtractorGMG() {
        return BackgroundSubtractorGMG.__fromPtr__(createBackgroundSubtractorGMG_2());
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, float f7, float f8) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_0(i, i2, f, f2, i3, f3, f4, f5, f6, f7, f8));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, float f7) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_1(i, i2, f, f2, i3, f3, f4, f5, f6, f7));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_2(i, i2, f, f2, i3, f3, f4, f5, f6));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_3(i, i2, f, f2, i3, f3, f4, f5));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3, float f4) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_4(i, i2, f, f2, i3, f3, f4));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3, float f3) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_5(i, i2, f, f2, i3, f3));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2, int i3) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_6(i, i2, f, f2, i3));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f, float f2) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_7(i, i2, f, f2));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2, float f) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_8(i, i2, f));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i, int i2) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_9(i, i2));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC(int i) {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_10(i));
    }

    public static BackgroundSubtractorGSOC createBackgroundSubtractorGSOC() {
        return BackgroundSubtractorGSOC.__fromPtr__(createBackgroundSubtractorGSOC_11());
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i4, int i5) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_0(i, i2, i3, f, f2, f3, f4, f5, f6, f7, f8, i4, i5));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i4) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_1(i, i2, i3, f, f2, f3, f4, f5, f6, f7, f8, i4));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_2(i, i2, i3, f, f2, f3, f4, f5, f6, f7, f8));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_3(i, i2, i3, f, f2, f3, f4, f5, f6, f7));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, float f6) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_4(i, i2, i3, f, f2, f3, f4, f5, f6));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_5(i, i2, i3, f, f2, f3, f4, f5));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3, float f4) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_6(i, i2, i3, f, f2, f3, f4));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2, float f3) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_7(i, i2, i3, f, f2, f3));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f, float f2) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_8(i, i2, i3, f, f2));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3, float f) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_9(i, i2, i3, f));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2, int i3) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_10(i, i2, i3));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i, int i2) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_11(i, i2));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP(int i) {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_12(i));
    }

    public static BackgroundSubtractorLSBP createBackgroundSubtractorLSBP() {
        return BackgroundSubtractorLSBP.__fromPtr__(createBackgroundSubtractorLSBP_13());
    }

    public static BackgroundSubtractorMOG createBackgroundSubtractorMOG(int i, int i2, double d, double d2) {
        return BackgroundSubtractorMOG.__fromPtr__(createBackgroundSubtractorMOG_0(i, i2, d, d2));
    }

    public static BackgroundSubtractorMOG createBackgroundSubtractorMOG(int i, int i2, double d) {
        return BackgroundSubtractorMOG.__fromPtr__(createBackgroundSubtractorMOG_1(i, i2, d));
    }

    public static BackgroundSubtractorMOG createBackgroundSubtractorMOG(int i, int i2) {
        return BackgroundSubtractorMOG.__fromPtr__(createBackgroundSubtractorMOG_2(i, i2));
    }

    public static BackgroundSubtractorMOG createBackgroundSubtractorMOG(int i) {
        return BackgroundSubtractorMOG.__fromPtr__(createBackgroundSubtractorMOG_3(i));
    }

    public static BackgroundSubtractorMOG createBackgroundSubtractorMOG() {
        return BackgroundSubtractorMOG.__fromPtr__(createBackgroundSubtractorMOG_4());
    }

    public static SyntheticSequenceGenerator createSyntheticSequenceGenerator(Mat mat, Mat mat2, double d, double d2, double d3, double d4) {
        return SyntheticSequenceGenerator.__fromPtr__(createSyntheticSequenceGenerator_0(mat.nativeObj, mat2.nativeObj, d, d2, d3, d4));
    }

    public static SyntheticSequenceGenerator createSyntheticSequenceGenerator(Mat mat, Mat mat2, double d, double d2, double d3) {
        return SyntheticSequenceGenerator.__fromPtr__(createSyntheticSequenceGenerator_1(mat.nativeObj, mat2.nativeObj, d, d2, d3));
    }

    public static SyntheticSequenceGenerator createSyntheticSequenceGenerator(Mat mat, Mat mat2, double d, double d2) {
        return SyntheticSequenceGenerator.__fromPtr__(createSyntheticSequenceGenerator_2(mat.nativeObj, mat2.nativeObj, d, d2));
    }

    public static SyntheticSequenceGenerator createSyntheticSequenceGenerator(Mat mat, Mat mat2, double d) {
        return SyntheticSequenceGenerator.__fromPtr__(createSyntheticSequenceGenerator_3(mat.nativeObj, mat2.nativeObj, d));
    }

    public static SyntheticSequenceGenerator createSyntheticSequenceGenerator(Mat mat, Mat mat2) {
        return SyntheticSequenceGenerator.__fromPtr__(createSyntheticSequenceGenerator_4(mat.nativeObj, mat2.nativeObj));
    }
}
