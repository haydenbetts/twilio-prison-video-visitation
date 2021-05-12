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
public class ClassifierThreshold extends Pointer {
    private native void allocate(EstimatedGaussDistribution estimatedGaussDistribution, EstimatedGaussDistribution estimatedGaussDistribution2);

    public native int eval(float f);

    public native Pointer getDistribution(int i);

    public native void update(float f, int i);

    static {
        Loader.load();
    }

    public ClassifierThreshold(Pointer pointer) {
        super(pointer);
    }

    public ClassifierThreshold(EstimatedGaussDistribution estimatedGaussDistribution, EstimatedGaussDistribution estimatedGaussDistribution2) {
        super((Pointer) null);
        allocate(estimatedGaussDistribution, estimatedGaussDistribution2);
    }
}
