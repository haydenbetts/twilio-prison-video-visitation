package org.bytedeco.opencv.global;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.IntIntPairVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatSize;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.opencv_core.Rect2dVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.RotatedRect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_dnn.Net;
import org.bytedeco.opencv.opencv_dnn._Range;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_dnn extends org.bytedeco.opencv.presets.opencv_dnn {
    public static final int DNN_BACKEND_CUDA = 5;
    public static final int DNN_BACKEND_DEFAULT = 0;
    public static final int DNN_BACKEND_HALIDE = 1;
    public static final int DNN_BACKEND_INFERENCE_ENGINE = 2;
    public static final int DNN_BACKEND_OPENCV = 3;
    public static final int DNN_BACKEND_VKCOM = 4;
    public static final int DNN_TARGET_CPU = 0;
    public static final int DNN_TARGET_CUDA = 6;
    public static final int DNN_TARGET_CUDA_FP16 = 7;
    public static final int DNN_TARGET_FPGA = 5;
    public static final int DNN_TARGET_MYRIAD = 3;
    public static final int DNN_TARGET_OPENCL = 1;
    public static final int DNN_TARGET_OPENCL_FP16 = 2;
    public static final int DNN_TARGET_VULKAN = 4;
    public static final int OPENCV_DNN_API_VERSION = 20200609;

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const Rect2dVector rect2dVector, @StdVector FloatBuffer floatBuffer, float f, float f2, @StdVector IntBuffer intBuffer);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const Rect2dVector rect2dVector, @StdVector FloatBuffer floatBuffer, float f, float f2, @StdVector IntBuffer intBuffer, float f3, int i);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const Rect2dVector rect2dVector, @StdVector FloatPointer floatPointer, float f, float f2, @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const Rect2dVector rect2dVector, @StdVector FloatPointer floatPointer, float f, float f2, @StdVector IntPointer intPointer, float f3, int i);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const Rect2dVector rect2dVector, @StdVector float[] fArr, float f, float f2, @StdVector int[] iArr);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const Rect2dVector rect2dVector, @StdVector float[] fArr, float f, float f2, @StdVector int[] iArr, float f3, int i);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const RectVector rectVector, @StdVector FloatBuffer floatBuffer, float f, float f2, @StdVector IntBuffer intBuffer);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const RectVector rectVector, @StdVector FloatBuffer floatBuffer, float f, float f2, @StdVector IntBuffer intBuffer, float f3, int i);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const RectVector rectVector, @StdVector FloatPointer floatPointer, float f, float f2, @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const RectVector rectVector, @StdVector FloatPointer floatPointer, float f, float f2, @StdVector IntPointer intPointer, float f3, int i);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const RectVector rectVector, @StdVector float[] fArr, float f, float f2, @StdVector int[] iArr);

    @Namespace("cv::dnn")
    public static native void NMSBoxes(@ByRef @Const RectVector rectVector, @StdVector float[] fArr, float f, float f2, @StdVector int[] iArr, float f3, int i);

    @Namespace("cv::dnn")
    @Name({"NMSBoxes"})
    public static native void NMSBoxesRotated(@StdVector RotatedRect rotatedRect, @StdVector FloatBuffer floatBuffer, float f, float f2, @StdVector IntBuffer intBuffer);

    @Namespace("cv::dnn")
    @Name({"NMSBoxes"})
    public static native void NMSBoxesRotated(@StdVector RotatedRect rotatedRect, @StdVector FloatBuffer floatBuffer, float f, float f2, @StdVector IntBuffer intBuffer, float f3, int i);

    @Namespace("cv::dnn")
    @Name({"NMSBoxes"})
    public static native void NMSBoxesRotated(@StdVector RotatedRect rotatedRect, @StdVector FloatPointer floatPointer, float f, float f2, @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    @Name({"NMSBoxes"})
    public static native void NMSBoxesRotated(@StdVector RotatedRect rotatedRect, @StdVector FloatPointer floatPointer, float f, float f2, @StdVector IntPointer intPointer, float f3, int i);

    @Namespace("cv::dnn")
    @Name({"NMSBoxes"})
    public static native void NMSBoxesRotated(@StdVector RotatedRect rotatedRect, @StdVector float[] fArr, float f, float f2, @StdVector int[] iArr);

    @Namespace("cv::dnn")
    @Name({"NMSBoxes"})
    public static native void NMSBoxesRotated(@StdVector RotatedRect rotatedRect, @StdVector float[] fArr, float f, float f2, @StdVector int[] iArr, float f3, int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImage(@ByVal GpuMat gpuMat);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImage(@ByVal GpuMat gpuMat, double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImage(@ByVal Mat mat);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImage(@ByVal Mat mat, double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImage(@ByVal UMat uMat);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImage(@ByVal UMat uMat, double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::dnn")
    public static native void blobFromImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImage(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::dnn")
    public static native void blobFromImage(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImage(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::dnn")
    public static native void blobFromImage(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImages(@ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImages(@ByVal GpuMatVector gpuMatVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImages(@ByVal MatVector matVector);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImages(@ByVal MatVector matVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImages(@ByVal UMatVector uMatVector);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat blobFromImages(@ByVal UMatVector uMatVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal MatVector matVector, @ByVal Mat mat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal MatVector matVector, @ByVal Mat mat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal MatVector matVector, @ByVal UMat uMat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal MatVector matVector, @ByVal UMat uMat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal UMatVector uMatVector, @ByVal Mat mat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    @Namespace("cv::dnn")
    public static native void blobFromImages(@ByVal UMatVector uMatVector, @ByVal UMat uMat, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i);

    @Namespace("cv::dnn")
    public static native int clamp(int i, int i2);

    @Namespace("cv::dnn")
    public static native int clamp(int i, @ByRef @Const @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Range clamp(@ByRef @Const Range range, int i);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer concat(@ByRef @Const @StdVector IntPointer intPointer, @ByRef @Const @StdVector IntPointer intPointer2);

    @Namespace("cv::dnn")
    @Cast({"std::vector<std::pair<cv::dnn::Backend,cv::dnn::Target> >*"})
    @ByVal
    public static native IntIntPairVector getAvailableBackends();

    @Namespace("cv::dnn")
    @Cast({"cv::dnn::Target*"})
    @StdVector
    public static native IntPointer getAvailableTargets(@Cast({"cv::dnn::Backend"}) int i);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat getPlane(@ByRef @Const Mat mat, int i, int i2);

    @Namespace("cv::dnn")
    public static native void imagesFromBlob(@ByRef @Const Mat mat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::dnn")
    public static native void imagesFromBlob(@ByRef @Const Mat mat, @ByVal MatVector matVector);

    @Namespace("cv::dnn")
    public static native void imagesFromBlob(@ByRef @Const Mat mat, @ByVal UMatVector uMatVector);

    @Namespace("cv::dnn")
    @Cast({"bool"})
    public static native boolean is_neg(int i);

    @Namespace("cv::dnn")
    public static native void print(@ByRef @Const @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    public static native void print(@ByRef @Const @StdVector IntPointer intPointer, @opencv_core.Str String str);

    @Namespace("cv::dnn")
    public static native void print(@ByRef @Const @StdVector IntPointer intPointer, @opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @opencv_core.Str String str2, @opencv_core.Str String str3);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @Cast({"uchar*"}) @StdVector BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @Cast({"uchar*"}) @StdVector BytePointer bytePointer, @Cast({"uchar*"}) @StdVector BytePointer bytePointer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @Cast({"uchar*"}) @StdVector byte[] bArr);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str String str, @Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer, @Cast({"uchar*"}) @StdVector BytePointer bytePointer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @opencv_core.Str BytePointer bytePointer3);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer, @Cast({"uchar*"}) @StdVector byte[] bArr);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNet(@opencv_core.Str BytePointer bytePointer, @Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(String str, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(String str, @Cast({"size_t"}) long j, String str2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@Cast({"uchar*"}) @StdVector byte[] bArr);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromCaffe(@Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(String str, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(String str, @Cast({"size_t"}) long j, String str2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@Cast({"uchar*"}) @StdVector byte[] bArr);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromDarknet(@Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromModelOptimizer(@Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromONNX(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromONNX(String str, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromONNX(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromONNX(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromONNX(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromONNX(@Cast({"uchar*"}) @StdVector byte[] bArr);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(String str, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(String str, @Cast({"size_t"}) long j, String str2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@Cast({"uchar*"}) @StdVector byte[] bArr);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTensorflow(@Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTorch(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTorch(@opencv_core.Str String str, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTorch(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Net readNetFromTorch(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat readTensorFromONNX(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat readTensorFromONNX(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat readTorchBlob(@opencv_core.Str String str);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat readTorchBlob(@opencv_core.Str String str, @Cast({"bool"}) boolean z);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat readTorchBlob(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat readTorchBlob(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(int i);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(int i, int i2, int i3, int i4);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(@Const IntBuffer intBuffer, int i);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(@Const IntPointer intPointer, int i);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(@ByRef @Const Mat mat);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(@ByRef @Const MatSize matSize);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(@ByRef @Const UMat uMat);

    @Namespace("cv::dnn")
    @StdVector
    @ByVal
    public static native IntPointer shape(@Const int[] iArr, int i);

    @ByRef
    @Cast({"std::ostream*"})
    @Name({"operator <<"})
    @Namespace("cv::dnn")
    public static native Pointer shiftLeft(@ByRef @Cast({"std::ostream*"}) Pointer pointer, @ByRef @Const @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    public static native void shrinkCaffeModel(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv::dnn")
    public static native void shrinkCaffeModel(@opencv_core.Str String str, @opencv_core.Str String str2, @ByRef(nullValue = "std::vector<cv::String>()") @Const StringVector stringVector);

    @Namespace("cv::dnn")
    public static native void shrinkCaffeModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Namespace("cv::dnn")
    public static native void shrinkCaffeModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByRef(nullValue = "std::vector<cv::String>()") @Const StringVector stringVector);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat slice(@ByRef @Const Mat mat, @ByRef @Const _Range _range);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat slice(@ByRef @Const Mat mat, @ByRef @Const _Range _range, @ByRef @Const _Range _range2);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat slice(@ByRef @Const Mat mat, @ByRef @Const _Range _range, @ByRef @Const _Range _range2, @ByRef @Const _Range _range3);

    @Namespace("cv::dnn")
    @ByVal
    public static native Mat slice(@ByRef @Const Mat mat, @ByRef @Const _Range _range, @ByRef @Const _Range _range2, @ByRef @Const _Range _range3, @ByRef @Const _Range _range4);

    @Namespace("cv::dnn")
    @StdString
    public static native String toString(@ByRef @Const @StdVector IntPointer intPointer, @opencv_core.Str String str);

    @Namespace("cv::dnn")
    @StdString
    public static native BytePointer toString(@ByRef @Const @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    @StdString
    public static native BytePointer toString(@ByRef @Const @StdVector IntPointer intPointer, @opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::dnn")
    public static native int total(@ByRef @Const @StdVector IntPointer intPointer);

    @Namespace("cv::dnn")
    public static native int total(@ByRef @Const @StdVector IntPointer intPointer, int i, int i2);

    @Namespace("cv::dnn")
    public static native void writeTextGraph(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv::dnn")
    public static native void writeTextGraph(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    static {
        Loader.load();
    }
}
