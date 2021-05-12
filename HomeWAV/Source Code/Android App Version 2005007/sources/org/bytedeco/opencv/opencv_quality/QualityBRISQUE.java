package org.bytedeco.opencv.opencv_quality;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_ml.SVM;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_quality;

@Namespace("cv::quality")
@Properties(inherit = {opencv_quality.class})
@NoOffset
public class QualityBRISQUE extends QualityBase {
    @ByVal
    public static native Scalar compute(@ByVal GpuMat gpuMat, @opencv_core.Str String str, @opencv_core.Str String str2);

    @ByVal
    public static native Scalar compute(@ByVal GpuMat gpuMat, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @ByVal
    public static native Scalar compute(@ByVal Mat mat, @opencv_core.Str String str, @opencv_core.Str String str2);

    @ByVal
    public static native Scalar compute(@ByVal Mat mat, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @ByVal
    public static native Scalar compute(@ByVal UMat uMat, @opencv_core.Str String str, @opencv_core.Str String str2);

    @ByVal
    public static native Scalar compute(@ByVal UMat uMat, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public static native void computeFeatures(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public static native void computeFeatures(@ByVal Mat mat, @ByVal Mat mat2);

    public static native void computeFeatures(@ByVal UMat uMat, @ByVal UMat uMat2);

    @opencv_core.Ptr
    public static native QualityBRISQUE create(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native QualityBRISQUE create(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    public static native QualityBRISQUE create(@opencv_core.Ptr SVM svm, @ByRef @Const Mat mat);

    @ByVal
    public native Scalar compute(@ByVal GpuMat gpuMat);

    @ByVal
    public native Scalar compute(@ByVal Mat mat);

    @ByVal
    public native Scalar compute(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public QualityBRISQUE(Pointer pointer) {
        super(pointer);
    }
}
