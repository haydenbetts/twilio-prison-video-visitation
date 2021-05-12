package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class NoBundleAdjuster extends BundleAdjusterBase {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public NoBundleAdjuster(Pointer pointer) {
        super(pointer);
    }

    public NoBundleAdjuster(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NoBundleAdjuster position(long j) {
        return (NoBundleAdjuster) super.position(j);
    }

    public NoBundleAdjuster getPointer(long j) {
        return new NoBundleAdjuster((Pointer) this).position(this.position + j);
    }

    public NoBundleAdjuster() {
        super((Pointer) null);
        allocate();
    }
}
