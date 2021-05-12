package org.bytedeco.opencv.opencv_text;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_text;

@Namespace("cv::text")
@Properties(inherit = {opencv_text.class})
public class OCRTesseract extends BaseOCR {
    @opencv_core.Ptr
    public static native OCRTesseract create();

    @opencv_core.Ptr
    public static native OCRTesseract create(String str, String str2, String str3, int i, int i2);

    @opencv_core.Ptr
    public static native OCRTesseract create(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i, int i2);

    @opencv_core.Str
    public native String run(@ByVal UMat uMat, int i);

    @opencv_core.Str
    public native String run(@ByVal UMat uMat, int i, int i2);

    @opencv_core.Str
    public native String run(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @opencv_core.Str
    public native String run(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @opencv_core.Str
    public native BytePointer run(@ByVal GpuMat gpuMat, int i);

    @opencv_core.Str
    public native BytePointer run(@ByVal GpuMat gpuMat, int i, int i2);

    @opencv_core.Str
    public native BytePointer run(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @opencv_core.Str
    public native BytePointer run(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @opencv_core.Str
    public native BytePointer run(@ByVal Mat mat, int i);

    @opencv_core.Str
    public native BytePointer run(@ByVal Mat mat, int i, int i2);

    @opencv_core.Str
    public native BytePointer run(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @opencv_core.Str
    public native BytePointer run(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    public native void run(@ByRef Mat mat, @ByRef @StdString BytePointer bytePointer);

    public native void run(@ByRef Mat mat, @ByRef @StdString BytePointer bytePointer, RectVector rectVector, StringVector stringVector, FloatVector floatVector, int i);

    public native void run(@ByRef Mat mat, @ByRef Mat mat2, @ByRef @StdString BytePointer bytePointer);

    public native void run(@ByRef Mat mat, @ByRef Mat mat2, @ByRef @StdString BytePointer bytePointer, RectVector rectVector, StringVector stringVector, FloatVector floatVector, int i);

    public native void setWhiteList(@opencv_core.Str String str);

    public native void setWhiteList(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public OCRTesseract(Pointer pointer) {
        super(pointer);
    }
}
