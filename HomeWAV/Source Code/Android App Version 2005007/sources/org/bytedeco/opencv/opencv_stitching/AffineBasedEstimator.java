package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class AffineBasedEstimator extends Estimator {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public AffineBasedEstimator(Pointer pointer) {
        super(pointer);
    }

    public AffineBasedEstimator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AffineBasedEstimator position(long j) {
        return (AffineBasedEstimator) super.position(j);
    }

    public AffineBasedEstimator getPointer(long j) {
        return new AffineBasedEstimator((Pointer) this).position(this.position + j);
    }

    public AffineBasedEstimator() {
        super((Pointer) null);
        allocate();
    }
}
