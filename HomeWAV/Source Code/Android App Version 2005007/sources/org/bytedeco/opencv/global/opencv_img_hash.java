package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;

public class opencv_img_hash extends org.bytedeco.opencv.presets.opencv_img_hash {
    public static final int BLOCK_MEAN_HASH_MODE_0 = 0;
    public static final int BLOCK_MEAN_HASH_MODE_1 = 1;

    @Namespace("cv::img_hash")
    public static native void averageHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::img_hash")
    public static native void averageHash(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::img_hash")
    public static native void averageHash(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::img_hash")
    public static native void blockMeanHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::img_hash")
    public static native void blockMeanHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::img_hash")
    public static native void blockMeanHash(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::img_hash")
    public static native void blockMeanHash(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::img_hash")
    public static native void blockMeanHash(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::img_hash")
    public static native void blockMeanHash(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::img_hash")
    public static native void colorMomentHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::img_hash")
    public static native void colorMomentHash(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::img_hash")
    public static native void colorMomentHash(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::img_hash")
    public static native void marrHildrethHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::img_hash")
    public static native void marrHildrethHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2);

    @Namespace("cv::img_hash")
    public static native void marrHildrethHash(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::img_hash")
    public static native void marrHildrethHash(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2);

    @Namespace("cv::img_hash")
    public static native void marrHildrethHash(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::img_hash")
    public static native void marrHildrethHash(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2);

    @Namespace("cv::img_hash")
    public static native void pHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::img_hash")
    public static native void pHash(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::img_hash")
    public static native void pHash(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::img_hash")
    public static native void radialVarianceHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::img_hash")
    public static native void radialVarianceHash(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, int i);

    @Namespace("cv::img_hash")
    public static native void radialVarianceHash(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::img_hash")
    public static native void radialVarianceHash(@ByVal Mat mat, @ByVal Mat mat2, double d, int i);

    @Namespace("cv::img_hash")
    public static native void radialVarianceHash(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::img_hash")
    public static native void radialVarianceHash(@ByVal UMat uMat, @ByVal UMat uMat2, double d, int i);

    static {
        Loader.load();
    }
}
