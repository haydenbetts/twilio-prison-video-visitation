package org.bytedeco.opencv.opencv_xfeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class LUCID extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native LUCID create();

    @opencv_core.Ptr
    public static native LUCID create(int i, int i2);

    static {
        Loader.load();
    }

    public LUCID() {
        super((Pointer) null);
        allocate();
    }

    public LUCID(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public LUCID(Pointer pointer) {
        super(pointer);
    }

    public LUCID position(long j) {
        return (LUCID) super.position(j);
    }

    public LUCID getPointer(long j) {
        return new LUCID((Pointer) this).position(this.position + j);
    }
}
