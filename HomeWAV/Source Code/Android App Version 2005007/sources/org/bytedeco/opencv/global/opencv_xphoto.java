package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_xphoto.GrayworldWB;
import org.bytedeco.opencv.opencv_xphoto.LearningBasedWB;
import org.bytedeco.opencv.opencv_xphoto.SimpleWB;
import org.bytedeco.opencv.opencv_xphoto.TonemapDurand;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_xphoto extends org.bytedeco.opencv.presets.opencv_xphoto {
    public static final int BM3D_STEP1 = 1;
    public static final int BM3D_STEP2 = 2;
    public static final int BM3D_STEPALL = 0;
    public static final int HAAR = 0;
    public static final int INPAINT_FSR_BEST = 1;
    public static final int INPAINT_FSR_FAST = 2;
    public static final int INPAINT_SHIFTMAP = 0;

    @Namespace("cv::xphoto")
    public static native void applyChannelGains(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2, float f3);

    @Namespace("cv::xphoto")
    public static native void applyChannelGains(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2, float f3);

    @Namespace("cv::xphoto")
    public static native void applyChannelGains(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2, float f3);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal Mat mat, @ByVal Mat mat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::xphoto")
    public static native void bm3dDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, int i9);

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native GrayworldWB createGrayworldWB();

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native LearningBasedWB createLearningBasedWB();

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native LearningBasedWB createLearningBasedWB(@opencv_core.Str String str);

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native LearningBasedWB createLearningBasedWB(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native SimpleWB createSimpleWB();

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native TonemapDurand createTonemapDurand();

    @Namespace("cv::xphoto")
    @opencv_core.Ptr
    public static native TonemapDurand createTonemapDurand(float f, float f2, float f3, float f4, float f5);

    @Namespace("cv::xphoto")
    public static native void dctDenoising(@ByRef @Const Mat mat, @ByRef Mat mat2, double d);

    @Namespace("cv::xphoto")
    public static native void dctDenoising(@ByRef @Const Mat mat, @ByRef Mat mat2, double d, int i);

    @Namespace("cv::xphoto")
    public static native void inpaint(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef Mat mat3, int i);

    @Namespace("cv::xphoto")
    public static native void oilPainting(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv::xphoto")
    public static native void oilPainting(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv::xphoto")
    public static native void oilPainting(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv::xphoto")
    public static native void oilPainting(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv::xphoto")
    public static native void oilPainting(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv::xphoto")
    public static native void oilPainting(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    static {
        Loader.load();
    }
}
