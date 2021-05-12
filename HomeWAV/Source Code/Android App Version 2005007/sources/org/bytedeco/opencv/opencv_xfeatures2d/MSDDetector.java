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
public class MSDDetector extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native MSDDetector create();

    @opencv_core.Ptr
    public static native MSDDetector create(int i, int i2, int i3, int i4, float f, int i5, float f2, int i6, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public MSDDetector() {
        super((Pointer) null);
        allocate();
    }

    public MSDDetector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MSDDetector(Pointer pointer) {
        super(pointer);
    }

    public MSDDetector position(long j) {
        return (MSDDetector) super.position(j);
    }

    public MSDDetector getPointer(long j) {
        return new MSDDetector((Pointer) this).position(this.position + j);
    }
}
