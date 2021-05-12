package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
@NoOffset
public class ParamGrid extends Pointer {
    private native void allocate();

    private native void allocate(double d, double d2, double d3);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native ParamGrid create();

    @opencv_core.Ptr
    public static native ParamGrid create(double d, double d2, double d3);

    public native double logStep();

    public native ParamGrid logStep(double d);

    public native double maxVal();

    public native ParamGrid maxVal(double d);

    public native double minVal();

    public native ParamGrid minVal(double d);

    static {
        Loader.load();
    }

    public ParamGrid(Pointer pointer) {
        super(pointer);
    }

    public ParamGrid(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ParamGrid position(long j) {
        return (ParamGrid) super.position(j);
    }

    public ParamGrid getPointer(long j) {
        return new ParamGrid((Pointer) this).position(this.position + j);
    }

    public ParamGrid() {
        super((Pointer) null);
        allocate();
    }

    public ParamGrid(double d, double d2, double d3) {
        super((Pointer) null);
        allocate(d, d2, d3);
    }
}
