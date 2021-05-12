package org.bytedeco.opencv.global;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_cudaimgproc.CannyEdgeDetector;
import org.bytedeco.opencv.opencv_cudaimgproc.CornernessCriteria;
import org.bytedeco.opencv.opencv_cudaimgproc.CornersDetector;
import org.bytedeco.opencv.opencv_cudaimgproc.CudaCLAHE;
import org.bytedeco.opencv.opencv_cudaimgproc.HoughCirclesDetector;
import org.bytedeco.opencv.opencv_cudaimgproc.HoughLinesDetector;
import org.bytedeco.opencv.opencv_cudaimgproc.HoughSegmentDetector;
import org.bytedeco.opencv.opencv_cudaimgproc.TemplateMatching;
import org.bytedeco.opencv.opencv_imgproc.GeneralizedHoughBallard;
import org.bytedeco.opencv.opencv_imgproc.GeneralizedHoughGuil;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_cudaimgproc extends org.bytedeco.opencv.presets.opencv_cudaimgproc {
    public static final int ALPHA_ATOP = 3;
    public static final int ALPHA_ATOP_PREMUL = 9;
    public static final int ALPHA_IN = 1;
    public static final int ALPHA_IN_PREMUL = 7;
    public static final int ALPHA_OUT = 2;
    public static final int ALPHA_OUT_PREMUL = 8;
    public static final int ALPHA_OVER = 0;
    public static final int ALPHA_OVER_PREMUL = 6;
    public static final int ALPHA_PLUS = 5;
    public static final int ALPHA_PLUS_PREMUL = 11;
    public static final int ALPHA_PREMUL = 12;
    public static final int ALPHA_XOR = 4;
    public static final int ALPHA_XOR_PREMUL = 10;
    public static final int COLOR_BayerBG2BGR_MHT = 256;
    public static final int COLOR_BayerBG2GRAY_MHT = 260;
    public static final int COLOR_BayerBG2RGB_MHT = 258;
    public static final int COLOR_BayerGB2BGR_MHT = 257;
    public static final int COLOR_BayerGB2GRAY_MHT = 261;
    public static final int COLOR_BayerGB2RGB_MHT = 259;
    public static final int COLOR_BayerGR2BGR_MHT = 259;
    public static final int COLOR_BayerGR2GRAY_MHT = 263;
    public static final int COLOR_BayerGR2RGB_MHT = 257;
    public static final int COLOR_BayerRG2BGR_MHT = 258;
    public static final int COLOR_BayerRG2GRAY_MHT = 262;
    public static final int COLOR_BayerRG2RGB_MHT = 256;

    @Namespace("cv::cuda")
    public static native void alphaComp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv::cuda")
    public static native void alphaComp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void alphaComp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv::cuda")
    public static native void alphaComp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void alphaComp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv::cuda")
    public static native void alphaComp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bilateralFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, float f, float f2);

    @Namespace("cv::cuda")
    public static native void bilateralFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, float f, float f2, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bilateralFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, float f, float f2);

    @Namespace("cv::cuda")
    public static native void bilateralFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, float f, float f2, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bilateralFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, float f, float f2);

    @Namespace("cv::cuda")
    public static native void bilateralFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, float f, float f2, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void blendLinear(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv::cuda")
    public static native void blendLinear(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void blendLinear(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv::cuda")
    public static native void blendLinear(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void blendLinear(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::cuda")
    public static native void blendLinear(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void calcHist(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CudaCLAHE createCLAHE();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CudaCLAHE createCLAHE(double d, @ByVal(nullValue = "cv::Size(8, 8)") Size size);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CannyEdgeDetector createCannyEdgeDetector(double d, double d2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CannyEdgeDetector createCannyEdgeDetector(double d, double d2, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native GeneralizedHoughBallard createGeneralizedHoughBallard();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native GeneralizedHoughGuil createGeneralizedHoughGuil();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CornersDetector createGoodFeaturesToTrackDetector(int i);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CornersDetector createGoodFeaturesToTrackDetector(int i, int i2, double d, double d2, int i3, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CornernessCriteria createHarrisCorner(int i, int i2, int i3, double d);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CornernessCriteria createHarrisCorner(int i, int i2, int i3, double d, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native HoughCirclesDetector createHoughCirclesDetector(float f, float f2, int i, int i2, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native HoughCirclesDetector createHoughCirclesDetector(float f, float f2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native HoughLinesDetector createHoughLinesDetector(float f, float f2, int i);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native HoughLinesDetector createHoughLinesDetector(float f, float f2, int i, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native HoughSegmentDetector createHoughSegmentDetector(float f, float f2, int i, int i2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native HoughSegmentDetector createHoughSegmentDetector(float f, float f2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CornernessCriteria createMinEigenValCorner(int i, int i2, int i3);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native CornernessCriteria createMinEigenValCorner(int i, int i2, int i3, int i4);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native TemplateMatching createTemplateMatching(int i, int i2);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native TemplateMatching createTemplateMatching(int i, int i2, @ByVal(nullValue = "cv::Size()") Size size);

    @Namespace("cv::cuda")
    public static native void cvtColor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::cuda")
    public static native void cvtColor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void cvtColor(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::cuda")
    public static native void cvtColor(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void cvtColor(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::cuda")
    public static native void cvtColor(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void demosaicing(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::cuda")
    public static native void demosaicing(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void demosaicing(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::cuda")
    public static native void demosaicing(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void demosaicing(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::cuda")
    public static native void demosaicing(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void equalizeHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void equalizeHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void equalizeHist(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void equalizeHist(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void equalizeHist(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void equalizeHist(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void evenLevels(@ByVal GpuMat gpuMat, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void evenLevels(@ByVal GpuMat gpuMat, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void evenLevels(@ByVal Mat mat, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void evenLevels(@ByVal Mat mat, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void evenLevels(@ByVal UMat uMat, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void evenLevels(@ByVal UMat uMat, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void gammaCorrection(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void gammaCorrection(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void gammaCorrection(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void gammaCorrection(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void gammaCorrection(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void gammaCorrection(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, GpuMat gpuMat2, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, GpuMat gpuMat2, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, GpuMat gpuMat2, IntPointer intPointer, IntPointer intPointer2, IntPointer intPointer3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, GpuMat gpuMat2, IntPointer intPointer, IntPointer intPointer2, IntPointer intPointer3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, GpuMat gpuMat2, int[] iArr, int[] iArr2, int[] iArr3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal GpuMat gpuMat, GpuMat gpuMat2, int[] iArr, int[] iArr2, int[] iArr3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, GpuMat gpuMat, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, GpuMat gpuMat, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, GpuMat gpuMat, IntPointer intPointer, IntPointer intPointer2, IntPointer intPointer3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, GpuMat gpuMat, IntPointer intPointer, IntPointer intPointer2, IntPointer intPointer3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, GpuMat gpuMat, int[] iArr, int[] iArr2, int[] iArr3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, GpuMat gpuMat, int[] iArr, int[] iArr2, int[] iArr3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, GpuMat gpuMat, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, GpuMat gpuMat, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, GpuMat gpuMat, IntPointer intPointer, IntPointer intPointer2, IntPointer intPointer3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, GpuMat gpuMat, IntPointer intPointer, IntPointer intPointer2, IntPointer intPointer3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, GpuMat gpuMat, int[] iArr, int[] iArr2, int[] iArr3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, GpuMat gpuMat, int[] iArr, int[] iArr2, int[] iArr3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void histEven(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal Mat mat, GpuMat gpuMat, @Const GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal Mat mat, GpuMat gpuMat, @Const GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal UMat uMat, GpuMat gpuMat, @Const GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal UMat uMat, GpuMat gpuMat, @Const GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void histRange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftFiltering(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void meanShiftFiltering(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftFiltering(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void meanShiftFiltering(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftFiltering(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void meanShiftFiltering(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftProc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2);

    @Namespace("cv::cuda")
    public static native void meanShiftProc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftProc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2);

    @Namespace("cv::cuda")
    public static native void meanShiftProc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftProc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2);

    @Namespace("cv::cuda")
    public static native void meanShiftProc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftSegmentation(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void meanShiftSegmentation(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftSegmentation(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void meanShiftSegmentation(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanShiftSegmentation(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void meanShiftSegmentation(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER + cv::TermCriteria::EPS, 5, 1)") TermCriteria termCriteria, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal GpuMat gpuMat, @Const IntBuffer intBuffer);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal GpuMat gpuMat, @Const IntBuffer intBuffer, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal GpuMat gpuMat, @Const IntPointer intPointer);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal GpuMat gpuMat, @Const IntPointer intPointer, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal GpuMat gpuMat, @Const int[] iArr);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal GpuMat gpuMat, @Const int[] iArr, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal Mat mat, @Const IntBuffer intBuffer);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal Mat mat, @Const IntBuffer intBuffer, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal Mat mat, @Const IntPointer intPointer);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal Mat mat, @Const IntPointer intPointer, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal Mat mat, @Const int[] iArr);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal Mat mat, @Const int[] iArr, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal UMat uMat, @Const IntBuffer intBuffer);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal UMat uMat, @Const IntBuffer intBuffer, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal UMat uMat, @Const IntPointer intPointer);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal UMat uMat, @Const IntPointer intPointer, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal UMat uMat, @Const int[] iArr);

    @Namespace("cv::cuda")
    public static native void swapChannels(@ByVal UMat uMat, @Const int[] iArr, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }
}
