package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class MotionInpainter extends InpainterBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native int borderMode();

    public native float distThresh();

    public native float flowErrorThreshold();

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    @opencv_core.Ptr
    public native IDenseOptFlowEstimator optFlowEstimator();

    public native void setBorderMode(int i);

    public native void setDistThreshold(float f);

    public native void setFlowErrorThreshold(float f);

    public native void setOptFlowEstimator(@opencv_core.Ptr IDenseOptFlowEstimator iDenseOptFlowEstimator);

    static {
        Loader.load();
    }

    public MotionInpainter(Pointer pointer) {
        super(pointer);
    }

    public MotionInpainter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MotionInpainter position(long j) {
        return (MotionInpainter) super.position(j);
    }

    public MotionInpainter getPointer(long j) {
        return new MotionInpainter((Pointer) this).position(this.position + j);
    }

    public MotionInpainter() {
        super((Pointer) null);
        allocate();
    }
}
