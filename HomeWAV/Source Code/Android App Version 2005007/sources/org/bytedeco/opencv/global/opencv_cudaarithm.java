package org.bytedeco.opencv.global;

import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Scalar4i;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_cudaarithm.Convolution;
import org.bytedeco.opencv.opencv_cudaarithm.DFT;
import org.bytedeco.opencv.opencv_cudaarithm.LookUpTable;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_cudaarithm extends org.bytedeco.opencv.presets.opencv_cudaarithm {
    @Namespace("cv::cuda")
    public static native void abs(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void abs(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void abs(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void abs(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void abs(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void abs(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar absSum(@ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar absSum(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar absSum(@ByVal Mat mat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar absSum(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar absSum(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar absSum(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void absdiff(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void absdiff(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void absdiff(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void absdiff(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void absdiff(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void absdiff(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void add(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void add(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void add(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void add(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void add(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void add(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void addWeighted(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, double d2, double d3, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void addWeighted(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, double d2, double d3, @ByVal GpuMat gpuMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void addWeighted(@ByVal Mat mat, double d, @ByVal Mat mat2, double d2, double d3, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void addWeighted(@ByVal Mat mat, double d, @ByVal Mat mat2, double d2, double d3, @ByVal Mat mat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void addWeighted(@ByVal UMat uMat, double d, @ByVal UMat uMat2, double d2, double d3, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void addWeighted(@ByVal UMat uMat, double d, @ByVal UMat uMat2, double d2, double d3, @ByVal UMat uMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_and(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void bitwise_and(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_and(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void bitwise_and(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_and(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void bitwise_and(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_not(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void bitwise_not(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_not(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void bitwise_not(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_not(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void bitwise_not(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_or(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void bitwise_or(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_or(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void bitwise_or(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_or(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void bitwise_or(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_xor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void bitwise_xor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_xor(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void bitwise_xor(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void bitwise_xor(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void bitwise_xor(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcAbsSum(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void calcAbsSum(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcAbsSum(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void calcAbsSum(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcAbsSum(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void calcAbsSum(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcNorm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::cuda")
    public static native void calcNorm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcNorm(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::cuda")
    public static native void calcNorm(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcNorm(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::cuda")
    public static native void calcNorm(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcNormDiff(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void calcNormDiff(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcNormDiff(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void calcNormDiff(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcNormDiff(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void calcNormDiff(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcSqrSum(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void calcSqrSum(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcSqrSum(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void calcSqrSum(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcSqrSum(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void calcSqrSum(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcSum(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void calcSum(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcSum(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void calcSum(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void calcSum(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void calcSum(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void cartToPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::cuda")
    public static native void cartToPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void cartToPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::cuda")
    public static native void cartToPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void cartToPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::cuda")
    public static native void cartToPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void compare(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv::cuda")
    public static native void compare(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void compare(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv::cuda")
    public static native void compare(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void compare(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv::cuda")
    public static native void compare(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void copyMakeBorder(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::cuda")
    public static native void copyMakeBorder(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, int i4, int i5, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void copyMakeBorder(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::cuda")
    public static native void copyMakeBorder(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, int i4, int i5, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void copyMakeBorder(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv::cuda")
    public static native void copyMakeBorder(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, int i4, int i5, @ByVal(nullValue = "cv::Scalar()") Scalar scalar, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native int countNonZero(@ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    public static native int countNonZero(@ByVal Mat mat);

    @Namespace("cv::cuda")
    public static native int countNonZero(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    public static native void countNonZero(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void countNonZero(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void countNonZero(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void countNonZero(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void countNonZero(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void countNonZero(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Convolution createConvolution();

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native Convolution createConvolution(@ByVal(nullValue = "cv::Size()") Size size);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native DFT createDFT(@ByVal Size size, int i);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native LookUpTable createLookUpTable(@ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native LookUpTable createLookUpTable(@ByVal Mat mat);

    @Namespace("cv::cuda")
    @opencv_core.Ptr
    public static native LookUpTable createLookUpTable(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    public static native void dft(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void dft(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void dft(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void dft(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void dft(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size);

    @Namespace("cv::cuda")
    public static native void dft(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void divide(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void divide(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void divide(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void divide(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void divide(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void divide(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void exp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void exp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void exp(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void exp(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void exp(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void exp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void findMinMax(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void findMinMax(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void findMinMax(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void findMinMax(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void findMinMax(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void findMinMax(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void findMinMaxLoc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void findMinMaxLoc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void findMinMaxLoc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void findMinMaxLoc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void findMinMaxLoc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void findMinMaxLoc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void flip(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::cuda")
    public static native void flip(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void flip(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::cuda")
    public static native void flip(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void flip(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::cuda")
    public static native void flip(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void gemm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByVal GpuMat gpuMat3, double d2, @ByVal GpuMat gpuMat4);

    @Namespace("cv::cuda")
    public static native void gemm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByVal GpuMat gpuMat3, double d2, @ByVal GpuMat gpuMat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void gemm(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByVal Mat mat3, double d2, @ByVal Mat mat4);

    @Namespace("cv::cuda")
    public static native void gemm(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByVal Mat mat3, double d2, @ByVal Mat mat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void gemm(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByVal UMat uMat3, double d2, @ByVal UMat uMat4);

    @Namespace("cv::cuda")
    public static native void gemm(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByVal UMat uMat3, double d2, @ByVal UMat uMat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void integral(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void integral(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void integral(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void integral(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void integral(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void integral(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void log(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void log(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void log(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void log(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void log(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void log(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal GpuMat gpuMat, @ByVal Scalar4i scalar4i, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal GpuMat gpuMat, @ByVal Scalar4i scalar4i, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal GpuMat gpuMat, @ByVal Scalar scalar, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal GpuMat gpuMat, @ByVal Scalar scalar, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal Mat mat, @ByVal Scalar4i scalar4i, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal Mat mat, @ByVal Scalar4i scalar4i, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal Mat mat, @ByVal Scalar scalar, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal Mat mat, @ByVal Scalar scalar, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal UMat uMat, @ByVal Scalar4i scalar4i, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal UMat uMat, @ByVal Scalar4i scalar4i, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal UMat uMat, @ByVal Scalar scalar, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void lshift(@ByVal UMat uMat, @ByVal Scalar scalar, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void magnitude(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void magnitudeSqr(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void max(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void max(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void max(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void max(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void max(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void max(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal GpuMat gpuMat, @ByRef Scalar scalar, @ByRef Scalar scalar2);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal Mat mat, @ByRef Scalar scalar, @ByRef Scalar scalar2);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal UMat uMat, @ByRef Scalar scalar, @ByRef Scalar scalar2);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void meanStdDev(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void merge(@Const GpuMat gpuMat, @Cast({"size_t"}) long j, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void merge(@Const GpuMat gpuMat, @Cast({"size_t"}) long j, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void merge(@Const GpuMat gpuMat, @Cast({"size_t"}) long j, @ByVal Mat mat);

    @Namespace("cv::cuda")
    public static native void merge(@Const GpuMat gpuMat, @Cast({"size_t"}) long j, @ByVal Mat mat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void merge(@Const GpuMat gpuMat, @Cast({"size_t"}) long j, @ByVal UMat uMat);

    @Namespace("cv::cuda")
    public static native void merge(@Const GpuMat gpuMat, @Cast({"size_t"}) long j, @ByVal UMat uMat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void merge(@ByRef @Const GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    public static native void merge(@ByRef @Const GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void merge(@ByRef @Const GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Namespace("cv::cuda")
    public static native void merge(@ByRef @Const GpuMatVector gpuMatVector, @ByVal Mat mat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void merge(@ByRef @Const GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Namespace("cv::cuda")
    public static native void merge(@ByRef @Const GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void min(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void min(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void min(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void min(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void min(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void min(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal GpuMat gpuMat, DoublePointer doublePointer, DoublePointer doublePointer2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal GpuMat gpuMat, DoublePointer doublePointer, DoublePointer doublePointer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal GpuMat gpuMat, double[] dArr, double[] dArr2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal GpuMat gpuMat, double[] dArr, double[] dArr2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal Mat mat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal Mat mat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal Mat mat, DoublePointer doublePointer, DoublePointer doublePointer2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal Mat mat, DoublePointer doublePointer, DoublePointer doublePointer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal Mat mat, double[] dArr, double[] dArr2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal Mat mat, double[] dArr, double[] dArr2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal UMat uMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal UMat uMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal UMat uMat, DoublePointer doublePointer, DoublePointer doublePointer2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal UMat uMat, DoublePointer doublePointer, DoublePointer doublePointer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal UMat uMat, double[] dArr, double[] dArr2);

    @Namespace("cv::cuda")
    public static native void minMax(@ByVal UMat uMat, double[] dArr, double[] dArr2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, double[] dArr, double[] dArr2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, double[] dArr, double[] dArr2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal Mat mat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal Mat mat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal Mat mat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal Mat mat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal Mat mat, double[] dArr, double[] dArr2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal Mat mat, double[] dArr, double[] dArr2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal UMat uMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal UMat uMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal UMat uMat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal UMat uMat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal UMat uMat, double[] dArr, double[] dArr2, Point point, Point point2);

    @Namespace("cv::cuda")
    public static native void minMaxLoc(@ByVal UMat uMat, double[] dArr, double[] dArr2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void mulAndScaleSpectrums(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, float f);

    @Namespace("cv::cuda")
    public static native void mulAndScaleSpectrums(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, float f, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void mulAndScaleSpectrums(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, float f);

    @Namespace("cv::cuda")
    public static native void mulAndScaleSpectrums(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, float f, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void mulAndScaleSpectrums(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, float f);

    @Namespace("cv::cuda")
    public static native void mulAndScaleSpectrums(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, float f, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void mulSpectrums(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv::cuda")
    public static native void mulSpectrums(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void mulSpectrums(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv::cuda")
    public static native void mulSpectrums(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void mulSpectrums(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv::cuda")
    public static native void mulSpectrums(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void multiply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void multiply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void multiply(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void multiply(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void multiply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void multiply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal GpuMat gpuMat, int i);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal GpuMat gpuMat, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal Mat mat, int i);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal Mat mat, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal UMat uMat, int i);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal UMat uMat, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native double norm(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::cuda")
    public static native void normalize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void normalize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i, int i2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void normalize(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void normalize(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i, int i2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void normalize(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void normalize(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i, int i2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void phase(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void phase(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void phase(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void phase(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void phase(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void phase(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void polarToCart(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::cuda")
    public static native void polarToCart(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void polarToCart(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::cuda")
    public static native void polarToCart(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void polarToCart(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::cuda")
    public static native void polarToCart(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pow(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void pow(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pow(@ByVal Mat mat, double d, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void pow(@ByVal Mat mat, double d, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void pow(@ByVal UMat uMat, double d, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void pow(@ByVal UMat uMat, double d, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rectStdDev(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Rect rect);

    @Namespace("cv::cuda")
    public static native void rectStdDev(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Rect rect, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rectStdDev(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Rect rect);

    @Namespace("cv::cuda")
    public static native void rectStdDev(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Rect rect, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rectStdDev(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Rect rect);

    @Namespace("cv::cuda")
    public static native void rectStdDev(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Rect rect, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void reduce(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void reduce(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void reduce(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void reduce(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void reduce(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv::cuda")
    public static native void reduce(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal GpuMat gpuMat, @ByVal Scalar4i scalar4i, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal GpuMat gpuMat, @ByVal Scalar4i scalar4i, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal GpuMat gpuMat, @ByVal Scalar scalar, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal GpuMat gpuMat, @ByVal Scalar scalar, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal Mat mat, @ByVal Scalar4i scalar4i, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal Mat mat, @ByVal Scalar4i scalar4i, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal Mat mat, @ByVal Scalar scalar, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal Mat mat, @ByVal Scalar scalar, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal UMat uMat, @ByVal Scalar4i scalar4i, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal UMat uMat, @ByVal Scalar4i scalar4i, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal UMat uMat, @ByVal Scalar scalar, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void rshift(@ByVal UMat uMat, @ByVal Scalar scalar, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void scaleAdd(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void scaleAdd(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void scaleAdd(@ByVal Mat mat, double d, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void scaleAdd(@ByVal Mat mat, double d, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void scaleAdd(@ByVal UMat uMat, double d, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void scaleAdd(@ByVal UMat uMat, double d, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void split(@ByVal GpuMat gpuMat, GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void split(@ByVal GpuMat gpuMat, GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void split(@ByVal GpuMat gpuMat, @ByRef GpuMatVector gpuMatVector);

    @Namespace("cv::cuda")
    public static native void split(@ByVal GpuMat gpuMat, @ByRef GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void split(@ByVal Mat mat, GpuMat gpuMat);

    @Namespace("cv::cuda")
    public static native void split(@ByVal Mat mat, GpuMat gpuMat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void split(@ByVal Mat mat, @ByRef GpuMatVector gpuMatVector);

    @Namespace("cv::cuda")
    public static native void split(@ByVal Mat mat, @ByRef GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void split(@ByVal UMat uMat, GpuMat gpuMat);

    @Namespace("cv::cuda")
    public static native void split(@ByVal UMat uMat, GpuMat gpuMat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void split(@ByVal UMat uMat, @ByRef GpuMatVector gpuMatVector);

    @Namespace("cv::cuda")
    public static native void split(@ByVal UMat uMat, @ByRef GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void sqr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqr(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void sqr(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqr(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void sqr(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqrIntegral(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void sqrIntegral(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqrIntegral(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void sqrIntegral(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqrIntegral(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void sqrIntegral(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sqrSum(@ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sqrSum(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sqrSum(@ByVal Mat mat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sqrSum(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sqrSum(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sqrSum(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native void sqrt(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void sqrt(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqrt(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void sqrt(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void sqrt(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void sqrt(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void subtract(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::cuda")
    public static native void subtract(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void subtract(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::cuda")
    public static native void subtract(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void subtract(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void subtract(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sum(@ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sum(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sum(@ByVal Mat mat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sum(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sum(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    @ByVal
    public static native Scalar sum(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv::cuda")
    public static native double threshold(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i);

    @Namespace("cv::cuda")
    public static native double threshold(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native double threshold(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i);

    @Namespace("cv::cuda")
    public static native double threshold(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native double threshold(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i);

    @Namespace("cv::cuda")
    public static native double threshold(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void transpose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::cuda")
    public static native void transpose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void transpose(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::cuda")
    public static native void transpose(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @Namespace("cv::cuda")
    public static native void transpose(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void transpose(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }
}
