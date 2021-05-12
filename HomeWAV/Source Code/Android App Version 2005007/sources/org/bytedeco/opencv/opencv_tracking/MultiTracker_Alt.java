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
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect2d;
import org.bytedeco.opencv.opencv_core.Rect2dVector;
import org.bytedeco.opencv.opencv_core.ScalarVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class MultiTracker_Alt extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean addTarget(@ByVal GpuMat gpuMat, @ByRef @Const Rect2d rect2d, @opencv_core.Ptr @ByVal Tracker tracker);

    @Cast({"bool"})
    public native boolean addTarget(@ByVal Mat mat, @ByRef @Const Rect2d rect2d, @opencv_core.Ptr @ByVal Tracker tracker);

    @Cast({"bool"})
    public native boolean addTarget(@ByVal UMat uMat, @ByRef @Const Rect2d rect2d, @opencv_core.Ptr @ByVal Tracker tracker);

    @ByRef
    public native Rect2dVector boundingBoxes();

    public native MultiTracker_Alt boundingBoxes(Rect2dVector rect2dVector);

    @ByRef
    public native ScalarVector colors();

    public native MultiTracker_Alt colors(ScalarVector scalarVector);

    public native int targetNum();

    public native MultiTracker_Alt targetNum(int i);

    public native MultiTracker_Alt trackers(TrackerVector trackerVector);

    @ByRef
    public native TrackerVector trackers();

    @Cast({"bool"})
    public native boolean update(@ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean update(@ByVal Mat mat);

    @Cast({"bool"})
    public native boolean update(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public MultiTracker_Alt(Pointer pointer) {
        super(pointer);
    }

    public MultiTracker_Alt(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MultiTracker_Alt position(long j) {
        return (MultiTracker_Alt) super.position(j);
    }

    public MultiTracker_Alt getPointer(long j) {
        return new MultiTracker_Alt((Pointer) this).position(this.position + j);
    }

    public MultiTracker_Alt() {
        super((Pointer) null);
        allocate();
    }
}
