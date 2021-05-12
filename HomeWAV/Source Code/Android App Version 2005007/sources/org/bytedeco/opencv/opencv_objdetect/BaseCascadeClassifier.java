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
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Namespace("cv")
@Properties(inherit = {opencv_objdetect.class})
public class BaseCascadeClassifier extends Algorithm {
    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector int[] iArr, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector int[] iArr, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector int[] iArr, double d, int i, int i2, @ByVal Size size, @ByVal Size size2);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, double d, int i, int i2, @ByVal Size size, @ByVal Size size2, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean empty();

    public native int getFeatureType();

    @opencv_core.Ptr
    public native MaskGenerator getMaskGenerator();

    public native Pointer getOldCascade();

    @ByVal
    public native Size getOriginalWindowSize();

    @Cast({"bool"})
    public native boolean isOldFormatCascade();

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str BytePointer bytePointer);

    public native void setMaskGenerator(@opencv_core.Ptr MaskGenerator maskGenerator);

    static {
        Loader.load();
    }

    public BaseCascadeClassifier(Pointer pointer) {
        super(pointer);
    }

    public static class MaskGenerator extends Pointer {
        @ByVal
        public native Mat generateMask(@ByRef @Const Mat mat);

        public native void initializeMask(@ByRef @Const Mat mat);

        static {
            Loader.load();
        }

        public MaskGenerator(Pointer pointer) {
            super(pointer);
        }
    }
}
