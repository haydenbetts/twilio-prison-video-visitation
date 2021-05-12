package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
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
public class Queue extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const Context context);

    private native void allocate(@ByRef @Const Context context, @ByRef(nullValue = "cv::ocl::Device()") @Const Device device);

    private native void allocate(@ByRef @Const Queue queue);

    private native void allocateArray(long j);

    @ByRef
    public static native Queue getDefault();

    @Cast({"bool"})
    public native boolean create();

    @Cast({"bool"})
    public native boolean create(@ByRef(nullValue = "cv::ocl::Context()") @Const Context context, @ByRef(nullValue = "cv::ocl::Device()") @Const Device device);

    public native void finish();

    @Cast({"cv::ocl::Queue::Impl*"})
    public native Pointer getImpl();

    @ByRef
    @Const
    public native Queue getProfilingQueue();

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native Queue put(@ByRef @Const Queue queue);

    static {
        Loader.load();
    }

    public Queue(Pointer pointer) {
        super(pointer);
    }

    public Queue(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Queue position(long j) {
        return (Queue) super.position(j);
    }

    public Queue getPointer(long j) {
        return new Queue(this).position(this.position + j);
    }

    public Queue() {
        super((Pointer) null);
        allocate();
    }

    public Queue(@ByRef @Const Context context, @ByRef(nullValue = "cv::ocl::Device()") @Const Device device) {
        super((Pointer) null);
        allocate(context, device);
    }

    public Queue(@ByRef @Const Context context) {
        super((Pointer) null);
        allocate(context);
    }

    public Queue(@ByRef @Const Queue queue) {
        super((Pointer) null);
        allocate(queue);
    }
}
