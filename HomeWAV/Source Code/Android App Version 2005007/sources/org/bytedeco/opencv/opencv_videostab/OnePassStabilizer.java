package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
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
public class OnePassStabilizer extends StabilizerBase {
    private native void allocate();

    private native void allocateArray(long j);

    @Namespace
    @Name({"static_cast<cv::videostab::IFrameSource*>"})
    public static native IFrameSource asIFrameSource(OnePassStabilizer onePassStabilizer);

    @opencv_core.Ptr
    public native MotionFilterBase motionFilter();

    @ByVal
    public native Mat nextFrame();

    public native void reset();

    public native void setMotionFilter(@opencv_core.Ptr MotionFilterBase motionFilterBase);

    static {
        Loader.load();
    }

    public OnePassStabilizer(Pointer pointer) {
        super(pointer);
    }

    public OnePassStabilizer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public OnePassStabilizer position(long j) {
        return (OnePassStabilizer) super.position(j);
    }

    public OnePassStabilizer getPointer(long j) {
        return new OnePassStabilizer((Pointer) this).position(this.position + j);
    }

    public IFrameSource asIFrameSource() {
        return asIFrameSource(this);
    }

    public OnePassStabilizer() {
        super((Pointer) null);
        allocate();
    }
}
