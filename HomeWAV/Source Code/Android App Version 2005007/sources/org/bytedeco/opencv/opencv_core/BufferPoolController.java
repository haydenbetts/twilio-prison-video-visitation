package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class BufferPoolController extends Pointer {
    public native void freeAllReservedBuffers();

    @Cast({"size_t"})
    public native long getMaxReservedSize();

    @Cast({"size_t"})
    public native long getReservedSize();

    public native void setMaxReservedSize(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public BufferPoolController(Pointer pointer) {
        super(pointer);
    }
}
