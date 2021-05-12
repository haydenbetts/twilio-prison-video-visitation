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
public class TrackerFeatureHOG extends TrackerFeature {
    private native void allocate();

    private native void allocateArray(long j);

    public native void selection(@ByRef Mat mat, int i);

    static {
        Loader.load();
    }

    public TrackerFeatureHOG(Pointer pointer) {
        super(pointer);
    }

    public TrackerFeatureHOG(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerFeatureHOG position(long j) {
        return (TrackerFeatureHOG) super.position(j);
    }

    public TrackerFeatureHOG getPointer(long j) {
        return new TrackerFeatureHOG((Pointer) this).position(this.position + j);
    }

    public TrackerFeatureHOG() {
        super((Pointer) null);
        allocate();
    }
}
