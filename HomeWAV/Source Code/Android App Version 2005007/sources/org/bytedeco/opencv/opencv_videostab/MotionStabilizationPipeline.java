package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class MotionStabilizationPipeline extends IMotionStabilizer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean empty();

    public native void pushBack(@opencv_core.Ptr IMotionStabilizer iMotionStabilizer);

    public native void stabilize(int i, @ByRef @Const MatVector matVector, @ByRef @Const Range range, Mat mat);

    static {
        Loader.load();
    }

    public MotionStabilizationPipeline() {
        super((Pointer) null);
        allocate();
    }

    public MotionStabilizationPipeline(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MotionStabilizationPipeline(Pointer pointer) {
        super(pointer);
    }

    public MotionStabilizationPipeline position(long j) {
        return (MotionStabilizationPipeline) super.position(j);
    }

    public MotionStabilizationPipeline getPointer(long j) {
        return new MotionStabilizationPipeline((Pointer) this).position(this.position + j);
    }
}
