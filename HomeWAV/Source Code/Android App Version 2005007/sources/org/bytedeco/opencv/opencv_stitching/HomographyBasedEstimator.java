package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class HomographyBasedEstimator extends Estimator {
    private native void allocate();

    private native void allocate(@Cast({"bool"}) boolean z);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public HomographyBasedEstimator(Pointer pointer) {
        super(pointer);
    }

    public HomographyBasedEstimator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public HomographyBasedEstimator position(long j) {
        return (HomographyBasedEstimator) super.position(j);
    }

    public HomographyBasedEstimator getPointer(long j) {
        return new HomographyBasedEstimator((Pointer) this).position(this.position + j);
    }

    public HomographyBasedEstimator(@Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(z);
    }

    public HomographyBasedEstimator() {
        super((Pointer) null);
        allocate();
    }
}
