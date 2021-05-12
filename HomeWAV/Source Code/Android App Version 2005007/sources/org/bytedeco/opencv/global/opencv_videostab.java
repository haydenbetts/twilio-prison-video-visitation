package org.bytedeco.opencv.global;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_videostab.RansacParams;

public class opencv_videostab extends org.bytedeco.opencv.presets.opencv_videostab {
    public static final int MM_AFFINE = 5;
    public static final int MM_HOMOGRAPHY = 6;
    public static final int MM_RIGID = 3;
    public static final int MM_ROTATION = 2;
    public static final int MM_SIMILARITY = 4;
    public static final int MM_TRANSLATION = 0;
    public static final int MM_TRANSLATION_AND_SCALE = 1;
    public static final int MM_UNKNOWN = 7;

    @Namespace("cv::videostab")
    public static native float calcBlurriness(@ByRef @Const Mat mat);

    @Namespace("cv::videostab")
    public static native void calcFlowMask(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, float f, @ByRef @Const Mat mat4, @ByRef @Const Mat mat5, @ByRef Mat mat6);

    @Namespace("cv::videostab")
    public static native void completeFrameAccordingToFlow(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, @ByRef @Const Mat mat4, @ByRef @Const Mat mat5, float f, @ByRef Mat mat6, @ByRef Mat mat7);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat ensureInclusionConstraint(@ByRef @Const Mat mat, @ByVal Size size, float f);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, FloatBuffer floatBuffer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, FloatPointer floatPointer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, float[] fArr);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal Mat mat, @ByVal Mat mat2, int i, FloatBuffer floatBuffer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal Mat mat, @ByVal Mat mat2, int i, FloatPointer floatPointer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal Mat mat, @ByVal Mat mat2, int i, float[] fArr);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal UMat uMat, @ByVal UMat uMat2, int i, FloatBuffer floatBuffer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal UMat uMat, @ByVal UMat uMat2, int i, FloatPointer floatPointer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionLeastSquares(@ByVal UMat uMat, @ByVal UMat uMat2, int i, float[] fArr);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, FloatBuffer floatBuffer, IntBuffer intBuffer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, FloatPointer floatPointer, IntPointer intPointer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, float[] fArr, int[] iArr);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, FloatBuffer floatBuffer, IntBuffer intBuffer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, FloatPointer floatPointer, IntPointer intPointer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, float[] fArr, int[] iArr);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, FloatBuffer floatBuffer, IntBuffer intBuffer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, FloatPointer floatPointer, IntPointer intPointer);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat estimateGlobalMotionRansac(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByRef(nullValue = "cv::videostab::RansacParams::default2dMotion(cv::videostab::MM_AFFINE)") @Const RansacParams ransacParams, float[] fArr, int[] iArr);

    @Namespace("cv::videostab")
    public static native float estimateOptimalTrimRatio(@ByRef @Const Mat mat, @ByVal Size size);

    @Namespace("cv::videostab")
    @ByVal
    public static native Mat getMotion(int i, int i2, @ByRef @Const MatVector matVector);

    static {
        Loader.load();
    }
}
