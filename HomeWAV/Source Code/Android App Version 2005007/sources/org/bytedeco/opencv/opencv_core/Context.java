package org.bytedeco.opencv.opencv_core;

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
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Namespace("cv::ocl")
@NoOffset
public class Context extends Pointer {
    private native void allocate();

    private native void allocate(int i);

    private native void allocate(@ByRef @Const Context context);

    @ByRef
    public static native Context getDefault();

    @ByRef
    public static native Context getDefault(@Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean create();

    @Cast({"bool"})
    public native boolean create(int i);

    @ByRef
    @Const
    public native Device device(@Cast({"size_t"}) long j);

    public native Impl getImpl();

    @ByVal
    public native Program getProg(@ByRef @Const ProgramSource programSource, @opencv_core.Str String str, @opencv_core.Str String str2);

    @ByVal
    public native Program getProg(@ByRef @Const ProgramSource programSource, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Cast({"size_t"})
    public native long ndevices();

    public native Impl p();

    public native Context p(Impl impl);

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native Context put(@ByRef @Const Context context);

    public native void setUseSVM(@Cast({"bool"}) boolean z);

    public native void unloadProg(@ByRef Program program);

    @Cast({"bool"})
    public native boolean useSVM();

    static {
        Loader.load();
    }

    public Context(Pointer pointer) {
        super(pointer);
    }

    public Context() {
        super((Pointer) null);
        allocate();
    }

    public Context(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public Context(@ByRef @Const Context context) {
        super((Pointer) null);
        allocate(context);
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
