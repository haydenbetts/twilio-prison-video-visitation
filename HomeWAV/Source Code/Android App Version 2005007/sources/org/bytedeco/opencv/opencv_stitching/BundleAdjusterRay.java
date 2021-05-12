package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class BundleAdjusterRay extends BundleAdjusterBase {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public BundleAdjusterRay(Pointer pointer) {
        super(pointer);
    }

    public BundleAdjusterRay(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BundleAdjusterRay position(long j) {
        return (BundleAdjusterRay) super.position(j);
    }

    public BundleAdjusterRay getPointer(long j) {
        return new BundleAdjusterRay((Pointer) this).position(this.position + j);
    }

    public BundleAdjusterRay() {
        super((Pointer) null);
        allocate();
    }
}
