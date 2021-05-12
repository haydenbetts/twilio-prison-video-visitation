package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Program extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const Program program);

    private native void allocate(@ByRef @Const ProgramSource programSource, @opencv_core.Str String str, @opencv_core.Str String str2);

    private native void allocate(@ByRef @Const ProgramSource programSource, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    private native void allocateArray(long j);

    @Deprecated
    @opencv_core.Str
    public static native String getPrefix(@opencv_core.Str String str);

    @Deprecated
    @opencv_core.Str
    public static native BytePointer getPrefix(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean create(@ByRef @Const ProgramSource programSource, @opencv_core.Str String str, @opencv_core.Str String str2);

    @Cast({"bool"})
    public native boolean create(@ByRef @Const ProgramSource programSource, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native void getBinary(@Cast({"char*"}) @StdVector ByteBuffer byteBuffer);

    public native void getBinary(@Cast({"char*"}) @StdVector BytePointer bytePointer);

    public native void getBinary(@Cast({"char*"}) @StdVector byte[] bArr);

    @Cast({"cv::ocl::Program::Impl*"})
    public native Pointer getImpl();

    @Deprecated
    @opencv_core.Str
    public native BytePointer getPrefix();

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native Program put(@ByRef @Const Program program);

    @Deprecated
    @Cast({"bool"})
    public native boolean read(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Deprecated
    @Cast({"bool"})
    public native boolean read(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @ByRef
    @Deprecated
    @Const
    public native ProgramSource source();

    @Deprecated
    @Cast({"bool"})
    public native boolean write(@opencv_core.Str String str);

    @Deprecated
    @Cast({"bool"})
    public native boolean write(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public Program(Pointer pointer) {
        super(pointer);
    }

    public Program(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Program position(long j) {
        return (Program) super.position(j);
    }

    public Program getPointer(long j) {
        return new Program(this).position(this.position + j);
    }

    public Program() {
        super((Pointer) null);
        allocate();
    }

    public Program(@ByRef @Const ProgramSource programSource, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(programSource, bytePointer, bytePointer2);
    }

    public Program(@ByRef @Const ProgramSource programSource, @opencv_core.Str String str, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(programSource, str, str2);
    }

    public Program(@ByRef @Const Program program) {
        super((Pointer) null);
        allocate(program);
    }
}
