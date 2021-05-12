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
public class BundleAdjusterAffine extends BundleAdjusterBase {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public BundleAdjusterAffine(Pointer pointer) {
        super(pointer);
    }

    public BundleAdjusterAffine(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BundleAdjusterAffine position(long j) {
        return (BundleAdjusterAffine) super.position(j);
    }

    public BundleAdjusterAffine getPointer(long j) {
        return new BundleAdjusterAffine((Pointer) this).position(this.position + j);
    }

    public BundleAdjusterAffine() {
        super((Pointer) null);
        allocate();
    }
}
