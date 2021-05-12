package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.SizeVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class Timelapser extends Pointer {
    public static final int AS_IS = 0;
    public static final int CROP = 1;

    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native Timelapser createDefault(int i);

    @ByRef
    @Const
    public native UMat getDst();

    public native void initialize(@ByRef @Const PointVector pointVector, @ByRef @Const SizeVector sizeVector);

    public native void process(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point point);

    public native void process(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point point);

    public native void process(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point point);

    static {
        Loader.load();
    }

    public Timelapser() {
        super((Pointer) null);
        allocate();
    }

    public Timelapser(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Timelapser(Pointer pointer) {
        super(pointer);
    }

    public Timelapser position(long j) {
        return (Timelapser) super.position(j);
    }

    public Timelapser getPointer(long j) {
        return new Timelapser((Pointer) this).position(this.position + j);
    }
}
