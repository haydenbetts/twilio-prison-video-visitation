package org.bytedeco.opencv.opencv_text;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_text;

@Properties(inherit = {opencv_text.class})
@Namespace("cv::text")
@NoOffset
public class OCRBeamSearchDecoder extends BaseOCR {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Ptr ClassifierCallback classifierCallback, @StdString String str, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"cv::text::decoder_mode"}) int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Ptr ClassifierCallback classifierCallback, @StdString String str, @ByVal Mat mat, @ByVal Mat mat2, @Cast({"cv::text::decoder_mode"}) int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Ptr ClassifierCallback classifierCallback, @StdString String str, @ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"cv::text::decoder_mode"}) int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Ptr ClassifierCallback classifierCallback, @StdString BytePointer bytePointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"cv::text::decoder_mode"}) int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Ptr ClassifierCallback classifierCallback, @StdString BytePointer bytePointer, @ByVal Mat mat, @ByVal Mat mat2, @Cast({"cv::text::decoder_mode"}) int i, int i2);

    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder create(@opencv_core.Ptr ClassifierCallback classifierCallback, @StdString BytePointer bytePointer, @ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"cv::text::decoder_mode"}) int i, int i2);

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

    static {
        Loader.load();
    }

    public OCRBeamSearchDecoder() {
        super((Pointer) null);
        allocate();
    }

    public OCRBeamSearchDecoder(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public OCRBeamSearchDecoder(Pointer pointer) {
        super(pointer);
    }

    public OCRBeamSearchDecoder position(long j) {
        return (OCRBeamSearchDecoder) super.position(j);
    }

    public OCRBeamSearchDecoder getPointer(long j) {
        return new OCRBeamSearchDecoder((Pointer) this).position(this.position + j);
    }

    public static class ClassifierCallback extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native void eval(@ByVal GpuMat gpuMat, @StdVector DoubleVector doubleVector, @ByRef IntVector intVector);

        public native void eval(@ByVal Mat mat, @StdVector DoubleVector doubleVector, @ByRef IntVector intVector);

        public native void eval(@ByVal UMat uMat, @StdVector DoubleVector doubleVector, @ByRef IntVector intVector);

        public native int getStepSize();

        public native int getWindowSize();

        static {
            Loader.load();
        }

        public ClassifierCallback() {
            super((Pointer) null);
            allocate();
        }

        public ClassifierCallback(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public ClassifierCallback(Pointer pointer) {
            super(pointer);
        }

        public ClassifierCallback position(long j) {
            return (ClassifierCallback) super.position(j);
        }

        public ClassifierCallback getPointer(long j) {
            return new ClassifierCallback((Pointer) this).position(this.position + j);
        }
    }
}
