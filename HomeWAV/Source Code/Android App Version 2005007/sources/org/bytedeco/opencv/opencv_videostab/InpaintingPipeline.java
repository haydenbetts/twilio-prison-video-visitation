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
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class InpaintingPipeline extends InpainterBase {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean empty();

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    public native void pushBack(@opencv_core.Ptr InpainterBase inpainterBase);

    public native void setFrames(@ByRef @Const MatVector matVector);

    public native void setMotionModel(@Cast({"cv::videostab::MotionModel"}) int i);

    public native void setMotions(@ByRef @Const MatVector matVector);

    public native void setRadius(int i);

    public native void setStabilizationMotions(@ByRef @Const MatVector matVector);

    public native void setStabilizedFrames(@ByRef @Const MatVector matVector);

    static {
        Loader.load();
    }

    public InpaintingPipeline() {
        super((Pointer) null);
        allocate();
    }

    public InpaintingPipeline(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public InpaintingPipeline(Pointer pointer) {
        super(pointer);
    }

    public InpaintingPipeline position(long j) {
        return (InpaintingPipeline) super.position(j);
    }

    public InpaintingPipeline getPointer(long j) {
        return new InpaintingPipeline((Pointer) this).position(this.position + j);
    }
}
