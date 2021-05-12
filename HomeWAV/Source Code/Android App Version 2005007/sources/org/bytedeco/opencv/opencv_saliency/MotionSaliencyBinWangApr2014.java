package org.bytedeco.opencv.opencv_saliency;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_saliency;

@Namespace("cv::saliency")
@Properties(inherit = {opencv_saliency.class})
@NoOffset
public class MotionSaliencyBinWangApr2014 extends MotionSaliency {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native MotionSaliencyBinWangApr2014 create();

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native int getImageHeight();

    public native int getImageWidth();

    @Cast({"bool"})
    public native boolean init();

    public native void setImageHeight(int i);

    public native void setImageWidth(int i);

    public native void setImagesize(int i, int i2);

    static {
        Loader.load();
    }

    public MotionSaliencyBinWangApr2014(Pointer pointer) {
        super(pointer);
    }

    public MotionSaliencyBinWangApr2014(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MotionSaliencyBinWangApr2014 position(long j) {
        return (MotionSaliencyBinWangApr2014) super.position(j);
    }

    public MotionSaliencyBinWangApr2014 getPointer(long j) {
        return new MotionSaliencyBinWangApr2014((Pointer) this).position(this.position + j);
    }

    public MotionSaliencyBinWangApr2014() {
        super((Pointer) null);
        allocate();
    }
}
