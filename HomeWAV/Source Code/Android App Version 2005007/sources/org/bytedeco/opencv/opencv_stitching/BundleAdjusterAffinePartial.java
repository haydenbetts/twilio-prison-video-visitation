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
public class BundleAdjusterAffinePartial extends BundleAdjusterBase {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public BundleAdjusterAffinePartial(Pointer pointer) {
        super(pointer);
    }

    public BundleAdjusterAffinePartial(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BundleAdjusterAffinePartial position(long j) {
        return (BundleAdjusterAffinePartial) super.position(j);
    }

    public BundleAdjusterAffinePartial getPointer(long j) {
        return new BundleAdjusterAffinePartial((Pointer) this).position(this.position + j);
    }

    public BundleAdjusterAffinePartial() {
        super((Pointer) null);
        allocate();
    }
}
