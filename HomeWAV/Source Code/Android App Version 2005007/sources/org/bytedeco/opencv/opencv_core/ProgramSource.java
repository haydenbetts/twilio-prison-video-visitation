package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
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
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class ProgramSource extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @opencv_core.Str String str2, @opencv_core.Str String str3, @opencv_core.Str String str4);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @opencv_core.Str BytePointer bytePointer3, @opencv_core.Str BytePointer bytePointer4);

    private native void allocate(@ByRef @Const ProgramSource programSource);

    private native void allocateArray(long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j, @opencv_core.Str String str3);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) BytePointer bytePointer, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) BytePointer bytePointer, @Cast({"const size_t"}) long j, @opencv_core.Str String str3);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j, @opencv_core.Str String str3);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j, @opencv_core.Str BytePointer bytePointer3);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) BytePointer bytePointer3, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) BytePointer bytePointer3, @Cast({"const size_t"}) long j, @opencv_core.Str BytePointer bytePointer4);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromBinary(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j, @opencv_core.Str BytePointer bytePointer3);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j, @opencv_core.Str String str3);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) BytePointer bytePointer, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) BytePointer bytePointer, @Cast({"const size_t"}) long j, @opencv_core.Str String str3);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str String str, @opencv_core.Str String str2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j, @opencv_core.Str String str3);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const size_t"}) long j, @opencv_core.Str BytePointer bytePointer3);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) BytePointer bytePointer3, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) BytePointer bytePointer3, @Cast({"const size_t"}) long j, @opencv_core.Str BytePointer bytePointer4);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j);

    @ByVal
    public static native ProgramSource fromSPIR(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const size_t"}) long j, @opencv_core.Str BytePointer bytePointer3);

    @Cast({"cv::ocl::ProgramSource::Impl*"})
    public native Pointer getImpl();

    @Cast({"cv::ocl::ProgramSource::hash_t"})
    public native int hash();

    @ByRef
    @Name({"operator ="})
    public native ProgramSource put(@ByRef @Const ProgramSource programSource);

    @opencv_core.Str
    public native BytePointer source();

    static {
        Loader.load();
    }

    public ProgramSource(Pointer pointer) {
        super(pointer);
    }

    public ProgramSource(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ProgramSource position(long j) {
        return (ProgramSource) super.position(j);
    }

    public ProgramSource getPointer(long j) {
        return new ProgramSource(this).position(this.position + j);
    }

    public ProgramSource() {
        super((Pointer) null);
        allocate();
    }

    public ProgramSource(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @opencv_core.Str BytePointer bytePointer3, @opencv_core.Str BytePointer bytePointer4) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2, bytePointer3, bytePointer4);
    }

    public ProgramSource(@opencv_core.Str String str, @opencv_core.Str String str2, @opencv_core.Str String str3, @opencv_core.Str String str4) {
        super((Pointer) null);
        allocate(str, str2, str3, str4);
    }

    public ProgramSource(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public ProgramSource(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public ProgramSource(@ByRef @Const ProgramSource programSource) {
        super((Pointer) null);
        allocate(programSource);
    }
}
