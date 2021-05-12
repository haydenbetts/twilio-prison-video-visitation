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
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class TrackerSampler extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean addTrackerSamplerAlgorithm(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean addTrackerSamplerAlgorithm(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean addTrackerSamplerAlgorithm(@opencv_core.Ptr @ByVal TrackerSamplerAlgorithm trackerSamplerAlgorithm);

    @ByRef
    @Const
    public native StringTrackerSamplerAlgorithmPairVector getSamplers();

    @ByRef
    @Const
    public native MatVector getSamples();

    public native void sampling(@ByRef @Const Mat mat, @ByVal Rect rect);

    static {
        Loader.load();
    }

    public TrackerSampler(Pointer pointer) {
        super(pointer);
    }

    public TrackerSampler(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerSampler position(long j) {
        return (TrackerSampler) super.position(j);
    }

    public TrackerSampler getPointer(long j) {
        return new TrackerSampler((Pointer) this).position(this.position + j);
    }

    public TrackerSampler() {
        super((Pointer) null);
        allocate();
    }
}
