package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class SIFT extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native SIFT create();

    @opencv_core.Ptr
    public static native SIFT create(int i, int i2, double d, double d2, double d3);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    static {
        Loader.load();
    }

    public SIFT() {
        super((Pointer) null);
        allocate();
    }

    public SIFT(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SIFT(Pointer pointer) {
        super(pointer);
    }

    public SIFT position(long j) {
        return (SIFT) super.position(j);
    }

    public SIFT getPointer(long j) {
        return new SIFT((Pointer) this).position(this.position + j);
    }
}
