package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class TrackerFeatureSet extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean addTrackerFeature(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean addTrackerFeature(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean addTrackerFeature(@opencv_core.Ptr @ByVal TrackerFeature trackerFeature);

    public native void extraction(@ByRef @Const MatVector matVector);

    @ByRef
    @Const
    public native MatVector getResponses();

    @ByRef
    @Const
    public native StringTrackerFeaturePairVector getTrackerFeature();

    public native void removeOutliers();

    public native void selection();

    static {
        Loader.load();
    }

    public TrackerFeatureSet(Pointer pointer) {
        super(pointer);
    }

    public TrackerFeatureSet(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerFeatureSet position(long j) {
        return (TrackerFeatureSet) super.position(j);
    }

    public TrackerFeatureSet getPointer(long j) {
        return new TrackerFeatureSet((Pointer) this).position(this.position + j);
    }

    public TrackerFeatureSet() {
        super((Pointer) null);
        allocate();
    }
}
