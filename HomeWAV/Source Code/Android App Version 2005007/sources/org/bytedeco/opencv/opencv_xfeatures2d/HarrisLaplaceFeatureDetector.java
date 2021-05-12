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
public class HarrisLaplaceFeatureDetector extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native HarrisLaplaceFeatureDetector create();

    @opencv_core.Ptr
    public static native HarrisLaplaceFeatureDetector create(int i, float f, float f2, int i2, int i3);

    static {
        Loader.load();
    }

    public HarrisLaplaceFeatureDetector() {
        super((Pointer) null);
        allocate();
    }

    public HarrisLaplaceFeatureDetector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public HarrisLaplaceFeatureDetector(Pointer pointer) {
        super(pointer);
    }

    public HarrisLaplaceFeatureDetector position(long j) {
        return (HarrisLaplaceFeatureDetector) super.position(j);
    }

    public HarrisLaplaceFeatureDetector getPointer(long j) {
        return new HarrisLaplaceFeatureDetector((Pointer) this).position(this.position + j);
    }
}
