package org.bytedeco.opencv.opencv_videoio;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videoio;

@Namespace("cv")
@Properties(inherit = {opencv_videoio.class})
@NoOffset
public class VideoCapture extends Pointer {
    private native void allocate();

    private native void allocate(int i);

    private native void allocate(int i, int i2);

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, int i);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i);

    @Cast({"bool"})
    public static native boolean waitAny(@StdVector VideoCapture videoCapture, @ByRef @StdVector IntBuffer intBuffer);

    @Cast({"bool"})
    public static native boolean waitAny(@StdVector VideoCapture videoCapture, @ByRef @StdVector IntBuffer intBuffer, @Cast({"int64"}) long j);

    @Cast({"bool"})
    public static native boolean waitAny(@StdVector VideoCapture videoCapture, @ByRef @StdVector IntPointer intPointer);

    @Cast({"bool"})
    public static native boolean waitAny(@StdVector VideoCapture videoCapture, @ByRef @StdVector IntPointer intPointer, @Cast({"int64"}) long j);

    @Cast({"bool"})
    public static native boolean waitAny(@StdVector VideoCapture videoCapture, @ByRef @StdVector int[] iArr);

    @Cast({"bool"})
    public static native boolean waitAny(@StdVector VideoCapture videoCapture, @ByRef @StdVector int[] iArr, @Cast({"int64"}) long j);

    public native double get(int i);

    @opencv_core.Str
    public native BytePointer getBackendName();

    @Cast({"bool"})
    public native boolean getExceptionMode();

    @Cast({"bool"})
    public native boolean grab();

    @Cast({"bool"})
    public native boolean isOpened();

    @Cast({"bool"})
    public native boolean open(int i);

    @Cast({"bool"})
    public native boolean open(int i, int i2);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i);

    @Cast({"bool"})
    public native boolean read(@ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean read(@ByVal Mat mat);

    @Cast({"bool"})
    public native boolean read(@ByVal UMat uMat);

    public native void release();

    @Cast({"bool"})
    public native boolean retrieve(@ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean retrieve(@ByVal GpuMat gpuMat, int i);

    @Cast({"bool"})
    public native boolean retrieve(@ByVal Mat mat);

    @Cast({"bool"})
    public native boolean retrieve(@ByVal Mat mat, int i);

    @Cast({"bool"})
    public native boolean retrieve(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean retrieve(@ByVal UMat uMat, int i);

    @Cast({"bool"})
    public native boolean set(int i, double d);

    public native void setExceptionMode(@Cast({"bool"}) boolean z);

    @ByRef
    @Name({"operator >>"})
    public native VideoCapture shiftRight(@ByRef Mat mat);

    @ByRef
    @Name({"operator >>"})
    public native VideoCapture shiftRight(@ByRef UMat uMat);

    static {
        Loader.load();
    }

    public VideoCapture(Pointer pointer) {
        super(pointer);
    }

    public VideoCapture() {
        super((Pointer) null);
        allocate();
    }

    public VideoCapture(@opencv_core.Str BytePointer bytePointer, int i) {
        super((Pointer) null);
        allocate(bytePointer, i);
    }

    public VideoCapture(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public VideoCapture(@opencv_core.Str String str, int i) {
        super((Pointer) null);
        allocate(str, i);
    }

    public VideoCapture(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public VideoCapture(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public VideoCapture(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
