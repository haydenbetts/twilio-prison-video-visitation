package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class BufferPool extends Pointer {
    private native void allocate(@ByRef Stream stream);

    @opencv_core.Ptr
    public native GpuMat.Allocator getAllocator();

    @ByVal
    public native GpuMat getBuffer(int i, int i2, int i3);

    @ByVal
    public native GpuMat getBuffer(@ByVal Size size, int i);

    static {
        Loader.load();
    }

    public BufferPool(Pointer pointer) {
        super(pointer);
    }

    public BufferPool(@ByRef Stream stream) {
        super((Pointer) null);
        allocate(stream);
    }
}
