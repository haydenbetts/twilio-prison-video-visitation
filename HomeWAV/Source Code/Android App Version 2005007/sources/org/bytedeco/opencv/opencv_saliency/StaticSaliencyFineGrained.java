package org.bytedeco.opencv.opencv_saliency;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_saliency;

@Namespace("cv::saliency")
@Properties(inherit = {opencv_saliency.class})
public class StaticSaliencyFineGrained extends StaticSaliency {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native StaticSaliencyFineGrained create();

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public StaticSaliencyFineGrained(Pointer pointer) {
        super(pointer);
    }

    public StaticSaliencyFineGrained(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public StaticSaliencyFineGrained position(long j) {
        return (StaticSaliencyFineGrained) super.position(j);
    }

    public StaticSaliencyFineGrained getPointer(long j) {
        return new StaticSaliencyFineGrained((Pointer) this).position(this.position + j);
    }

    public StaticSaliencyFineGrained() {
        super((Pointer) null);
        allocate();
    }
}
