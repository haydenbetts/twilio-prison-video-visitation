package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class EstimatedGaussDistribution extends Pointer {
    private native void allocate();

    private native void allocate(float f, float f2, float f3, float f4);

    private native void allocateArray(long j);

    public native float getMean();

    public native float getSigma();

    public native void setValues(float f, float f2);

    public native void update(float f);

    static {
        Loader.load();
    }

    public EstimatedGaussDistribution(Pointer pointer) {
        super(pointer);
    }

    public EstimatedGaussDistribution(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public EstimatedGaussDistribution position(long j) {
        return (EstimatedGaussDistribution) super.position(j);
    }

    public EstimatedGaussDistribution getPointer(long j) {
        return new EstimatedGaussDistribution((Pointer) this).position(this.position + j);
    }

    public EstimatedGaussDistribution() {
        super((Pointer) null);
        allocate();
    }

    public EstimatedGaussDistribution(float f, float f2, float f3, float f4) {
        super((Pointer) null);
        allocate(f, f2, f3, f4);
    }
}
