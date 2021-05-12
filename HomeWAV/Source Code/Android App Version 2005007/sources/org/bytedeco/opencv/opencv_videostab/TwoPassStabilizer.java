package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class TwoPassStabilizer extends StabilizerBase {
    private native void allocate();

    private native void allocateArray(long j);

    @Namespace
    @Name({"static_cast<cv::videostab::IFrameSource*>"})
    public static native IFrameSource asIFrameSource(TwoPassStabilizer twoPassStabilizer);

    @opencv_core.Ptr
    public native IMotionStabilizer motionStabilizer();

    @Cast({"bool"})
    public native boolean mustEstimateTrimaRatio();

    @ByVal
    public native Mat nextFrame();

    public native void reset();

    public native void setEstimateTrimRatio(@Cast({"bool"}) boolean z);

    public native void setMotionStabilizer(@opencv_core.Ptr IMotionStabilizer iMotionStabilizer);

    public native void setWobbleSuppressor(@opencv_core.Ptr WobbleSuppressorBase wobbleSuppressorBase);

    @opencv_core.Ptr
    public native WobbleSuppressorBase wobbleSuppressor();

    static {
        Loader.load();
    }

    public TwoPassStabilizer(Pointer pointer) {
        super(pointer);
    }

    public TwoPassStabilizer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TwoPassStabilizer position(long j) {
        return (TwoPassStabilizer) super.position(j);
    }

    public TwoPassStabilizer getPointer(long j) {
        return new TwoPassStabilizer((Pointer) this).position(this.position + j);
    }

    public IFrameSource asIFrameSource() {
        return asIFrameSource(this);
    }

    public TwoPassStabilizer() {
        super((Pointer) null);
        allocate();
    }
}
