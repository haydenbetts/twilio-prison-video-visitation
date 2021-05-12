package org.bytedeco.opencv.opencv_objdetect;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_objdetect.BaseCascadeClassifier;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Namespace("cv")
@Properties(inherit = {opencv_objdetect.class})
@NoOffset
public class CascadeClassifier extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public static native boolean convert(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Cast({"bool"})
    public static native boolean convert(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    public native BaseCascadeClassifier cc();

    public native CascadeClassifier cc(BaseCascadeClassifier baseCascadeClassifier);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector int[] iArr);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector int[] iArr, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector int[] iArr);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector int[] iArr, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector int[] iArr);

    @Name({"detectMultiScale"})
    public native void detectMultiScale2(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector int[] iArr, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr);

    @Name({"detectMultiScale"})
    public native void detectMultiScale3(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, double d, int i, int i2, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean empty();

    public native int getFeatureType();

    @opencv_core.Ptr
    public native BaseCascadeClassifier.MaskGenerator getMaskGenerator();

    public native Pointer getOldCascade();

    @ByVal
    public native Size getOriginalWindowSize();

    @Cast({"bool"})
    public native boolean isOldFormatCascade();

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean read(@ByRef @Const FileNode fileNode);

    public native void setMaskGenerator(@opencv_core.Ptr BaseCascadeClassifier.MaskGenerator maskGenerator);

    static {
        Loader.load();
    }

    public CascadeClassifier(Pointer pointer) {
        super(pointer);
    }

    public CascadeClassifier(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CascadeClassifier position(long j) {
        return (CascadeClassifier) super.position(j);
    }

    public CascadeClassifier getPointer(long j) {
        return new CascadeClassifier((Pointer) this).position(this.position + j);
    }

    public CascadeClassifier() {
        super((Pointer) null);
        allocate();
    }

    public CascadeClassifier(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public CascadeClassifier(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
