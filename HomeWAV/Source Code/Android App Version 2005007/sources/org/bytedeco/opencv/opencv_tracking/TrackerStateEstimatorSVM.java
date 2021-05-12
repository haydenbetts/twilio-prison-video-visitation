package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerStateEstimatorSVM extends TrackerStateEstimator {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public TrackerStateEstimatorSVM(Pointer pointer) {
        super(pointer);
    }

    public TrackerStateEstimatorSVM(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerStateEstimatorSVM position(long j) {
        return (TrackerStateEstimatorSVM) super.position(j);
    }

    public TrackerStateEstimatorSVM getPointer(long j) {
        return new TrackerStateEstimatorSVM((Pointer) this).position(this.position + j);
    }

    public TrackerStateEstimatorSVM() {
        super((Pointer) null);
        allocate();
    }
}
