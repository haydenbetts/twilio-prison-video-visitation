package org.bytedeco.opencv.opencv_videoio;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
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
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videoio;

@Namespace("cv")
@Properties(inherit = {opencv_videoio.class})
@NoOffset
public class VideoWriter extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str, int i, double d, @ByVal Size size);

    private native void allocate(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    private native void allocate(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    private native void allocate(@opencv_core.Str String str, int i, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    private native void allocate(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    private native void allocate(@opencv_core.Str String str, int i, int i2, double d, @ByVal Size size);

    private native void allocate(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    private native void allocate(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    private native void allocate(@opencv_core.Str String str, int i, int i2, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    private native void allocate(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByVal Size size);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByVal Size size);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    private native void allocateArray(long j);

    public static native int fourcc(@Cast({"char"}) byte b, @Cast({"char"}) byte b2, @Cast({"char"}) byte b3, @Cast({"char"}) byte b4);

    public native double get(int i);

    @opencv_core.Str
    public native BytePointer getBackendName();

    @Cast({"bool"})
    public native boolean isOpened();

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, double d, @ByVal Size size);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, int i2, double d, @ByVal Size size);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, int i2, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByVal Size size);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByVal Size size);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByVal Size size, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr);

    public native void release();

    @Cast({"bool"})
    public native boolean set(int i, double d);

    @ByRef
    @Name({"operator <<"})
    public native VideoWriter shiftLeft(@ByRef @Const Mat mat);

    @ByRef
    @Name({"operator <<"})
    public native VideoWriter shiftLeft(@ByRef @Const UMat uMat);

    public native void write(@ByVal GpuMat gpuMat);

    public native void write(@ByVal Mat mat);

    public native void write(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public VideoWriter(Pointer pointer) {
        super(pointer);
    }

    public VideoWriter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public VideoWriter position(long j) {
        return (VideoWriter) super.position(j);
    }

    public VideoWriter getPointer(long j) {
        return new VideoWriter((Pointer) this).position(this.position + j);
    }

    public VideoWriter() {
        super((Pointer) null);
        allocate();
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByVal Size size, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(bytePointer, i, d, size, z);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByVal Size size) {
        super((Pointer) null);
        allocate(bytePointer, i, d, size);
    }

    public VideoWriter(@opencv_core.Str String str, int i, double d, @ByVal Size size, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(str, i, d, size, z);
    }

    public VideoWriter(@opencv_core.Str String str, int i, double d, @ByVal Size size) {
        super((Pointer) null);
        allocate(str, i, d, size);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByVal Size size, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(bytePointer, i, i2, d, size, z);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByVal Size size) {
        super((Pointer) null);
        allocate(bytePointer, i, i2, d, size);
    }

    public VideoWriter(@opencv_core.Str String str, int i, int i2, double d, @ByVal Size size, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(str, i, i2, d, size, z);
    }

    public VideoWriter(@opencv_core.Str String str, int i, int i2, double d, @ByVal Size size) {
        super((Pointer) null);
        allocate(str, i, i2, d, size);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer) {
        super((Pointer) null);
        allocate(bytePointer, i, d, size, intPointer);
    }

    public VideoWriter(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer) {
        super((Pointer) null);
        allocate(str, i, d, size, intBuffer);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr) {
        super((Pointer) null);
        allocate(bytePointer, i, d, size, iArr);
    }

    public VideoWriter(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer) {
        super((Pointer) null);
        allocate(str, i, d, size, intPointer);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer) {
        super((Pointer) null);
        allocate(bytePointer, i, d, size, intBuffer);
    }

    public VideoWriter(@opencv_core.Str String str, int i, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr) {
        super((Pointer) null);
        allocate(str, i, d, size, iArr);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer) {
        super((Pointer) null);
        allocate(bytePointer, i, i2, d, size, intPointer);
    }

    public VideoWriter(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer) {
        super((Pointer) null);
        allocate(str, i, i2, d, size, intBuffer);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr) {
        super((Pointer) null);
        allocate(bytePointer, i, i2, d, size, iArr);
    }

    public VideoWriter(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntPointer intPointer) {
        super((Pointer) null);
        allocate(str, i, i2, d, size, intPointer);
    }

    public VideoWriter(@opencv_core.Str BytePointer bytePointer, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector IntBuffer intBuffer) {
        super((Pointer) null);
        allocate(bytePointer, i, i2, d, size, intBuffer);
    }

    public VideoWriter(@opencv_core.Str String str, int i, int i2, double d, @ByRef @Const Size size, @Cast({"int*", "std::vector<int>&"}) @StdVector int[] iArr) {
        super((Pointer) null);
        allocate(str, i, i2, d, size, iArr);
    }
}
