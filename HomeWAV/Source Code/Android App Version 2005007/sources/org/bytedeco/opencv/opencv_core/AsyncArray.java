package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoException;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class AsyncArray extends Pointer {
    @NoException
    private native void allocate();

    @NoException
    private native void allocate(@ByRef @Const AsyncArray asyncArray);

    private native void allocateArray(long j);

    @NoException
    public native Pointer _getImpl();

    public native void get(@ByVal GpuMat gpuMat);

    public native void get(@ByVal Mat mat);

    public native void get(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean get(@ByVal GpuMat gpuMat, double d);

    @Cast({"bool"})
    public native boolean get(@ByVal GpuMat gpuMat, @Cast({"int64"}) long j);

    @Cast({"bool"})
    public native boolean get(@ByVal Mat mat, double d);

    @Cast({"bool"})
    public native boolean get(@ByVal Mat mat, @Cast({"int64"}) long j);

    @Cast({"bool"})
    public native boolean get(@ByVal UMat uMat, double d);

    @Cast({"bool"})
    public native boolean get(@ByVal UMat uMat, @Cast({"int64"}) long j);

    @NoException
    @ByRef
    @Name({"operator ="})
    public native AsyncArray put(@ByRef @Const AsyncArray asyncArray);

    @NoException
    public native void release();

    @NoException
    @Cast({"bool"})
    public native boolean valid();

    @Cast({"bool"})
    public native boolean wait_for(double d);

    @Cast({"bool"})
    public native boolean wait_for(@Cast({"int64"}) long j);

    static {
        Loader.load();
    }

    public AsyncArray(Pointer pointer) {
        super(pointer);
    }

    public AsyncArray(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AsyncArray position(long j) {
        return (AsyncArray) super.position(j);
    }

    public AsyncArray getPointer(long j) {
        return new AsyncArray(this).position(this.position + j);
    }

    public AsyncArray() {
        super((Pointer) null);
        allocate();
    }

    public AsyncArray(@ByRef @Const AsyncArray asyncArray) {
        super((Pointer) null);
        allocate(asyncArray);
    }
}
