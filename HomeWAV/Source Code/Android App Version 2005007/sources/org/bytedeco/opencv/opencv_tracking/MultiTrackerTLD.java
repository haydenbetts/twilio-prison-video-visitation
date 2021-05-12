package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class MultiTrackerTLD extends MultiTracker_Alt {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean update_opt(@ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean update_opt(@ByVal Mat mat);

    @Cast({"bool"})
    public native boolean update_opt(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public MultiTrackerTLD() {
        super((Pointer) null);
        allocate();
    }

    public MultiTrackerTLD(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MultiTrackerTLD(Pointer pointer) {
        super(pointer);
    }

    public MultiTrackerTLD position(long j) {
        return (MultiTrackerTLD) super.position(j);
    }

    public MultiTrackerTLD getPointer(long j) {
        return new MultiTrackerTLD((Pointer) this).position(this.position + j);
    }
}
