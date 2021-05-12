package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Namespace("cv::cuda")
@NoOffset
public class Stream extends Pointer {
    @ByRef
    public static native Stream Null();

    private native void allocate();

    private native void allocate(@opencv_core.Ptr GpuMat.Allocator allocator);

    private native void allocateArray(long j);

    public native Pointer cudaPtr();

    public native void enqueueHostCallback(StreamCallback streamCallback, Pointer pointer);

    @Cast({"bool"})
    public native boolean queryIfComplete();

    public native void waitEvent(@ByRef @Const Event event);

    public native void waitForCompletion();

    static {
        Loader.load();
    }

    public Stream(Pointer pointer) {
        super(pointer);
    }

    public Stream(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Stream position(long j) {
        return (Stream) super.position(j);
    }

    public Stream getPointer(long j) {
        return new Stream((Pointer) this).position(this.position + j);
    }

    public static class StreamCallback extends FunctionPointer {
        private native void allocate();

        public native void call(int i, Pointer pointer);

        static {
            Loader.load();
        }

        public StreamCallback(Pointer pointer) {
            super(pointer);
        }

        protected StreamCallback() {
            allocate();
        }
    }

    public Stream() {
        super((Pointer) null);
        allocate();
    }

    public Stream(@opencv_core.Ptr GpuMat.Allocator allocator) {
        super((Pointer) null);
        allocate(allocator);
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
