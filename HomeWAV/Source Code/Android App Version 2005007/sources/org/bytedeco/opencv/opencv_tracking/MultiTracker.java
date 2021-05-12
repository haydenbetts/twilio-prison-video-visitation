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
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect2d;
import org.bytedeco.opencv.opencv_core.Rect2dVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class MultiTracker extends Algorithm {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native MultiTracker create();

    @Cast({"bool"})
    public native boolean add(@opencv_core.Ptr @ByVal Tracker tracker, @ByVal GpuMat gpuMat, @ByRef @Const Rect2d rect2d);

    @Cast({"bool"})
    public native boolean add(@opencv_core.Ptr @ByVal Tracker tracker, @ByVal Mat mat, @ByRef @Const Rect2d rect2d);

    @Cast({"bool"})
    public native boolean add(@opencv_core.Ptr @ByVal Tracker tracker, @ByVal UMat uMat, @ByRef @Const Rect2d rect2d);

    @Cast({"bool"})
    public native boolean add(@ByVal TrackerVector trackerVector, @ByVal GpuMat gpuMat, @ByVal Rect2dVector rect2dVector);

    @Cast({"bool"})
    public native boolean add(@ByVal TrackerVector trackerVector, @ByVal Mat mat, @ByVal Rect2dVector rect2dVector);

    @Cast({"bool"})
    public native boolean add(@ByVal TrackerVector trackerVector, @ByVal UMat uMat, @ByVal Rect2dVector rect2dVector);

    @ByRef
    @Const
    public native Rect2dVector getObjects();

    @Cast({"bool"})
    public native boolean update(@ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean update(@ByVal GpuMat gpuMat, @ByRef Rect2dVector rect2dVector);

    @Cast({"bool"})
    public native boolean update(@ByVal Mat mat);

    @Cast({"bool"})
    public native boolean update(@ByVal Mat mat, @ByRef Rect2dVector rect2dVector);

    @Cast({"bool"})
    public native boolean update(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean update(@ByVal UMat uMat, @ByRef Rect2dVector rect2dVector);

    static {
        Loader.load();
    }

    public MultiTracker(Pointer pointer) {
        super(pointer);
    }

    public MultiTracker(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MultiTracker position(long j) {
        return (MultiTracker) super.position(j);
    }

    public MultiTracker getPointer(long j) {
        return new MultiTracker((Pointer) this).position(this.position + j);
    }

    public MultiTracker() {
        super((Pointer) null);
        allocate();
    }
}
