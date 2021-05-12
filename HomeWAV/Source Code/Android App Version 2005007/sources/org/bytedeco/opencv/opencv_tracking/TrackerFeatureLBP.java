package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerFeatureLBP extends TrackerFeature {
    private native void allocate();

    private native void allocateArray(long j);

    public native void selection(@ByRef Mat mat, int i);

    static {
        Loader.load();
    }

    public TrackerFeatureLBP(Pointer pointer) {
        super(pointer);
    }

    public TrackerFeatureLBP(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerFeatureLBP position(long j) {
        return (TrackerFeatureLBP) super.position(j);
    }

    public TrackerFeatureLBP getPointer(long j) {
        return new TrackerFeatureLBP((Pointer) this).position(this.position + j);
    }

    public TrackerFeatureLBP() {
        super((Pointer) null);
        allocate();
    }
}
