package org.bytedeco.opencv.global;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_photo.AlignMTB;
import org.bytedeco.opencv.opencv_photo.CalibrateDebevec;
import org.bytedeco.opencv.opencv_photo.CalibrateRobertson;
import org.bytedeco.opencv.opencv_photo.MergeDebevec;
import org.bytedeco.opencv.opencv_photo.MergeMertens;
import org.bytedeco.opencv.opencv_photo.MergeRobertson;
import org.bytedeco.opencv.opencv_photo.Tonemap;
import org.bytedeco.opencv.opencv_photo.TonemapDrago;
import org.bytedeco.opencv.opencv_photo.TonemapMantiuk;
import org.bytedeco.opencv.opencv_photo.TonemapReinhard;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_photo extends org.bytedeco.opencv.presets.opencv_photo {
    public static final int INPAINT_NS = 0;
    public static final int INPAINT_TELEA = 1;
    public static final int LDR_SIZE = 256;
    public static final int MIXED_CLONE = 2;
    public static final int MONOCHROME_TRANSFER = 3;
    public static final int NORMAL_CLONE = 1;
    public static final int NORMCONV_FILTER = 2;
    public static final int RECURS_FILTER = 1;

    @Namespace("cv")
    public static native void colorChange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void colorChange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, float f2, float f3);

    @Namespace("cv")
    public static native void colorChange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void colorChange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, float f2, float f3);

    @Namespace("cv")
    public static native void colorChange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void colorChange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, float f2, float f3);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native AlignMTB createAlignMTB();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native AlignMTB createAlignMTB(int i, int i2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native CalibrateDebevec createCalibrateDebevec();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native CalibrateDebevec createCalibrateDebevec(int i, float f, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native CalibrateRobertson createCalibrateRobertson();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native CalibrateRobertson createCalibrateRobertson(int i, float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native MergeDebevec createMergeDebevec();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native MergeMertens createMergeMertens();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native MergeMertens createMergeMertens(float f, float f2, float f3);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native MergeRobertson createMergeRobertson();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Tonemap createTonemap();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Tonemap createTonemap(float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native TonemapDrago createTonemapDrago();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native TonemapDrago createTonemapDrago(float f, float f2, float f3);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native TonemapMantiuk createTonemapMantiuk();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native TonemapMantiuk createTonemapMantiuk(float f, float f2, float f3);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native TonemapReinhard createTonemapReinhard();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native TonemapReinhard createTonemapReinhard(float f, float f2, float f3, float f4);

    @Namespace("cv")
    public static native void decolor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void decolor(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void decolor(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void denoise_TVL1(@ByRef @Const MatVector matVector, @ByRef Mat mat);

    @Namespace("cv")
    public static native void denoise_TVL1(@ByRef @Const MatVector matVector, @ByRef Mat mat, double d, int i);

    @Namespace("cv")
    public static native void detailEnhance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void detailEnhance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2);

    @Namespace("cv")
    public static native void detailEnhance(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void detailEnhance(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2);

    @Namespace("cv")
    public static native void detailEnhance(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void detailEnhance(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2);

    @Namespace("cv")
    public static native void edgePreservingFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void edgePreservingFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, float f, float f2);

    @Namespace("cv")
    public static native void edgePreservingFilter(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void edgePreservingFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, float f, float f2);

    @Namespace("cv")
    public static native void edgePreservingFilter(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void edgePreservingFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, float f, float f2);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, int i, int i2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector FloatBuffer floatBuffer, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector FloatPointer floatPointer, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector float[] fArr, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, float f);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, float f, int i, int i2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, float f, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, @StdVector FloatBuffer floatBuffer, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, @StdVector FloatPointer floatPointer, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal Mat mat, @ByVal Mat mat2, @StdVector float[] fArr, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, float f);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, float f, int i, int i2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, float f, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector FloatBuffer floatBuffer, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector FloatPointer floatPointer, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void fastNlMeansDenoising(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector float[] fArr, int i, int i2, int i3);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColored(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoisingColored(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColored(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoisingColored(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColored(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoisingColored(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColored(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoisingColored(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColored(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoisingColored(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColored(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void fastNlMeansDenoisingColored(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2, int i, int i2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal MatVector matVector, @ByVal Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal MatVector matVector, @ByVal Mat mat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal MatVector matVector, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal MatVector matVector, @ByVal UMat uMat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingColoredMulti(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i, int i2, float f, float f2, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i, int i2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i, int i2, @StdVector float[] fArr, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i, int i2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i, int i2, @StdVector float[] fArr, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i, int i2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i, int i2, @StdVector float[] fArr, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i, int i2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i, int i2, @StdVector FloatPointer floatPointer, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal Mat mat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal Mat mat, int i, int i2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal Mat mat, int i, int i2, @StdVector FloatPointer floatPointer, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal UMat uMat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal UMat uMat, int i, int i2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal MatVector matVector, @ByVal UMat uMat, int i, int i2, @StdVector FloatPointer floatPointer, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i, int i2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i, int i2, @StdVector FloatBuffer floatBuffer, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i, int i2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i, int i2, @StdVector FloatBuffer floatBuffer, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i, int i2);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i, int i2, float f, int i3, int i4);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i, int i2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void fastNlMeansDenoisingMulti(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i, int i2, @StdVector FloatBuffer floatBuffer, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void illuminationChange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void illuminationChange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, float f2);

    @Namespace("cv")
    public static native void illuminationChange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void illuminationChange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, float f2);

    @Namespace("cv")
    public static native void illuminationChange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void illuminationChange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, float f2);

    @Namespace("cv")
    public static native void inpaint(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i);

    @Namespace("cv")
    public static native void inpaint(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i);

    @Namespace("cv")
    public static native void inpaint(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i);

    @Namespace("cv::cuda")
    public static native void nonLocalMeans(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f);

    @Namespace("cv::cuda")
    public static native void nonLocalMeans(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void nonLocalMeans(@ByVal Mat mat, @ByVal Mat mat2, float f);

    @Namespace("cv::cuda")
    public static native void nonLocalMeans(@ByVal Mat mat, @ByVal Mat mat2, float f, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void nonLocalMeans(@ByVal UMat uMat, @ByVal UMat uMat2, float f);

    @Namespace("cv::cuda")
    public static native void nonLocalMeans(@ByVal UMat uMat, @ByVal UMat uMat2, float f, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv")
    public static native void pencilSketch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void pencilSketch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, float f2, float f3);

    @Namespace("cv")
    public static native void pencilSketch(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void pencilSketch(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, float f2, float f3);

    @Namespace("cv")
    public static native void pencilSketch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void pencilSketch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, float f2, float f3);

    @Namespace("cv")
    public static native void seamlessClone(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Point point, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void seamlessClone(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Point point, @ByVal Mat mat4, int i);

    @Namespace("cv")
    public static native void seamlessClone(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Point point, @ByVal UMat uMat4, int i);

    @Namespace("cv")
    public static native void stylization(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void stylization(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, float f2);

    @Namespace("cv")
    public static native void stylization(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void stylization(@ByVal Mat mat, @ByVal Mat mat2, float f, float f2);

    @Namespace("cv")
    public static native void stylization(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void stylization(@ByVal UMat uMat, @ByVal UMat uMat2, float f, float f2);

    @Namespace("cv")
    public static native void textureFlattening(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void textureFlattening(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, float f2, int i);

    @Namespace("cv")
    public static native void textureFlattening(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void textureFlattening(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, float f2, int i);

    @Namespace("cv")
    public static native void textureFlattening(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void textureFlattening(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, float f2, int i);

    static {
        Loader.load();
    }
}
