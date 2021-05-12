package org.bytedeco.opencv.opencv_xfeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class LATCH extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native LATCH create();

    @opencv_core.Ptr
    public static native LATCH create(int i, @Cast({"bool"}) boolean z, int i2, double d);

    static {
        Loader.load();
    }

    public LATCH() {
        super((Pointer) null);
        allocate();
    }

    public LATCH(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public LATCH(Pointer pointer) {
        super(pointer);
    }

    public LATCH position(long j) {
        return (LATCH) super.position(j);
    }

    public LATCH getPointer(long j) {
        return new LATCH((Pointer) this).position(this.position + j);
    }
}
