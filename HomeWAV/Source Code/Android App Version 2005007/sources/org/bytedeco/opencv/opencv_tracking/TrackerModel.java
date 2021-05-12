package org.bytedeco.opencv.opencv_tracking;

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
public class TrackerModel extends Pointer {
    @ByRef
    @Const
    public native ConfidenceMapVector getConfidenceMaps();

    @ByRef
    @Const
    public native ConfidenceMap getLastConfidenceMap();

    @opencv_core.Ptr
    @ByVal
    public native TrackerTargetState getLastTargetState();

    @opencv_core.Ptr
    public native TrackerStateEstimator getTrackerStateEstimator();

    public native void modelEstimation(@ByRef @Const MatVector matVector);

    public native void modelUpdate();

    @Cast({"bool"})
    public native boolean runStateEstimator();

    public native void setLastTargetState(@ByRef @Const @opencv_core.Ptr TrackerTargetState trackerTargetState);

    @Cast({"bool"})
    public native boolean setTrackerStateEstimator(@opencv_core.Ptr TrackerStateEstimator trackerStateEstimator);

    static {
        Loader.load();
    }

    public TrackerModel(Pointer pointer) {
        super(pointer);
    }
}
