package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Namespace("cv::ocl")
@NoOffset
public class Kernel extends Pointer {
    private native void allocate();

    private native void allocate(String str, @ByRef @Const Program program);

    private native void allocate(String str, @ByRef @Const ProgramSource programSource, @opencv_core.Str String str2, @Cast({"", "cv::String*"}) @opencv_core.Str BytePointer bytePointer);

    private native void allocate(@Cast({"const char*"}) BytePointer bytePointer, @ByRef @Const Program program);

    private native void allocate(@ByRef @Const Kernel kernel);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean compileWorkGroupSize(@Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"bool"})
    public native boolean create(String str, @ByRef @Const Program program);

    @Cast({"bool"})
    public native boolean create(String str, @ByRef @Const ProgramSource programSource, @opencv_core.Str String str2, @Cast({"", "cv::String*"}) @opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean create(@Cast({"const char*"}) BytePointer bytePointer, @ByRef @Const Program program);

    @Cast({"bool"})
    public native boolean empty();

    @Cast({"size_t"})
    public native long localMemSize();

    @Cast({"size_t"})
    public native long preferedWorkGroupSizeMultiple();

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native Kernel put(@ByRef @Const Kernel kernel);

    @Cast({"bool"})
    public native boolean run(int i, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer2, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean run(int i, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer2, @Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::ocl::Queue()") @Const Queue queue);

    @Cast({"int64"})
    public native long runProfiling(int i, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer2);

    @Cast({"int64"})
    public native long runProfiling(int i, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer2, @ByRef(nullValue = "cv::ocl::Queue()") @Const Queue queue);

    @Cast({"bool"})
    public native boolean runTask(@Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean runTask(@Cast({"bool"}) boolean z, @ByRef(nullValue = "cv::ocl::Queue()") @Const Queue queue);

    public native int set(int i, @Const Pointer pointer, @Cast({"size_t"}) long j);

    public native int set(int i, @ByRef @Const Image2D image2D);

    public native int set(int i, @ByRef @Const KernelArg kernelArg);

    public native int set(int i, @ByRef @Const UMat uMat);

    @Cast({"size_t"})
    public native long workGroupSize();

    static {
        Loader.load();
    }

    public Kernel(Pointer pointer) {
        super(pointer);
    }

    public Kernel(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Kernel position(long j) {
        return (Kernel) super.position(j);
    }

    public Kernel getPointer(long j) {
        return new Kernel(this).position(this.position + j);
    }

    public Kernel() {
        super((Pointer) null);
        allocate();
    }

    public Kernel(@Cast({"const char*"}) BytePointer bytePointer, @ByRef @Const Program program) {
        super((Pointer) null);
        allocate(bytePointer, program);
    }

    public Kernel(String str, @ByRef @Const Program program) {
        super((Pointer) null);
        allocate(str, program);
    }

    public Kernel(String str, @ByRef @Const ProgramSource programSource, @opencv_core.Str String str2, @opencv_core.Str BytePointer bytePointer) {
        allocate(str, programSource, str2, bytePointer);
    }

    public Kernel(@ByRef @Const Kernel kernel) {
        super((Pointer) null);
        allocate(kernel);
    }

    @Opaque
    public static class Impl extends Pointer {
        public Impl() {
            super((Pointer) null);
        }

        public Impl(Pointer pointer) {
            super(pointer);
        }
    }
}
