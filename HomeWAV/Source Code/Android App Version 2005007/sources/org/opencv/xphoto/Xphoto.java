package org.opencv.xphoto;

import org.opencv.core.Mat;

public class Xphoto {
    public static final int BM3D_STEP1 = 1;
    public static final int BM3D_STEP2 = 2;
    public static final int BM3D_STEPALL = 0;
    public static final int HAAR = 0;
    public static final int INPAINT_FSR_BEST = 1;
    public static final int INPAINT_FSR_FAST = 2;
    public static final int INPAINT_SHIFTMAP = 0;

    private static native void applyChannelGains_0(long j, long j2, float f, float f2, float f3);

    private static native void bm3dDenoising_0(long j, long j2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    private static native void bm3dDenoising_1(long j, long j2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8);

    private static native void bm3dDenoising_10(long j, long j2, float f);

    private static native void bm3dDenoising_11(long j, long j2);

    private static native void bm3dDenoising_12(long j, long j2, long j3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    private static native void bm3dDenoising_13(long j, long j2, long j3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8);

    private static native void bm3dDenoising_14(long j, long j2, long j3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7);

    private static native void bm3dDenoising_15(long j, long j2, long j3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2);

    private static native void bm3dDenoising_16(long j, long j2, long j3, float f, int i, int i2, int i3, int i4, int i5, int i6);

    private static native void bm3dDenoising_17(long j, long j2, long j3, float f, int i, int i2, int i3, int i4, int i5);

    private static native void bm3dDenoising_18(long j, long j2, long j3, float f, int i, int i2, int i3, int i4);

    private static native void bm3dDenoising_19(long j, long j2, long j3, float f, int i, int i2, int i3);

    private static native void bm3dDenoising_2(long j, long j2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7);

    private static native void bm3dDenoising_20(long j, long j2, long j3, float f, int i, int i2);

    private static native void bm3dDenoising_21(long j, long j2, long j3, float f, int i);

    private static native void bm3dDenoising_22(long j, long j2, long j3, float f);

    private static native void bm3dDenoising_23(long j, long j2, long j3);

    private static native void bm3dDenoising_3(long j, long j2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2);

    private static native void bm3dDenoising_4(long j, long j2, float f, int i, int i2, int i3, int i4, int i5, int i6);

    private static native void bm3dDenoising_5(long j, long j2, float f, int i, int i2, int i3, int i4, int i5);

    private static native void bm3dDenoising_6(long j, long j2, float f, int i, int i2, int i3, int i4);

    private static native void bm3dDenoising_7(long j, long j2, float f, int i, int i2, int i3);

    private static native void bm3dDenoising_8(long j, long j2, float f, int i, int i2);

    private static native void bm3dDenoising_9(long j, long j2, float f, int i);

    private static native long createGrayworldWB_0();

    private static native long createLearningBasedWB_0(String str);

    private static native long createLearningBasedWB_1();

    private static native long createSimpleWB_0();

    private static native long createTonemapDurand_0(float f, float f2, float f3, float f4, float f5);

    private static native long createTonemapDurand_1(float f, float f2, float f3, float f4);

    private static native long createTonemapDurand_2(float f, float f2, float f3);

    private static native long createTonemapDurand_3(float f, float f2);

    private static native long createTonemapDurand_4(float f);

    private static native long createTonemapDurand_5();

    private static native void dctDenoising_0(long j, long j2, double d, int i);

    private static native void dctDenoising_1(long j, long j2, double d);

    private static native void inpaint_0(long j, long j2, long j3, int i);

    private static native void oilPainting_0(long j, long j2, int i, int i2, int i3);

    private static native void oilPainting_1(long j, long j2, int i, int i2);

    public static GrayworldWB createGrayworldWB() {
        return GrayworldWB.__fromPtr__(createGrayworldWB_0());
    }

    public static LearningBasedWB createLearningBasedWB(String str) {
        return LearningBasedWB.__fromPtr__(createLearningBasedWB_0(str));
    }

    public static LearningBasedWB createLearningBasedWB() {
        return LearningBasedWB.__fromPtr__(createLearningBasedWB_1());
    }

    public static SimpleWB createSimpleWB() {
        return SimpleWB.__fromPtr__(createSimpleWB_0());
    }

    public static TonemapDurand createTonemapDurand(float f, float f2, float f3, float f4, float f5) {
        return TonemapDurand.__fromPtr__(createTonemapDurand_0(f, f2, f3, f4, f5));
    }

    public static TonemapDurand createTonemapDurand(float f, float f2, float f3, float f4) {
        return TonemapDurand.__fromPtr__(createTonemapDurand_1(f, f2, f3, f4));
    }

    public static TonemapDurand createTonemapDurand(float f, float f2, float f3) {
        return TonemapDurand.__fromPtr__(createTonemapDurand_2(f, f2, f3));
    }

    public static TonemapDurand createTonemapDurand(float f, float f2) {
        return TonemapDurand.__fromPtr__(createTonemapDurand_3(f, f2));
    }

    public static TonemapDurand createTonemapDurand(float f) {
        return TonemapDurand.__fromPtr__(createTonemapDurand_4(f));
    }

    public static TonemapDurand createTonemapDurand() {
        return TonemapDurand.__fromPtr__(createTonemapDurand_5());
    }

    public static void applyChannelGains(Mat mat, Mat mat2, float f, float f2, float f3) {
        applyChannelGains_0(mat.nativeObj, mat2.nativeObj, f, f2, f3);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9) {
        bm3dDenoising_0(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4, i5, i6, f2, i7, i8, i9);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8) {
        bm3dDenoising_1(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4, i5, i6, f2, i7, i8);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7) {
        bm3dDenoising_2(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4, i5, i6, f2, i7);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2) {
        bm3dDenoising_3(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4, i5, i6, f2);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4, int i5, int i6) {
        bm3dDenoising_4(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4, i5, i6);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4, int i5) {
        bm3dDenoising_5(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4, i5);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3, int i4) {
        bm3dDenoising_6(mat.nativeObj, mat2.nativeObj, f, i, i2, i3, i4);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2, int i3) {
        bm3dDenoising_7(mat.nativeObj, mat2.nativeObj, f, i, i2, i3);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i, int i2) {
        bm3dDenoising_8(mat.nativeObj, mat2.nativeObj, f, i, i2);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f, int i) {
        bm3dDenoising_9(mat.nativeObj, mat2.nativeObj, f, i);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, float f) {
        bm3dDenoising_10(mat.nativeObj, mat2.nativeObj, f);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2) {
        bm3dDenoising_11(mat.nativeObj, mat2.nativeObj);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9) {
        bm3dDenoising_12(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4, i5, i6, f2, i7, i8, i9);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8) {
        bm3dDenoising_13(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4, i5, i6, f2, i7, i8);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7) {
        bm3dDenoising_14(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4, i5, i6, f2, i7);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2) {
        bm3dDenoising_15(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4, i5, i6, f2);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4, int i5, int i6) {
        bm3dDenoising_16(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4, i5, i6);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4, int i5) {
        bm3dDenoising_17(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4, i5);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3, int i4) {
        bm3dDenoising_18(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3, i4);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2, int i3) {
        bm3dDenoising_19(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2, i3);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i, int i2) {
        bm3dDenoising_20(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i, i2);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f, int i) {
        bm3dDenoising_21(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, i);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3, float f) {
        bm3dDenoising_22(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f);
    }

    public static void bm3dDenoising(Mat mat, Mat mat2, Mat mat3) {
        bm3dDenoising_23(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void dctDenoising(Mat mat, Mat mat2, double d, int i) {
        dctDenoising_0(mat.nativeObj, mat2.nativeObj, d, i);
    }

    public static void dctDenoising(Mat mat, Mat mat2, double d) {
        dctDenoising_1(mat.nativeObj, mat2.nativeObj, d);
    }

    public static void inpaint(Mat mat, Mat mat2, Mat mat3, int i) {
        inpaint_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i);
    }

    public static void oilPainting(Mat mat, Mat mat2, int i, int i2, int i3) {
        oilPainting_0(mat.nativeObj, mat2.nativeObj, i, i2, i3);
    }

    public static void oilPainting(Mat mat, Mat mat2, int i, int i2) {
        oilPainting_1(mat.nativeObj, mat2.nativeObj, i, i2);
    }
}
