package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Name({"std::vector<std::pair<cv::Ptr<cv::TrackerTargetState>,float> >"})
public class ConfidenceMap extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    public native ConfidenceMap first(@Cast({"size_t"}) long j, TrackerTargetState trackerTargetState);

    @Index(function = "at")
    @opencv_core.Ptr
    public native TrackerTargetState first(@Cast({"size_t"}) long j);

    @ByRef
    @Name({"operator ="})
    public native ConfidenceMap put(@ByRef ConfidenceMap confidenceMap);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native float second(@Cast({"size_t"}) long j);

    public native ConfidenceMap second(@Cast({"size_t"}) long j, float f);

    public native long size();

    static {
        Loader.load();
    }

    public ConfidenceMap(Pointer pointer) {
        super(pointer);
    }

    public ConfidenceMap(TrackerTargetState[] trackerTargetStateArr, float[] fArr) {
        this((long) Math.min(trackerTargetStateArr.length, fArr.length));
        put(trackerTargetStateArr, fArr);
    }

    public ConfidenceMap() {
        allocate();
    }

    public ConfidenceMap(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public ConfidenceMap put(TrackerTargetState[] trackerTargetStateArr, float[] fArr) {
        int i = 0;
        while (i < trackerTargetStateArr.length && i < fArr.length) {
            long j = (long) i;
            first(j, trackerTargetStateArr[i]);
            second(j, fArr[i]);
            i++;
        }
        return this;
    }
}
