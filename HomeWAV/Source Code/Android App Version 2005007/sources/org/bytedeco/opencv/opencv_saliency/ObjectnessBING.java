package org.bytedeco.opencv.opencv_saliency;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_saliency;

@Namespace("cv::saliency")
@Properties(inherit = {opencv_saliency.class})
@NoOffset
public class ObjectnessBING extends Objectness {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native ObjectnessBING create();

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native double getBase();

    public native int getNSS();

    public native int getW();

    @StdVector
    public native FloatPointer getobjectnessValues();

    public native void read();

    public native void setBBResDir(@opencv_core.Str String str);

    public native void setBBResDir(@opencv_core.Str BytePointer bytePointer);

    public native void setBase(double d);

    public native void setNSS(int i);

    public native void setTrainingPath(@opencv_core.Str String str);

    public native void setTrainingPath(@opencv_core.Str BytePointer bytePointer);

    public native void setW(int i);

    public native void write();

    static {
        Loader.load();
    }

    public ObjectnessBING(Pointer pointer) {
        super(pointer);
    }

    public ObjectnessBING(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ObjectnessBING position(long j) {
        return (ObjectnessBING) super.position(j);
    }

    public ObjectnessBING getPointer(long j) {
        return new ObjectnessBING((Pointer) this).position(this.position + j);
    }

    public ObjectnessBING() {
        super((Pointer) null);
        allocate();
    }
}
