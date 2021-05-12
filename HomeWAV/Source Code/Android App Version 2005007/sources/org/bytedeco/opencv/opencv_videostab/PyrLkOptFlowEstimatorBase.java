package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class PyrLkOptFlowEstimatorBase extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int maxLevel();

    public native void setMaxLevel(int i);

    public native void setWinSize(@ByVal Size size);

    @ByVal
    public native Size winSize();

    static {
        Loader.load();
    }

    public PyrLkOptFlowEstimatorBase(Pointer pointer) {
        super(pointer);
    }

    public PyrLkOptFlowEstimatorBase(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PyrLkOptFlowEstimatorBase position(long j) {
        return (PyrLkOptFlowEstimatorBase) super.position(j);
    }

    public PyrLkOptFlowEstimatorBase getPointer(long j) {
        return new PyrLkOptFlowEstimatorBase((Pointer) this).position(this.position + j);
    }

    public PyrLkOptFlowEstimatorBase() {
        super((Pointer) null);
        allocate();
    }
}
