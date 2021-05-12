package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.SizeVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class Blender extends Pointer {
    public static final int FEATHER = 1;
    public static final int MULTI_BAND = 2;
    public static final int NO = 0;

    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native Blender createDefault(int i);

    @opencv_core.Ptr
    public static native Blender createDefault(int i, @Cast({"bool"}) boolean z);

    public native void blend(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void blend(@ByVal Mat mat, @ByVal Mat mat2);

    public native void blend(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void feed(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point point);

    public native void feed(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point point);

    public native void feed(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point point);

    public native void prepare(@ByRef @Const PointVector pointVector, @ByRef @Const SizeVector sizeVector);

    public native void prepare(@ByVal Rect rect);

    static {
        Loader.load();
    }

    public Blender() {
        super((Pointer) null);
        allocate();
    }

    public Blender(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Blender(Pointer pointer) {
        super(pointer);
    }

    public Blender position(long j) {
        return (Blender) super.position(j);
    }

    public Blender getPointer(long j) {
        return new Blender((Pointer) this).position(this.position + j);
    }
}
