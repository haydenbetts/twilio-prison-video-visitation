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
public class BundleAdjusterReproj extends BundleAdjusterBase {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public BundleAdjusterReproj(Pointer pointer) {
        super(pointer);
    }

    public BundleAdjusterReproj(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BundleAdjusterReproj position(long j) {
        return (BundleAdjusterReproj) super.position(j);
    }

    public BundleAdjusterReproj getPointer(long j) {
        return new BundleAdjusterReproj((Pointer) this).position(this.position + j);
    }

    public BundleAdjusterReproj() {
        super((Pointer) null);
        allocate();
    }
}
