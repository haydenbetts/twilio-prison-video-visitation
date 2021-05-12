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
public class StarDetector extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native StarDetector create();

    @opencv_core.Ptr
    public static native StarDetector create(int i, int i2, int i3, int i4, int i5);

    static {
        Loader.load();
    }

    public StarDetector() {
        super((Pointer) null);
        allocate();
    }

    public StarDetector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public StarDetector(Pointer pointer) {
        super(pointer);
    }

    public StarDetector position(long j) {
        return (StarDetector) super.position(j);
    }

    public StarDetector getPointer(long j) {
        return new StarDetector((Pointer) this).position(this.position + j);
    }
}
